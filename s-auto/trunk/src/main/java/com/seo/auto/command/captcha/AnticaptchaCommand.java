package com.seo.auto.command.captcha;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.captcha.exception.CaptchaProcessingFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command to retrieve captcha from service provider
 */
public class AnticaptchaCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(AnticaptchaCommand.class);

    private final static String NAME = "AntiCaptcha Command";

    private String image;
    private String ext;
    private boolean phrase;
    private String name;

    public void setImage(String image) {
        this.image = image;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setPhrase(boolean phrase) {
        this.phrase = phrase;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        image = evaluatePlaceholder(image, registry);
        ext = evaluatePlaceholder(ext, registry);
        name = evaluatePlaceholder(name, registry);
    }

    @Override
    public void execute(CommandClient commandClient) throws CommandExecutionFailedException{
        Registry registry = commandClient.getRegistry();
        initializeVariables(registry);

        try {
            String guessedCaptcha = getProviderManager().getCaptchaService().retrieve(image, ext, phrase);

            registry.put(name, guessedCaptcha);
        } catch (CaptchaProcessingFailedException e) {
            LOGGER.error("cannot process captcha: " + e);

            throw new CommandExecutionFailedException("cannot process captcha: " + e);
        }
    }

    @Override
    public String toString() {
        return String.format("%s @ name=%s", NAME, name);
    }
}
