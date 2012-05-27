package com.seo.core.facade.request;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import java.io.File;

public class CreateKeywordGroupRequest {
    private String name;
    private File content;
    private String contentFileName;
    private String contentContentType;

    public String getName() {
        return name;
    }

    @RequiredStringValidator(key = "keyword.group.name.required")
    public void setName(String name) {
        this.name = name;
    }

    public File getContent() {
        return content;
    }

    @RequiredFieldValidator(key = "keyword.group.content.required")
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
