package com.seo.auto.command.mods.extract.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.extract.ExtractCommand;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Extracts string by provided attribute name, its value and needed field
 */
public class AttValueExtractCommand implements ExtractCommand {
    private final static Logger LOGGER = LoggerFactory.getLogger(AttValueExtractCommand.class);

    private final static HtmlCleaner CLEANER = new HtmlCleaner();

    private String name;
    private String attribute;
    private String value;

    private String field;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean extract(String source, Registry registry) {
        TagNode node = CLEANER.clean(source);

        TagNode field = node.findElementByAttValue(attribute, value, true, false);
        if(field == null) {
            LOGGER.error("no such field: {}={}", attribute, value);

            throw new RuntimeException("no such field");
        }

        String result = field.getAttributeByName(this.field);
        registry.put(name, result);

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("parsed value: " + name + ":" + result);
        }

        return result != null;
    }
}
