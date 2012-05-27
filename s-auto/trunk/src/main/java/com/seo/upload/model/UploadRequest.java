package com.seo.upload.model;

import com.seo.upload.UploadMethodType;

public class UploadRequest {
    private String uploadDir;
    private UploadMethodType uploadMethod;
    private ServerInfo serverInfo;
    private boolean useProxy;

    public UploadRequest(String uploadDir, UploadMethodType uploadMethod, ServerInfo serverInfo, boolean useProxy) {
        this.uploadDir = uploadDir;
        this.uploadMethod = uploadMethod;
        this.serverInfo = serverInfo;
        this.useProxy = useProxy;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public UploadMethodType getUploadMethod() {
        return uploadMethod;
    }

    public boolean isUseProxy() {
        return useProxy;
    }
    
}
