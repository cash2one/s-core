package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.request.SaveImageCommand;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

public class SaveImageCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Test
    public void testSaveImage() {
        SaveImageCommand command = new SaveImageCommand();

        command.initCommand(providerManager);

        command.setName("testimage");
        command.setUrl("https://www.google.com/images/srpr/logo3w.png");

        CommandClient commandClient = CommandClientImpl.newInstance();

        command.execute(commandClient);

        String imageValue = commandClient.getRegistry().get("testimage");

        assertNotNull(imageValue);
    }
}
