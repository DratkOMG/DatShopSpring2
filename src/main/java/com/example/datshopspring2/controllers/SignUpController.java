package com.example.datshopspring2.controllers;


import com.example.datshopspring2.exceptions.EmailValidatorException;
import com.example.datshopspring2.exceptions.PasswordValidatorException;
import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.User;
import com.example.datshopspring2.repositories.AccountRepository;
import com.example.datshopspring2.repositories.UserRepository;
import com.example.datshopspring2.validate.EmailValidator;
import com.example.datshopspring2.validate.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "/login/signup";
    }

    @PostMapping("/sign-up")
    public String signUpAccount(Model model, Account account) {
        if (!account.getPassword().equals(account.getConfirmPassword())) {
            model.addAttribute("email", account.getEmail());
            model.addAttribute("username", account.getUsername());
            model.addAttribute("messError", "Confirm password not correct");
            return "/login/signup";
        }

        try {
            passwordValidator.validate(account.getPassword());
            emailValidator.validate(account.getEmail());
            Account accountSave = Account.builder()
                    .email(account.getEmail())
                    .password(account.getPassword())
                    .isAdmin(false)
                    .isSeller(false)
                    .build();
            accountRepository.save(accountSave);
            User user = User.builder()
                    .userId(accountRepository.findAccountByEmail(account.getEmail()).getAccountId())
                    .email(account.getEmail())
                    .userName(account.getUsername())
                    .age(0)
                    .city("Moscow")
                    .phoneNumber("0")
                    .sex("Male")
                    .balance(0)
                    .build();
            userRepository.save(user);
        } catch (PasswordValidatorException e) {
            model.addAttribute("email", account.getEmail());
            model.addAttribute("username", account.getUsername());
            model.addAttribute("messError", e.getMessage());
            return "/login/signup";
        } catch (EmailValidatorException e) {
            model.addAttribute("messError", e.getMessage());
            return "/login/signup";
        }

        return "redirect:/sign-in";
    }

}
