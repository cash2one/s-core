package com.seo.auto.command.generate;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.text.words.WordProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates string and puts it into application registry
 */
public class GenerateStringCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(GenerateStringCommand.class);

    private final static String NAME = "Generate String Command";

    private String name;
    private Integer minSize;
    private Integer maxSize;
    private Boolean appendDigits = false;

    public void setName(String name) {
        this.name = name;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public void setAppendDigits(Boolean appendDigits) {
        this.appendDigits = appendDigits;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        name = evaluatePlaceholder(name, registry);
    }

    @Override
    public void execute(CommandClient commandClient) throws CommandExecutionFailedException {
        Registry registry = commandClient.getRegistry();
        initializeVariables(registry);

        WordProvider wordProvider = getProviderManager().getWordProvider();
        String generatedString = appendDigits
                ? wordProvider.getRandomWordWithDigits(minSize, maxSize)
                : wordProvider.getRandomWord(minSize, maxSize);

        registry.put(name, generatedString);
    }

    @Override
    public String toString() {
        return String.format("%s @ name=%s", NAME, name);
    }
}
