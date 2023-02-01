package com.example.datshopspring2.controllers;

import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Categories;
import com.example.datshopspring2.repositories.BookRepository;
import com.example.datshopspring2.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Component
public class LeftShow {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public void show(Model model) {
        List<Categories> categoriesList = categoriesRepository.findAll();
        model.addAttribute("listCategories", categoriesList);

        Book hotBook = bookRepository.findFirstByOrderByQuantitySoldDesc();
        model.addAttribute("hotBook", hotBook);

        Book lastBook = bookRepository.findFirstByOrderByBookIdDesc();
        model.addAttribute("lastBook", lastBook);

    }

}
