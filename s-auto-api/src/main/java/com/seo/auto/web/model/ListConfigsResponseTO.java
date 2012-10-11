package com.seo.auto.web.model;

import com.seo.auto.web.model.enums.ResponseStatus;

import java.util.List;

public class ListConfigsResponseTO extends BaseResponseTO {
    private List<String> configs;

    public ListConfigsResponseTO(ResponseStatus status, List<String> configs) {
        super(status);
        this.configs = configs;
    }

    public List<String> getConfigs() {
        return configs;
    }
}
