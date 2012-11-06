package com.seo.proxy.service.http;

import com.seo.antigate.client.DefaultHttpClientWrapper;
import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.ProxyService;
import com.seo.proxy.service.exception.ProxyServiceNotAvailableException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

@Named
public class HttpProxyService implements ProxyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpProxyService.class);

    private static final String PROXY_LIST_URL = "http://66.232.118.26/proxies.php";
    public static final String PROXY_DELIMITER = ":";

    private final Set<Proxy> proxies = Collections.synchronizedSet(new HashSet<Proxy>());

    private HttpClientFactory httpClientFactory;

    @Override
    public Proxy getProxy(ProxyType proxyType) throws ProxyServiceNotAvailableException {
        LOGGER.debug("getting proxy, type: {}", proxyType);

        synchronized (proxies) {
            if (proxies.size() == 0) {
                    String content = loadProxyList();

                    parseProxyList(content);
            }

            Proxy proxy = proxies.iterator().next();
            proxies.remove(proxy);

            return proxy;
        }
    }

    private void parseProxyList(String content) throws ProxyServiceNotAvailableException {
        String[] lines = content.split("\n");

        for (String line : lines) {
            if(line.contains(PROXY_DELIMITER)) {
                LOGGER.debug("parsing proxy line: {}", line);
                String[] proxyEntry = line.replaceAll("\"", "").split(PROXY_DELIMITER);

                Proxy proxy = new Proxy(proxyEntry[0], Integer.valueOf(proxyEntry[1]), ProxyType.SOCKS);

                proxies.add(proxy);
            } else {
                throw new ProxyServiceNotAvailableException();
            }
        }
    }

    private String loadProxyList() {
        HttpClient httpClient = createHttpClient();

        HttpGet httpGet = new HttpGet(PROXY_LIST_URL);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);

            return EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            LOGGER.error("i/o exception:", e);

            throw new RuntimeException(e);
        }
    }

    private synchronized HttpClient createHttpClient() {
        if(httpClientFactory == null) {
            httpClientFactory = new HttpClientFactory() {
                @Override
                public HttpClient createHttpClient() {
                    return new DefaultHttpClient();
                }
            };
        }

        return httpClientFactory.createHttpClient();
    }

    public interface HttpClientFactory {
        HttpClient createHttpClient();
    }


    public synchronized void setHttpClientFactory(HttpClientFactory httpClientFactory) {
        this.httpClientFactory = httpClientFactory;
    }
}
