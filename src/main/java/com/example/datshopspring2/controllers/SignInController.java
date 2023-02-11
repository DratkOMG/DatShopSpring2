package com.example.datshopspring2.controllers;

import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.services.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/sign-in")
@SessionAttributes({"account", "seller", "admin"})
public class SignInController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String getSignInPage(HttpSession session, Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("acc")) {
                model.addAttribute("email", cookie.getValue());
            }
            if (cookie.getName().equals("pass")) {
                model.addAttribute("password", cookie.getValue());
            }
            if (cookie.getName().equals("rem")) {
                model.addAttribute("remember", cookie.getValue());
            }
        }
        return "/login/login";
    }

    @GetMapping("/log-out")
    public String logout(HttpSession session, Model model) {
        if (model.getAttribute("account") != null) {
            session.removeAttribute("account");
            session.removeAttribute("seller");
            session.removeAttribute("admin");
            model.asMap().remove("account");
            model.asMap().remove("seller");
            model.asMap().remove("admin");

            return "redirect:/home";
        }
        return "/login/login";
    }

    @PostMapping
    public String login(String email, String password, String remember,
                        Model model, HttpServletResponse response, HttpServletRequest request) {
        if (accountService.signIn(email, password, remember, model, response, request)) {
            Account account = accountService.findAccountByEmail(email);
            model.addAttribute("account", account.getAccountId());
            model.addAttribute("seller", account.getIsSeller());
            model.addAttribute("admin", account.getIsAdmin());

            return "redirect:/home";
        }
        model.addAttribute("messError", "Wrong email or password");
        return "/login/login";

    }

}
