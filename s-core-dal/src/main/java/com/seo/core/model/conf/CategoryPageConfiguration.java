package com.seo.core.model.conf;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategoryPageConfiguration {
    @Column(name = "category_page_min_number_pages")
    private Integer minNumberOfPages;

    @Column(name = "category_page_max_number_pages")
    private Integer maxNumberOfPages;

    public CategoryPageConfiguration() {
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
}
