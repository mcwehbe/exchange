package com.exchange.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("operation is not allowed");
    }

    public ForbiddenException(String message) {
        super(message);
    }

}
