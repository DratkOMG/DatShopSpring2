package com.example.datshopspring2.services;

import com.example.datshopspring2.dto.ChangePasswordDTO;
import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.User;
import org.springframework.ui.Model;

public interface AccountService {

//    boolean signIn(Model model, Account account);
    boolean signIn(String email, String password, String remember, Model response);

    Account findAccountByAccountId(Long sellerId);

    Account findAccountByEmail(String email);

    void updatePassword(User user, ChangePasswordDTO changePasswordDTO, Model model);

    void updateSeller(Long eid, String isSeller);
}
