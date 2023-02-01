package com.example.datshopspring2.validate.impl;

import com.example.datshopspring2.exceptions.EmailValidatorException;
import com.example.datshopspring2.repositories.AccountRepository;
import com.example.datshopspring2.validate.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailValidatorImpl implements EmailValidator {
    @Autowired
    private AccountRepository accountsRepository;

    @Override
    public void validate(String email) throws EmailValidatorException {
        if (this.accountsRepository.findAccountByEmail(email) != null) {
            throw new EmailValidatorException("Email used");
        }
    }
}
