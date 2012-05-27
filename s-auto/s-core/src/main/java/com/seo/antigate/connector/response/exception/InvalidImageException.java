package com.seo.antigate.connector.response.exception;

public class InvalidImageException extends Exception{
    public InvalidImageException() {
    }

    public InvalidImageException(String message) {
        super(message);
    }

    public InvalidImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidImageException(Throwable cause) {
        super(cause);
    }
}
