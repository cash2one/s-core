package com.seo.auto.command.test;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.test.impl.StringTestCommand;
import com.seo.webclient.model.Response;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StringTestCommandTest {

    @Test
    public void testStringTestCommand() {
        StringTestCommand stringTestCommand = new StringTestCommand();

        stringTestCommand.setValue("teststring");

        boolean result = stringTestCommand.test(new Response("this string should contain word teststring in order to pass the test", null), new Registry());

        assertTrue(result);
    }
}
