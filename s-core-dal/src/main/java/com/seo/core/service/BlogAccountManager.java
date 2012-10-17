package com.seo.core.service;

import com.seo.core.model.account.BlogAccount;

public interface BlogAccountManager {
    BlogAccount save(BlogAccount blogAccount);
    BlogAccount findById(Long id);
    BlogAccount findByUrl(String url);
}
