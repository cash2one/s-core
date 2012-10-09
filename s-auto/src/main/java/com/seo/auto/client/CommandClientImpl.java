package com.seo.auto.client;

import com.seo.auto.client.registry.Registry;
import com.seo.email.EmailClient;
import com.seo.email.EmailClientImpl;
import com.seo.webclient.WebClient;
import com.seo.webclient.WebClientImpl;

public class CommandClientImpl implements CommandClient{
    private WebClient webClient;
    private EmailClient emailClient;
    private Registry registry;

    public WebClient getWebClient() {
        return webClient;
    }

    public EmailClient getEmailClient() {
        return emailClient;
    }

    public Registry getRegistry() {
        return registry;
    }

    private CommandClientImpl() {
        this.registry = Registry.getInstance();
        this.emailClient = EmailClientImpl.newInstance();
        this.webClient = WebClientImpl.newInstance();
    }

    public static CommandClient newInstance() {
        return new CommandClientImpl();
    }
}
