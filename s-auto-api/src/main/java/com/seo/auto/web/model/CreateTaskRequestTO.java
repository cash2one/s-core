package com.seo.auto.web.model;

public class CreateTaskRequestTO {
    private Long autoConfigId;
    private Long times;

    public Long getAutoConfigId() {
        return autoConfigId;
    }

    public void setAutoConfigId(Long autoConfigId) {
        this.autoConfigId = autoConfigId;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }
}
