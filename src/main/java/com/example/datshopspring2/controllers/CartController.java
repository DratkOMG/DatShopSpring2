package com.example.datshopspring2.controllers;


import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.services.BookService;
import com.example.datshopspring2.services.OrderService;
import com.example.datshopspring2.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"account", "seller", "admin"})
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    private String getCartPage(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<Book> bookList = new ArrayList<>();

        int countProduct = 0;
        for (Cookie cookie: cookies) {
            if (cookie.getName().startsWith("BookAdded")) {
                Book book = bookService.findBookByBookId(Long.parseLong(cookie.getName().substring(9)));
                try {
                    book.setQuantitySold(Integer.parseInt(cookie.getValue()));
                } catch (NumberFormatException e){
                    book.setQuantitySold(0);
                }
                bookList.add(book);
                countProduct++;
            }
        }
        int total = 0;
        for (Book book: bookList) {
            total += book.getPrice() * book.getQuantitySold();
        }
        model.addAttribute("total", total);
        model.addAttribute("listBookAdded", bookList);
        model.addAttribute("countProduct", countProduct);
        return "views/cart";
    }

    @PostMapping("/add-to-cart")
    @ResponseBody
    private void addToCart(HttpServletRequest request, HttpServletResponse response, String bookId) throws IOException {
        int count = addBookToCart(request, response, bookId);

        response.getWriter().println(count);
    }

    private int addBookToCart(HttpServletRequest request, HttpServletResponse response, String bookId) {
        Cookie[] cookies = request.getCookies();
        int count = 0;

        boolean cookieExist = false;
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(bookId)) {
                cookie.setValue(String.valueOf(Integer.parseInt(cookie.getValue()) + 1));
                cookieExist = true;
                response.addCookie(cookie);
            }

            if (cookie.getName().startsWith("BookAdded")) {
                count++;
            }
        }
        if (!cookieExist) {
            Cookie cookieBook = new Cookie(bookId, "1");
            count++;
            response.addCookie(cookieBook);
        }
        return count;
    }
}
