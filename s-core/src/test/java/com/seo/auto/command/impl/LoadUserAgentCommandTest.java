package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.InjectUtil;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.useragent.LoadUserAgentCommand;
import com.seo.provider.manager.ProviderManager;
import com.seo.webclient.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class LoadUserAgentCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Mock
    private WebClient webClient;

    private LoadUserAgentCommand loadUserAgentCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        loadUserAgentCommand = new LoadUserAgentCommand();
        loadUserAgentCommand.initCommand(providerManager);
    }

    @Test
    public void testLoadUserAgent() {
        CommandClient commandClient = CommandClientImpl.newInstance();

        InjectUtil.injectMock(commandClient, "webClient", webClient);

        loadUserAgentCommand.execute(commandClient);

        verify(webClient).updateUserAgent(anyString());
    }
}
