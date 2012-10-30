package com.seo.auto.client;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CommandClientTest {

    @Test
    public void testCommandClient() {
        CommandClient commandClient = CommandClientImpl.newInstance();

        assertNotNull(commandClient);
        assertNotNull(commandClient.getRegistry());
        assertNotNull(commandClient.getWebClient());
        assertNotNull(commandClient.getEmailClient());
    }
}
