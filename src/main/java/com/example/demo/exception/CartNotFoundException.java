package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class CartNotFoundException extends GeneralExceptionConfig {
    public CartNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
