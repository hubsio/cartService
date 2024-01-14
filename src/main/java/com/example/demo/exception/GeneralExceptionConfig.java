package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralExceptionConfig extends RuntimeException {
    private final HttpStatus httpStatus;

    public GeneralExceptionConfig(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
