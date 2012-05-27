package com.seo.auto.command.email;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Deletes provided email from database
 */
public class DeleteEmailCommand extends AbstractCommand implements Command{
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteEmailCommand.class);

    private final static String NAME = "Delete Email Account Command";

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        email = evaluatePlaceholder(email, registry);
    }

    @Override
    public void execute(CommandClient commandClient) {
        initializeVariables(commandClient.getRegistry());
        
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("deleting email " + email);
        }
        getProviderManager().getEmailProvider().deleteByEmail(email);
    }

    @Override
    public String toString() {
        return String.format("%s @ email=%s", NAME, email);
    }
}
