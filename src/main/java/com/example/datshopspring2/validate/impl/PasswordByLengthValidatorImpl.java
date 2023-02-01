package com.example.datshopspring2.validate.impl;

import com.example.datshopspring2.exceptions.PasswordValidatorException;
import com.example.datshopspring2.validate.PasswordValidator;
import org.springframework.stereotype.Component;

@Component
public class PasswordByLengthValidatorImpl implements PasswordValidator {
    private final int minLength = 8;

    @Override
    public void validate(String password) throws PasswordValidatorException {
        if (password.length() < minLength) {
            throw new PasswordValidatorException("Short password");
        }
    }
}
