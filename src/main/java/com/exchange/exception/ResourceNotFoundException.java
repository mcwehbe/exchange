package com.exchange.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not Found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}