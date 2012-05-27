package com.seo.auto.command.mods.test.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.test.TestCommand;
import com.seo.auto.command.mods.test.exception.TestFailedException;
import com.seo.webclient.model.Response;

import java.util.Map;

/**
 * Checks for header existence in http response and checks its value.
 */
public class HeaderTestCommand extends AbstractTestCommand implements TestCommand {
    private String header;
    private String value;

    public void setHeader(String header) {
        this.header = header;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean test(Response response, Registry registry) {
        boolean result = false;
        boolean headerNotExists = false;

        Map<String,String> headers = response.getHeaders();
        if(headers.get(header) == null) {
            headerNotExists = true;
        } else{
            headerNotExists = false;

            if(headers.get(header).equals(value)) {
                result = true;
            }
        }

        if(headerNotExists) {
            throw new TestFailedException("no such header in response: " + header + ":" + value);
        }

        return expected.equals(result);
    }

}
