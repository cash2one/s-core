package com.seo.auto.facade;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.command.CommandProcessor;
import com.seo.auto.model.Project;
import com.seo.auto.model.exception.ProjectFailedException;
import com.seo.auto.parser.ProjectParser;
import com.seo.auto.parser.exception.ConfigErrorException;
import com.seo.message.MessageListener;
import com.seo.message.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ConfigFacadeImpl implements ConfigFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigFacadeImpl.class);

    private final static int MAX_ATTEMPT_COUNT = 10;

    @Inject
    private ProjectParser projectParser;

    @Inject
    private CommandProcessor commandProcessor;

    @Override
    public boolean validateConfig(String config) {
        try {
            projectParser.parseConfig(config);
        } catch (ConfigErrorException e) {
            LOGGER.debug("can't parse config: {}", config, e);

            return false;
        }

        return true;
    }

    @Override
    public void processConfig(String config) {
        Project project;

        try {
            project = projectParser.parseConfig(config);
        } catch (ConfigErrorException e) {
            LOGGER.error("config error exception: {}", e.getMessage());

            throw new RuntimeException("config error: " + e.getMessage());
        }

        CommandClient commandClient = CommandClientImpl.newInstance();

        int attemptCount = 0;
        while (attemptCount < MAX_ATTEMPT_COUNT) {
            commandClient.addMessage("starting execution, attempt=" + attemptCount);

            try {
                commandProcessor.process(project, commandClient);

                break;
            } catch (ProjectFailedException e) {
                LOGGER.error("project failed: {}. repeating for the {} time", new Object[]{e.getMessage(), attemptCount} , e);

                attemptCount++;
            }
        }

        if(attemptCount >= MAX_ATTEMPT_COUNT) {
            LOGGER.debug("exceed max attempt count");

            throw new RuntimeException("exceeded attempt count");
        }
    }
}
