package com.seo.core.model;

import javax.persistence.*;

@Entity
@Table(name = "keyword_part")
public class KeywordPart {
    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String content;

    public KeywordPart() {
    }

    public KeywordPart(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
