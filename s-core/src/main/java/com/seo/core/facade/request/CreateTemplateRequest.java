package com.seo.core.facade.request;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import java.io.File;

public class CreateTemplateRequest {
    private String name;
    private File indexTemplate;
    private String indexTemplateFileName;
    private String indexTemplateContentType;
    private File contentTemplate;
    private String contentTemplateFileName;
    private String contentTemplateContentType;
    private File categoryTemplate;
    private String categoryTemplateFileName;
    private String categoryTemplateContentType;
    private File robotsTemplate;
    private String robotsTemplateFileName;
    private String robotsTemplateContentType;
    private File sitemapXmlTemplate;
    private String sitemapXmlTemplateFileName;
    private String sitemapXmlTemplateContentType;
    private File sitemapTemplate;
    private String sitemapTemplateFileName;
    private String sitemapTemplateContentType;
    private File media;
    private String mediaFileName;
    private String mediaContentType;

    @RequiredStringValidator(key = "template.name.required")
    public void setName(String name) {
        this.name = name;
    }

    @RequiredFieldValidator(key = "template.index.file.required")
    public void setIndexTemplate(File indexTemplate) {
        this.indexTemplate = indexTemplate;
    }

    public void setIndexTemplateFileName(String indexTemplateFileName) {
        this.indexTemplateFileName = indexTemplateFileName;
    }

    public void setIndexTemplateContentType(String indexTemplateContentType) {
        this.indexTemplateContentType = indexTemplateContentType;
    }

    @RequiredFieldValidator(key = "template.content.file.required")
    public void setContentTemplate(File contentTemplate) {
        this.contentTemplate = contentTemplate;
    }

    public void setContentTemplateFileName(String contentTemplateFileName) {
        this.contentTemplateFileName = contentTemplateFileName;
    }

    public void setContentTemplateContentType(String contentTemplateContentType) {
        this.contentTemplateContentType = contentTemplateContentType;
    }



    public String getName() {
        return name;
    }

    public File getIndexTemplate() {
        return indexTemplate;
    }

    public String getIndexTemplateFileName() {
        return indexTemplateFileName;
    }

    public String getIndexTemplateContentType() {
        return indexTemplateContentType;
    }

    public File getContentTemplate() {
        return contentTemplate;
    }

    public String getContentTemplateFileName() {
        return contentTemplateFileName;
    }

    public String getContentTemplateContentType() {
        return contentTemplateContentType;
    }

    public File getCategoryTemplate() {
        return categoryTemplate;
    }

    @RequiredFieldValidator(key = "template.category.file.required")
    public void setCategoryTemplate(File categoryTemplate) {
        this.categoryTemplate = categoryTemplate;
    }

    public String getCategoryTemplateFileName() {
        return categoryTemplateFileName;
    }

    public void setCategoryTemplateFileName(String categoryTemplateFileName) {
        this.categoryTemplateFileName = categoryTemplateFileName;
    }

    public String getCategoryTemplateContentType() {
        return categoryTemplateContentType;
    }

    public void setCategoryTemplateContentType(String categoryTemplateContentType) {
        this.categoryTemplateContentType = categoryTemplateContentType;
    }

    public File getMedia() {
        return media;
    }

    @RequiredFieldValidator(key = "template.media.file.required")
    public void setMedia(File media) {
        this.media = media;
    }

    public String getMediaFileName() {
        return mediaFileName;
    }

    public void setMediaFileName(String mediaFileName) {
        this.mediaFileName = mediaFileName;
    }

    public String getMediaContentType() {
        return mediaContentType;
    }

    public void setMediaContentType(String mediaContentType) {
        this.mediaContentType = mediaContentType;
    }

    public File getRobotsTemplate() {
        return robotsTemplate;
    }

    @RequiredFieldValidator(key = "template.robots.file.required")
    public void setRobotsTemplate(File robotsTemplate) {
        this.robotsTemplate = robotsTemplate;
    }

    public String getRobotsTemplateFileName() {
        return robotsTemplateFileName;
    }

    public void setRobotsTemplateFileName(String robotsTemplateFileName) {
        this.robotsTemplateFileName = robotsTemplateFileName;
    }

    public String getRobotsTemplateContentType() {
        return robotsTemplateContentType;
    }

    public void setRobotsTemplateContentType(String robotsTemplateContentType) {
        this.robotsTemplateContentType = robotsTemplateContentType;
    }

    public File getSitemapXmlTemplate() {
        return sitemapXmlTemplate;
    }

    @RequiredFieldValidator(key = "template.sitemapxml.file.required")
    public void setSitemapXmlTemplate(File sitemapXmlTemplate) {
        this.sitemapXmlTemplate = sitemapXmlTemplate;
    }

    public String getSitemapXmlTemplateFileName() {
        return sitemapXmlTemplateFileName;
    }

    public void setSitemapXmlTemplateFileName(String sitemapXmlTemplateFileName) {
        this.sitemapXmlTemplateFileName = sitemapXmlTemplateFileName;
    }

    public String getSitemapXmlTemplateContentType() {
        return sitemapXmlTemplateContentType;
    }

    public void setSitemapXmlTemplateContentType(String sitemapXmlTemplateContentType) {
        this.sitemapXmlTemplateContentType = sitemapXmlTemplateContentType;
    }

    public File getSitemapTemplate() {
        return sitemapTemplate;
    }

    @RequiredFieldValidator(key = "template.sitemap.file.required")
    public void setSitemapTemplate(File sitemapTemplate) {
        this.sitemapTemplate = sitemapTemplate;
    }

    public String getSitemapTemplateFileName() {
        return sitemapTemplateFileName;
    }

    public void setSitemapTemplateFileName(String sitemapTemplateFileName) {
        this.sitemapTemplateFileName = sitemapTemplateFileName;
    }

    public String getSitemapTemplateContentType() {
        return sitemapTemplateContentType;
    }

    public void setSitemapTemplateContentType(String sitemapTemplateContentType) {
        this.sitemapTemplateContentType = sitemapTemplateContentType;
    }
}
