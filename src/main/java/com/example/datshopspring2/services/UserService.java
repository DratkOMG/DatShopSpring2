package com.example.datshopspring2.services;

import com.example.datshopspring2.dto.ChangeProfileDTO;
import com.example.datshopspring2.models.User;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService {
    User findUserByUserId(Long accountId);

    void updateProfile(User user, ChangeProfileDTO changeProfileDTO, Model model);

    void updateBalance(User user, Integer money, Model model);

    List<User> findAll();

    void deleteUserById(Long eid);

    void buy(Integer total, User user);
}
