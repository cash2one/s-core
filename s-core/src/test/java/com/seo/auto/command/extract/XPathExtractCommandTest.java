package com.seo.auto.command.extract;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.extract.impl.XPathCommand;
import com.seo.provider.manager.ProviderManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XPathExtractCommandTest {

    @Test
    public void testXPathExtractCommand() {
        XPathCommand xPathCommand = new XPathCommand();

        xPathCommand.setField("id");
        xPathCommand.setName("testvar");
        xPathCommand.setQuery("//div/derp");

        Registry registry = new Registry();
        xPathCommand.extract("<html><div testatt=\"testvalue\" id=\"herpyderp\"><derp id=\"herp\"></derp></div></html>", registry);

        String var = registry.get("testvar");

        assertNotNull(var);
        assertEquals(var, "herp");
    }
}
