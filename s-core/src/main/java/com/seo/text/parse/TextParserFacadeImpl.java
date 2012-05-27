package com.seo.text.parse;

import com.seo.text.parse.model.ParsePageResponse;
import com.seo.webclient.WebClient;
import com.seo.webclient.WebClientImpl;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextParserFacadeImpl implements TextParserFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(TextParserFacadeImpl.class);

    private PageParser pageParser;

    public void setPageParser(PageParser pageParser) {
        this.pageParser = pageParser;
    }

    @Override
    public ParsePageResponse parsePage(String url) {
        LOGGER.debug("parsing text from url: {}", url);

        WebClient webClient = WebClientImpl.newInstance();
        Response response = webClient.retrievePage(
                new Request(url)
        );

        return pageParser.parsePage(response.getContent());
    }
}
