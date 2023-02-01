package com.example.datshopspring2.services.impl;


import com.example.datshopspring2.dto.ChangePasswordDTO;
import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.User;
import com.example.datshopspring2.repositories.AccountRepository;
import com.example.datshopspring2.services.AccountService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean signIn(String email, String password, String remember, Model model) {
        Account account = accountRepository.findAccountByEmailAndPassword(email, password);
        if (account != null) {
            if (remember != null) {
                Cookie acc = new Cookie("acc", email);
                Cookie pass = new Cookie("pass", password);
                Cookie rem = new Cookie("remember", remember);
                model.addAttribute(acc);
                model.addAttribute(pass);
                model.addAttribute(rem);
            }
        }
        return account != null;
    }

    @Override
    public Account findAccountByAccountId(Long sellerId) {
        return accountRepository.findAccountByAccountId(sellerId);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public void updatePassword(User user, ChangePasswordDTO changePasswordDTO, Model model) {
        String email = user.getEmail();
        String rootPassword = accountRepository.findPasswordByEmail(email);
        String oldPassword = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();
        String confirmNewPassword = changePasswordDTO.getRepeatNewPassword();
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            model.addAttribute("errorChange", "Please fill in the data");
            model.addAttribute("old_password", oldPassword);
            model.addAttribute("new_password", newPassword);
            model.addAttribute("confirm_new_password", confirmNewPassword);
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            model.addAttribute("old_password", oldPassword);
            model.addAttribute("errorChange", "Confirm new password not correct");
            return;
        }

        if (!oldPassword.equals(rootPassword)) {
            model.addAttribute("errorChange", "Old password not correct");
            return;
        }

        if (oldPassword.equals(newPassword)) {
            model.addAttribute("errorChange", "The password is the same");
            return;
        }

        if (newPassword.length() < 8) {
            model.addAttribute("errorChange", "Short password");
        } else {
            Account account = accountRepository.findAccountByAccountId(user.getUserId());
            account.setPassword(newPassword);
            accountRepository.save(account);
            model.addAttribute("niceChange", "Change password successful");
        }
    }

    @Override
    public void updateSeller(Long eid, String isSeller) {
        if (isSeller != null) {
            isSeller = "true";
        } else {
            isSeller = "false";
        }

        Account account = accountRepository.findAccountByAccountId(eid);
        account.setIsSeller(Boolean.valueOf(isSeller));
        accountRepository.save(account);
    }

}
