package com.seo.webclient;

import com.seo.proxy.model.Proxy;
import com.seo.webclient.factory.RequestFactory;
import com.seo.webclient.model.EncodedImage;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import com.seo.webclient.mods.CustomHttpClient;
import com.seo.webclient.response.ResponseParser;
import com.seo.webclient.util.EncoderUtil;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebClientImpl implements WebClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(WebClient.class);

    private final static int DEFAULT_SO_TIMEOUT = 60 * 1000;

    private HttpClient client;

    private HttpContext localContext;
    private CookieStore cookieStore = new BasicCookieStore();


    private WebClientImpl() {
        initClient();
    }

    private void initClient() {
        client = new CustomHttpClient();
        client.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
//        client.getParams().setParameter(ClientPNames.CONNECTION_MANAGER_FACTORY_CLASS_NAME, "org.apache.http.impl.conn.tsccm.");
        client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, DEFAULT_SO_TIMEOUT);
        client.getParams().setBooleanParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, Boolean.TRUE);

        localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        CookieSpec cookieSpec = new BrowserCompatSpec();
        client.getParams().setParameter(ClientPNames.COOKIE_POLICY, cookieSpec.toString());
    }

    public void updateUserAgent(String userAgent) {
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, userAgent);
    }

    public void updateProxy(Proxy proxy) {
        HttpHost proxyHost = new HttpHost(proxy.getHost(), proxy.getPort());

        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);
    }

    public Response retrievePage(Request request) {
        try {
            HttpUriRequest httpUriRequest = RequestFactory.createRequest(request);

            if (request.getPostParameters() != null) {
                assert httpUriRequest instanceof HttpPost;

                initPostParameters(request.getPostParameters(), (HttpPost) httpUriRequest);
            }

            HttpResponse httpResponse = client.execute(httpUriRequest, localContext);

            return ResponseParser.parseResponse(httpResponse);

        } catch (IOException e) {
            LOGGER.error("IO error: " + e.getMessage());

            throw new IllegalStateException("IO error: " + e.getMessage());
        }
    }

    private void initPostParameters(Map<String, String> postParameters, HttpPost request) throws UnsupportedEncodingException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : postParameters.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");

        request.setEntity(entity);
    }

    public static WebClient newInstance() {
        return new WebClientImpl();
    }

    public EncodedImage retrieveImage(Request request){
        HttpGet imageget = new HttpGet(request.getUrl());

        EncodedImage resultImage = null;
        try {
            HttpResponse imageResponse = client.execute(imageget, localContext);
            resultImage = new EncodedImage(
                    EncoderUtil.encodeBase64(EntityUtils.toByteArray(imageResponse.getEntity()))
            );
        } catch (IOException e) {
            LOGGER.error("IOerror: " + e.getMessage());

            throw new IllegalStateException("IOerror: " + e.getMessage());
        }

        return resultImage;
    }

    public String retrieveRecaptchaChallenge(Request request) {
        try {
            HttpGet recaptchaget = new HttpGet(request.getUrl());

            HttpResponse recaptchaResponse = client.execute(recaptchaget, localContext);
            String responseString = EntityUtils.toString(recaptchaResponse.getEntity());
            Matcher m = Pattern.compile("challenge : '(.+)'").matcher(responseString);

            String challenge = null;
            if (m.find()) {
                challenge = m.group(1);
            }

            return challenge;
        } catch (IOException e) {
            LOGGER.error("IOException: " + e);
        }
        return null;
    }

}
