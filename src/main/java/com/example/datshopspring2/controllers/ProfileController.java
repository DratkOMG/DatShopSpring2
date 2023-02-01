package com.example.datshopspring2.controllers;


import com.example.datshopspring2.dto.ChangePasswordDTO;
import com.example.datshopspring2.dto.ChangeProfileDTO;
import com.example.datshopspring2.models.User;
import com.example.datshopspring2.services.AccountService;
import com.example.datshopspring2.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
@SessionAttributes({"account", "seller", "admin"})
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping
    private String getProfilePage(Model model) {
        User user = userService.findUserByUserId((Long) model.getAttribute("account"));
        addAttributes(model, user);
        return "views/profile";
    }

    @PostMapping("/save-profile")
    private String changeProfile(ChangeProfileDTO changeProfileDTO, Model model) {
        User user = userService.findUserByUserId((Long) model.getAttribute("account"));
        userService.updateProfile(user, changeProfileDTO, model);

        addAttributes(model, user);
        return "views/profile";
    }

    @PostMapping("/change-password")
    private String changePassword(ChangePasswordDTO changePasswordDTO, Model model) {
        User user = userService.findUserByUserId((Long) model.getAttribute("account"));
        accountService.updatePassword(user, changePasswordDTO, model);

        addAttributes(model, user);
        return "views/profile";
    }

    @PostMapping("/donate")
    private String donate(Integer donate, Model model) {
        User user = userService.findUserByUserId((Long) model.getAttribute("account"));
        userService.updateBalance(user, donate, model);

        addAttributes(model, user);
        return "views/profile";
    }
    private static void addAttributes(Model model, User user) {
        model.addAttribute("userProfile", user);

        List<String> cities = new ArrayList<>();
        cities.add("Samara");
        cities.add("Ufa");
        cities.add("Moscow");
        cities.add("Kazan");
        cities.add("Saint Petersburg");
        cities.add("Novosibirsk");
        cities.add("Yekaterinburg");
        cities.add("Nizhny Novgorod");
        cities.add("Chelyabinsk");
        cities.add("Krasnoyarsk");

        model.addAttribute("cities", cities);
    }
}
