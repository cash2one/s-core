package com.seo.blogposter.impl;

import com.seo.blogposter.BlogPoster;
import com.seo.blogposter.model.PostBlogRequest;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WordPressBlogPoster implements BlogPoster {
    private final static Logger LOGGER = LoggerFactory.getLogger(WordPressBlogPoster.class);

    private final static String NAME = "wordpress";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void post(PostBlogRequest request) {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL( request.getXmlRpcPath() ));
        } catch (MalformedURLException e) {
            LOGGER.error("malformed url: " + e);

            throw new IllegalArgumentException("malformed url: " + e);
        }

        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        Map<String, String> struct = new HashMap<String, String>();
        struct.put("title", request.getTitle());
        struct.put("description", request.getText());
        Object[] params = new Object[]{
                "1",
                request.getLogin(),
                request.getPassword(),
                struct,
                Boolean.TRUE
        };
        try {
            client.execute("metaWeblog.newPost", params);
        } catch (XmlRpcException e) {
            LOGGER.error("XmlRpc exception: " + e);

            throw new IllegalStateException("xmlrpc error:" + e.getMessage());
        }
    }
}
