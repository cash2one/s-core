package com.seo.upload.factory;

import com.seo.upload.DirectoryUploader;
import com.seo.upload.model.UploadRequest;

public interface UploaderFactory {
    DirectoryUploader createUploader(UploadRequest uploadRequest);
}
