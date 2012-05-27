package com.seo.doorgen.model;

import java.util.List;

public class DoorwayPage {
    private String title;
    private String filename;
    private List<String> contents;
    private DoorwayPageType pageType;
    private List<Keyword> keywords;
    private List<DoorwayPage> linkedPages;

    public DoorwayPage(String title, String filename, List<String> contents, DoorwayPageType pageType, List<Keyword> keywords, List<DoorwayPage> linkedPages) {
        this.title = title;
        this.filename = filename;
        this.contents = contents;
        this.pageType = pageType;
        this.keywords = keywords;
        this.linkedPages = linkedPages;
    }

    public String getTitle() {
        return title;
    }

    public String getFilename() {
        return filename;
    }

    public List<String> getContents() {
        return contents;
    }

    public DoorwayPageType getPageType() {
        return pageType;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public List<DoorwayPage> getLinkedPages() {
        return linkedPages;
    }
}
