package com.seo.antigate.connector.response.exception;

public class NoSlotsAvailableException extends Exception{
    public NoSlotsAvailableException() {
    }

    public NoSlotsAvailableException(String message) {
        super(message);
    }

    public NoSlotsAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSlotsAvailableException(Throwable cause) {
        super(cause);
    }
}
