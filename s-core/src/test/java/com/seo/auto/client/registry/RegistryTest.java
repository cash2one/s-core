package com.seo.auto.client.registry;

import static org.testng.Assert.assertEquals;

@org.testng.annotations.Test

public class RegistryTest {
    @org.testng.annotations.Test
    public void testRegistry() {
        Registry registry = Registry.getInstance();
        registry.put("testname", "testvalue");
        assertEquals("testvalue", registry.get("testname"));
    }
}
