package com.seo.upload;

import com.seo.message.MessageNotifier;
import com.seo.upload.model.UploadRequest;

public interface DirectoryUploader extends MessageNotifier{
    void upload(UploadRequest uploadRequest);
}
