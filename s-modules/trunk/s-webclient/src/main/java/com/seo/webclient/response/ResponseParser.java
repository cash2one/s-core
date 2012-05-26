package com.seo.webclient.response;

import com.seo.webclient.model.Response;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(ResponseParser.class);

    public static Response parseResponse(HttpResponse httpResponse) {
        Map<String, String> headers = new HashMap<String, String>();

        for (Header header : httpResponse.getAllHeaders()) {
            headers.put(
                    header.getName(),
                    header.getValue()
            );
        }
        String contentType = headers.get("Content-Type");
        String encoding = "utf8";
        if(contentType != null) {
            if(contentType.toLowerCase().contains("1251")) {
                encoding = "cp1251";
            }
        }

        String content = null;
        try {
            content = EntityUtils.toString(httpResponse.getEntity(), encoding);
        } catch (IOException e) {
            LOGGER.error("IO error: " + e.getMessage());

            throw new IllegalStateException("IO error: " + e.getMessage());
        }

        return new Response(
                content,
                headers
        );
    }
}
