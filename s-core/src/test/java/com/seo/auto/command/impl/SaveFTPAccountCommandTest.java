package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.Command;
import com.seo.auto.command.result.SaveFTPAccountCommand;
import com.seo.core.model.account.FTPAccount;
import com.seo.core.service.FTPAccountManager;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SaveFTPAccountCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Inject
    private FTPAccountManager ftpAccountManager;

    @Test
    public void testSaveFtpAccount() {
        SaveFTPAccountCommand command = new SaveFTPAccountCommand();
        command.initCommand(providerManager);

        command.setUrl("http://ftp.command.com");
        command.setLogin("testlogin");
        command.setPassword("testpassword");
        command.setHost("command.com");
        command.setPrefix("ftp_prefix");
        command.setType("command.com");

        command.execute(CommandClientImpl.newInstance());

        FTPAccount account = ftpAccountManager.findRandomByType("command.com");

        assertNotNull(account);

        assertEquals(account.getUrl(), "http://ftp.command.com");
    }
}
