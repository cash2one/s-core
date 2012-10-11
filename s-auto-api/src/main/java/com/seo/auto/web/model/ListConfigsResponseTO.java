package com.seo.auto.web.model;

import java.util.List;

public class ListConfigsResponseTO {
    private List<String> configs;

    public ListConfigsResponseTO(List<String> configs) {
        this.configs = configs;
    }

    public List<String> getConfigs() {
        return configs;
    }
}
