package com.seo.core.model;

import javax.persistence.*;

@Entity
@Table(name = "redirect_script")
public class RedirectScript extends BaseModel {
    private String name;
    private String fileName;
    @Lob
    private String content;

    public RedirectScript() {
    }

    public RedirectScript(String name, String fileName, String content) {
        this.name = name;
        this.fileName = fileName;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
