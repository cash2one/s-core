package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.InjectUtil;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.result.SaveBlogAccountCommand;
import com.seo.core.model.account.BlogAccount;
import com.seo.core.service.BlogAccountManager;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SaveBlogAccountCommandTest extends AbstractCoreTest {

    public static final String TEST_URL = "http://testblog.livejournal.com";
    @Inject
    private ProviderManager providerManager;

    @Inject
    private BlogAccountManager blogAccountManager;

    @Test
    public void testSaveBlogAccount() {
        SaveBlogAccountCommand command = new SaveBlogAccountCommand();

        command.initCommand(providerManager);

        command.setLogin("testlogin");
        command.setPassword("testpassword");
        command.setType("testtype");
        command.setUrl(TEST_URL);
        command.setXmlrpc("http://testblog.livejournal.com/xml-rpc.api");

        command.execute(CommandClientImpl.newInstance());

        BlogAccount account = blogAccountManager.findByUrl(TEST_URL);

        assertNotNull(account);
        assertEquals(account.getUrl(), TEST_URL);
    }
}
