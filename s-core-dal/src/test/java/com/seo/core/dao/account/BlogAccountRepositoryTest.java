package com.seo.core.dao.account;

import com.seo.core.dao.AbstractDaoTest;
import com.seo.core.dao.BaseRepository;
import com.seo.core.dao.BlogAccountRepository;
import com.seo.core.model.BaseModel;
import com.seo.core.model.account.BlogAccount;

import javax.inject.Inject;

public class BlogAccountRepositoryTest extends AbstractDaoTest {

    @Inject
    public void setRepository(BlogAccountRepository blogAccountRepository) {
        this.repository = (BaseRepository)blogAccountRepository;
    }

    @Override
    public BaseModel createModel() {
        return new BlogAccount();
    }
}
