package com.example.datshopspring2.controllers;


import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Order;
import com.example.datshopspring2.models.User;
import com.example.datshopspring2.services.AccountService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
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

    @Autowired
    private AccountService accountService;


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

    @PostMapping("/buy-product")
    private String buyProduct(Integer total, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        total = -total;

        User user = userService.findUserByUserId((Long) model.getAttribute("account"));

        if (user.getBalance() < (-total)) {
            redirectAttributes.addFlashAttribute("errorBuy", "Money??");
            return "redirect:/cart";
        } else {
            userService.buy(total, user);

            Order order = Order.builder()
                    .date(OffsetDateTime.now())
                    .price(-total)
                    .user(accountService.findAccountByAccountId(user.getUserId()))
                    .userName(user.getUserName())
                    .build();
            long orderId = orderService.saveOrder(order);

            List<Book> bookList = new ArrayList<>();
            List<Integer> quantity = new ArrayList<>();

            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().startsWith("BookAdded")) {
                    bookList.add(bookService.findBookByBookId(Long.parseLong(cookie.getName().substring(9))));
                    quantity.add(Integer.valueOf(cookie.getValue()));
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }

            orderService.saveInformationOrder(orderId, bookList, quantity);
        }
        return "redirect:/home";
    }

    @GetMapping("/update-cart")
    private String updateCart(Long bid, HttpServletRequest request, HttpServletResponse response) {
        String book = "BookAdded" + bid;

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(book)) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        return "redirect:/cart";
    }

    @GetMapping("/add-to-cart")
    private String addToCartAndLoadPage(HttpServletRequest request, HttpServletResponse response, String bid) {
        String book = "BookAdded" + bid;
        int count = addBookToCart(request, response, book);

        return "redirect:/cart";
    }

    @PostMapping("/add-to-cart")
    @ResponseBody
    private void addToCart(HttpServletRequest request, HttpServletResponse response, String bookId) throws IOException {
        String book = "BookAdded" + bookId;
        int count = addBookToCart(request, response, book);

        response.getWriter().println(count);
    }

    private int addBookToCart(HttpServletRequest request, HttpServletResponse response, String book) {
        Cookie[] cookies = request.getCookies();
        int count = 0;

        boolean cookieExist = false;
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(book)) {
                cookie.setPath("/");
                cookie.setValue(String.valueOf(Integer.parseInt(cookie.getValue()) + 1));
                cookieExist = true;
                response.addCookie(cookie);
            }

            if (cookie.getName().startsWith("BookAdded")) {
                count++;
            }
        }
        if (!cookieExist) {
            Cookie cookieBook = new Cookie(book, "1");
            cookieBook.setPath("/");
            count++;
            response.addCookie(cookieBook);
        }
        return count;
    }
}
