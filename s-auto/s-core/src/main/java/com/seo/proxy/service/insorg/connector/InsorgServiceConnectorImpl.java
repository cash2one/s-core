package com.seo.proxy.service.insorg.connector;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.insorg.client.DefaultHttpClientWrapper;
import com.seo.proxy.service.insorg.client.exception.HttpClientErrorException;
import com.seo.proxy.service.insorg.connector.exception.InsorgConnectorException;
import com.seo.proxy.service.insorg.model.Credentials;
import com.seo.proxy.service.insorg.model.LoginResponseType;
import com.seo.proxy.service.insorg.request.RequestFactory;
import com.seo.proxy.service.insorg.response.ResponseParser;
import com.seo.proxy.service.insorg.response.exception.InvalidResponseException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class InsorgServiceConnectorImpl implements InsorgProxyConnector{
    private final static Integer INITIAL_PAGE = 1;

    private final static Logger LOGGER = LoggerFactory.getLogger(InsorgServiceConnectorImpl.class);

    private DefaultHttpClientWrapper defaultHttpClientWrapper;
    private ResponseParser responseParser;
    private RequestFactory requestFactory;

    private Integer pageNumber = INITIAL_PAGE;

    public void setResponseParser(ResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public void setRequestFactory(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public void setDefaultHttpClientWrapper(DefaultHttpClientWrapper defaultHttpClientWrapper) {
        this.defaultHttpClientWrapper = defaultHttpClientWrapper;
    }

    @Override
    public LoginResponseType login(Credentials credentials) throws InsorgConnectorException {
        HttpUriRequest loginRequest = requestFactory.createLoginRequest(credentials);

        HttpResponse loginResponse;
        try {
            loginResponse = defaultHttpClientWrapper.execute(loginRequest);
        } catch (HttpClientErrorException e) {
            LOGGER.error("http client error: " + e.getMessage());

            throw new InsorgConnectorException("http client error: " + e.getMessage());
        }

        return responseParser.parseLoginResponse(loginResponse);
    }

    @Override
    public Collection<String> fetchProxyIds() throws InsorgConnectorException{
        HttpUriRequest fetchPageRequest = requestFactory.createGetPageRequest(pageNumber);

        HttpResponse fetchPageResponse;
        try {
            fetchPageResponse = defaultHttpClientWrapper.execute(fetchPageRequest);
        } catch (HttpClientErrorException e) {
            LOGGER.error("http client error: " + e.getMessage());

            //reset page number
            pageNumber = INITIAL_PAGE;

            throw new InsorgConnectorException("http client error: " + e.getMessage());
        }

        //increment page number
        pageNumber++;

        try {
            return responseParser.parseGetPageResponse(fetchPageResponse);
        } catch (InvalidResponseException e) {
            LOGGER.error("invalid response: " + e.getMessage());

            throw new InsorgConnectorException("invalid fetch ids response: " + e.getMessage());
        }
    }

    @Override
    public Proxy fetchProxy(String proxyId, ProxyType proxyType) throws InsorgConnectorException{
        HttpUriRequest fetchProxyRequest = requestFactory.createGetProxyRequest(proxyId);

        HttpResponse fetchProxyResponse;
        try {
            fetchProxyResponse = defaultHttpClientWrapper.execute(fetchProxyRequest);
        } catch (HttpClientErrorException e) {
            LOGGER.error("http client error: " + e.getMessage());

            throw new InsorgConnectorException("http client error: " + e.getMessage());
        }

        try {
            return responseParser.parseGetProxyResponse(fetchProxyResponse, proxyType);
        } catch (InvalidResponseException e) {
            LOGGER.error("invalid response: " + e.getMessage());

            throw new InsorgConnectorException("invalid fetch proxy response: " + e.getMessage());
        }
    }
}
