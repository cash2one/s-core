package com.seo.auto.command;

import com.seo.auto.client.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PlaceholderSupport {
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractCommand.class);

    private final static String EXTRACT_PLACEHOLDER_PATTERN = "(\\$\\{\\w+\\})";
    private final static String EXTRACT_VARIABLE_PATTERN = "\\$\\{(\\w+)\\}";

    protected String evaluatePlaceholder(String string, Registry registry) {
        if(string == null) {
            return null;
        }

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("evaluating placeholder for: " + string);
        }

        //extract placeholder
        Pattern placeholderPattern = Pattern.compile(EXTRACT_PLACEHOLDER_PATTERN);

        Matcher placeholderMatcher = placeholderPattern.matcher(string);
        if(placeholderMatcher.find()) {
            return substitutePlaceholder(string, registry, placeholderMatcher);
        } else {
            //no placeholder in string
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("skipping string: " + string);
            }

            return string;
        }
    }

    private String substitutePlaceholder(String string, Registry registry, Matcher placeholderMatcher) {
        Pattern variablePattern = Pattern.compile(EXTRACT_VARIABLE_PATTERN);

        String placeholder = placeholderMatcher.group(1);
        Matcher variableMatcher = variablePattern.matcher(placeholder);
        if(variableMatcher.find()) {
            String variable = variableMatcher.group(1);
            String value = registry.get(variable);

            String result = string.replace(placeholder, value);

            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("substituting '" + string + "' with '" + result + "'");
            }

            return result;
        } else {
            //should never happen
            LOGGER.error("placeholder does not match: " + string);

            throw new IllegalArgumentException("placeholder does not match: " + string);
        }
    }
}
