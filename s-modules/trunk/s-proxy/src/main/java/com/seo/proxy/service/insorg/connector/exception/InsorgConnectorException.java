package com.seo.proxy.service.insorg.connector.exception;

public class InsorgConnectorException extends Exception{
    public InsorgConnectorException() {
    }

    public InsorgConnectorException(String message) {
        super(message);
    }

    public InsorgConnectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsorgConnectorException(Throwable cause) {
        super(cause);
    }
}
