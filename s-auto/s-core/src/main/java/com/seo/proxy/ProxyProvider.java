package com.seo.proxy;

import com.seo.proxy.exception.ProxyNotAvailableException;
import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;

public interface ProxyProvider {
    /**
     * Fetches and provides proxy server for destination
     * @param destination used for choosing proxy which is not used before for provided destination
     * @return proxy server
     * @throws com.seo.proxy.exception.ProxyNotAvailableException thrown if error is occurred while getting proxy
     */
    Proxy getProxy(String destination, ProxyType proxyType) throws ProxyNotAvailableException;
}
