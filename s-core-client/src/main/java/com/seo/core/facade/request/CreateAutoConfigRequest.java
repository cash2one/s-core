package com.seo.core.facade.request;

import java.io.File;

public class CreateAutoConfigRequest {
    private String name;
    private File content;
    private String contentFileName;
    private String contentContentType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getContent() {
        return content;
    }

    public void setContent(File content) {
        this.content = content;
    }

    public String getContentFileName() {
        return contentFileName;
    }

    public void setContentFileName(String contentFileName) {
        this.contentFileName = contentFileName;
    }

    public String getContentContentType() {
        return contentContentType;
    }

    public void setContentContentType(String contentContentType) {
        this.contentContentType = contentContentType;
    }
}
