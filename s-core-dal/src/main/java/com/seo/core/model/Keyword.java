package com.seo.core.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "keyword")
public class Keyword extends BaseModel {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<KeywordPart> keywordParts;

    public Keyword() {
    }

    public Keyword(List<KeywordPart> keywordParts) {
        this.keywordParts = keywordParts;
    }

    public List<KeywordPart> getKeywordParts() {
        return keywordParts;
    }

    public void setKeywordParts(List<KeywordPart> keywordParts) {
        this.keywordParts = keywordParts;
    }
}
