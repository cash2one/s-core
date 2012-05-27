package com.seo.proxy.service.insorg.request;

import com.seo.proxy.service.insorg.model.Credentials;
import org.apache.http.client.methods.HttpUriRequest;

public interface RequestFactory {
    HttpUriRequest createLoginRequest(Credentials credentials);
    HttpUriRequest createGetPageRequest(Integer pageNumber);
    HttpUriRequest createGetProxyRequest(String proxyId);
}
