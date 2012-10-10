package com.seo.core.model;

import javax.persistence.*;

@Entity
@Table(name = "keyword_part")
public class KeywordPart extends BaseModel {
    @Basic
    private String content;

    public KeywordPart() {
    }

    public KeywordPart(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
