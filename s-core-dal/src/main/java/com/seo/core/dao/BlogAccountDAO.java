package com.seo.core.dao;

import com.seo.core.model.account.BlogAccount;
import org.springframework.data.jpa.repository.Query;

public interface BlogAccountDAO extends BaseRepository<BlogAccount, Long> {
    @Query("select b from BlogAccount b order by rand()")
    BlogAccount getRandom();
    BlogAccount findByUrl(String url);
}
