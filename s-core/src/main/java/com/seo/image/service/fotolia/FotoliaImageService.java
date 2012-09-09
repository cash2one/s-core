package com.seo.image.service.fotolia;

import com.seo.image.service.ImageService;
import com.seo.webclient.WebClient;
import com.seo.webclient.WebClientImpl;
import com.seo.webclient.factory.RequestFactory;
import com.seo.webclient.model.EncodedImage;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

public class FotoliaImageService implements ImageService{
    private WebClient client = WebClientImpl.newInstance();

    private final static Logger LOGGER = LoggerFactory.getLogger(FotoliaImageService.class);
    private final static HtmlCleaner CLEANER = new HtmlCleaner();
    private final static Random RANDOM = new Random();

    private final static String SERVICE_NAME = "fotolia";
    private final static String URL = "http://www.fotolia.com/id/";

    @Override
    public EncodedImage fetchImage() {
        String url = findImageSource();

        Request imageRequest = new Request(url, RequestFactory.GET_METHOD, null, null);
        EncodedImage encodedImage = client.retrieveImage(imageRequest);

        return encodedImage;
    }

    @Override
    public String getName() {
        return SERVICE_NAME;
    }

    private String findImageSource() {
        Long id = generateId();
        Request request = new Request(URL + String.valueOf(id), RequestFactory.GET_METHOD, null, null);
        Response response = client.retrievePage(request);

        try {
            TagNode node = CLEANER.clean(response.getContent());
            TagNode div = node.findElementByAttValue("class", "deco", true, true);

            if (div != null) {
                TagNode image = div.findElementByName("img", true);
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("found image: " + image.getAttributeByName("src"));
                }
                return image.getAttributeByName("src");
            }
        } catch (IOException e) {
            LOGGER.error("I/O Error: " + e);

            throw new RuntimeException("I/O Error: " + e);

        }
        return null;
    }

    private Long generateId() {
        return RANDOM.nextInt(9000000) + 1000000L;
    }
}
