package com.seo.core.web.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.proxy.cache.ProxyCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClearProxyCache extends ActionSupport {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClearProxyCache.class);

    private ProxyCache proxyCache;

    public void setProxyCache(ProxyCache proxyCache) {
        this.proxyCache = proxyCache;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing clear proxy cache action");

        proxyCache.clear();

        return SUCCESS;
    }
}
