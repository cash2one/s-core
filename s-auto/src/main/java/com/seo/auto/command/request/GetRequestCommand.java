package com.seo.auto.command.request;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.Command;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds and executes HTTP GET request.
 */
public class GetRequestCommand extends AbstractRequestCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(GetRequestCommand.class);

    private final static String NAME = "Get Request Command";
    private final static String METHOD = "get";

    @Override
    public void execute(CommandClient commandClient) {
        LOGGER.debug("executing get request command");

        initializeVariables(commandClient.getRegistry());

        Request request = createRequest();
        Response response = commandClient.getWebClient().retrievePage(request);

        test(response, commandClient.getRegistry());
        extract(response, commandClient.getRegistry());
    }

    @Override
    protected void initializeVariables(Registry registry) {
        initializeRequestVariables(registry);
    }

    @Override
    protected Request createRequest() {
        return new Request(
                getUrl(),
                METHOD,
                getReferer(),
                null
        );
    }

    @Override
    public String toString() {
        return String.format("%s @ url=%s", NAME, getUrl());
    }
}
