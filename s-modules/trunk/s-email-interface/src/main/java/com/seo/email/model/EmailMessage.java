package com.seo.email.model;

public class EmailMessage {
    private String content;

    public EmailMessage() {
    }

    public EmailMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
