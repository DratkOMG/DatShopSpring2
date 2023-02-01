package com.example.datshopspring2.controllers;

import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Categories;
import com.example.datshopspring2.models.User;
import com.example.datshopspring2.services.AccountService;
import com.example.datshopspring2.services.BookService;
import com.example.datshopspring2.services.CategoriesService;
import com.example.datshopspring2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
@SessionAttributes({"account", "seller", "admin"})
public class HomeController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private LeftShow leftShow;

    @RequestMapping
    public String getHomePage(Model model) {
        leftShow.show(model);
        List<Book> bookList = bookService.findTop12();
        model.addAttribute("listBook", bookList);
        return "/views/home_page";
    }

    @RequestMapping("/category")
    public String getBooksByCategory(Model model, @RequestParam("cid") Long categoriesId) {
        leftShow.show(model);
        Categories categories = categoriesService.findByCategoriesId(categoriesId);
        List<Book> bookList = bookService.findAllByCategoriesId(categories);

        model.addAttribute("listBook", bookList);
        return "/views/home_page";
    }

    @RequestMapping("/show-book")
    private String showBookPage(Model model, @RequestParam("bid") Long bookId) {
        leftShow.show(model);

        Book book = bookService.findBookByBookId(bookId);
        model.addAttribute("book", book);

        User sellerName = userService.findUserByUserId(book.getSeller().getAccountId());
        model.addAttribute("sellerIs", sellerName);
        return "/views/detail";
    }

    @RequestMapping("/book-by")
    private String showBooksByUserId(Model model, @RequestParam("uid") Long sellerId) {
        leftShow.show(model);
        Account account = accountService.findAccountByAccountId(sellerId);
        List<Book> bookList = bookService.findBooksBySeller(account);
        model.addAttribute("listBook", bookList);

        return "/views/home_page";

    }

    @PostMapping("/live-search")
    @ResponseBody
    private String updateBooksSearch(@RequestParam String search, Model model) {
        leftShow.show(model);
        List<Book> bookList;

        if (search.isEmpty()) {
            bookList = bookService.findAll();
        } else {
            bookList = bookService.findByLikeTitle(search);
        }

        StringBuffer stringBuffer = getStringBuffer(bookList);

        return stringBuffer.toString();
    }


    @PostMapping("/load-more")
    @ResponseBody
    private String loadMoreBook(@RequestParam Integer exits, Model model) {
        List<Book> bookList;

        bookList = bookService.findNext3Book(exits);
        StringBuffer stringBuffer = getStringBuffer(bookList);

        return stringBuffer.toString();
    }

    private static StringBuffer getStringBuffer(List<Book> bookList) {
        StringBuffer stringBuffer = new StringBuffer();

        for (Book book : bookList) {
            stringBuffer.append("<div class=\"product col-12 col-md-6 col-lg-4\">\n" +
                    "                        <div class=\"card\">\n" +
                    "                            <img class=\"\" src=\"" + book.getImage() + "\" alt=\"Book image\">\n" +
                    "                            <div class=\"card-body\">\n" +
                    "                                <h4 class=\"card-title show_txt\">\n" +
                    "                                    <a href=\"/home/show-book?bid=" + book.getBookId() + "\" title=\"View Product\">" + book.getTitle() + "</a>\n" +
                    "                                </h4>\n" +
                    "                                <p class=\"card-text show_txt\">" + book.getDescription() + "</p>\n" +
                    "                                <div class=\"row\">\n" +
                    "                                    <div class=\"col\">\n" +
                    "                                         <a href=\"/DatBookShop_war_exploded/add_cart_controller?bid=" + book.getBookId() + "\" class=\"btn btn-danger btn-block\">" + book.getPrice() + " $</a>\n" +
                    "                                    </div>\n" +
                    "                                    <div class=\"col\">\n" +
                    "                                          <button onclick=\"addToCart(" + book.getBookId() + ")\" class=\"btn btn-success btn-block\">Add to cart</button>\n" +
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>");
        }
        return stringBuffer;
    }

}
