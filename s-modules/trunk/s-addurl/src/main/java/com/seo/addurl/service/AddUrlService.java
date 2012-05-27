package com.seo.addurl.service;

public interface AddUrlService {
    void postUrl(String url) throws AddUrlFailedException;
}
