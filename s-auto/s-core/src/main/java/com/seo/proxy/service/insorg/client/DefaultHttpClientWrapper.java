package com.seo.proxy.service.insorg.client;

import com.seo.proxy.service.insorg.client.exception.HttpClientErrorException;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DefaultHttpClientWrapper{
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultHttpClientWrapper.class);

    private final static String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; ru; rv:1.9.1.7) Gecko/20091221 Firefox/3.5.7";

    private AbstractHttpClient client;
    private HttpContext localContext;

    public DefaultHttpClientWrapper() {
        client = new DefaultHttpClient(new ThreadSafeClientConnManager(new BasicHttpParams(), createSchemeRegistry()), null);

        initClient();
    }

    public HttpResponse execute(HttpUriRequest request) throws HttpClientErrorException {

        try {
            return client.execute(request, localContext);
        } catch (IOException e) {
            LOGGER.error("I/O error: " + e.getMessage());

            throw new HttpClientErrorException("I/O error: " + e.getMessage());
        }
    }


    private static SchemeRegistry createSchemeRegistry() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();

        schemeRegistry.register(
                new Scheme("http", PlainSocketFactory.getSocketFactory(), 80)
        );

        schemeRegistry.register(
                new Scheme("https", SSLSocketFactory.getSocketFactory(), 443)
        );

        return schemeRegistry;
    }

    private void initClient() {
        CookieStore cookieStore = new BasicCookieStore();

        client.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);

        localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

        CookieSpec cookieSpec = new BrowserCompatSpec();
        client.getParams().setParameter(ClientPNames.COOKIE_POLICY, cookieSpec.toString());

        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, USER_AGENT);        
    }
}
