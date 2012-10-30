package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.InjectUtil;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.captcha.AnticaptchaCommand;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.captcha.CaptchaService;
import com.seo.captcha.exception.CaptchaProcessingFailedException;
import com.seo.provider.manager.ProviderManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AnticaptchaCommandTest extends AbstractCoreTest {

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
    public void testAnticaptchaCommand() throws CommandExecutionFailedException, CaptchaProcessingFailedException {
        AnticaptchaCommand anticaptchaCommand = new AnticaptchaCommand();

        when(captchaService.retrieve("testimage", "jpg", false)).thenReturn("testvalue");

        anticaptchaCommand.setExt("jpg");
        anticaptchaCommand.setImage("testimage");
        anticaptchaCommand.setName("testvar");
        anticaptchaCommand.setPhrase(false);

        anticaptchaCommand.initCommand(providerManager);

        CommandClient commandClient = CommandClientImpl.newInstance();
        anticaptchaCommand.execute(commandClient);

        String value = commandClient.getRegistry().get("testvar");

        assertEquals(value, "testvalue");
    }
}
