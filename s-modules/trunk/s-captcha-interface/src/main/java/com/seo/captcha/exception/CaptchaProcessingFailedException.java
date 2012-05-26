package com.seo.captcha.exception;

public class CaptchaProcessingFailedException extends Exception{
    public CaptchaProcessingFailedException() {
    }

    public CaptchaProcessingFailedException(String message) {
        super(message);
    }

    public CaptchaProcessingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaProcessingFailedException(Throwable cause) {
        super(cause);
    }
}