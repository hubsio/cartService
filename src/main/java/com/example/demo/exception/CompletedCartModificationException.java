package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class CompletedCartModificationException extends GeneralExceptionConfig {
    public CompletedCartModificationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
