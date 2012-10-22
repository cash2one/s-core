package com.seo.provider;

import com.seo.core.model.account.FTPAccount;
import com.seo.provider.model.FTP;

public interface FtpAccountProvider {
    void createAccount(String url, String login, String password, String host, String prefix, String type);
    FTPAccount getAccountByType(String type);
}
