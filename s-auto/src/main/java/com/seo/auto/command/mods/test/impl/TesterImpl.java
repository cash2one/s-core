package com.seo.auto.command.mods.test.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.test.TestCommand;
import com.seo.auto.command.mods.test.Tester;
import com.seo.auto.command.mods.test.exception.TestFailedException;
import com.seo.webclient.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TesterImpl implements Tester {
    private final static Logger logger = LoggerFactory.getLogger(TesterImpl.class);
    private Response response;
    private Registry registry;

    public TesterImpl(Response response, Registry registry) {
        this.response = response;
        this.registry = registry;
    }

    @Override
    public void test(TestCommand testCommand) {
        if(testCommand.test(response, registry)) {
            logger.info(testCommand.toString() + " test passed: " + ((AbstractTestCommand)testCommand).getMessage());
        } else {
            logger.info(testCommand.toString() + " test failed");
            throw new TestFailedException(testCommand.toString() + " test failed");
        }
    }
}
