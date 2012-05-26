package com.seo.upload.model;

public class ServerInfo {
    private String host;
    private String login;
    private String password;
    private String prefix;

    public ServerInfo(String host, String login, String password, String prefix) {
        this.host = host;
        this.login = login;
        this.password = password;
        this.prefix = prefix;
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
}