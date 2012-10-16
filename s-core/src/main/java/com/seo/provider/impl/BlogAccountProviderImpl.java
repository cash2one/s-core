package com.seo.provider.impl;

import com.seo.core.model.account.BlogAccount;
import com.seo.core.service.BlogAccountManager;
import com.seo.provider.BlogAccountProvider;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BlogAccountProviderImpl implements BlogAccountProvider {

    @Inject
    private BlogAccountManager blogAccountManager;

    @Override
    public void createAccount(String type, String url, String xmlRpcPath, String login, String password) {
        BlogAccount blogAccount = new BlogAccount(type, url, xmlRpcPath, login, password);

        blogAccountManager.save(blogAccount);
    }
}
