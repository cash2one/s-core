package com.seo.core.model;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import javax.persistence.*;

@Entity
@Table(name = "redirect_script")
public class RedirectScript {
    @Id
    @GeneratedValue
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @RequiredStringValidator(key = "redirect.script.name.required")
    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    @RequiredStringValidator(key = "redirect.script.filename.required")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    @RequiredStringValidator(key = "redirect.script.content.required")
    public void setContent(String content) {
        this.content = content;
    }
}
