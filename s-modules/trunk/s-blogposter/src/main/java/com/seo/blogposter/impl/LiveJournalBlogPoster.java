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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LiveJournalBlogPoster implements BlogPoster {
    private final static Logger LOGGER = LoggerFactory.getLogger(LiveJournalBlogPoster.class);

    private final static String NAME = "livejournal";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void post(PostBlogRequest request) {

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL(request.getXmlRpcPath()));
        } catch (MalformedURLException e) {
            LOGGER.error("malformed url: " + e);

            throw new IllegalArgumentException("malformed url: " + e);
        }
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        Map<String, String> struct = new HashMap<String, String>();
        struct.put("username", request.getLogin());
        struct.put("password", request.getPassword());
        struct.put("event", request.getText());
        struct.put("subject", request.getTitle());
        struct.put("lineendings", "pc");
        struct.put("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        struct.put("mon", String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1));
        struct.put("day", String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        struct.put("hour", String.valueOf(Calendar.getInstance().get(Calendar.HOUR)));
        struct.put("min", String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("creating xmlrpc request for livejournal; login=" + request.getLogin() +" time: " + struct.get("year") + "-" + struct.get("mon") + "-" + struct.get("day") + " " + struct.get("hour") + ":" + struct.get("min"));
        }

        Object[] params = new Object[]{struct};
        try {
            client.execute("LJ.XMLRPC.postevent", params);
            LOGGER.info("posted to " + request.getLogin() + ".livejournal.com");
        } catch (XmlRpcException e) {
            LOGGER.error("XmlRpc exception: " + e);

            throw new IllegalStateException("xmlrpc error: " + e.getMessage());
        }
    }
}
