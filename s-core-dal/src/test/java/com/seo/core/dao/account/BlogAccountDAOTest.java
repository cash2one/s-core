package com.seo.core.dao.account;

import com.seo.core.dao.AbstractDaoTest;
import com.seo.core.dao.BaseRepository;
import com.seo.core.dao.BlogAccountDAO;
import com.seo.core.model.BaseModel;
import com.seo.core.model.account.BlogAccount;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

public class BlogAccountDAOTest extends AbstractDaoTest {

    private BlogAccountDAO blogAccountDAO;

    @Inject
    public void setRepository(BlogAccountDAO blogAccountDAO) {
        this.repository = (BaseRepository) blogAccountDAO;
        this.blogAccountDAO = blogAccountDAO;
    }

    @Override
    public BaseModel createModel() {
        return new BlogAccount();
    }

    @Test
    public void testGetRandom() {
        BaseModel model = createModel();
        model = blogAccountDAO.save((BlogAccount)model);

        BlogAccount blogAccount = blogAccountDAO.getRandom();

        assertNotNull(blogAccount);
    }
}
