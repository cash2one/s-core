package com.seo.auto.command;

import com.seo.auto.client.registry.Registry;
import com.seo.provider.manager.ProviderManager;

public abstract class AbstractCommand extends PlaceholderSupport implements Command{
    private ProviderManager providerManager;

    @Override
    public void initCommand(ProviderManager providerManager) {
        this.providerManager = providerManager;
    }

    protected ProviderManager getProviderManager() {
        return providerManager;
    }

    /**
     * Initializes variables by substituting placeholder with values from registry
     * @param registry application registry to take variable values from
     */
    protected abstract void initializeVariables(Registry registry);
    public abstract String toString();
}
