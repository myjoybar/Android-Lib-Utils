package com.joy.libbase.net.retrofit.exception;

/**
 * Created by joybar on 5/23/16.
 */
public class ResponseFormatException extends RestException {

    public ResponseFormatException() {
    }

    public ResponseFormatException(String detailMessage) {
        super(detailMessage);
    }

    public ResponseFormatException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ResponseFormatException(Throwable throwable) {
        super(throwable);
    }
}
