package com.seo.core.model.conf;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GeneralConfiguration {
    @Column(name = "file_extension")
    private String fileExtension;
    private String encoding;

    public String getFileExtension() {
        return fileExtension;
    }

    @RequiredStringValidator(key = "doorway.configuration.general.file.extension")
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getEncoding() {
        return encoding;
    }

    @RequiredStringValidator(key = "doorway.configuration.general.encoding")
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
