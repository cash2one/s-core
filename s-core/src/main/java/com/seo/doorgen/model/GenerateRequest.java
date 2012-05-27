package com.seo.doorgen.model;

import com.seo.doorgen.model.component.CategoryPageProperties;
import com.seo.doorgen.model.component.ContentPageProperties;
import com.seo.doorgen.model.component.IndexPageProperties;

import java.util.List;

public class GenerateRequest {
    private GeneralProperties generalProperties;
    private IndexPageProperties indexPageProperties;
    private ContentPageProperties contentPageProperties;
    private CategoryPageProperties categoryPageProperties;
    private TextSource textSource;
    private List<Keyword> keywords;
    private byte[] media;

    public GenerateRequest(GeneralProperties generalProperties, IndexPageProperties indexPageProperties, ContentPageProperties contentPageProperties, CategoryPageProperties categoryPageProperties, TextSource textSource, List<Keyword> keywords, byte[] media) {
        this.generalProperties = generalProperties;
        this.indexPageProperties = indexPageProperties;
        this.contentPageProperties = contentPageProperties;
        this.categoryPageProperties = categoryPageProperties;
        this.textSource = textSource;
        this.keywords = keywords;
        this.media = media;
    }

    public CategoryPageProperties getCategoryPageProperties() {
        return categoryPageProperties;
    }

    public GeneralProperties getGeneralProperties() {
        return generalProperties;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public IndexPageProperties getIndexPageProperties() {
        return indexPageProperties;
    }

    public ContentPageProperties getContentPageProperties() {
        return contentPageProperties;
    }

    public TextSource getTextSource() {
        return textSource;
    }

    public byte[] getMedia() {
        return media;
    }
}


