package com.seo.auto.command.extract;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.extract.impl.AttValueExtractCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AttValueExtractCommandTest {

    @Test
    public void testAttValueExtractCommand() {
        AttValueExtractCommand attValueExtractCommand = new AttValueExtractCommand();

        attValueExtractCommand.setAttribute("testatt");
        attValueExtractCommand.setValue("testvalue");
        attValueExtractCommand.setName("testvar");
        attValueExtractCommand.setField("id");

        Registry registry = new Registry();

        attValueExtractCommand.extract("<html><div testatt=\"testvalue\" id=\"herpyderp\"></div></html>", registry);

        String var = registry.get("testvar");

        assertNotNull(var);
        assertEquals(var, "herpyderp");
    }
}
