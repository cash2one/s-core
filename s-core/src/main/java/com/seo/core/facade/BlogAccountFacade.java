package com.seo.core.facade;

import com.seo.core.model.account.BlogAccount;
import com.seo.provider.BlogAccountProvider;

import java.util.List;

public interface BlogAccountFacade extends BlogAccountProvider{
    void save(BlogAccount blogAccount);
    void deleteById(long id);
    BlogAccount getById(long id);
    List<BlogAccount> getAll();
    BlogAccount getRandom();
}
