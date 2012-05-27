package com.seo.proxy.exception;

public class ProxyNotAvailableException extends Exception{
    public ProxyNotAvailableException() {
    }

    public ProxyNotAvailableException(String message) {
        super(message);
    }

    public ProxyNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProxyNotAvailableException(Throwable cause) {
        super(cause);
    }
}
