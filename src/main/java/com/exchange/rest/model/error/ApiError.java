package com.exchange.rest.model.error;

import org.springframework.http.HttpStatus;

import java.util.List;


public class ApiError {
    private HttpStatus status;
    private String message;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getResponse() {
        return message;
    }
    public HttpStatus getHttpStatus() {
        return status;
    }
}