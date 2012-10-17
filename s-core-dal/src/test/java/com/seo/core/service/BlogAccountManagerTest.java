package com.seo.core.service;

import com.seo.core.AbstractDalTest;
import com.seo.core.model.account.BlogAccount;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BlogAccountManagerTest extends AbstractDalTest {
    @Inject
    private BlogAccountManager blogAccountManager;

    @Test
    public void testSaveBlogAccount() {
        BlogAccount blogAccount = new BlogAccount("livejournal", "http://testblog.livejournal.com", "http://testblog.livejournal.com/xml-rpc/api", "testblog", "testpassword");

        BlogAccount savedBlogAccount = blogAccountManager.save(blogAccount);

        assertNotNull(savedBlogAccount);
        assertNotNull(savedBlogAccount.getId());

        BlogAccount loadedBlogAccount = blogAccountManager.findById(savedBlogAccount.getId());

        assertEquals(loadedBlogAccount.getId(), savedBlogAccount.getId());
        assertEquals(loadedBlogAccount.getLogin(), savedBlogAccount.getLogin());
        assertEquals(loadedBlogAccount.getPassword(), savedBlogAccount.getPassword());
    }

    @Test
    public void testFindByNonExistingId() {
        BlogAccount blogAccount = new BlogAccount("livejournal", "http://testblog.livejournal.com", "http://testblog.livejournal.com/xml-rpc/api", "testblog", "testpassword");

        BlogAccount savedBlogAccount = blogAccountManager.save(blogAccount);

        assertNotNull(savedBlogAccount);
        assertNotNull(savedBlogAccount.getId());

        BlogAccount loadedBlogAccount = blogAccountManager.findById(savedBlogAccount.getId());

        assertEquals(loadedBlogAccount.getId(), savedBlogAccount.getId());
        assertEquals(loadedBlogAccount.getLogin(), savedBlogAccount.getLogin());
        assertEquals(loadedBlogAccount.getPassword(), savedBlogAccount.getPassword());

        BlogAccount notFoundBlogAccount = blogAccountManager.findById(-42L);

        assertNull(notFoundBlogAccount);
    }

    @Test
    public void testFindByUrl() {
        BlogAccount blogAccount = new BlogAccount("livejournal", "http://testblog.livejournal.com", "http://testblog.livejournal.com/xml-rpc/api", "testblog", "testpassword");

        BlogAccount savedBlogAccount = blogAccountManager.save(blogAccount);

        assertNotNull(savedBlogAccount);
        assertNotNull(savedBlogAccount.getId());

        BlogAccount loadedBlogAccount = blogAccountManager.findByUrl(savedBlogAccount.getUrl());

        assertEquals(loadedBlogAccount.getUrl(), savedBlogAccount.getUrl());
    }
}
