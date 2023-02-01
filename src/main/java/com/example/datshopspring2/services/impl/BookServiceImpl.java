package com.example.datshopspring2.services.impl;

import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Categories;
import com.example.datshopspring2.repositories.BookRepository;
import com.example.datshopspring2.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByLikeTitle(String search) {
        search = "%" + search + "%";
        return bookRepository.findAllByTitleIsLike(search);
    }

    @Override
    public List<Book> findAllOrderByBookId() {
        return bookRepository.findAllOrderByBookId();
    }

    @Override
    public List<Book> findAllByCategoriesId(Categories categories) {
        return bookRepository.findAllByCategoriesId(categories);
    }

    @Override
    public Book findBookByBookId(Long bookId) {
        return bookRepository.findBookByBookId(bookId);
    }

    @Override
    public List<Book> findBooksBySeller(Account account) {
        return bookRepository.findAllBySeller(account);
    }

    @Override
    public List<Book> findTop12() {
        return bookRepository.findAllLimit12();
    }

    @Override
    public List<Book> findNext3Book(Integer amount) {
        return bookRepository.findNext3Book(amount);
    }

    @Override
    public List<Book> findNext3ByCategoriesId(Integer amount, Long categoriesId) {
        return bookRepository.findNext3ByCategoriesId(amount, categoriesId);
    }

    @Override
    public List<Book> findTop10BookBySellerId(Long id) {
        return bookRepository.findTop10BookBySellerId(id);
    }

    @Override
    public List<Book> findNext10BookBySellerId(int exits, Long id) {
        return bookRepository.findNext10BookBySellerId(exits, id);
    }

    @Override
    public boolean addBook(Model model, Book book, Account account) {
        boolean f = true;

        String title = book.getTitle();
        String image = book.getImage();
        Integer price = book.getPrice();
        String author = book.getAuthor();
        String description = book.getDescription();
        Categories category = book.getCategoriesId();

        model.addAttribute("title", title);
        model.addAttribute("image", image);
        model.addAttribute("price", price);
        model.addAttribute("author", author);
        model.addAttribute("description", description);
        model.addAttribute("categoriesId", category);

        if (title.isEmpty() || title.isBlank()) {
            model.addAttribute("errorTitle", "Title where bro?");
            f = false;
        }
        if (image.isBlank() || image.isEmpty()) {
            model.addAttribute("errorImage", "We need image");
            f = false;
        }
        if (price < 0) {
            model.addAttribute("price", price);
            model.addAttribute("errorPrice", "??");
            f = false;
        }
        if (author.isEmpty() || author.isBlank()) {
            model.addAttribute("errorAuthor", "Who is author, bro?");
            f = false;
        }
        if (description.isBlank() || description.isEmpty()) {
            model.addAttribute("errorDescription", "Hmm, is bad book?");
            f = false;
        }

        if (f) {
            Book book1 = Book.builder()
                    .title(title)
                    .image(image)
                    .price(price)
                    .author(author)
                    .quantitySold(0)
                    .description(description)
                    .categoriesId(category)
                    .seller(account)
                    .build();
            bookRepository.save(book1);
            model.addAttribute("Done", "Added!");

        }
        return f;
    }

    @Override
    public void deleteBookById(Long bid) {
        bookRepository.deleteById(bid);
    }

    @Override
    public void updateBook(Model model, Book book, Long bid) {
        changeTitle(model, book.getTitle(), bid);
        changeImage(model, book.getImage(), bid);
        changePrice(model, book.getPrice(), bid);
        changeAuthor(model, book.getAuthor(), bid);
        changeDescription(model, book.getDescription(), bid);
        changeCategoryId(model, book.getCategoriesId(), bid);
    }
    public void changeTitle(Model model, String title, Long bid) {
        if (title.isEmpty() || title.isBlank()) {
            model.addAttribute("errorTitle", "Bad Title :((");
        } else if (!title.equals(bookRepository.findBookByBookId(bid).getTitle())) {
            Book book = bookRepository.findBookByBookId(bid);
            book.setTitle(title);
            bookRepository.save(book);
            model.addAttribute("niceTitle", "Niceeee!");
        }
    }

    public void changeImage(Model model, String image, Long bid) {
        if (image.isBlank() || image.isEmpty()) {
            model.addAttribute("errorImage", "We need image :((");
        } else if (!image.equals(bookRepository.findBookByBookId(bid).getImage())) {
            Book book = bookRepository.findBookByBookId(bid);
            book.setImage(image);
            bookRepository.save(book);
            model.addAttribute("niceImage", "Wow, nice");
        }
    }

    public void changePrice(Model model, Integer price, Long bid) {
        if (price >= 1) {
            if (!price.equals(bookRepository.findBookByBookId(bid).getPrice())) {
                Book book = bookRepository.findBookByBookId(bid);
                book.setPrice(price);
                bookRepository.save(book);
                model.addAttribute("nicePrice", "Done!");
            }
        } else {
            model.addAttribute("price", price);
            model.addAttribute("errorPrice", "Your price is not correct");
        }
    }

    public void changeAuthor(Model model, String author, Long bid) {
        if (author.isEmpty() || author.isBlank()) {
            model.addAttribute("errorAuthor", "Who is author, bro?");
        } else if (!author.equals(bookRepository.findBookByBookId(bid).getAuthor())) {
            Book book = bookRepository.findBookByBookId(bid);
            book.setAuthor(author);
            bookRepository.save(book);
            model.addAttribute("niceAuthor", "Good bro");
        }
    }

    public void changeDescription(Model model, String description, Long bid) {
        if (description.isBlank() || description.isEmpty()) {
            model.addAttribute("errorDescription", "Description pleaseee!!");
        } else if (!description.equals(bookRepository.findBookByBookId(bid).getDescription()) && !description.isBlank()) {
            Book book = bookRepository.findBookByBookId(bid);
            book.setDescription(description);
            bookRepository.save(book);
            model.addAttribute("niceDescription", "Changed!");
        }
    }

    public void changeCategoryId(Model model, Categories categories, Long bid) {
        if (!categories.equals(bookRepository.findBookByBookId(bid).getCategoriesId())) {
            Book book = bookRepository.findBookByBookId(bid);
            book.setCategoriesId(categories);
            bookRepository.save(book);
            model.addAttribute("niceCategory", "Doneee");
        }
    }
}
