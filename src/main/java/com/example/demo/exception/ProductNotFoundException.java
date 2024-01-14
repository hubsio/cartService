package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends GeneralExceptionConfig {
    public ProductNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
