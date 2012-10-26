package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.result.SaveEmailCommand;
import com.seo.core.model.account.EmailAccount;
import com.seo.core.service.EmailAccountManager;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

public class SaveEmailCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Inject
    private EmailAccountManager emailAccountManager;

    @Test
    public void testSaveEmailCommand() {
        SaveEmailCommand command = new SaveEmailCommand();

        command.setEmail("test@test.com");
        command.setHost("test.com");
        command.setUser("testuser");
        command.setPassword("testpassword");

        command.initCommand(providerManager);
        command.execute(CommandClientImpl.newInstance());

        EmailAccount account = emailAccountManager.findByEmail("test@test.com");

        assertNotNull(account);
    }
}
