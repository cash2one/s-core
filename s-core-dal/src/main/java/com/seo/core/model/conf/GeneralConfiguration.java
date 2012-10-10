package com.seo.core.model.conf;

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

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
