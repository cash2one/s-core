package com.seo.auto.command.test;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.test.impl.ElementTestCommand;
import com.seo.webclient.model.Response;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ElementTestCommandTest {

    @Test
    public void testElementTestCommand() {
        ElementTestCommand elementTestCommand = new ElementTestCommand();

        elementTestCommand.setAttribute("testatt");
        elementTestCommand.setValue("testvalue");

        boolean result = elementTestCommand.test(new Response("<html><div testatt=\"testvalue\" id=\"herpyderp\"></div></html>", null), new Registry());

        assertTrue(result);
    }
}
