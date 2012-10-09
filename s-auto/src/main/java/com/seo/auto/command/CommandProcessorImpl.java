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

public class CommandProcessorImpl implements CommandProcessor{
    private final static Logger LOGGER = LoggerFactory.getLogger(CommandProcessorImpl.class);

    private CommandClient commandClient;
    private ProviderManager providerManager;
    private MessageListener messageListener;

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void setProviderManager(ProviderManager providerManager) {
        this.providerManager = providerManager;
    }

    @Override
    public void process(Project project) throws ProjectFailedException{
        this.commandClient = CommandClientImpl.newInstance();

        for (Command command : project.getCommands()) {
            command.initCommand(providerManager);

            try {
                command.execute(commandClient);
                messageListener.addMessage(new Message("executed command: " + command));
            } catch (Exception e) {
                LOGGER.error("command execution failed: {} {}", e.getMessage(), e.getStackTrace());

                throw new ProjectFailedException("command execution failed: " + e.getMessage());
            }            
        }
    }
}
