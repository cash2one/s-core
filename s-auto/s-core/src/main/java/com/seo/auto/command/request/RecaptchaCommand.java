package com.seo.auto.command.request;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.captcha.CaptchaService;
import com.seo.captcha.exception.CaptchaProcessingFailedException;
import com.seo.webclient.WebClient;
import com.seo.webclient.model.EncodedImage;
import com.seo.webclient.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Guesses recaptcha
 */
public class RecaptchaCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(RecaptchaCommand.class);

    private final static String NAME = "Recaptcha Command";

    private String name;
    private String url;
    private String image;

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        name = evaluatePlaceholder(name, registry);
        url = evaluatePlaceholder(url, registry);
        image = evaluatePlaceholder(image, registry);
    }

    @Override
    public void execute(CommandClient commandClient) throws CommandExecutionFailedException {
        initializeVariables(commandClient.getRegistry());

        try {

            WebClient webClient = commandClient.getWebClient();

            Request request = new Request();
            request.setUrl(url);
            String challenge = webClient.retrieveRecaptchaChallenge(request);

            Registry registry = commandClient.getRegistry();
            LOGGER.info("found challenge: " + challenge);
            registry.put(name, challenge);

            CaptchaService captchaService = getProviderManager().getCaptchaService();
            String image = "https://api-secure.recaptcha.net/image?c=" + challenge;

            Request imageRequest = new Request();
            imageRequest.setUrl(image);
            EncodedImage encodedImage = webClient.retrieveImage(imageRequest);

            String code = captchaService.retrieve(encodedImage.getHash(), "jpg", true);
            LOGGER.info("found recaptcha:" + code);
            registry.put(this.image, code);
        } catch (CaptchaProcessingFailedException e) {
            LOGGER.error("captcha processing failed: " + e.getMessage());

            throw new CommandExecutionFailedException("captcha processing failed: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("%s @ name=%s", NAME, name);
    }
}
