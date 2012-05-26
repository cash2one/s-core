package com.seo.doorgen.model.component;

public class CategoryPageProperties {
    private Integer minNumberOfPages;
    private Integer maxNumberOfPages;

    public CategoryPageProperties(Integer minNumberOfPages, Integer maxNumberOfPages) {
        this.minNumberOfPages = minNumberOfPages;
        this.maxNumberOfPages = maxNumberOfPages;
    }

    public Integer getMinNumberOfPages() {
        return minNumberOfPages;
    }

    public Integer getMaxNumberOfPages() {
        return maxNumberOfPages;
    }
}
