package com.seo.addurl.service.exception;

public class AddUrlFailedException extends Exception{
    public AddUrlFailedException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public AddUrlFailedException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public AddUrlFailedException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public AddUrlFailedException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
