package com.seo.core.model.account;

import com.seo.core.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "ftp_account")
public class FTPAccount extends BaseModel {
    private String url;
    private String host;
    private String login;
    private String password;
    private String prefix;
    private String type;
    @Column(name = "account_state")
    private FTPAccountState state;

    public FTPAccount() {
    }

    public FTPAccount(String url, String host, String login, String password, String prefix, String type, FTPAccountState state) {
        this.url = url;
        this.host = host;
        this.login = login;
        this.password = password;
        this.prefix = prefix;
        this.type = type;
        this.state = state;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getHost() {
        return host;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FTPAccountState getState() {
        return state;
    }

    public void setState(FTPAccountState state) {
        this.state = state;
    }


}
