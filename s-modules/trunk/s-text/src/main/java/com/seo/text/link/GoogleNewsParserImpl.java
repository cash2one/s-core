package com.seo.text.link;

import org.apache.commons.validator.UrlValidator;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleNewsParserImpl implements GoogleNewsParser{
    private final static Logger LOGGER = LoggerFactory.getLogger(GoogleNewsParserImpl.class);

    private final static HtmlCleaner CLEANER = new HtmlCleaner();

    @Override
    public List<String> parseNews(String content) {
        LOGGER.debug("parsing news from content");

        List<String> links = new ArrayList<String>();

        try {
            TagNode tagNode = CLEANER.clean(content);

            Object[] linkTags = tagNode.evaluateXPath("//h2[@class='title']/a");

            String[] schemes = new String[]{"http"};
            UrlValidator urlValidator = new UrlValidator(schemes);
            
            for (Object linkTag : linkTags) {
                if(linkTag instanceof TagNode) {
                    TagNode tag = (TagNode) linkTag;

                    String url = tag.getAttributeByName("href");
                    if(urlValidator.isValid(url)) {
                        links.add(url);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("i/o exception: {} {}", e.getMessage(), e.getStackTrace());

            throw new RuntimeException("i/o exception");
        } catch (XPatherException e) {
            LOGGER.error("xpath exception: {} {}", e.getMessage(), e.getStackTrace());

            throw new RuntimeException("xpath exception: " + e.getMessage());
        }

        return links;
    }
}
