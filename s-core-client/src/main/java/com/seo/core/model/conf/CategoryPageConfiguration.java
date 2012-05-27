package com.seo.core.model.conf;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

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

    @RequiredFieldValidator(key = "doorway.configuration.min.category.pages.required")
    public void setMinNumberOfPages(Integer minNumberOfPages) {
        this.minNumberOfPages = minNumberOfPages;
    }

    public Integer getMaxNumberOfPages() {
        return maxNumberOfPages;
    }

    @RequiredFieldValidator(key = "doorway.configuration.max.category.pages.required")
    public void setMaxNumberOfPages(Integer maxNumberOfPages) {
        this.maxNumberOfPages = maxNumberOfPages;
    }
}
