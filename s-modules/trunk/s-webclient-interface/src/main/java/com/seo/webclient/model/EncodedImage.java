package com.seo.webclient.model;

public class EncodedImage {
    private String hash;

    public EncodedImage(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
