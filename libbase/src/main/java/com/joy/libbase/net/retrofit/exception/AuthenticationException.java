package com.joy.libbase.net.retrofit.exception;


public class AuthenticationException extends RestException {

    public AuthenticationException() {
    }

    public AuthenticationException(String detailMessage) {
        super(detailMessage);
    }

    public AuthenticationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }
}
