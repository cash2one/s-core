package com.seo.auto.command.result;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.provider.BlogAccountProvider;

/**
 * Creates blog account
 */
public class SaveBlogAccountCommand extends AbstractCommand implements Command {
    private String type;
    private String url;
    private String xmlrpc;
    private String login;
    private String password;

    private final static String NAME = "Save Blog Account Command";

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setXmlrpc(String xmlrpc) {
        this.xmlrpc = xmlrpc;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        type = evaluatePlaceholder(type, registry);
        url = evaluatePlaceholder(url, registry);
        xmlrpc = evaluatePlaceholder(xmlrpc, registry);
        login = evaluatePlaceholder(login, registry);
        password = evaluatePlaceholder(password, registry);
    }

    @Override
    public void execute(CommandClient commandClient) {
        initializeVariables(commandClient.getRegistry());

        BlogAccountProvider blogAccountProvider = getProviderManager().getBlogAccountProvider();
        blogAccountProvider.createAccount(type, url, xmlrpc, login, password);
    }

    @Override
    public String toString() {
        return String.format("%s @ login=%s, type=%s", NAME, login, type);
    }
}
