package com.seo.auto.command.useragent;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.useragent.UserAgentProvider;
import com.seo.webclient.WebClient;

/**
 * Loads user agent from database and sets it into web client
 */
public class LoadUserAgentCommand extends AbstractCommand implements Command {

    private final static String NAME = "Load Useragent Command";

    @Override
    protected void initializeVariables(Registry registry) {

    }

    @Override
    public void execute(CommandClient commandClient) {
        initializeVariables(commandClient.getRegistry());

        UserAgentProvider userAgentProvider = getProviderManager().getUserAgentProvider();
        String userAgent = userAgentProvider.getRandomUserAgent();

        WebClient webClient = commandClient.getWebClient();
        webClient.updateUserAgent(userAgent);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
