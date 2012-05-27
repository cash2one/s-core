package com.seo.upload.factory;

import com.seo.upload.DirectoryUploader;
import com.seo.upload.UploadMethodType;
import com.seo.upload.impl.DirectFTPUploader;
import com.seo.upload.model.UploadRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploaderFactoryImpl implements UploaderFactory{
    private final static Logger LOGGER = LoggerFactory.getLogger(UploaderFactoryImpl.class);

    @Override
    public DirectoryUploader createUploader(UploadRequest uploadRequest) {
        UploadMethodType uploadMethod = uploadRequest.getUploadMethod();

        DirectoryUploader directoryUploader;
        switch (uploadMethod) {
            case DIRECT_FTP:
                directoryUploader = new DirectFTPUploader();

                break;
            case COMPRESSED_FTP:
                throw new UnsupportedOperationException("");
            default:
                LOGGER.error("unknown upload method");

                throw new RuntimeException("unknown upload method") ;
        }

        return directoryUploader;
    }
}
