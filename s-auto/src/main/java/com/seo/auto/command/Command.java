package com.seo.auto.command;

import com.seo.auto.client.CommandClient;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.provider.manager.ProviderManager;

/**
 * Command interface
 */
public interface Command {
    /**
     * Initializes command by setting provider manager. Must be executed before <i>execute</i> method
     * @param providerManager provider manager
     */
    void initCommand(ProviderManager providerManager);

    /**
     * Executes command
     * @param commandClient command client
     * @throws CommandExecutionFailedException thrown if error occurred during execution
     */
    void execute(CommandClient commandClient) throws CommandExecutionFailedException;
}
