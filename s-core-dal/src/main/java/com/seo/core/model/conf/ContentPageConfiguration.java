package com.seo.core.model.conf;


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

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinNumberOfPages() {
        return minNumberOfPages;
    }

    public void setMinNumberOfPages(Integer minNumberOfPages) {
        this.minNumberOfPages = minNumberOfPages;
    }

    public Integer getMaxNumberOfPages() {
        return maxNumberOfPages;
    }

    public void setMaxNumberOfPages(Integer maxNumberOfPages) {
        this.maxNumberOfPages = maxNumberOfPages;
    }

    public Integer getMinNumberOfContents() {
        return minNumberOfContents;
    }

    public void setMinNumberOfContents(Integer minNumberOfContents) {
        this.minNumberOfContents = minNumberOfContents;
    }

    public Integer getMaxNumberOfContents() {
        return maxNumberOfContents;
    }

    public void setMaxNumberOfContents(Integer maxNumberOfContents) {
        this.maxNumberOfContents = maxNumberOfContents;
    }

    public Integer getMinContentLength() {
        return minContentLength;
    }

    public void setMinContentLength(Integer minContentLength) {
        this.minContentLength = minContentLength;
    }

    public Integer getMaxContentLength() {
        return maxContentLength;
    }

    public void setMaxContentLength(Integer maxContentLength) {
        this.maxContentLength = maxContentLength;
    }
}
