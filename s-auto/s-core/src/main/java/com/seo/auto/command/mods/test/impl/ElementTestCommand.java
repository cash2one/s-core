package com.seo.auto.command.mods.test.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.test.TestCommand;
import com.seo.webclient.model.Response;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Test for element exists by provided attribute name and value
 * Used when checking for <i>div</i> existence, which means that some expected action happened.
 */
public class ElementTestCommand extends AbstractTestCommand implements TestCommand {
    private final static Logger LOGGER = LoggerFactory.getLogger(ElementTestCommand.class);

    private final static HtmlCleaner CLEANER = new HtmlCleaner();
    private final static String NAME = "Element";

    private String attribute;
    private String value;

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean test(Response response, Registry registry) {
        try {
            TagNode node = CLEANER.clean(response.getContent());

            return expected.equals(node.findElementByAttValue(attribute, value, true, false) != null);
        } catch (IOException e) {
            LOGGER.error("IOException: " + e);
            throw new RuntimeException("ioexception: " + e);
        }
    }

    @Override
    public String toString() {
        return NAME;
    }
}
