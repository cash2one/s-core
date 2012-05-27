package com.seo.core.facade.request;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import java.io.File;

public class CreateTextSourceRequest {
    private String name;
    private File content;
    private String contentContentType;
    private String contentFileName;

    public String getName() {
        return name;
    }

    @RequiredStringValidator(key = "text.source.name.required")
    public void setName(String name) {
        this.name = name;
    }

    public File getContent() {
        return content;
    }

    @RequiredFieldValidator(key = "text.source.content.required")
    public void setContent(File content) {
        this.content = content;
    }

    public String getContentContentType() {
        return contentContentType;
    }

    public void setContentContentType(String contentContentType) {
        this.contentContentType = contentContentType;
    }

    public String getContentFileName() {
        return contentFileName;
    }

    public void setContentFileName(String contentFileName) {
        this.contentFileName = contentFileName;
    }
}
