package com.example.datshopspring2.exceptions;

public class PasswordValidatorException extends RuntimeException{
    public PasswordValidatorException(String message) {
        super(message);
    }
}
