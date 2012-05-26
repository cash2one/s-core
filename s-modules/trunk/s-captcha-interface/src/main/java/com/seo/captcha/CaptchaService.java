package com.seo.captcha;

import com.seo.captcha.exception.CaptchaProcessingFailedException;

public interface CaptchaService {
    String retrieve(String imageBase64, String imageExtension, boolean isPhrase) throws CaptchaProcessingFailedException;
    void reportInvalid() throws CaptchaProcessingFailedException;
}
