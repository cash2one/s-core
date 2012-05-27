package com.seo.useragent.parser;

import com.seo.useragent.store.UserAgentStore;
import com.seo.webclient.WebClient;
import com.seo.webclient.WebClientImpl;
import com.seo.webclient.factory.RequestFactory;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UASParserImpl implements UserAgentParser{
    private final static Logger LOGGER = LoggerFactory.getLogger(UASParserImpl.class);
    private final static WebClient CLIENT = WebClientImpl.newInstance();
    private final static HtmlCleaner CLEANER = new HtmlCleaner();

    private UserAgentStore userAgentStore;

    public void setUserAgentStore(UserAgentStore userAgentStore) {
        this.userAgentStore = userAgentStore;
    }

    private final static String URL = "http://www.useragentstring.com/pages/";

    @Override
    public void parse(String key) {
        Request request = new Request(URL + key, RequestFactory.GET_METHOD, null, null);
        Response response = CLIENT.retrievePage(request);

        TagNode node = null;
        try {
            node = CLEANER.clean(response.getContent());
            Object[] values = node.evaluateXPath("//ul/li/a");

            for (Object value : values) {
                if(value instanceof TagNode) {
                    TagNode link = (TagNode) value;

                    String userAgent = link.getText().toString();
                    if(LOGGER.isDebugEnabled()) {
                        LOGGER.debug("saving user agent: " + userAgent);
                    }

                    userAgentStore.save(userAgent);
                }
            }

        } catch (IOException e) {
            LOGGER.error("I/O error: " + e.getMessage());

            throw new RuntimeException("I/O error: " + e.getMessage());
        } catch (XPatherException e) {
            LOGGER.error("XPath error: " + e.getMessage());

            throw new RuntimeException("XPath error: " + e.getMessage());
        }
    }
}
