package com.seo.auto.command.request;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.Command;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds and executes HTTP POST request.
 */
public class PostRequestCommand extends AbstractRequestCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(PostRequestCommand.class);

    private final static String NAME = "Post Request Command";
    private final static String METHOD = "post";

    private List<PostParameter> postParameters = new ArrayList<PostParameter>();
    private Registry registry;

    @Override
    public void execute(CommandClient commandClient) {
        LOGGER.debug("executing post request command");

        initializeVariables(commandClient.getRegistry());
        
        this.registry = commandClient.getRegistry();
        Request request = createRequest();
        Response response = commandClient.getWebClient().retrievePage(request);

        test(response, commandClient.getRegistry());
        extract(response, commandClient.getRegistry());

    }

    @Override
    protected Request createRequest() {
        return new Request(
                getUrl(),
                METHOD,
                getReferer(),
                convertParametersToMap()
        );
    }

    private Map<String, String> convertParametersToMap() {
        Map<String, String> parameters = new HashMap<String, String>();

        for (PostParameter postParameter : postParameters) {
            parameters.put(postParameter.getName(), evaluatePlaceholder(postParameter.getValue(), registry));
        }

        return parameters;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        initializeRequestVariables(registry);
    }

    public void addPostParameter(PostParameter postParameter) {
        postParameters.add(postParameter);
    }

    public List<PostParameter> getPostParameters() {
        return postParameters;
    }

    @Override
    public String toString() {
        return String.format("%s @ url=%s", NAME, getUrl());
    }
}
