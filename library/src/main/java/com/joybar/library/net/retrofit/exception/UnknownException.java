package com.joybar.library.net.retrofit.exception;


public class UnknownException extends RestException {

    public UnknownException() {
    }

    public UnknownException(String detailMessage) {
        super(detailMessage);
    }

    public UnknownException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public UnknownException(Throwable throwable) {
        super(throwable);
    }
}
