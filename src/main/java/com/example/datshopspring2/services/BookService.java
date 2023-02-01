package com.example.datshopspring2.services;

import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Categories;
import org.springframework.ui.Model;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    List<Book> findByLikeTitle(String search);

    List<Book> findAllOrderByBookId();

    List<Book> findAllByCategoriesId(Categories categories);

    Book findBookByBookId(Long bookId);


    List<Book> findBooksBySeller(Account account);

    List<Book> findTop12();

    List<Book> findNext3Book(Integer amount);

    List<Book> findNext3ByCategoriesId(Integer amount, Long categoriesId);

    List<Book> findTop10BookBySellerId(Long account);

    List<Book> findNext10BookBySellerId(int exits, Long id);

    boolean addBook(Model model, Book book, Account account);

    void deleteBookById(Long bid);

    void updateBook(Model model, Book book, Long bid);
}
