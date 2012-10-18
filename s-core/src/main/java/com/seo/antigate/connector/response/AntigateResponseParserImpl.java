package com.seo.antigate.connector.response;

import com.seo.antigate.connector.response.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
public class AntigateResponseParserImpl implements AntigateResponseParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(AntigateResponseParserImpl.class);
    private final static int OK_RESPONSE_SIZE = 3;

    @Override
    public String processPostImageResponse(String response) throws AntigateAccessDeniedException, InvalidImageException, NoSlotsAvailableException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("processing antigate post image response: " + response);
        }

        if (response.startsWith("OK")) {
            String imageId = response.substring(OK_RESPONSE_SIZE);

            LOGGER.info("captcha uploaded, id: " + imageId);

            return imageId;
        } else if ("ERROR_KEY_DOES_NOT_EXIST".equals(response)) {
            LOGGER.error("wrong antigate key");

            throw new AntigateAccessDeniedException("wrong antigate key");
        } else if ("ERROR_NO_SLOT_AVAILABLE".equals(response)) {
            LOGGER.info("no slots available");

            throw new NoSlotsAvailableException("no slots available");
        } else if ("ERROR_ZERO_CAPTCHA_FILESIZE".equals(response)) {
            LOGGER.error("zero captcha filesize");

            throw new InvalidImageException("zero captcha filesize");
        } else if ("ERROR_TOO_BIG_CAPTCHA_FILESIZE".equals(response)) {
            LOGGER.error("too big captcha filesize");

            throw new InvalidImageException("too big captcha filesize");
        } else if ("ERROR_WRONG_FILE_EXTENSION".equals(response)) {
            LOGGER.error("wrong file extension");

            throw new InvalidImageException("wrong file extension");
        } else if ("ERROR_IP_NOT_ALLOWED".equals(response)) {
            LOGGER.error("ip not allowed");

            throw new AntigateAccessDeniedException("ip not allowed");
        } else {
            LOGGER.error("unknown response: " + response);

            throw new IllegalArgumentException("unknown response: " + response);
        }
    }

    @Override
    public String processResultResponse(String response) throws CaptchaNotReadyException, InvalidRequestException, AntigateAccessDeniedException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("processing antigate result response: " + response);
        }

        if (response.startsWith("OK|")) {
            String captcha = response.substring(OK_RESPONSE_SIZE);

            LOGGER.info("captcha recognized: " + captcha);

            return captcha;
        } else if ("ERROR_KEY_DOES_NOT_EXIST".equals(response)) {
            LOGGER.error("key does not exist");

            throw new AntigateAccessDeniedException("key does not exists");
        } else if ("ERROR_WRONG_ID_FORMAT".equals(response)) {
            LOGGER.error("wrong id format");

            throw new InvalidRequestException("wrong id format");
        } else if ("ERROR_NO_SUCH_CAPCHA_ID".equals(response)) {
            LOGGER.error("no such captcha id");

            throw new InvalidRequestException("no such captcha id");
        } else if ("ERROR_URL_METHOD_FORBIDDEN".equals(response)) {
            LOGGER.error("url method forbidden");

            throw new InvalidRequestException("url method forbidden");
        } else if ("CAPCHA_NOT_READY".equals(response)) {
            LOGGER.info("captcha not ready");

            throw new CaptchaNotReadyException("captcha not ready");
        } else {
            LOGGER.error("invalid response: " + response);

            throw new IllegalArgumentException("invalid response: " + response);
        }
    }

    @Override
    public void processReportResponse(String response) throws InvalidRequestException, AntigateAccessDeniedException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("processing antigate report response: " + response);
        }

        if ("OK_REPORT_RECORDED".equals(response)) {
            LOGGER.info("report recorded");
        } else if ("ERROR_KEY_DOES_NOT_EXIST".equals(response)) {
            LOGGER.error("key does not exist");

            throw new AntigateAccessDeniedException("key does not exist");
        } else if ("ERROR_WRONG_ID_FORMAT".equals(response)) {
            LOGGER.error("wrong id format");

            throw new InvalidRequestException("wrong id format");
        } else if ("ERROR_NO_SUCH_CAPCHA_ID".equals(response)) {
            LOGGER.error("no such captcha id");

            throw new InvalidRequestException("no such captcha id");
        } else if ("ERROR_URL_METHOD_FORBIDDEN".equals(response)) {
            LOGGER.error("url method forbidden");

            throw new InvalidRequestException("url method forbidden");
        } else{
            LOGGER.error("invalid response: " + response);

            throw new IllegalArgumentException("invalid response: " + response);
        }
    }
}
