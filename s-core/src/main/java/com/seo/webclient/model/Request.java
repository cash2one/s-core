package com.seo.webclient.model;

import java.util.Map;

public class Request {
    private final static String DEFAULT_METHOD = "get";

    private String url;
    private String method;
    private String referrer;
    private Map<String, String> postParameters;

    public Request(String url) {
        this.url = url;
        this.method = DEFAULT_METHOD;
        this.referrer = null;
        this.postParameters = null;
    }

    public Request() {
    }

    public Request(String url, String method, String referrer, Map<String, String> postParameters) {
        this.url = url;
        this.method = method;
        this.referrer = referrer;
        this.postParameters = postParameters;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getPostParameters() {
        return postParameters;
    }

    public void setPostParameters(Map<String, String> postParameters) {
        this.postParameters = postParameters;
    }
}
