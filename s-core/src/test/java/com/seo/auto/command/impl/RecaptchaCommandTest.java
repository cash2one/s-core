package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.InjectUtil;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.request.RecaptchaCommand;
import com.seo.captcha.CaptchaService;
import com.seo.provider.manager.ProviderManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

public class RecaptchaCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Mock
    private CaptchaService captchaService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        InjectUtil.injectMock(providerManager, "captchaService", captchaService);
    }

    @Test
    public void testRecaptchaCommand() throws Exception {
        RecaptchaCommand command = new RecaptchaCommand();

        command.setImage("testimage");
        command.setName("testvar");
        command.setUrl("https://www.google.com/images/srpr/logo3w.png");

        command.initCommand(providerManager);

        command.execute(CommandClientImpl.newInstance());
    }
}
