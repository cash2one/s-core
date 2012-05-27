package com.seo.upload.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.*;

public class ProxyfiedSocketFactory extends SocketFactory {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyfiedSocketFactory.class);
    private Proxy proxy;

    public ProxyfiedSocketFactory(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
        Socket socket = new Socket(proxy);
        socket.connect(new InetSocketAddress(s, i));

        return socket;
    }

    @Override
    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Socket createSocket() throws IOException {
        return new Socket(proxy);
    }
}