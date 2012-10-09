package com.seo.auto.command.result;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.provider.EmailProvider;

/**
 * Creates email command
 */
public class SaveEmailCommand extends AbstractCommand implements Command {
    private String email;
    private String host;
    private String user;
    private String password;
    private String type;

    private final static String NAME = "Save Email Account Command";

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        email = evaluatePlaceholder(email, registry);
        host = evaluatePlaceholder(host, registry);
        user = evaluatePlaceholder(user, registry);
        password = evaluatePlaceholder(password, registry);
        type = evaluatePlaceholder(type, registry);
    }

    @Override
    public void execute(CommandClient commandClient) {
        initializeVariables(commandClient.getRegistry());

        EmailProvider emailProvider = getProviderManager().getEmailProvider();

        emailProvider.createEmailAccount(email, host, user, password, type);
    }

    @Override
    public String toString() {
        return String.format("%s @ email=%s", NAME, email);
    }
}
