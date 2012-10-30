package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.email.DeleteEmailCommand;
import com.seo.core.model.account.EmailAccount;
import com.seo.core.service.EmailAccountManager;
import com.seo.provider.manager.ProviderManager;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DeleteEmailCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Inject
    private EmailAccountManager emailAccountManager;

    @Test
    public void testDeleteEmailCommand() {
        EmailAccount emailAccount = new EmailAccount("testemail@testhost.com", "testhost.com", "testemail", "testpassword");

        emailAccount = emailAccountManager.save(emailAccount);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());

        DeleteEmailCommand deleteEmailCommand = new DeleteEmailCommand();

        deleteEmailCommand.setEmail("testemail@testhost.com");

        deleteEmailCommand.initCommand(providerManager);

        deleteEmailCommand.execute(CommandClientImpl.newInstance());

        EmailAccount loadedEmailAccount = emailAccountManager.findByEmail("testemail@testhost.com");

        assertNull(loadedEmailAccount);
    }
}
