package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class CartModificationTimeExpiredException extends GeneralExceptionConfig {
    public CartModificationTimeExpiredException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
