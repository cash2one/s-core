package com.seo.core.dao.account;

import com.seo.core.dao.AbstractDaoTest;
import com.seo.core.dao.BaseRepository;
import com.seo.core.dao.BlogAccountRepository;
import com.seo.core.model.BaseModel;
import com.seo.core.model.account.BlogAccount;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

public class BlogAccountRepositoryTest extends AbstractDaoTest {

    private BlogAccountRepository blogAccountRepository;

    @Inject
    public void setRepository(BlogAccountRepository blogAccountRepository) {
        this.repository = (BaseRepository)blogAccountRepository;
        this.blogAccountRepository = blogAccountRepository;
    }

    @Override
    public BaseModel createModel() {
        return new BlogAccount();
    }

    @Test
    public void testGetRandom() {
        BaseModel model = createModel();
        model = blogAccountRepository.save((BlogAccount)model);

        BlogAccount blogAccount = blogAccountRepository.getRandom();

        assertNotNull(blogAccount);
    }
}
