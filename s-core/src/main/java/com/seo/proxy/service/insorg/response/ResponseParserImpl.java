package com.seo.proxy.service.insorg.response;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import com.seo.proxy.service.insorg.model.LoginResponseType;
import com.seo.proxy.service.insorg.response.exception.InvalidResponseException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named
public class ResponseParserImpl implements ResponseParser {
    private final static Integer SOCKS_PROXY_TD_NUMBER = 0;
    private final static Integer HTTP_PROXY_TD_NUMBER = 1;

    private final static Logger LOGGER = LoggerFactory.getLogger(ResponseParserImpl.class);
    private final static HtmlCleaner CLEANER = new HtmlCleaner();

    @Override
    public LoginResponseType parseLoginResponse(HttpResponse response) {
        try {
            String responseString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            LOGGER.error("IO error: " + e.getMessage());

            return LoginResponseType.FAILED;
        }

        return LoginResponseType.SUCCESS;
    }

    @Override
    public Collection<String> parseGetPageResponse(HttpResponse response) throws InvalidResponseException {
        TagNode node;
        try {
            String responseString = EntityUtils.toString(response.getEntity());
            node = CLEANER.clean(responseString);
        } catch (IOException e) {
            LOGGER.error("I/O error: " + e.getMessage());

            throw new InvalidResponseException("I/O error: " + e.getMessage());
        }


        Object[] table;
        try {
            table = node.evaluateXPath("//tr/td[position() = last()]/b/a");
        } catch (XPatherException e) {
            LOGGER.error("xpath error: " + e.getMessage());

            throw new InvalidResponseException("xpath error: " + e.getMessage());
        }

        Set<String> proxyIds = new HashSet<String>();
        for (Object row : table) {
            if (row instanceof TagNode) {
                TagNode link = (TagNode) row;

                String href = link.getAttributeByName("href");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("processing href: " + href);
                }

                String proxyId = parseLink(href);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("adding proxy id to result list: " + proxyId);
                }
                proxyIds.add(proxyId);

            } else {
                LOGGER.error("invalid row!");
            }
        }

        return proxyIds;
    }

    @Override
    public Proxy parseGetProxyResponse(HttpResponse response, ProxyType proxyType) throws InvalidResponseException {
        TagNode node = null;
        try {
            node = CLEANER.clean(response.getEntity().getContent());
        } catch (IOException e) {
            LOGGER.error("I/O error: " + e.getMessage());

            throw new InvalidResponseException("I/O error: " + e.getMessage());
        }

        String xPathQuery = proxyType == ProxyType.HTTP
                ? "//tr[position() = 2]/td[position() = last()]/b"
                : "//tr[position() = 3]/td[position() = last()]/b";
        Object[] table;
        try {
            table = node.evaluateXPath(xPathQuery);
        } catch (XPatherException e) {
            LOGGER.error("xpath error: " + e.getMessage());

            throw new InvalidResponseException("xpath error: " + e.getMessage());
        }

        Integer tdNumber = proxyType == ProxyType.HTTP
                ? HTTP_PROXY_TD_NUMBER
                : SOCKS_PROXY_TD_NUMBER;

        if (table.length > tdNumber && table[tdNumber] instanceof TagNode) {
            TagNode row = (TagNode) table[tdNumber];

            String proxyString = row.getText().toString();
            String[] splittedString = proxyString.split(":");

            String host = splittedString[0];
            String port = splittedString[1];
            LOGGER.debug("fetched proxy: {}:{} of type {}", new Object[]{host, port, proxyType});

            return new Proxy(host, Integer.valueOf(port), proxyType);
        } else {
            throw new InvalidResponseException("invalid table");
        }
    }

    private String parseLink(String link) {

        Pattern pattern = Pattern.compile("id=(\\d+)");
        Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
