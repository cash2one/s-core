package com.seo.proxy.cache;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ProxyCacheImpl implements ProxyCache{
    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyCacheImpl.class);

    private final static int PROXY_EXPIRE_TIMEOUT = 60 * 60 * 1000; //hour

    private Map<Proxy, Set<String>> proxies = new HashMap<Proxy, Set<String>>();

    @Override
    public synchronized Proxy getProxy(String key, ProxyType proxyType) {
        checkProxyExpiration();

        Proxy proxy = null;
        for (Map.Entry<Proxy, Set<String>> proxySetEntry : proxies.entrySet()) {
            if(!proxySetEntry.getValue().contains(key)) {
                proxy = proxySetEntry.getKey();

                break;
            }
        }

        if(proxy != null) {
            putProxy(key, proxy);
        }

        return proxy;
    }

    @Override
    public synchronized void putProxy(String key, Proxy proxy) {
        LOGGER.debug("adding proxy: {}", proxy);

        //create proxy
        if(!proxies.containsKey(proxy)) {
            proxies.put(proxy, new HashSet<String>());
        }

        //add key
        proxies.get(proxy).add(key);
    }

    @Override
    public Map<Proxy, Set<String>> getProxyMap() {
        return Collections.unmodifiableMap(proxies);
    }

    private void checkProxyExpiration() {
        List<Proxy> expiredProxies = new ArrayList<Proxy>();
        for (Proxy proxy : proxies.keySet()) {
            long dateDiff = new Date().getTime() - proxy.getCreationDate().getTime();
            if(PROXY_EXPIRE_TIMEOUT <= dateDiff) {
                LOGGER.debug("proxy {} expired, removing");

                expiredProxies.add(proxy);
            }
        }

        for (Proxy expiredProxy : expiredProxies) {
            proxies.remove(expiredProxy);
        }
    }

    @Override
    public synchronized void clear() {
        LOGGER.debug("clearing cache");

        this.proxies = new HashMap<Proxy, Set<String>>();
    }
}
