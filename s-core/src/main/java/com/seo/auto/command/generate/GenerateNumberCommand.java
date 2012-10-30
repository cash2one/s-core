package com.seo.auto.command.generate;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.auto.command.exception.CommandExecutionFailedException;

import java.util.Random;

public class GenerateNumberCommand extends AbstractCommand implements Command{
    private final static String NAME = "Generate Number Command";

    private String name;
    private Integer min;
    private Integer max;

    public void setName(String name) {
        this.name = name;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        this.name = evaluatePlaceholder(name, registry);
    }

    @Override
    public void execute(CommandClient commandClient) throws CommandExecutionFailedException {
        Registry registry = commandClient.getRegistry();
        initializeVariables(registry);

        Random random = new Random();

        int result = min + random.nextInt(max - min);

        registry.put(name, String.valueOf(result));
    }

    @Override
    public String toString() {
        return String.format("%s @ name=%s", NAME, name);
    }
}
