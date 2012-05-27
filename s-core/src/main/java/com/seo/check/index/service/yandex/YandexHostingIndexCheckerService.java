package com.seo.check.index.service.yandex;

import com.seo.check.index.service.IndexCheckerService;
import com.seo.webclient.WebClient;
import com.seo.webclient.WebClientImpl;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YandexHostingIndexCheckerService implements IndexCheckerService{
    private final static Logger LOGGER = LoggerFactory.getLogger(YandexHostingIndexCheckerService.class);

    private final static String NAME = "yandex_hosting";

    private final static String SEARCH_URL = "http://yandex.ru/yandsearch?date=&text=&site=%s&rstr=&zone=all&wordforms=all&lang=all&within=0&from_day=&from_month=&from_year=&to_day=&to_month=&to_year=&mime=all&numdoc=10";

    @Override
    public Integer checkIndex(String url) {
        WebClient webClient = WebClientImpl.newInstance();

        Response response = webClient.retrievePage(
                new Request(String.format(SEARCH_URL, url))
        );

        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode rootNode = cleaner.clean(response.getContent());

        TagNode strongNode = rootNode.findElementByAttValue("class", "b-head-logo__text", true, false);

        if(strongNode != null) {
            String text = strongNode.getText().toString();

            text = text.replace("&nbsp;", " ");

            Pattern pattern = Pattern.compile("Нашлось(.*?) ответов");
            Matcher matcher = pattern.matcher(text);

            if(matcher.find()) {
                String value = matcher.group(1);
                value = value.replace(" тыс.", "000");

                LOGGER.info("parsed value: {} - {}", url, value);
            } else {
                String value = text.replace("\n", "");

                LOGGER.debug("not parsed: {} - {}", url, value);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        YandexHostingIndexCheckerService service = new YandexHostingIndexCheckerService();

        service.checkIndex("narod.ru");
    }

    @Override
    public String getName() {
        return NAME;
    }
}
