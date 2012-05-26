package com.seo.doorgen.model;

public class Template {
    private DoorwayPageType type;
    private String content;

    public Template(DoorwayPageType type, String content) {
        this.type = type;
        this.content = content;
    }

    public DoorwayPageType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}

