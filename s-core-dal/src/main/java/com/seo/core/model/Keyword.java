package com.seo.core.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "keyword")
public class Keyword {
    @Id
    @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "keyword_id")
    private List<KeywordPart> keywordParts;

    public Keyword() {
    }

    public Keyword(List<KeywordPart> keywordParts) {
        this.keywordParts = keywordParts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<KeywordPart> getKeywordParts() {
        return keywordParts;
    }

    public void setKeywordParts(List<KeywordPart> keywordParts) {
        this.keywordParts = keywordParts;
    }
}
