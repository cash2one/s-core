package com.seo.core.model;

import javax.persistence.*;
import com.seo.core.model.conf.CategoryPageConfiguration;
import com.seo.core.model.conf.ContentPageConfiguration;
import com.seo.core.model.conf.GeneralConfiguration;
import com.seo.core.model.conf.IndexPageConfiguration;

import java.io.Serializable;

@Entity
@Table(name = "doorway_configuration")
public class DoorwayConfiguration extends BaseModel {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeneralConfiguration getGeneralConfiguration() {
        return generalConfiguration;
    }

    public void setGeneralConfiguration(GeneralConfiguration generalConfiguration) {
        this.generalConfiguration = generalConfiguration;
    }

    public IndexPageConfiguration getIndexPageConfiguration() {
        return indexPageConfiguration;
    }

    public void setIndexPageConfiguration(IndexPageConfiguration indexPageConfiguration) {
        this.indexPageConfiguration = indexPageConfiguration;
    }

    public ContentPageConfiguration getContentPageConfiguration() {
        return contentPageConfiguration;
    }

    public void setContentPageConfiguration(ContentPageConfiguration contentPageConfiguration) {
        this.contentPageConfiguration = contentPageConfiguration;
    }

    public CategoryPageConfiguration getCategoryPageConfiguration() {
        return categoryPageConfiguration;
    }

    public void setCategoryPageConfiguration(CategoryPageConfiguration categoryPageConfiguration) {
        this.categoryPageConfiguration = categoryPageConfiguration;
    }
}
