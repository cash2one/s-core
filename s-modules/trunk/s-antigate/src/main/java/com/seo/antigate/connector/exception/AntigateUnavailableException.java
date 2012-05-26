package com.seo.antigate.connector.exception;

public class AntigateUnavailableException extends Exception{
    public AntigateUnavailableException() {
    }

    public AntigateUnavailableException(String message) {
        super(message);
    }

    public AntigateUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public AntigateUnavailableException(Throwable cause) {
        super(cause);
    }
}
