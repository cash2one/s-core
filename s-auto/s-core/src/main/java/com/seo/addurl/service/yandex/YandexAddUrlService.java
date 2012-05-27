package com.seo.addurl.service.yandex;

import com.seo.addurl.service.AddUrlService;
import com.seo.addurl.service.exception.AddUrlFailedException;
import com.seo.captcha.CaptchaService;
import com.seo.proxy.ProxyProvider;
import com.seo.proxy.exception.ProxyNotAvailableException;
import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.useragent.UserAgentProvider;
import com.seo.webclient.WebClient;
import com.seo.webclient.WebClientImpl;
import com.seo.webclient.factory.RequestFactory;
import com.seo.webclient.model.EncodedImage;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class YandexAddUrlService implements AddUrlService {
    private final static Logger LOGGER = LoggerFactory.getLogger(YandexAddUrlService.class);
    private final static HtmlCleaner CLEANER = new HtmlCleaner();

    private static final String ADD_URL = "http://webmaster.yandex.ru/addurl.xml";
    private final static String YANDEX_ADDURL = "yandex_addurl";

    private WebClient client = WebClientImpl.newInstance();

    private CaptchaService captchaService;
    private ProxyProvider proxyProvider;
    private UserAgentProvider userAgentProvider;

    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    public void setProxyProvider(ProxyProvider proxyProvider) {
        this.proxyProvider = proxyProvider;
    }

    public void setUserAgentProvider(UserAgentProvider userAgentProvider) {
        this.userAgentProvider = userAgentProvider;
    }

    private void init() {
        client = WebClientImpl.newInstance();

        String userAgent = userAgentProvider.getRandomUserAgent();
        client.updateUserAgent(userAgent);

        try {
            Proxy proxy = proxyProvider.getProxy(YANDEX_ADDURL, ProxyType.HTTP);

            client.updateProxy(proxy);
        } catch (ProxyNotAvailableException e) {
            LOGGER.error("proxy not available: " + e.getMessage());

            throw new RuntimeException("proxy not available: " + e.getMessage());
        }
    }

    @Override
    public void postUrl(String url) throws AddUrlFailedException {
        init();
        
        try {
            Request request = new Request(ADD_URL, RequestFactory.GET_METHOD, null, null);
            Response addurlResponse = client.retrievePage(request);

            TagNode captchadiv = parseYandexAddurlPage(addurlResponse);

            TagNode keynode = captchadiv.findElementByAttValue("name", "key", true, false);
            String key = keynode.getAttributeByName("value");
            String image = captchadiv.findElementByName("img", true).getAttributeByName("src");

            EncodedImage imageBase64 = client.retrieveImage(new Request(image, RequestFactory.GET_METHOD, null, null));
            String result = captchaService.retrieve(imageBase64.getHash(), "jpg", false);

            Request postRequest = buildAddurlPostRequest(key, result);
            Response response = client.retrievePage(postRequest);

            parseAddurlResult(response);
        } catch (Exception e) {
            LOGGER.error("Error " + e);
        }
    }

    private void parseAddurlResult(Response response) throws IOException, URISyntaxException {
        TagNode res = CLEANER.clean(response.getContent());
        if (res.findElementByAttValue("class", "error-message", true, false) != null) {
            TagNode error = res.findElementByAttValue("class", "error-message", true, false);
            processAddurlError(error);

            LOGGER.error("Cannot add url: " + error.getText());
        } else {
            LOGGER.info("Added page");
        }
    }

    private void processAddurlError(TagNode error) throws URISyntaxException, IOException {
        String result = new String(error.getText());
/*
        if ("�� ������� ������� �������� ���.".equals(result)) {
            LOGGER.error("Reported invalid captcha.");
        } else if ("����� ������������ URL.".equals(result)) {
            LOGGER.error("Invalid url");
        } else if ("��������� URL ��� ���������������.".equals(result)) {
            LOGGER.error("URL already indexed");
        }
*/
        
        //todo: implemented error parsing
    }

    private Request buildAddurlPostRequest(String key, String result){
        Map<String, String> postParams = new HashMap<String, String>();
        postParams.put("url", ADD_URL);
        postParams.put("key", key);
        postParams.put("rep", result);
        postParams.put("do", "add");

        return new Request(
                ADD_URL,
                RequestFactory.POST_METHOD,
                ADD_URL,
                postParams
        );
    }

    private TagNode parseYandexAddurlPage(Response response) throws IOException {
        TagNode node = CLEANER.clean(response.getContent());

        return node.getElementsByAttValue("class", "captcha", true, false)[0];
    }
}
