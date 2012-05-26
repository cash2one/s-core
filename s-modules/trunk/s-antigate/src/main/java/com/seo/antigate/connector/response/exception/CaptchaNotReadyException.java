package com.seo.antigate.connector.response.exception;

public class CaptchaNotReadyException extends Exception{
    public CaptchaNotReadyException() {
    }

    public CaptchaNotReadyException(String message) {
        super(message);
    }

    public CaptchaNotReadyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaNotReadyException(Throwable cause) {
        super(cause);
    }
}
