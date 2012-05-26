package com.seo.proxy.service.insorg.facade.exception;

public class InsorgServiceUnavailableException extends Exception{
    public InsorgServiceUnavailableException() {
    }

    public InsorgServiceUnavailableException(String message) {
        super(message);
    }

    public InsorgServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsorgServiceUnavailableException(Throwable cause) {
        super(cause);
    }
}
