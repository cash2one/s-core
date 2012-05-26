package com.seo.check.index.service.yandex;

import com.seo.check.index.service.IndexCheckerService;
import com.seo.proxy.ProxyProvider;
import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.useragent.UserAgentProvider;
import com.seo.webclient.WebClient;
import com.seo.webclient.WebClientImpl;
import com.seo.webclient.factory.RequestFactory;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YandexIndexCheckerService implements IndexCheckerService {
    private final static Logger LOGGER = LoggerFactory.getLogger(YandexIndexCheckerService.class);
    private final static HtmlCleaner CLEANER = new HtmlCleaner();

    private final static String PATTERN = "[^\\d]+([\\d]+)[^\\d]+";

    private final static String QUERY_URL = "http://yandex.ru/yandsearch?serverurl=";

    private ProxyProvider proxyProvider;
    private UserAgentProvider userAgentProvider;

    public void setProxyProvider(ProxyProvider proxyProvider) {
        this.proxyProvider = proxyProvider;
    }

    public void setUserAgentProvider(UserAgentProvider userAgentProvider) {
        this.userAgentProvider = userAgentProvider;
    }

    private final static String SERVICE_NAME = "yandex";

    private WebClient webClient;

    private void init() {
        webClient = WebClientImpl.newInstance();

        String userAgent = userAgentProvider.getRandomUserAgent();
        webClient.updateUserAgent(userAgent);

        try {
            Proxy proxy = proxyProvider.getProxy(SERVICE_NAME, ProxyType.HTTP);
            webClient.updateProxy(proxy);
        } catch (Exception e) {
            LOGGER.error("proxy not available: {}. continuing without proxy", e.getMessage());

//            throw new RuntimeException("proxy not available: " + e.getMessage());
        }
    }

    private Integer parseResponse(Response response) {
        TagNode node = null;
        try {
            node = CLEANER.clean(response.getContent());
        } catch (IOException e) {
            LOGGER.error("I/O error: {}", e.getMessage());

            throw new RuntimeException("I/O error: " + e.getMessage());
        }

        TagNode text = node.findElementByAttValue("class", "b-head-logo__text", true, false);

        Matcher matcher = Pattern.compile(PATTERN).matcher(text.getText().toString());
        int result = 0;
        if (matcher.find()) {
            String someNumberStr = matcher.group(1);
            result = Integer.parseInt(someNumberStr);
        }

        return result;
    }

    @Override
    public Integer checkIndex(String url) {
        init();
        Request request = new Request(
                QUERY_URL + url,
                RequestFactory.GET_METHOD,
                null,
                null
        );

        Response response = webClient.retrievePage(request);

        Integer result = parseResponse(response);
        LOGGER.info("Site {} has {} pages in YAP.", url, request);

        return result;
    }

    @Override
    public String getName() {
        return SERVICE_NAME;
    }
}
