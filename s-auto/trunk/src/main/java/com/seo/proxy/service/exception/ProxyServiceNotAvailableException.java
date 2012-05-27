package com.seo.proxy.service.exception;

public class ProxyServiceNotAvailableException extends Exception{
    public ProxyServiceNotAvailableException() {
    }

    public ProxyServiceNotAvailableException(String message) {
        super(message);
    }

    public ProxyServiceNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProxyServiceNotAvailableException(Throwable cause) {
        super(cause);
    }
}
