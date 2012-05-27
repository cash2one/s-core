package com.seo.upload.impl;

import com.seo.message.MessageListener;
import com.seo.upload.DirectoryUploader;

public class CompressedFTPUploader extends DirectFTPUploader implements DirectoryUploader {
/*    private WebClient webClient = WebClientImpl.newInstance();

    private FileCompressor fileCompressor;

    public void setFileCompressor(FileCompressor fileCompressor) {
        this.fileCompressor = fileCompressor;
    }

    private final static Logger LOGGER = LoggerFactory.getLogger(CompressedFTPUploader.class);

    @Override
    public void upload(UploadRequest uploadRequest) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean upload(String dir, String destination, String login, String password, String prefixdir, String hosting) {

        compressor.compressDir(dir);
        boolean error = false;
        try {
            login(destination, login, password, hosting);
            if (prefixdir != null) {
                ftp.changeWorkingDirectory(prefixdir);
            }
            File directory = new File(dir);

            String archivename = directory.getParent() + "\\" + directory.getName() + ".zip";

            List<String> files = new ArrayList<String>();
            {
                files.add(archivename);
                files.add(directory + "\\" + "unzipper.php");
                files.add(directory + "\\" + "pclzip.lib.php");
            }
            uploadFiles(files);

            ftp.logout();

            uncompress(directory.getName() + ".zip");

            return true;

        } catch (IOException e) {
            logger.error("I/O error: " + e);
            logger.info("Retrying");
            return upload(dir, destination, login, password, prefixdir, hosting);
        } catch (NullPointerException e) {
            logger.error("NullPointer error: " + e);
        }
        finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    // do nothing
                }
            }
        }
        return false;

    }

    private void uploadFiles(List<String> uploadList) throws IOException {
        for (String filename : uploadList) {
            if (!uploaded.contains(filename)) {
                logger.info("Uploading file: " + filename);
                File file = new File(filename);
                InputStream is = new BufferedInputStream(new FileInputStream(file));
                ftp.storeFile(file.getName(), is);
                uploaded.add(filename);
            }
        }
    }


    private void uncompress(String url, String filename) {
        Request request = new Request(url + "/unzipper.php?dir=.&unzip=" + filename + "&action=unzip", RequestFactory.GET_METHOD, null, null);
        Response response = webClient.retrievePage(request);

        if (!response.getContent().contains("Archive sucessfuly extracted")) {
            throw new RuntimeException("cannot connect to unzipper");
        }

    }*/

    @Override
    public void setMessageListener(MessageListener messageListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
