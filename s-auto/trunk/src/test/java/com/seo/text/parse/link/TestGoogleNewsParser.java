package com.seo.text.parse.link;

import com.seo.text.link.GoogleNewsParser;
import com.seo.text.link.GoogleNewsParserImpl;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestGoogleNewsParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestGoogleNewsParser.class);

    private GoogleNewsParser googleNewsParser;

    @BeforeMethod
    public void init() {
        googleNewsParser = new GoogleNewsParserImpl();
    }

    @Test
    public void testParseLinks() {
        LOGGER.debug("testing link parsing");

        try {
            String response = IOUtils.toString(TestGoogleNewsParser.class.getClassLoader().getResourceAsStream("gnews.txt"));

            googleNewsParser.parseNews(response);
        } catch (IOException e) {
            LOGGER.debug("i/o exception: {} {}", e.getMessage(), e.getStackTrace());

            throw new RuntimeException("i/o exception: " + e.getMessage());
        }
    }
}
