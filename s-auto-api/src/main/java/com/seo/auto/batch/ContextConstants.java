package com.seo.auto.batch;

public enum ContextConstants {
    AUTO_CONFIG("com.seo.auto.AutoConfig");

    private String value;

    ContextConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
