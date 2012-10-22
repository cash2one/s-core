package com.seo.provider.impl;

import com.seo.core.model.account.FTPAccount;
import com.seo.core.model.account.FTPAccountState;
import com.seo.core.service.FTPAccountManager;
import com.seo.provider.FtpAccountProvider;
import com.seo.provider.model.FTP;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FtpAccountProviderImpl implements FtpAccountProvider {

    @Inject
    private FTPAccountManager ftpAccountManager;

    @Override
    public void createAccount(String url, String login, String password, String host, String prefix, String type) {
        FTPAccount account = new FTPAccount(
                url,
                host,
                login,
                password,
                prefix,
                type,
                FTPAccountState.FREE
        );

        ftpAccountManager.save(account);
    }

    @Override
    public FTPAccount getAccountByType(String type) {
        return ftpAccountManager.findRandomByType(type);
    }
}
