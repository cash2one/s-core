package com.seo.webclient.factory;

import com.seo.webclient.model.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestFactory {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestFactory.class);

    public final static String POST_METHOD = "post";
    public final static String GET_METHOD = "get";

    public static HttpUriRequest createRequest(Request request) {
        assert request != null;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("creating request to: " + request.getUrl());
        }

        HttpUriRequest httpUriRequest;
        if (GET_METHOD.equals(request.getMethod())) {
            httpUriRequest = new HttpGet(request.getUrl());
        } else if (POST_METHOD.equals(request.getMethod())) {
            httpUriRequest = new HttpPost(request.getUrl());
        } else {
            throw new IllegalArgumentException("Unsupported http method");
        }

        if(request.getReferrer() != null ) {
            httpUriRequest.setHeader("Referer", request.getReferrer());
        }

        return httpUriRequest;
    }
}
