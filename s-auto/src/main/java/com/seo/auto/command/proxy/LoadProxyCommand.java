package com.seo.auto.command.proxy;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.proxy.exception.ProxyNotAvailableException;
import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads proxy from provider and sets it into web client
 */
public class LoadProxyCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoadProxyCommand.class);

    private final static String NAME = "Load Proxy Command";

    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        key = evaluatePlaceholder(key, registry);
    }

    @Override
    public void execute(CommandClient commandClient) throws CommandExecutionFailedException {
        LOGGER.debug("loading proxy");

        initializeVariables(commandClient.getRegistry());

        Proxy proxy = null;
        try {
            proxy = getProviderManager().getProxyProvider().getProxy(key, ProxyType.HTTP);
        } catch (ProxyNotAvailableException e) {
            LOGGER.error("cannot load proxy: " + e.getMessage());

            throw new CommandExecutionFailedException("cannot load proxy: " + e.getMessage());
        }

        LOGGER.info("loaded proxy " + proxy.getHost() + ":" + proxy.getPort());

        commandClient.getWebClient().updateProxy(proxy);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
