package com.seo.auto.facade;

import com.seo.auto.command.CommandProcessor;
import com.seo.auto.model.Project;
import com.seo.auto.model.exception.ProjectFailedException;
import com.seo.auto.parser.ProjectParser;
import com.seo.auto.parser.exception.ConfigErrorException;
import com.seo.message.MessageListener;
import com.seo.message.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ConfigFacadeImpl implements ConfigFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigFacadeImpl.class);

    private final static int MAX_ATTEMPT_COUNT = 10;

    private ProjectParser projectParser;
    private MessageListener messageListener;

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void setProjectParser(ProjectParser projectParser) {
        this.projectParser = projectParser;
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

        CommandProcessor commandProcessor = createCommandProcessor();
        commandProcessor.setMessageListener(messageListener);

        int attemptCount = 0;
        while (attemptCount < MAX_ATTEMPT_COUNT) {
            messageListener.addMessage(new Message("starting execution, attempt=" + attemptCount));

            try {
                commandProcessor.process(project);

                break;
            } catch (ProjectFailedException e) {
                LOGGER.error("project failed: {}. repeating for the {} time", e.getMessage(), attemptCount);

                attemptCount++;
            }
        }

        if(attemptCount >= MAX_ATTEMPT_COUNT) {
            LOGGER.debug("exceed max attempt count");

            throw new RuntimeException("exceeded attempt count");
        }
    }

    protected abstract CommandProcessor createCommandProcessor();
}