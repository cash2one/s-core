package com.seo.antigate.connector.response.exception;

public class AntigateAccessDeniedException extends Exception{
    public AntigateAccessDeniedException() {
    }

    public AntigateAccessDeniedException(String message) {
        super(message);
    }

    public AntigateAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AntigateAccessDeniedException(Throwable cause) {
        super(cause);
    }
}
