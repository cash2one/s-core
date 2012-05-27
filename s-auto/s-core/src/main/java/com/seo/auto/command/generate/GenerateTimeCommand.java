package com.seo.auto.command.generate;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateTimeCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(GenerateTimeCommand.class);

    private final static String NAME = "Generate Time Command";

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        this.name = evaluatePlaceholder(name, registry);
    }

    @Override
    public void execute(CommandClient commandClient) throws CommandExecutionFailedException {
        Registry registry = commandClient.getRegistry();
        initializeVariables(registry);

        long time = System.currentTimeMillis();
        LOGGER.debug("executing generate time command to var: {}={}", name, time);
        
        registry.put(name, String.valueOf(time));
    }

    @Override
    public String toString() {
        return String.format("%s @ name=%s", NAME, name);
    }
}
