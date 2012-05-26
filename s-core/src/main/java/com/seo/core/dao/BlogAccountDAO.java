package com.seo.core.dao;

import com.seo.core.model.account.BlogAccount;

import java.util.List;

public interface BlogAccountDAO{
    void save(BlogAccount blogAccount);
    void delete(BlogAccount blogAccount);
    BlogAccount getById(long id);
    List<BlogAccount> getAll();
    BlogAccount getRandom();
}
