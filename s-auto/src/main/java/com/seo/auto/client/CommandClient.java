package com.seo.auto.client;

import com.seo.auto.client.registry.Registry;
import com.seo.email.EmailClient;
import com.seo.webclient.WebClient;

/**
 * Command client is container of session dependent objects which should differ
 * in different threads
 */
public interface CommandClient {
    /**
     * Getter for application registry
     * @return application registry
     */
    Registry getRegistry();

    /**
     * Getter for application email client
     * @return email client
     */
    EmailClient getEmailClient();

    /**
     * Getter for application web client
     * @return web client
     */
    WebClient getWebClient();
}
