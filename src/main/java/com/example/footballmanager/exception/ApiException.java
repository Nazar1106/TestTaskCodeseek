package com.example.footballmanager.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
