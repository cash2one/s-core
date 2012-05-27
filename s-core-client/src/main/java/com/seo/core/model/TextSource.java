package com.seo.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "text_source")
public class TextSource implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;

    public TextSource() {
    }

    public TextSource(String name, String content) {
        this.name = name;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
