package com.seo.proxy.service;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.exception.ProxyServiceNotAvailableException;

public interface ProxyService {
    /**
     * Fetches and returns proxy from  service
     * @param proxyType {@link com.seo.proxy.model.ProxyType} to load from service
     * @return proxy server
     * @throws com.seo.proxy.service.exception.ProxyServiceNotAvailableException thrown if proxy service is not available
     */
    Proxy getProxy(ProxyType proxyType) throws ProxyServiceNotAvailableException;
}
