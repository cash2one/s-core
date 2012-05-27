package com.seo.auto.client.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * Application registry which contains all used in commands variables
 */
public class Registry {
    private Map<String, String> vars = new HashMap<String, String>();

    /**
     * Puts provided value into system registry
     * @param name variable key
     * @param value variable value
     */
    public void put(String name, String value) {
        vars.put(name, value);
    }

    /**
     * Gets variable from application registry by key
     * @param name variable name
     * @return variable value or null
     */
    public String get(String name) {
        return vars.get(name);
    }

    /**
     * Default factory method
     * @return new registry instance
     */
    public static Registry getInstance() {
        return new Registry();
    }
}
