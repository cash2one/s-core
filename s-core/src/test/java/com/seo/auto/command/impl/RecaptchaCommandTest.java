package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.InjectUtil;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.request.RecaptchaCommand;
import com.seo.captcha.CaptchaService;
import com.seo.provider.manager.ProviderManager;
import com.seo.webclient.WebClient;
import com.seo.webclient.model.EncodedImage;
import com.seo.webclient.model.Request;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class RecaptchaCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Mock
    private CaptchaService captchaService;

    @Mock
    private WebClient webClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        InjectUtil.injectMock(providerManager, "captchaService", captchaService);
    }

    @Test
    public void testRecaptchaCommand() throws Exception {
        when(webClient.retrieveRecaptchaChallenge(any(Request.class))).thenReturn("testchallenge");
        when(webClient.retrieveImage(any(Request.class))).thenReturn(new EncodedImage("testhash"));
        when(captchaService.retrieve("testhash", "jpg", true)).thenReturn("testcode");

        RecaptchaCommand command = new RecaptchaCommand();

        command.setImage("testimage");
        command.setName("testvar");
        command.setUrl("https://www.google.com/images/srpr/logo3w.png");

        command.initCommand(providerManager);

        CommandClient commandClient = CommandClientImpl.newInstance();

        InjectUtil.injectMock(commandClient, "webClient", webClient);

        command.execute(commandClient);

        assertEquals(commandClient.getRegistry().get("testvar"), "testchallenge");
        assertEquals(commandClient.getRegistry().get("testimage"), "testcode");
    }
}
