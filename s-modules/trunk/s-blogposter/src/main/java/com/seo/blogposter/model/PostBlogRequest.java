package com.seo.blogposter.model;

public class PostBlogRequest {
    private String type;
    private String xmlRpcPath;
    private String login;
    private String password;
    private String title;
    private String text;

    public PostBlogRequest(String type, String xmlRpcPath, String login, String password, String title, String text) {
        this.type = type;
        this.xmlRpcPath = xmlRpcPath;
        this.login = login;
        this.password = password;
        this.title = title;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public String getXmlRpcPath() {
        return xmlRpcPath;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
