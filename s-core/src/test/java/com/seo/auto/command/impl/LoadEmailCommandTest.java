package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.load.LoadEmailCommand;
import com.seo.core.model.account.EmailAccount;
import com.seo.core.service.EmailAccountManager;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoadEmailCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Inject
    private EmailAccountManager emailAccountManager;

    @Test
    public void testLoadEmailCommand() {
        EmailAccount emailAccount = new EmailAccount("testemail@gmail.com", "gmail.com", "testemail", "testpassword");

        emailAccount = emailAccountManager.save(emailAccount);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());

        LoadEmailCommand loadEmailCommand = new LoadEmailCommand();

        loadEmailCommand.setHost("gmail.com");
        loadEmailCommand.setPrefix("gmail");

        loadEmailCommand.initCommand(providerManager);

        CommandClient commandClient = CommandClientImpl.newInstance();
        loadEmailCommand.execute(commandClient);

        assertEquals(commandClient.getRegistry().get("gmail"), emailAccount.getEmail());
        assertEquals(commandClient.getRegistry().get("gmaillogin"), emailAccount.getUser());
        assertEquals(commandClient.getRegistry().get("gmailpassword"), emailAccount.getPassword());
        assertEquals(commandClient.getRegistry().get("gmailhost"), emailAccount.getHost());
    }
}
