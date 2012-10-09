package com.seo.auto.command.mods.test;

import com.seo.auto.client.registry.Registry;
import com.seo.webclient.model.Response;

/**
 * Test command interface.
 */
public interface TestCommand {
    /**
     * Executes test command
     * @param response Http response to protect
     * @param registry
     * @return test execution result
     */
    boolean test(Response response, Registry registry);
}
