package com.seo.doorgen.context;

import com.seo.doorgen.keyword.KeywordFacade;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.text.generation.AbstractTextGenerator;

import java.util.ArrayList;
import java.util.List;

public class DoorwayContext {
    private AbstractTextGenerator textGenerator;
    private KeywordFacade keywordFacade;
    private List<DoorwayPage> doorwayPages = new ArrayList<DoorwayPage>();
    private String url;

    public void setKeywordFacade(KeywordFacade keywordFacade) {
        this.keywordFacade = keywordFacade;
    }

    public KeywordFacade getKeywordFacade() {
        return keywordFacade;
    }

    public AbstractTextGenerator getTextGenerator() {
        return textGenerator;
    }

    public void setTextGenerator(AbstractTextGenerator textGenerator) {
        this.textGenerator = textGenerator;
    }

    public List<DoorwayPage> getDoorwayPages() {
        return doorwayPages;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
