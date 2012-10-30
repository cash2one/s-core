package com.seo.auto.command.impl;

import com.seo.AbstractCoreTest;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.auto.command.generate.GenerateNumberCommand;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GenerateNumberCommandTest extends AbstractCoreTest {

    @Inject
    private ProviderManager providerManager;

    @Test
    public void testGenerateNumberCommand() throws CommandExecutionFailedException {
        GenerateNumberCommand generateNumberCommand = new GenerateNumberCommand();

        generateNumberCommand.setMin(10);
        generateNumberCommand.setMax(25);
        generateNumberCommand.setName("testvar");

        generateNumberCommand.initCommand(providerManager);

        CommandClient commandClient = CommandClientImpl.newInstance();
        generateNumberCommand.execute(commandClient);

        String value = commandClient.getRegistry().get("testvar");

        Integer intval = Integer.valueOf(value);

        assertNotNull(intval);
        assertTrue(intval >= 10);
        assertTrue(intval <= 25);
    }
}
