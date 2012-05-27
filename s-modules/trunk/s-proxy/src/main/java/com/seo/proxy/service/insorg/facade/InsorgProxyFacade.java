package com.seo.proxy.service.insorg.facade;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.insorg.facade.exception.InsorgServiceUnavailableException;

public interface InsorgProxyFacade {
    Proxy getProxy(ProxyType proxyType) throws InsorgServiceUnavailableException;
}
