package com.seo.auto.parser.exception;

public class ConfigErrorException extends Exception{
    public ConfigErrorException() {
    }

    public ConfigErrorException(String message) {
        super(message);
    }

    public ConfigErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigErrorException(Throwable cause) {
        super(cause);
    }
}
