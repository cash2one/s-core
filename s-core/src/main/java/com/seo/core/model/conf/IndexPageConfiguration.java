package com.seo.core.model.conf;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IndexPageConfiguration implements Serializable{
    @Basic
    @Column(name = "index_page_title")
    private String title;

    public String getTitle() {
        return title;
    }

    @RequiredStringValidator(key = "doorway.configuration.index.title.required")
    public void setTitle(String title) {
        this.title = title;
    }
}
