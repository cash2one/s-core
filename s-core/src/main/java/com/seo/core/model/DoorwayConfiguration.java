package com.seo.core.model;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.seo.core.model.conf.CategoryPageConfiguration;
import com.seo.core.model.conf.ContentPageConfiguration;
import com.seo.core.model.conf.GeneralConfiguration;
import com.seo.core.model.conf.IndexPageConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "doorway_configuration")
public class DoorwayConfiguration implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Basic
    private String name;

    @Embedded
    private GeneralConfiguration generalConfiguration;

    @Embedded
    private IndexPageConfiguration indexPageConfiguration;

    @Embedded
    private ContentPageConfiguration contentPageConfiguration;

    @Embedded
    private CategoryPageConfiguration categoryPageConfiguration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @RequiredStringValidator(key = "doorway.configuration.name.required")
    public void setName(String name) {
        this.name = name;
    }

    public GeneralConfiguration getGeneralConfiguration() {
        return generalConfiguration;
    }

    @VisitorFieldValidator(fieldName = "generalConfiguration", appendPrefix = false)
    public void setGeneralConfiguration(GeneralConfiguration generalConfiguration) {
        this.generalConfiguration = generalConfiguration;
    }

    public IndexPageConfiguration getIndexPageConfiguration() {
        return indexPageConfiguration;
    }

    @VisitorFieldValidator(fieldName = "indexPageConfiguration", appendPrefix = false)
    public void setIndexPageConfiguration(IndexPageConfiguration indexPageConfiguration) {
        this.indexPageConfiguration = indexPageConfiguration;
    }

    public ContentPageConfiguration getContentPageConfiguration() {
        return contentPageConfiguration;
    }

    @VisitorFieldValidator(fieldName = "contentPageConfiguration", appendPrefix = false)
    public void setContentPageConfiguration(ContentPageConfiguration contentPageConfiguration) {
        this.contentPageConfiguration = contentPageConfiguration;
    }

    @VisitorFieldValidator(fieldName = "categoryPageConfiguration", appendPrefix = false)
    public CategoryPageConfiguration getCategoryPageConfiguration() {
        return categoryPageConfiguration;
    }

    public void setCategoryPageConfiguration(CategoryPageConfiguration categoryPageConfiguration) {
        this.categoryPageConfiguration = categoryPageConfiguration;
    }
}
