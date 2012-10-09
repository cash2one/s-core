package com.seo.auto.model.exception;

public class ProjectFailedException extends Exception{
    public ProjectFailedException() {
    }

    public ProjectFailedException(String message) {
        super(message);
    }

    public ProjectFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectFailedException(Throwable cause) {
        super(cause);
    }
}
