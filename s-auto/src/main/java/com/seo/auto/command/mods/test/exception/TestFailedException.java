package com.seo.auto.command.mods.test.exception;

/**
 * Throw if any test fails during processing
 */
public class TestFailedException extends RuntimeException{
    public TestFailedException() {
    }

    public TestFailedException(String message) {
        super(message);
    }

    public TestFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestFailedException(Throwable cause) {
        super(cause);
    }
}
