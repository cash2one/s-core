package com.seo.core.model.account;

import com.seo.core.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_account")
public class EmailAccount extends BaseModel {

    private String email;
    private String host;
    private String user;
    private String password;

    public EmailAccount() {
    }

    public EmailAccount(String email, String host, String user, String password) {
        this.email = email;
        this.host = host;
        this.user = user;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
