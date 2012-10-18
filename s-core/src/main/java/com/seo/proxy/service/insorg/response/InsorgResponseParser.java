package com.seo.proxy.service.insorg.response;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.insorg.model.LoginResponseType;
import com.seo.proxy.service.insorg.response.exception.InvalidResponseException;
import org.apache.http.HttpResponse;

import java.util.Collection;

public interface InsorgResponseParser {
    LoginResponseType parseLoginResponse(HttpResponse response);
    Collection<String> parseGetPageResponse(HttpResponse response) throws InvalidResponseException;
    Proxy parseGetProxyResponse(HttpResponse response, ProxyType proxyType) throws InvalidResponseException;
}
