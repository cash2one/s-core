package com.seo.core.web.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.proxy.cache.ProxyCache;
import com.seo.proxy.model.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

public class ViewCachedProxies extends ActionSupport {
    private final static Logger LOGGER = LoggerFactory.getLogger(ViewCachedProxies.class);

    private ProxyCache proxyCache;

    public void setProxyCache(ProxyCache proxyCache) {
        this.proxyCache = proxyCache;
    }

    private Map<Proxy, Set<String>> proxyMap;

    public Map<Proxy, Set<String>> getProxyMap() {
        return proxyMap;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing view cached proxies action");

        this.proxyMap = proxyCache.getProxyMap();

        return SUCCESS;
    }
}
