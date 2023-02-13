package com.example.datshopspring2.controllers;


import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.User;
import com.example.datshopspring2.services.AccountService;
import com.example.datshopspring2.services.BookService;
import com.example.datshopspring2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage-employees")
public class ManageEmployeeController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private BookService bookService;

    @GetMapping
    private String getManageEmployeePage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("listEmployees", users);

        return "adminViews/managerEmployees";
    }

    @GetMapping("/load-employee")
    private String loadEmployee(Model model, Long eid) {
        Account account = accountService.findAccountByAccountId(eid);

        model.addAttribute("account", account);

        return "adminViews/editEmployee";
    }

    @GetMapping("/employee-products")
    private String loadEmployeeProduct(Model model, Long eid) {
        loadProducts(model, eid);

        return "adminViews/employeeProductManagement";
    }

    @GetMapping("/delete-product")
    private String deleteProduct(Model model, Long bid, Long eid) {
        bookService.deleteBookById(bid);

        loadProducts(model, eid);

        return "adminViews/employeeProductManagement";
    }

    @GetMapping("/delete-employee")
    private String deleteEmployee(Long eid) {
        userService.deleteUserById(eid);

        return "redirect:/manage-employees";
    }

    @PostMapping("/edit-employee")
    private String editEmployee(Long eid, String isSeller) {
        accountService.updateSeller(eid, isSeller);
        return "redirect:/manage-employees";
    }

    private void loadProducts(Model model, Long eid) {
        Account account = accountService.findAccountByAccountId(eid);

        List<Book> bookList = bookService.findBooksBySeller(account);
        User seller = userService.findUserByUserId(eid);
        model.addAttribute("listBook", bookList);
        model.addAttribute("sellerIs", seller);
    }

}
