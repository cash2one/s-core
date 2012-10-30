package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.load.LoadFTPAccountCommand;
import com.seo.core.model.account.FTPAccount;
import com.seo.core.model.account.FTPAccountState;
import com.seo.core.service.FTPAccountManager;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoadFTPAccountCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Inject
    private FTPAccountManager ftpAccountManager;

    @Test
    public void testLoadFTPAccount() {
        FTPAccount ftpAccount = new FTPAccount("http://ftp.com", "ftp.com", "testlogin", "testpassword", "testprefix", "testtype", FTPAccountState.FREE);

        ftpAccount = ftpAccountManager.save(ftpAccount);

        assertNotNull(ftpAccount);
        assertNotNull(ftpAccount.getId());

        LoadFTPAccountCommand loadFTPAccountCommand = new LoadFTPAccountCommand();

        loadFTPAccountCommand.setPrefix("testprefix");
        loadFTPAccountCommand.setType("testtype");

        loadFTPAccountCommand.initCommand(providerManager);

        CommandClient commandClient = CommandClientImpl.newInstance();
        loadFTPAccountCommand.execute(commandClient);

        assertEquals(commandClient.getRegistry().get("testprefixhost"), ftpAccount.getHost());
        assertEquals(commandClient.getRegistry().get("testprefixurl"), ftpAccount.getUrl());
        assertEquals(commandClient.getRegistry().get("testprefixlogin"), ftpAccount.getLogin());
        assertEquals(commandClient.getRegistry().get("testprefixpassword"), ftpAccount.getPassword());
        assertEquals(commandClient.getRegistry().get("testprefixprefix"), ftpAccount.getPrefix());
    }
}
