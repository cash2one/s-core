package com.seo.core.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "text_source")
public class TextSource extends BaseModel {
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
