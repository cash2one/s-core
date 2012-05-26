package com.seo.upload.impl;

import com.seo.message.MessageListener;
import com.seo.message.model.Message;
import com.seo.proxy.ProxyProvider;
import com.seo.proxy.exception.ProxyNotAvailableException;
import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.upload.DirectoryUploader;
import com.seo.upload.model.ServerInfo;
import com.seo.upload.model.UploadRequest;
import com.seo.upload.socket.ProxyfiedSocketFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.SocketFactory;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class DirectFTPUploader implements DirectoryUploader {
    private List<String> uploaded = new ArrayList<String>();

    private final static int DEFAULT_SOCKET_TIMEOUT = 60 * 1000;

    private final static Logger LOGGER = LoggerFactory.getLogger(DirectFTPUploader.class);
    private FTPClient ftpClient = new FTPClient();

    private ProxyProvider proxyProvider;
    private MessageListener messageListener;

    public void setProxyProvider(ProxyProvider proxyProvider) {
        this.proxyProvider = proxyProvider;
    }

    @Override
    public void upload(UploadRequest uploadRequest) {
        ServerInfo serverInfo = uploadRequest.getServerInfo();
        String host = serverInfo.getHost();

        if (uploadRequest.isUseProxy()) {
            initializeProxy(host);
        }
        int attemptCount = 0;
        while (true) {
            try {

                login(
                        serverInfo.getHost(),
                        serverInfo.getLogin(),
                        serverInfo.getPassword()
                );

                try {
                    ftpClient.setSoTimeout(DEFAULT_SOCKET_TIMEOUT);
                } catch (SocketException e) {
                    LOGGER.error("socket exception: {} {}", e.getMessage(), e.getStackTrace());

                    throw new RuntimeException("socket exception: " + e.getMessage());
                }

                String prefixDirectory = serverInfo.getPrefix();
                if (prefixDirectory != null) {
                    ftpClient.changeWorkingDirectory(prefixDirectory);
                }

                File directory = new File(uploadRequest.getUploadDir());
                uploadDir(directory);

                ftpClient.logout();
                break;

            } catch (IOException e) {
                LOGGER.error("i/o error: {}, retrying", e.getMessage());

                attemptCount++;

                if (attemptCount > 5) {
                    LOGGER.debug("choosing another proxy after 5 attempts");
                    initializeProxy(host);

                    attemptCount = 0;
                }
            }
            finally {
                if (ftpClient.isConnected()) {
                    try {
                        ftpClient.disconnect();
                    } catch (IOException ioe) {
                        // do nothing
                    }
                }
            }
        }
    }

    private void login(String host, String login, String password) throws IOException {
        ftpClient.connect(host);
        ftpClient.login(login, password);

        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();

            LOGGER.error("ftp server {} refused connection, reply string: {}", host, ftpClient.getReplyString());

            throw new RuntimeException("ftp server " + host + " refused connection");
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Connected to {}, reply string: {}", host, ftpClient.getReplyString());
        }

    }

    private void initializeProxy(String hosting) {
        Proxy proxy;
        try {
            proxy = proxyProvider.getProxy(hosting, ProxyType.SOCKS);
        } catch (ProxyNotAvailableException e) {
            LOGGER.error("proxy not available: {}", e.getMessage());

            throw new IllegalStateException("proxy not available: " + e.getMessage());
        }

        LOGGER.debug("using proxy: {}", proxy);

        SocketFactory socketFactory = createSocketFactory(proxy);
        ftpClient.setSocketFactory(socketFactory);
    }

    private SocketFactory createSocketFactory(Proxy proxy) {
        java.net.Proxy netProxy = new java.net.Proxy(
                java.net.Proxy.Type.SOCKS,
                new InetSocketAddress(
                        proxy.getHost(),
                        proxy.getPort()
                )
        );

        return new ProxyfiedSocketFactory(netProxy);
    }

    private void uploadDir(File directory) throws IOException {

        try {
            String[] children = directory.list();

            if (children == null) {
                // Either dir does not exist or is not a directory
            } else {
                for (String filename : children) {
                    File f = new File(directory, filename);
                    if (f.isDirectory()) {
                        ftpClient.mkd(f.getName());
                        ftpClient.changeWorkingDirectory(f.getName());

                        uploadDir(f);

                        ftpClient.changeToParentDirectory();
                    } else {
                        if (!uploaded.contains(filename)) {
                            LOGGER.debug("uploading file: {}", filename);
                            messageListener.addMessage(new Message("uploading file: " + filename));

                            ftpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
                            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

                            ftpClient.enterLocalPassiveMode();
                            InputStream is = new BufferedInputStream(
                                    new FileInputStream(directory + "/" + filename));
                            ftpClient.storeFile(filename, is);

                            uploaded.add(filename);
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Upload failure");
            throw new IOException(e);
        }
    }

    protected ProxyProvider getProxyProvider() {
        return proxyProvider;
    }

    @Override
    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}
