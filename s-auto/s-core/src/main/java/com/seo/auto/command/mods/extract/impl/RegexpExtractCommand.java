package com.seo.auto.command.mods.extract.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.extract.ExtractCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracts string by provided regular expression
 */
public class RegexpExtractCommand implements ExtractCommand{
    private final static Logger LOGGER = LoggerFactory.getLogger(RegexpExtractCommand.class);

    private String name;
    private String pattern;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean extract(String source, Registry registry) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(source);

        if(matcher.find()) {
            String result = matcher.group(1);
            registry.put(name, result);
            LOGGER.info("parsed value: " + name + ":" + result);

            return result != null;
        }

        return false;
    }
}
