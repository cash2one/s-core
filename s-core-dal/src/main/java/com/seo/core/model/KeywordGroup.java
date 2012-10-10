package com.seo.core.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "keyword_group")
public class KeywordGroup {
    @Id
    @GeneratedValue
    @Column(name = "keyword_group_id")
    private Long id;

    @Basic
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "keyword_group_id")
    private List<Keyword> keywords;

    public KeywordGroup() {
    }

    public KeywordGroup(String name, List<Keyword> keywords) {
        this.name = name;
        this.keywords = keywords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}
