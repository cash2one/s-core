package com.seo.auto.command.mods.extract.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.extract.ExtractCommand;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Extracts string by provided xpath expression
 */
public class XPathCommand implements ExtractCommand {
    private final static Logger LOGGER = LoggerFactory.getLogger(XPathCommand.class);

    private final static HtmlCleaner CLEANER = new HtmlCleaner();

    private String name;
    private String query;
    private String field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean extract(String source, Registry registry) {
        try {
            TagNode node = CLEANER.clean(source);

            Object[] objects = node.evaluateXPath(query);
            for (Object object : objects) {
                if(object instanceof TagNode) {
                    TagNode field = (TagNode) object;
                    String result = field.getAttributeByName(this.field);

                    registry.put(name, result);
                    LOGGER.info("parsed value: " + name + ":" + result);

                    return result != null;
                }
            }
        } catch (IOException e) {
            LOGGER.error("IOException: " + e.getMessage());

            throw new RuntimeException("cannot extract value");
        } catch (XPatherException e) {
            LOGGER.error("XPathException: " + e.getMessage());

            throw new RuntimeException("cannot extract value");
        }

        return false;
    }
}
