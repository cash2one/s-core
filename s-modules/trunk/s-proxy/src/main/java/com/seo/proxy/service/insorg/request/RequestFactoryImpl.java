package com.seo.proxy.service.insorg.request;

import com.seo.proxy.service.insorg.model.Credentials;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RequestFactoryImpl implements RequestFactory {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestFactoryImpl.class);

    private final static String LOGIN_URL = "http://proxy.insorg.org/en/index.php";
    private final static String PROXY_URL = "http://proxy.insorg.org/en/index.php?page=";
    private final static String GET_URL = "http://proxy.insorg.org/en/index.php?action=get&id=";

    @Override
    public HttpUriRequest createLoginRequest(Credentials credentials) {
        HttpPost post = new HttpPost(LOGIN_URL);

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        formparams.add(new BasicNameValuePair("login", credentials.getLogin()));
        formparams.add(new BasicNameValuePair("password", credentials.getPassword()));
        formparams.add(new BasicNameValuePair("user_cfg", "-1808913486"));
        formparams.add(new BasicNameValuePair("enter", "+++Sign+In++"));

        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("unsupported encoding: " + e.getMessage());

            throw new RuntimeException("unsupported encoding: " + e.getMessage());
        }

        post.setEntity(entity);

        return post;
    }

    @Override
    public HttpUriRequest createGetPageRequest(Integer pageNumber) {
        HttpGet getPageRequest = new HttpGet(PROXY_URL + String.valueOf(pageNumber));

        return getPageRequest;
    }

    @Override
    public HttpUriRequest createGetProxyRequest(String proxyId) {
        HttpGet getProxyRequest = new HttpGet(GET_URL + proxyId);

        return getProxyRequest;
    }
}
