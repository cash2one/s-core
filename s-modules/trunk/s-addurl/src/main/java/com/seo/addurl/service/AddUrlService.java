package com.seo.addurl.service;

import com.seo.addurl.service.exception.AddUrlFailedException;

public interface AddUrlService {
    void postUrl(String url) throws AddUrlFailedException;
}
