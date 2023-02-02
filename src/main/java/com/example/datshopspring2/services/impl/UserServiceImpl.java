package com.example.datshopspring2.services.impl;

import com.example.datshopspring2.dto.ChangeProfileDTO;
import com.example.datshopspring2.models.User;
import com.example.datshopspring2.repositories.UserRepository;
import com.example.datshopspring2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUserId(Long accountId) {
        return userRepository.findUserByUserId(accountId);
    }

    @Override
    public void updateProfile(User user, ChangeProfileDTO changeProfileDTO, Model model) {
        changeUsername(changeProfileDTO.getUsername(), model, user);
        changePhoneNumber(changeProfileDTO.getPhoneNumber(), model, user);
        changeCity(changeProfileDTO.getCity(), model, user);
        changeSex(changeProfileDTO.getSex(), model, user);
        changeAge(changeProfileDTO.getAge(), model, user);
    }

    @Override
    public void updateBalance(User user, Integer money, Model model) {
        if (money < 1) {
            model.addAttribute("errorDonate", "???");
        } else {
            user.setBalance(user.getBalance() + money);
            userRepository.save(user);
            model.addAttribute("niceDonate", "Done");
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long eid) {
        userRepository.deleteById(eid);
    }

    @Override
    public void buy(Integer total, User user) {
        user.setBalance(user.getBalance() + total);
        userRepository.save(user);
    }


    private void changeAge(Integer age, Model model, User user) {
        String email = user.getEmail();
        try {
            if (!(age > 6 && age < 120)) {
                model.addAttribute("errorAge", "Are you ok???");
            } else if (!age.equals(userRepository.findUserByEmail(email).getAge())) {
                user.setAge(age);
                userRepository.save(user);
                model.addAttribute("niceAge", "Niceee");
            }
        } catch (Exception e) {
            model.addAttribute("errorAge", "Whatttt???");
        }
    }

    private void changeSex(String sex, Model model, User user) {
        String email = user.getEmail();

        if (!sex.equals(userRepository.findUserByEmail(email).getSex())) {
            user.setSex(sex);
            userRepository.save(user);
            model.addAttribute("niceSex", "Done");
        }
    }

    private void changeCity(String city, Model model, User user) {
        String email = user.getEmail();

        if (!city.equals(userRepository.findUserByEmail(email).getCity())) {
            user.setCity(city);
            userRepository.save(user);
            model.addAttribute("niceCity", "Done!");
        }
    }

    private void changePhoneNumber(String phoneNumber, Model model, User user) {
        String email = user.getEmail();

        String regex = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (!matcher.matches()) {
            model.addAttribute("errorPhoneNumber", "Your number phone is not correct");
            model.addAttribute("number_phone", phoneNumber);
        } else if (!phoneNumber.equals(userRepository.findUserByEmail(email).getPhoneNumber())) {
            user.setPhoneNumber(phoneNumber);
            userRepository.save(user);
            model.addAttribute("nicePhoneNumber", "Number phone changed");
        }
    }


    private void changeUsername(String username, Model model, User user) {
        String email = user.getEmail();
        if (username.isEmpty() || username.isBlank()) {
            model.addAttribute("errorUsername", "Please fill username");
        } else if (username.length() < 3) {
            model.addAttribute("errorUsername", "Minimum username length - 3");
        } else if (!username.equals(userRepository.findUserByEmail(email).getUserName())) {
            if (userRepository.findUserByUserName(username) != null) {
                model.addAttribute("username", username);
                model.addAttribute("errorUsername", "Username used");
            } else {
                user.setUserName(username);
                userRepository.save(user);
                model.addAttribute("niceUsername", "Username changed");
            }
        }
    }
}
