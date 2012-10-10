package com.seo.core.model;

import javax.persistence.*;

@Entity
@Table(name = "template")
public class Template extends BaseModel {
    @Basic
    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String indexTemplate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String contentTemplate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String categoryTemplate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String robotsTemplate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String sitemapTemplate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String sitemapXmlTemplate;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] media;

    public Template() {
    }

    public Template(String name, String indexTemplate, String contentTemplate, String categoryTemplate, String robotsTemplate, String sitemapTemplate, String sitemapXmlTemplate, byte[] media) {
        this.name = name;
        this.indexTemplate = indexTemplate;
        this.contentTemplate = contentTemplate;
        this.categoryTemplate = categoryTemplate;
        this.robotsTemplate = robotsTemplate;
        this.sitemapTemplate = sitemapTemplate;
        this.sitemapXmlTemplate = sitemapXmlTemplate;
        this.media = media;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexTemplate() {
        return indexTemplate;
    }

    public void setIndexTemplate(String indexTemplate) {
        this.indexTemplate = indexTemplate;
    }

    public String getContentTemplate() {
        return contentTemplate;
    }

    public void setContentTemplate(String contentTemplate) {
        this.contentTemplate = contentTemplate;
    }

    public String getCategoryTemplate() {
        return categoryTemplate;
    }

    public void setCategoryTemplate(String categoryTemplate) {
        this.categoryTemplate = categoryTemplate;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public String getRobotsTemplate() {
        return robotsTemplate;
    }

    public void setRobotsTemplate(String robotsTemplate) {
        this.robotsTemplate = robotsTemplate;
    }

    public String getSitemapTemplate() {
        return sitemapTemplate;
    }

    public void setSitemapTemplate(String sitemapTemplate) {
        this.sitemapTemplate = sitemapTemplate;
    }

    public String getSitemapXmlTemplate() {
        return sitemapXmlTemplate;
    }

    public void setSitemapXmlTemplate(String sitemapXmlTemplate) {
        this.sitemapXmlTemplate = sitemapXmlTemplate;
    }
}
