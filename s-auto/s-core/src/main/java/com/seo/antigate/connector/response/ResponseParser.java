package com.seo.antigate.connector.response;

import com.seo.antigate.connector.response.exception.*;

/**
 * Parses antigate service responses
 */
public interface ResponseParser {
    /**
     * This method parses string from post image response and returns image id if success
     * or throws an exception if not.
     * @param response Response string from antigate service
     * @return image id
     * @throws com.seo.antigate.connector.response.exception.AntigateAccessDeniedException thrown if antigate key is invalid or ip address is denied
     * @throws com.seo.antigate.connector.response.exception.InvalidImageException thrown if image has zero or too big size, also if image extension is invalid
     * @throws com.seo.antigate.connector.response.exception.NoSlotsAvailableException thrown if antigate service has no free slots.
     */
    String processPostImageResponse(String response) throws AntigateAccessDeniedException, InvalidImageException, NoSlotsAvailableException;

    /**
     * Parses response string from the antigate response service and returns guessed captcha string or
     * throws an appropriate exception
     * @param response antigate response service string
     * @return guessed captcha
     * @throws com.seo.antigate.connector.response.exception.CaptchaNotReadyException thrown if captcha is not guessed but still processing
     * @throws com.seo.antigate.connector.response.exception.InvalidRequestException thrown if invalid request were posted
     * @throws com.seo.antigate.connector.response.exception.AntigateAccessDeniedException thrown if antigate key is invalid or ip address is denied
     */
    String processResultResponse(String response) throws CaptchaNotReadyException, InvalidRequestException, AntigateAccessDeniedException;

    /**
     * Parses response from antigate report command
     * @param response Response string from antigate report service
     * @throws com.seo.antigate.connector.response.exception.InvalidRequestException thrown if invalid request were posted
     * @throws com.seo.antigate.connector.response.exception.AntigateAccessDeniedException thrown if antigate key is invalid or ip address is denied
     */
    void processReportResponse(String response) throws InvalidRequestException, AntigateAccessDeniedException;
}
