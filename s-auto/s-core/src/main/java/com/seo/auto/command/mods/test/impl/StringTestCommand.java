package com.seo.auto.command.mods.test.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.test.TestCommand;
import com.seo.webclient.model.Response;

/**
 * Checks if http response content contains provided string.
 */
public class StringTestCommand extends AbstractTestCommand implements TestCommand{
    private String value;
    private final static String NAME = "String";

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean test(Response response, Registry registry){
        String content = response.getContent();
        
        return expected.equals(content.contains(value));
    }

    @Override
    public String toString() {
        return NAME;
    }

}
