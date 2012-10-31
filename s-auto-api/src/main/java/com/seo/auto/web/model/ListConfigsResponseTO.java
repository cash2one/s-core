package com.seo.auto.web.model;

import com.seo.auto.web.model.enums.ResponseStatus;
import com.seo.core.model.AutoConfig;

import java.util.List;

public class ListConfigsResponseTO extends BaseResponseTO {
    private List<AutoConfig> configs;

    public ListConfigsResponseTO(ResponseStatus status, List<AutoConfig> configs) {
        super(status);
        this.configs = configs;
    }

    public List<AutoConfig> getConfigs() {
        return configs;
    }
}
