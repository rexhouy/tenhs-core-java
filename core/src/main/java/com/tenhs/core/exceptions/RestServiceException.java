package com.tenhs.core.exceptions;

public class RestServiceException extends ComServiceException {

    public RestServiceException(String message) {
        super(message, null);
    }

    public RestServiceException(String message, Throwable cause) {
        super(message, cause, null);
    }
}
