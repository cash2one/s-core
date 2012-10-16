package com.seo.antigate;

import com.seo.antigate.connector.AntigateConnector;
import com.seo.antigate.connector.exception.AntigateUnavailableException;
import com.seo.captcha.CaptchaService;
import com.seo.captcha.exception.CaptchaProcessingFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AntigateCaptchaServiceImpl implements CaptchaService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AntigateCaptchaServiceImpl.class);

    @Inject
    private AntigateConnector antigateConnector;

    private String currentImageId = null;

    public String retrieve(String image, String ext, boolean phrase) throws CaptchaProcessingFailedException {
        String guessedCaptcha = null;

        try {
            currentImageId = antigateConnector.postImage(image, ext, phrase);
            guessedCaptcha = antigateConnector.checkResult(currentImageId);
        } catch (AntigateUnavailableException e) {
            LOGGER.error("antigate unavailable: " + e.getMessage());

            throw new CaptchaProcessingFailedException("antigate unavailable: " + e.getMessage());
        }

        assert guessedCaptcha != null;

        return guessedCaptcha;
    }

    public void reportInvalid() throws CaptchaProcessingFailedException {
        assert currentImageId != null;
        
        try {
            antigateConnector.reportInvalid(currentImageId);
        } catch (AntigateUnavailableException e) {
            LOGGER.error("antigate not available: " + e.getMessage());

            throw new CaptchaProcessingFailedException("antigate not available: " + e.getMessage());
        }
    }

}
