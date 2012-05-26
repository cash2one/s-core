package com.seo.auto.command.load;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.provider.EmailProvider;
import com.seo.provider.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads email from database and puts it into registry. Takes prefix argument and add creates three variables
 * with this prefix.
 * 1. prefixlogin - email login
 * 2. prefixpassword - email password
 * 3. prefixhost - email host
 */
public class LoadEmailCommand extends AbstractCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadEmailCommand.class);

    private final static String NAME = "Load Email Command";

    private String prefix;
    private String host;

    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String HOST = "host";

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        prefix = evaluatePlaceholder(prefix, registry);
        host = evaluatePlaceholder(host, registry);
    }

    @Override
    public void execute(CommandClient commandClient) {
        Registry registry = commandClient.getRegistry();

        initializeVariables(registry);

        EmailProvider emailProvider = getProviderManager().getEmailProvider();

        Email email = host != null
                ? emailProvider.getRandomByHost(host)
                : emailProvider.getRandomEmail();

        registry.put(prefix, email.getEmail());
        registry.put(prefix + LOGIN, email.getUser());
        registry.put(prefix + PASSWORD, email.getPassword());
        registry.put(prefix + HOST, email.getHost());

        LOGGER.info("loaded email " + email.getEmail());
    }

    @Override
    public String toString() {
        return String.format("%s @ host=%s", NAME, host);
    }
}
