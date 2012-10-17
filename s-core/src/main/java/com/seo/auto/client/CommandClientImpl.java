package com.seo.auto.client;

import com.seo.auto.client.registry.Registry;
import com.seo.email.EmailClient;
import com.seo.email.EmailClientImpl;
import com.seo.webclient.WebClient;
import com.seo.webclient.WebClientImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandClientImpl implements CommandClient{
    private WebClient webClient;
    private EmailClient emailClient;
    private Registry registry;
    private List<String> messages;

    public WebClient getWebClient() {
        return webClient;
    }

    public EmailClient getEmailClient() {
        return emailClient;
    }

    public Registry getRegistry() {
        return registry;
    }

    @Override
    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return Collections.synchronizedList(messages);
    }

    private CommandClientImpl() {
        this.registry = Registry.getInstance();
        this.emailClient = EmailClientImpl.newInstance();
        this.webClient = WebClientImpl.newInstance();
        this.messages = new ArrayList<String>();
    }

    public static CommandClient newInstance() {
        return new CommandClientImpl();
    }
}
