package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.InjectUtil;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.check.CheckFTPAccountCommand;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.checker.ftp.FtpAccountChecker;
import com.seo.provider.manager.ProviderManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.mockito.Mockito.when;

public class CheckFTPAccountCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Mock
    private FtpAccountChecker ftpAccountChecker;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        InjectUtil.injectMock(providerManager, "ftpAccountChecker", ftpAccountChecker);
    }

    @Test
    public void testCheckFTPAccountCommand() throws CommandExecutionFailedException {
        when(ftpAccountChecker.checkAccount("testlogin", "testpassword", "testhost")).thenReturn(true);

        CheckFTPAccountCommand checkFTPAccountCommand = new CheckFTPAccountCommand();

        checkFTPAccountCommand.setHost("testhost");
        checkFTPAccountCommand.setLogin("testlogin");
        checkFTPAccountCommand.setPassword("testpassword");

        checkFTPAccountCommand.initCommand(providerManager);

        checkFTPAccountCommand.execute(CommandClientImpl.newInstance());
    }
}
