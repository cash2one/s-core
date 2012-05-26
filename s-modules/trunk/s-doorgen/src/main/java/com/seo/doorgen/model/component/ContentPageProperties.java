package com.seo.doorgen.model.component;

public class ContentPageProperties {
    private String title;
    private Integer minNumberOfPages;
    private Integer maxNumberOfPages;
    private Integer minNumberOfContents;
    private Integer maxNumberOfContents;
    private Integer minContentLength;
    private Integer maxContentLength;

    public ContentPageProperties(String title, Integer minNumberOfPages, Integer maxNumberOfPages, Integer minNumberOfContents, Integer maxNumberOfContents, Integer minContentLength, Integer maxContentLength) {
        this.title = title;
        this.minNumberOfPages = minNumberOfPages;
        this.maxNumberOfPages = maxNumberOfPages;
        this.minNumberOfContents = minNumberOfContents;
        this.maxNumberOfContents = maxNumberOfContents;
        this.minContentLength = minContentLength;
        this.maxContentLength = maxContentLength;
    }

    public String getTitle() {
        return title;
    }

    public Integer getMinNumberOfPages() {
        return minNumberOfPages;
    }

    public Integer getMaxNumberOfPages() {
        return maxNumberOfPages;
    }

    public Integer getMinNumberOfContents() {
        return minNumberOfContents;
    }

    public Integer getMaxNumberOfContents() {
        return maxNumberOfContents;
    }

    public Integer getMinContentLength() {
        return minContentLength;
    }

    public Integer getMaxContentLength() {
        return maxContentLength;
    }
}
