package com.seo.text.parse;

import com.seo.text.parse.model.ParsePageResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPageParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestPageParser.class);

    private PageParser pageParser;

    @BeforeMethod
    public void init() {
        pageParser = new PageParserImpl();
    }

    @Test
    public void testParser() {
        try {
            String response = IOUtils.toString(TestPageParser.class.getClassLoader().getResourceAsStream("response.txt"));

            ParsePageResponse pageResponse = pageParser.parsePage(response);

            System.out.println(pageResponse);
        } catch (IOException e) {
            LOGGER.error("i/o exception: {} {}", e.getMessage(), e.getStackTrace());
        }
    }
}
