package com.seo.proxy.service.insorg.connector;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.insorg.connector.exception.InsorgConnectorException;
import com.seo.proxy.service.insorg.model.Credentials;
import com.seo.proxy.service.insorg.model.LoginResponseType;

import java.util.Collection;

public interface InsorgProxyConnector {
    LoginResponseType login(Credentials credentials) throws InsorgConnectorException;
    Collection<String> fetchProxyIds() throws InsorgConnectorException;
    Proxy fetchProxy(String proxyId, ProxyType proxyType) throws InsorgConnectorException;
}
