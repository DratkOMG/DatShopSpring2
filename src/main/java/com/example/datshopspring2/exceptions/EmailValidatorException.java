package com.example.datshopspring2.exceptions;

public class EmailValidatorException extends RuntimeException {
    public EmailValidatorException(String message) {
        super(message);
    }
}
