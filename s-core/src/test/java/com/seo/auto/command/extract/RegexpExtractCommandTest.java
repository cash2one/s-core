package com.seo.auto.command.extract;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.extract.impl.RegexpExtractCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RegexpExtractCommandTest {

    @Test
    public void testRegexpExtractCommand() {
        RegexpExtractCommand regexpExtractCommand = new RegexpExtractCommand();

        regexpExtractCommand.setName("testvar");
        regexpExtractCommand.setPattern("derpy(\\w+)");

        Registry registry = new Registry();
        regexpExtractCommand.extract("from this string the derpyherp is what i want to extract", registry);

        String var = registry.get("testvar");

        assertNotNull(var);
        assertEquals("herp", var);
    }
}
