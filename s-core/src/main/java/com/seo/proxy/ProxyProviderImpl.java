package com.seo.proxy;

import com.seo.proxy.cache.ProxyCache;
import com.seo.proxy.exception.ProxyNotAvailableException;
import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.ProxyService;
import com.seo.proxy.service.exception.ProxyServiceNotAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;

@Named
public class ProxyProviderImpl implements ProxyProvider{
    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyProviderImpl.class);

    @Inject
    @Named("httpProxyService")
    private ProxyService proxyService;
    @Inject
    private ProxyCache proxyCache;

    @Override
    public Proxy getProxy(String destination, ProxyType proxyType) throws ProxyNotAvailableException {
        Proxy resultProxy = proxyCache.getProxy(destination, proxyType);

        if(resultProxy == null) {
            try {
                resultProxy = proxyService.getProxy(proxyType);
            } catch (ProxyServiceNotAvailableException e) {
                LOGGER.error("proxy service not available: " + e.getMessage());

                throw new ProxyNotAvailableException("proxy service not available: " + e.getMessage());
            }

            proxyCache.putProxy(destination, resultProxy);
        }

        return resultProxy;
    }
}
