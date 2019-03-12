package com.joy.libbase.net.retrofit.exception;


public class RestException extends RuntimeException {

    public RestException() {
    }

    public RestException(String detailMessage) {
        super(detailMessage);
    }

    public RestException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RestException(Throwable throwable) {
        super(throwable);
    }
}
