package com.seo.core.facade.exception;

public class NoAccountAvailableException extends RuntimeException{
    public NoAccountAvailableException() {
    }

    public NoAccountAvailableException(String message) {
        super(message);
    }

    public NoAccountAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAccountAvailableException(Throwable cause) {
        super(cause);
    }
}
