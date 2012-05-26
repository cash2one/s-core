package com.seo.webclient;

import com.seo.proxy.model.Proxy;
import com.seo.webclient.model.EncodedImage;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;

public interface WebClient {
    void updateUserAgent(String userAgent);
    void updateProxy(Proxy proxy);
    Response retrievePage(Request request);
    EncodedImage retrieveImage(Request request);
    String retrieveRecaptchaChallenge(Request request);
}
