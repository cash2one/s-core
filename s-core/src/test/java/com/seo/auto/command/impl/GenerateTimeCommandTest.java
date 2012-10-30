package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.auto.command.generate.GenerateTimeCommand;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

public class GenerateTimeCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Test
    public void testGenerateTimeCommand() throws CommandExecutionFailedException {
        GenerateTimeCommand generateTimeCommand = new GenerateTimeCommand();

        generateTimeCommand.setName("testvar");

        generateTimeCommand.initCommand(providerManager);

        CommandClient commandClient = CommandClientImpl.newInstance();
        generateTimeCommand.execute(commandClient);

        String var = commandClient.getRegistry().get("testvar");

        assertNotNull(var);
    }
}
