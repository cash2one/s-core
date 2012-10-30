package com.seo.auto.client;

import com.seo.auto.client.registry.Registry;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RegistryTest {

    @Test
    public void testRegistry() {
        Registry registry = Registry.getInstance();

        assertNotNull(registry);

        registry.put("testvar", "testvalue");

        String var = registry.get("testvar");

        assertNotNull(var);
        assertEquals("testvalue", var);
    }
}
