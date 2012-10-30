package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.InjectUtil;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.check.CheckEmailAccountCommand;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.checker.pop.EmailAccountChecker;
import com.seo.provider.manager.ProviderManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.mockito.Mockito.when;

public class CheckEmailAccountCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Mock
    private EmailAccountChecker emailAccountChecker;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        InjectUtil.injectMock(providerManager, "emailAccountChecker", emailAccountChecker);
    }

    @Test
    public void testCheckEmailCommand() throws CommandExecutionFailedException {
        when(emailAccountChecker.checkAccount("testlogin", "testpassword", "testhost")).thenReturn(true);

        CheckEmailAccountCommand checkEmailAccountCommand = new CheckEmailAccountCommand();

        checkEmailAccountCommand.setHost("testhost");
        checkEmailAccountCommand.setLogin("testlogin");
        checkEmailAccountCommand.setPassword("testpassword");

        checkEmailAccountCommand.initCommand(providerManager);

        CommandClient commandClient = CommandClientImpl.newInstance();

        checkEmailAccountCommand.execute(commandClient);
    }
}
