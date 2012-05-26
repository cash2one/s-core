package com.seo.proxy.service.insorg.facade;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.insorg.connector.InsorgProxyConnector;
import com.seo.proxy.service.insorg.connector.exception.InsorgConnectorException;
import com.seo.proxy.service.insorg.facade.exception.InsorgServiceUnavailableException;
import com.seo.proxy.service.insorg.model.Credentials;
import com.seo.proxy.service.insorg.model.LoginResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class InsorgProxyFacadeImpl implements InsorgProxyFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(InsorgProxyFacadeImpl.class);

    private InsorgProxyConnector insorgProxyConnector;
    private Credentials credentials;

    private Set<String> pendingProxyIds;
    private Set<String> processedProxyIds;

    public InsorgProxyFacadeImpl() {
        pendingProxyIds = new HashSet<String>();
        processedProxyIds = new HashSet<String>();
    }

    public void setInsorgProxyConnector(InsorgProxyConnector insorgProxyConnector) {
        this.insorgProxyConnector = insorgProxyConnector;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public Proxy getProxy(ProxyType proxyType) throws InsorgServiceUnavailableException{
        //login
        try {
            LoginResponseType loginResponse = insorgProxyConnector.login(credentials);

            if(loginResponse == LoginResponseType.FAILED) {
                LOGGER.error("login failed");

                throw new InsorgServiceUnavailableException("login failed");
            } else if(loginResponse == LoginResponseType.SUCCESS) {
                LOGGER.info("login successful");
            }
        } catch (InsorgConnectorException e) {
            LOGGER.error("insorg connector error: " + e.getMessage());

            throw new InsorgServiceUnavailableException("insorg connector error: " + e.getMessage());
        }

        String proxyId = chooseProxyId();
        //while proxy id is not available
        while(proxyId == null) {
            //populate proxy ids
            try {
                populateProxyIds();
            } catch (InsorgConnectorException e) {
                LOGGER.error("insorg connector exception: " + e.getMessage());

                throw new InsorgServiceUnavailableException("insorg connector exception: " + e.getMessage());
            }

            //choose unprocessed one
            proxyId = chooseProxyId();
        }

        //get proxy by id
        Proxy resultProxy = null;
        try {
            resultProxy = insorgProxyConnector.fetchProxy(proxyId, proxyType);
        } catch (InsorgConnectorException e) {
                LOGGER.error("insorg connector exception: " + e.getMessage());

                throw new InsorgServiceUnavailableException("insorg connector exception: " + e.getMessage());
        }

        //return result
        return resultProxy;
    }

    private String chooseProxyId() {
        for (String pendingProxyId : pendingProxyIds) {
            if(!processedProxyIds.contains(pendingProxyId)) {
                LOGGER.debug("choosing proxy id: {}", pendingProxyId);

                processedProxyIds.add(pendingProxyId);

                return pendingProxyId;
            }
        }

        LOGGER.debug("no proxy available, returning null");

        return null;
    }

    private void populateProxyIds() throws InsorgConnectorException {
        pendingProxyIds.addAll(insorgProxyConnector.fetchProxyIds());
    }
}
