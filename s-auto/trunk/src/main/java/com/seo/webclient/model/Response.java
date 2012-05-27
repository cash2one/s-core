package com.seo.webclient.model;

import java.util.Map;

public class Response {
    private String content;
    private Map<String, String> headers;

    public Response() {
    }

    public Response(String content, Map<String, String> headers) {
        this.content = content;
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
