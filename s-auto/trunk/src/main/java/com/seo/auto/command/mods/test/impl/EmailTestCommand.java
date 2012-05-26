package com.seo.auto.command.mods.test.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.test.TestCommand;
import com.seo.email.EmailClient;
import com.seo.email.EmailClientImpl;
import com.seo.webclient.model.Response;

public class EmailTestCommand extends AbstractTestCommand implements TestCommand{
    private final static String NAME = "email";

    private String login;
    private String password;
    private String host;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean test(Response response, Registry registry) {
        initializeVariables(registry);

        EmailClient emailClient = EmailClientImpl.newInstance();
        boolean result = emailClient.checkEmail(login, password, host);

        return expected.equals(result);
    }

    @Override
    public String toString() {
        return NAME;
    }

    private void initializeVariables(Registry registry) {
        this.login = evaluatePlaceholder(login, registry);
        this.password = evaluatePlaceholder(password, registry);
        this.host = evaluatePlaceholder(host, registry);
    }
}
