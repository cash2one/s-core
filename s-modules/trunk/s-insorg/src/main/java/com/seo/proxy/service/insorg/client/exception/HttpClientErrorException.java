package com.seo.proxy.service.insorg.client.exception;

public class HttpClientErrorException extends Exception{
    public HttpClientErrorException() {
    }

    public HttpClientErrorException(String message) {
        super(message);
    }

    public HttpClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpClientErrorException(Throwable cause) {
        super(cause);
    }
}
