package com.seo.auto.command.test;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.test.impl.HeaderTestCommand;
import com.seo.webclient.model.Response;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class HeaderTestCommandTest {

    @Test
    public void testHeaderTestCommand() {
        HeaderTestCommand headerTestCommand = new HeaderTestCommand();

        headerTestCommand.setHeader("testheader");
        headerTestCommand.setValue("testvalue");

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("testheader", "testvalue");

        boolean result = headerTestCommand.test(new Response("herp", headers), new Registry());

        assertTrue(result);
    }
}
