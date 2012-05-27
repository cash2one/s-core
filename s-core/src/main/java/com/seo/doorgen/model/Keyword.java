package com.seo.doorgen.model;

public class Keyword {
    private String content;

    public Keyword(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keyword keyword = (Keyword) o;

        if (!content.equals(keyword.content)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
