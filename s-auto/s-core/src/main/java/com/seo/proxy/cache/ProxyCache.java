package com.seo.proxy.cache;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;

import java.util.Map;
import java.util.Set;

public interface ProxyCache {
    Proxy getProxy(String key, ProxyType proxyType);
    void putProxy(String key, Proxy proxy);
    Map<Proxy, Set<String>> getProxyMap();
    void clear();
}
