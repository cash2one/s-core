package com.seo.antigate.connector;

import com.seo.antigate.client.DefaultHttpClientWrapper;
import com.seo.antigate.connector.exception.AntigateUnavailableException;
import com.seo.antigate.connector.response.ResponseParser;
import com.seo.antigate.connector.response.exception.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AntigateConnectorImpl implements AntigateConnector {
    private final static Logger LOGGER = LoggerFactory.getLogger(AntigateConnectorImpl.class);
    private String antigateKey;
    private String antigateInputUrl;

    private final static Integer POST_IMAGE_SLEEP_TIMEOUT = 15000;
    private final static Integer GET_RESPONSE_SLEEP_TIMEOUT = 15000;

    private HttpClient client = new DefaultHttpClientWrapper();
    private ResponseParser responseParser;

    public void setResponseParser(ResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public void setAntigateKey(String antigateKey) {
        this.antigateKey = antigateKey;
    }

    public void setAntigateInputUrl(String antigateInputUrl) {
        this.antigateInputUrl = antigateInputUrl;
    }

    @Override
    public String postImage(String imageBase64, String imageExtension, Boolean phrase) throws AntigateUnavailableException {
        String imageId = null;

        while (true) {
            String antigateResponse = null;
            try {
                antigateResponse = postCaptcha(imageBase64, imageExtension, phrase);
            } catch (IOException e) {
                LOGGER.error("I/O error: " + e.getMessage());

                throw new AntigateUnavailableException("I/O error: " + e.getMessage());
            }

            try {
                imageId = responseParser.processPostImageResponse(antigateResponse);

                break;
            } catch (AntigateAccessDeniedException e) {
                LOGGER.error("antigate access denied exception: " + e.getMessage());

                throw new AntigateUnavailableException("antigate access denied exception: " + e.getMessage());
            } catch (InvalidImageException e) {
                LOGGER.error("invalid image exception: " + e.getMessage());

                throw new AntigateUnavailableException("invalid image exception: " + e.getMessage());
            } catch (NoSlotsAvailableException e) {
                LOGGER.info("no slots available. repeating");

                sleep(POST_IMAGE_SLEEP_TIMEOUT);
            }
        }

        assert imageId != null;

        return imageId;
    }

    @Override
    public void reportInvalid(String imageId) throws AntigateUnavailableException {
        if (imageId == null) {
            throw new IllegalStateException("id == null");
        }

        String antigateResponse;
        try {
            antigateResponse = postReportRequest(imageId);
        } catch (IOException e) {
            LOGGER.error("I/O error: " + e.getMessage());

            throw new AntigateUnavailableException("I/O error: " + e.getMessage());
        }

        try {
            responseParser.processReportResponse(antigateResponse);
        } catch (InvalidRequestException e) {
            LOGGER.error("invalid request: " + e.getMessage());

            throw new AntigateUnavailableException("invalid request: " + e.getMessage());
        } catch (AntigateAccessDeniedException e) {
            LOGGER.error("antigate access denied exception: " + e.getMessage());

            throw new AntigateUnavailableException("antigate access denied exception: " + e.getMessage());
        }
    }

    private String postReportRequest(String imageId) throws IOException {
        List<NameValuePair> qparams = createReportRequest(imageId);

        URI uri = null;
        try {
            uri = URIUtils.createURI("http", "antigate.com", -1, "res.php", URLEncodedUtils.format(qparams, "UTF-8"), null);
        } catch (URISyntaxException e) {
            LOGGER.error("uri syntax error: " + e.getMessage());

            throw new IllegalArgumentException("uri syntax error: " + e.getMessage());
        }
        HttpGet httpget = new HttpGet(uri);

        HttpResponse response = client.execute(httpget);

        return EntityUtils.toString(response.getEntity());
    }

    private List<NameValuePair> createReportRequest(String imageId) {
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("key", antigateKey));
        qparams.add(new BasicNameValuePair("action", "report"));
        qparams.add(new BasicNameValuePair("id", imageId));
        return qparams;
    }

    @Override
    public String checkResult(String imageId) throws AntigateUnavailableException {
        String processedImage = null;

        while (true) {
            String response;
            try {
                response = receiveAntiCaptchaResponse(imageId);
            } catch (IOException e) {
                LOGGER.error("I/O error: " + e.getMessage());

                throw new AntigateUnavailableException("I/O error: " + e.getMessage());
            }

            try {
                processedImage = responseParser.processResultResponse(response);

                break;
            } catch (CaptchaNotReadyException e) {
                LOGGER.info("captcha not ready. repeating");

                sleep(GET_RESPONSE_SLEEP_TIMEOUT);
            } catch (InvalidRequestException e) {
                LOGGER.error("invalid request error: " + e.getMessage());

                throw new AntigateUnavailableException("invalid request error: " + e.getMessage());
            } catch (AntigateAccessDeniedException e) {
                LOGGER.error("antigate access denied error: " + e.getMessage());

                throw new AntigateUnavailableException("antigate access denied error: " + e.getMessage());
            }
        }

        assert processedImage != null;

        return processedImage;
    }

    private HttpPost createPostImageRequest(String base64, String ext, boolean phrase) {
        HttpPost httppost = null;

        try {
            List<NameValuePair> requestParameters = populatePostImageFormParamsMap(base64, ext, phrase);

            httppost = new HttpPost(antigateInputUrl);

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(requestParameters, "UTF-8");
            httppost.setEntity(entity);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Unsupported encoding: " + e.getMessage());
            throw new RuntimeException("Unsupported encoding: " + e.getMessage());
        }
        return httppost;
    }

    private List<NameValuePair> populatePostImageFormParamsMap(String base64, String ext, boolean phrase) {
        List<NameValuePair> requestParameters = new ArrayList<NameValuePair>();

        requestParameters.add(new BasicNameValuePair("method", "base64"));
        requestParameters.add(new BasicNameValuePair("key", antigateKey));
        requestParameters.add(new BasicNameValuePair("body", base64));
        requestParameters.add(new BasicNameValuePair("ext", ext));

        if (phrase) {
            requestParameters.add(new BasicNameValuePair("phrase", "1"));
        }

        return requestParameters;
    }

    private String receiveAntiCaptchaResponse(String imageId) throws IOException {
        List<NameValuePair> qparams = populateResultRequest(imageId);

        URI uri = createResponseUri(qparams);
        HttpGet httpget = new HttpGet(uri);

        HttpResponse response = client.execute(httpget);
        return EntityUtils.toString(response.getEntity());
    }

    private URI createResponseUri(List<NameValuePair> qparams) {
        URI uri = null;
        try {
            uri = URIUtils.createURI("http", "antigate.com", -1, "res.php", URLEncodedUtils.format(qparams, "UTF-8"), null);
        } catch (URISyntaxException e) {
            LOGGER.error("Invalid URI syntax: " + e.getMessage());
            throw new RuntimeException("Invalid URI syntax: " + e.getMessage());
        }
        return uri;
    }

    private List<NameValuePair> populateResultRequest(String imageId) {
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("key", antigateKey));
        qparams.add(new BasicNameValuePair("action", "get"));
        qparams.add(new BasicNameValuePair("id", imageId));

        return qparams;
    }

    private String postCaptcha(String image, String ext, boolean phrase) throws IOException {
        HttpPost httppost = createPostImageRequest(image, ext, phrase);

        HttpResponse response = client.execute(httppost);

        return EntityUtils.toString(response.getEntity());
    }

    private void sleep(int sleep) throws AntigateUnavailableException {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e1) {
            LOGGER.error("interrupted exception: " + e1.getMessage());

            throw new AntigateUnavailableException("interrupted exception: " + e1.getMessage());
        }
    }
}
