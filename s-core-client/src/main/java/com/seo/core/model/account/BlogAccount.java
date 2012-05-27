package com.seo.core.model.account;

import javax.persistence.*;

@Entity
@Table(name = "blog_account")
public class BlogAccount {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String url;
    @Column(name = "api_path")
    private String apiPath;
    private String login;
    private String password;

    public BlogAccount() {
    }

    public BlogAccount(String type, String url, String apiPath, String login, String password) {
        this.type = type;
        this.url = url;
        this.apiPath = apiPath;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
