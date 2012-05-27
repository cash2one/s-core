package com.seo.core.model.conf;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContentPageConfiguration implements Serializable{
    @Column(name = "content_page_title")
    private String title;
    @Column(name = "content_page_min_number_pages")
    private Integer minNumberOfPages;
    @Column(name = "content_page_max_number_pages")
    private Integer maxNumberOfPages;
    @Column(name = "content_page_min_number_contents")
    private Integer minNumberOfContents;
    @Column(name = "content_page_max_number_contents")
    private Integer maxNumberOfContents;
    @Column(name = "min_content_length")
    private Integer minContentLength;
    @Column(name = "max_content_length")
    private Integer maxContentLength;

    public String getTitle() {
        return title;
    }

    @RequiredStringValidator(key = "doorway.configuration.content.title.required")
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinNumberOfPages() {
        return minNumberOfPages;
    }

    @RequiredFieldValidator(key = "doorway.configuration.min.content.pages.required")
    public void setMinNumberOfPages(Integer minNumberOfPages) {
        this.minNumberOfPages = minNumberOfPages;
    }

    public Integer getMaxNumberOfPages() {
        return maxNumberOfPages;
    }

    @RequiredFieldValidator(key = "doorway.configuration.max.content.pages.required")
    public void setMaxNumberOfPages(Integer maxNumberOfPages) {
        this.maxNumberOfPages = maxNumberOfPages;
    }

    public Integer getMinNumberOfContents() {
        return minNumberOfContents;
    }

    @RequiredFieldValidator(key = "doorway.configuration.content.min.contents.required")
    public void setMinNumberOfContents(Integer minNumberOfContents) {
        this.minNumberOfContents = minNumberOfContents;
    }

    public Integer getMaxNumberOfContents() {
        return maxNumberOfContents;
    }

    @RequiredFieldValidator(key = "doorway.configuration.content.max.contents.required")
    public void setMaxNumberOfContents(Integer maxNumberOfContents) {
        this.maxNumberOfContents = maxNumberOfContents;
    }

    public Integer getMinContentLength() {
        return minContentLength;
    }

    @RequiredFieldValidator(key = "doorway.configuration.content.min.length.required")
    public void setMinContentLength(Integer minContentLength) {
        this.minContentLength = minContentLength;
    }

    public Integer getMaxContentLength() {
        return maxContentLength;
    }

    @RequiredFieldValidator(key = "doorway.configuration.content.max.length.required")
    public void setMaxContentLength(Integer maxContentLength) {
        this.maxContentLength = maxContentLength;
    }
}
