package com.seo.check.index.model;

import java.util.HashMap;
import java.util.Map;

public class IndexCheckResponse {
    private Map<String, Integer> index = new HashMap<String, Integer>();

    public void addResult(String key, Integer value) {
        index.put(key, value);
    }

    public Map<String, Integer> getResult() {
        return index;
    }
}
