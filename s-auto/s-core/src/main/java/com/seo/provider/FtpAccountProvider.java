package com.seo.provider;

import com.seo.provider.model.FTP;

public interface FtpAccountProvider {
    void createAccount(String url, String login, String password, String host, String prefix, String type);
    FTP getAccountByType(String type);
}
