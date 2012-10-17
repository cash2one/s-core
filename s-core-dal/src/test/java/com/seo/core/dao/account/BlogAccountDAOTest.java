package com.seo.core.dao.account;

import com.seo.core.dao.AbstractDaoTest;
import com.seo.core.dao.BaseRepository;
import com.seo.core.dao.BlogAccountDAO;
import com.seo.core.model.BaseModel;
import com.seo.core.model.account.BlogAccount;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BlogAccountDAOTest extends AbstractDaoTest {

    public static final String TEST_BLOG_URL = "http://testblog.livejournal.com";
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

    @Test
    public void testFindByUrl() {
        BaseModel model = createModel();
        ((BlogAccount)model).setUrl(TEST_BLOG_URL);
        model = blogAccountDAO.save((BlogAccount)model);

        BlogAccount blogAccount = blogAccountDAO.findByUrl(TEST_BLOG_URL);

        assertNotNull(blogAccount);
        assertEquals(blogAccount.getUrl(), TEST_BLOG_URL);
    }
}
