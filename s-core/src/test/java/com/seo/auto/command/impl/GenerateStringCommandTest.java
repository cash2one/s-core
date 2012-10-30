package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.auto.command.generate.GenerateStringCommand;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GenerateStringCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Test
    public void testGenerateStringCommand() throws CommandExecutionFailedException {
        GenerateStringCommand generateStringCommand = new GenerateStringCommand();

        generateStringCommand.setMaxSize(20);
        generateStringCommand.setMinSize(10);
        generateStringCommand.setAppendDigits(false);
        generateStringCommand.setName("testvar");

        generateStringCommand.initCommand(providerManager);

        CommandClient commandClient = CommandClientImpl.newInstance();
        generateStringCommand.execute(commandClient);

        String var = commandClient.getRegistry().get("testvar");

        assertNotNull(var);
        assertTrue(var.length() >= 10);
        assertTrue(var.length() <= 20);
    }

    @Test
    public void testGenerateStringCommandWithDigits() throws CommandExecutionFailedException {
        GenerateStringCommand generateStringCommand = new GenerateStringCommand();

        generateStringCommand.setMaxSize(10);
        generateStringCommand.setMinSize(10);
        generateStringCommand.setAppendDigits(true);
        generateStringCommand.setName("testvar");

        generateStringCommand.initCommand(providerManager);

        CommandClient commandClient = CommandClientImpl.newInstance();
        generateStringCommand.execute(commandClient);

        String var = commandClient.getRegistry().get("testvar");

        assertNotNull(var);
        assertTrue(var.length() >= 10);
        assertTrue(var.length() <= 10);
    }
}
