package com.seo.auto.command.exception;

/**
 * Thrown if error occurred during project execution
 */
public class CommandExecutionFailedException extends Exception{
    public CommandExecutionFailedException() {
    }

    public CommandExecutionFailedException(String message) {
        super(message);
    }

    public CommandExecutionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandExecutionFailedException(Throwable cause) {
        super(cause);
    }
}
