package com.seo.auto.command;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.model.Project;
import com.seo.auto.model.exception.ProjectFailedException;
import com.seo.message.MessageListener;
import com.seo.message.model.Message;
import com.seo.provider.manager.ProviderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Scope;

@Named
public class CommandProcessorImpl implements CommandProcessor{
    private final static Logger LOGGER = LoggerFactory.getLogger(CommandProcessorImpl.class);

    private ProviderManager providerManager;

    public void setProviderManager(ProviderManager providerManager) {
        this.providerManager = providerManager;
    }

    @Override
    public void process(Project project, CommandClient commandClient) throws ProjectFailedException{

        for (Command command : project.getCommands()) {
            command.initCommand(providerManager);

            try {
                command.execute(commandClient);

                commandClient.addMessage("executed command: " + command);
            } catch (Exception e) {
                LOGGER.error("command execution failed: {}", e.getMessage(), e);

                throw new ProjectFailedException(e);
            }            
        }
    }
}
