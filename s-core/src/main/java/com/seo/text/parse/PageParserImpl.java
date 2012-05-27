package com.seo.text.parse;

import com.seo.text.parse.model.ParsePageResponse;
import org.apache.commons.lang.StringEscapeUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageParserImpl implements PageParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(PageParserImpl.class);

    private final static HtmlCleaner CLEANER = new HtmlCleaner();
    private final static int MINIMAL_CONTENT_LENGTH = 250;
    private final static int MINIMAL_STRING_LENGTH = 250;
    private final static String[] CONTENT_NODE_LIST = new String[]{
            "div",
            "p"
    };

    @Override
    public ParsePageResponse parsePage(String page) {
        LOGGER.debug("starting page parsing");

        StringBuffer text = new StringBuffer();
        TagNode root = CLEANER.clean(page);

        List<String> texts = new ArrayList<String>();
        for (TagNode tagNode : root.getAllElements(true)) {
            if (isContentNode(tagNode.getName()) && tagNode.getChildren() != null && tagNode.getText() != null) {
                StringBuffer buffer = tagNode.getText();

                String replacedHtml = buffer.toString().replaceAll("\\<[^>]*>", "");
                replacedHtml = replacedHtml.replaceAll("\\n", "");
                replacedHtml = replacedHtml.replaceAll(" +", " ");
                replacedHtml = StringEscapeUtils.unescapeHtml(replacedHtml);

                String jsPattern = "[^{}\\[\\]=]+";
                if (replacedHtml.length() >= MINIMAL_CONTENT_LENGTH && replacedHtml.matches(jsPattern)) {
                    texts.add(replacedHtml);
                }
            }
        }

        List<String> notUniqueTexts = new ArrayList<String>();
        for (String s : texts) {
            List<String> textsWithoutString = new ArrayList<String>(texts);
            textsWithoutString.remove(s);

            for (String s1 : textsWithoutString) {
                if (s1.contains(s)) {
                    notUniqueTexts.add(s1);
                }
            }
        }

        texts.removeAll(notUniqueTexts);
        for (String string : texts) {
            text.append(string).append("\n");
        }

        String textString = text.toString();
        String[] strings = textString.split("[\n\r]+");

        StringBuffer resultText = new StringBuffer();
        for (String string : strings) {
            if (MINIMAL_STRING_LENGTH <= string.length()) {
                resultText.append(string).append("\n");
            }
        }

        return new ParsePageResponse(
                resultText.toString()
        );

    }

    private boolean isContentNode(String nodeName) {
        for (String node : CONTENT_NODE_LIST) {
            if (nodeName.equals(node)) {
                return true;
            }
        }

        return false;
    }
}
