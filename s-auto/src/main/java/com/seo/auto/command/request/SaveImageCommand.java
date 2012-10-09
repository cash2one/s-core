package com.seo.auto.command.request;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.webclient.WebClient;
import com.seo.webclient.model.EncodedImage;
import com.seo.webclient.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fetches image from provided url and saves its base64 hash into application registry
 */
public class SaveImageCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(SaveImageCommand.class);

    private final static String NAME = "Save Image Command";

    private String name;
    private String url;

    @Override
    protected void initializeVariables(Registry registry) {
        name = evaluatePlaceholder(name, registry);
        url = evaluatePlaceholder(url, registry);
    }

    @Override
    public void execute(CommandClient commandClient) {
        initializeVariables(commandClient.getRegistry());

        Request request = new Request();
        request.setUrl(url);

        WebClient webClient = commandClient.getWebClient();
        EncodedImage encodedImage = webClient.retrieveImage(request);

        LOGGER.debug("got image, saving to {}", name);

        Registry registry = commandClient.getRegistry();
        registry.put(name, encodedImage.getHash());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("%s @ url=%s", NAME, url);
    }
}
