package com.seo.provider;

import com.seo.BaseCoreTest;
import com.seo.core.model.account.BlogAccount;
import com.seo.core.service.BlogAccountManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BlogAccountProviderTest extends BaseCoreTest {

    public static final String TEST_BLOG_URL = "http://testblog.livejournal.com";
    @Inject
    private BlogAccountProvider blogAccountProvider;

    @Inject
    private BlogAccountManager blogAccountManager;

    @Test
    public void testCreateAccount() {
        blogAccountProvider.createAccount("livejournal", TEST_BLOG_URL, "http://testblog.livejournal.com/xml-rpc/api", "testlogin", "testpassword");

        BlogAccount blogAccount = blogAccountManager.findByUrl(TEST_BLOG_URL);

        assertNotNull(blogAccount);
        assertEquals(blogAccount.getUrl(), TEST_BLOG_URL);
    }
}
