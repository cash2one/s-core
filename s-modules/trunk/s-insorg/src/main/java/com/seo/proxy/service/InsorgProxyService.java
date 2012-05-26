package com.seo.proxy.service;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.exception.ProxyServiceNotAvailableException;
import com.seo.proxy.service.insorg.facade.InsorgProxyFacade;
import com.seo.proxy.service.insorg.facade.exception.InsorgServiceUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsorgProxyService implements ProxyService{
    private final static Logger LOGGER = LoggerFactory.getLogger(InsorgProxyService.class);

    private InsorgProxyFacade insorgProxyFacade;

    public void setInsorgProxyFacade(InsorgProxyFacade insorgProxyFacade) {
        this.insorgProxyFacade = insorgProxyFacade;
    }

    @Override
    public Proxy getProxy(ProxyType proxyType) throws ProxyServiceNotAvailableException {
        try {
            return insorgProxyFacade.getProxy(proxyType);
        } catch (InsorgServiceUnavailableException e) {
            LOGGER.error("insorg service error: " + e.getMessage());

            throw new ProxyServiceNotAvailableException("insorg service error: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("unknown error: " + e.getMessage());

            throw new ProxyServiceNotAvailableException("unknown error: " + e.getMessage());
        }
    }
}
