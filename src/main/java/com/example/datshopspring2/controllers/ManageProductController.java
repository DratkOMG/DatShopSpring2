package com.example.datshopspring2.controllers;


import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Categories;
import com.example.datshopspring2.services.AccountService;
import com.example.datshopspring2.services.BookService;
import com.example.datshopspring2.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage-product")
@SessionAttributes({"account", "seller", "admin"})
public class ManageProductController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    private String getManageProductPage(Model model) {
        Long id = (Long) model.getAttribute("account");
        List<Book> bookList = bookService.findTop10BookBySellerId(id);
        model.addAttribute("listBook", bookList);
        return "managerViews/managerProduct";
    }

    @GetMapping("/load-more")
    @ResponseBody
    private String loadMoreBook(Model model, int exits) {
        Long id = (Long) model.getAttribute("account");
        List<Book> bookList = bookService.findNext10BookBySellerId(exits, id);
        StringBuffer stringBuffer = new StringBuffer();
        for (Book book : bookList) {
            stringBuffer.append("<tr class=\"product\">\n" +
                    "                    <td>" + book.getBookId() + "</td>\n" +
                    "                    <td>" + book.getTitle() + "</td>\n" +
                    "                    <td>\n" +
                    "                        <img src=\"" + book.getImage() + "\">\n" +
                    "                    </td>\n" +
                    "                    <td>" + book.getPrice() + " $</td>\n" +
                    "                    <td>" + book.getQuantitySold() + " $</td>\n" +
                    "                    <td> " + book.getAuthor() + " </td>\n" +
                    "                    <td>" + book.getDescription() + " </td>\n" +
                    "                    <td>\n" +
                    "                        <a href=\"/manage-product/load-product?bid=" + book.getBookId() + "\" class=\"edit\" data-toggle=\"modal\">\n" +
                    "                            <i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Edit\">&#xE254;</i>\n" +
                    "                        </a>\n" +
                    "                        <a href=\"/manage-product/delete-product?bid=" + book.getBookId() + "\" class=\"delete\" data-toggle=\"modal\">\n" +
                    "                            <i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Delete\">&#xE872;</i>\n" +
                    "                        </a>\n" +
                    "                    </td>\n" +
                    "                </tr>");
        }

        return stringBuffer.toString();
    }


    @GetMapping("/add-product")
    private String loadAddProductPage(Model model) {
        List<Categories> categoriesList = categoriesService.findAll();
        model.addAttribute("listCategory", categoriesList);
        return "managerViews/addProduct";
    }

    @PostMapping("/add-product")
    private String addProduct(Model model, Book book) {
        Account account = accountService.findAccountByAccountId((Long) model.getAttribute("account"));
        if (!bookService.addBook(model, book, account)) {
            List<Categories> categoriesList = categoriesService.findAll();
            model.addAttribute("listCategory", categoriesList);
            return "managerViews/addProduct";
        }
        return "redirect:/manage-product";

    }

    @GetMapping("/delete-product")
    private String deleteProduct(Long bid) {
        bookService.deleteBookById(bid);
        return "redirect:/manage-product";
    }

    @GetMapping("/load-product")
    private String loadProduct(Long bid, Model model) {
        Book book = bookService.findBookByBookId(bid);
        model.addAttribute("book", book);

        List<Categories> categoriesList = categoriesService.findAll();
        model.addAttribute("listCategory", categoriesList);
        return "managerViews/editProduct";
    }

    @PostMapping("/edit-product")
    private String editProduct(Model model, Book book, Long bid) {
        bookService.updateBook(model, book, bid);
        Book book1 = bookService.findBookByBookId(bid);
        model.addAttribute("book", book1);

        List<Categories> categoriesList = categoriesService.findAll();
        model.addAttribute("listCategory", categoriesList);
        return "managerViews/editProduct";
    }

    @PostMapping("/add-category")
    private String addCategory(String category) {
        categoriesService.addNewCategory(category);
        return "redirect:/manage-product";
    }


}
