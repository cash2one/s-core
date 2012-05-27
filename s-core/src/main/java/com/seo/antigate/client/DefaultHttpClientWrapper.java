package com.seo.antigate.client;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

public class DefaultHttpClientWrapper extends DefaultHttpClient{
    public DefaultHttpClientWrapper() {
        super();
        initParameters();
    }

    private void initParameters() {
        getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
        getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/5.0 (Windows; U; Windows NT 6.1; ru; rv:1.9.1.4) Gecko/20091016 Firefox/3.5.4");
    }
}
