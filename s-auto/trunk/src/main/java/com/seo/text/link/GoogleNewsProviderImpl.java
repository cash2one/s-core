package com.seo.text.link;

import com.seo.webclient.WebClient;
import com.seo.webclient.WebClientImpl;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GoogleNewsProviderImpl implements GoogleNewsProvider{
    private final static Logger LOGGER = LoggerFactory.getLogger(GoogleNewsProviderImpl.class);

    private final static String QUERY_URL = "http://news.google.com/news/search?aq=f&pz=1&cf=all&ned=us&hl=en&q=%s&start=%s";

    private GoogleNewsParser googleNewsParser;

    public void setGoogleNewsParser(GoogleNewsParser googleNewsParser) {
        this.googleNewsParser = googleNewsParser;
    }

    @Override
    public List<String> fetchNews(String query, int start) {
        WebClient webClient = WebClientImpl.newInstance();

        Response response = webClient.retrievePage(new Request(String.format(QUERY_URL, query, start)));

        return googleNewsParser.parseNews(response.getContent());
    }
}
