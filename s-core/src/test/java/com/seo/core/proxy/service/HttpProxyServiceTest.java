package com.seo.core.proxy.service;

import com.seo.AbstractCoreTest;
import com.seo.proxy.exception.ProxyNotAvailableException;
import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.exception.ProxyServiceNotAvailableException;
import com.seo.proxy.service.http.HttpProxyService;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HttpProxyServiceTest extends AbstractCoreTest {

    private static final String TEST_RESPONSE = "192.168.1.1:3128\r\n" +
            "192.168.1.2:3128\r\n" +
            "192.168.1.3:3128\r\n" +
            "192.168.1.4:3128\r\n" +
            "192.168.1.5:3128\r\n" +
            "192.168.1.6:3128\r\n" +
            "192.168.1.7:3128\r\n" +
            "192.168.1.8:3128\r\n" +
            "192.168.1.9:3128\r\n" +
            "192.168.1.10:3128\r\n";


    @Inject
    private HttpProxyService httpProxyService;

    @Mock
    private HttpClient httpClient;

    @Test
    public void testHttpProxyService() throws ProxyServiceNotAvailableException, IOException {
        MockitoAnnotations.initMocks(this);

        BasicHttpResponse value = new BasicHttpResponse(new BasicStatusLine(new HttpVersion(1, 1), 200, null));
        value.setEntity(new StringEntity(TEST_RESPONSE));
        when(httpClient.execute(any(HttpGet.class))).thenReturn(value);

        httpProxyService.setHttpClientFactory(
                new HttpProxyService.HttpClientFactory() {
                    @Override
                    public HttpClient createHttpClient() {
                        return httpClient;
                    }
                });

        for(int i=0; i<10; i++) {
            Proxy proxy = httpProxyService.getProxy(ProxyType.SOCKS);

            assertNotNull(proxy);
            assertNotNull(proxy.getHost());
            assertNotNull(proxy.getPort());
        }

        verify(httpClient).execute(any(HttpGet.class));
    }
}
