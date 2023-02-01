package com.example.datshopspring2.repositories;

import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Categories;
import com.example.datshopspring2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b order by b.bookId")
    List<Book> findAll();

//    List<Book> findAllBySeller(Account seller);

    @Query("select b from Book b order by b.bookId")
    List<Book> findAllOrderByBookId();

    Book findFirstByOrderByBookIdDesc();

    Book findFirstByOrderByQuantitySoldDesc();

    Book findBookByBookId(Long id);

    List<Book> findAllByCategoriesId(Categories categoriesId);

    @Query(value = "select * from book where title ilike ?1", nativeQuery = true)
    List<Book> findAllByTitleIsLike(String title);

    @Query("select b from Book b where b.seller = ?1 order by b.bookId")
    List<Book> findAllBySeller(Account seller);

    @Query(value = "select * from Book order by book_id limit 12", nativeQuery = true)
    List<Book> findAllLimit12();

    @Query(value = "select * from book order by book_id offset ?1 rows fetch next 3 rows only", nativeQuery = true)
    List<Book> findNext3Book(Integer amount);

    @Query(value = "select * from book where categories_id = ?2 order by book_id offset ?1 rows fetch next 3 rows only", nativeQuery = true)
    List<Book> findNext3ByCategoriesId(Integer amount, Long categoriesId);

    @Query(value = "select * from book where seller_id = ?1 order by book_id limit 10", nativeQuery = true)
    List<Book> findTop10BookBySellerId(Long id);

    @Query(value = "select * from book where seller_id = ?2 order by book_id offset ?1 rows fetch next 10 rows only", nativeQuery = true)
    List<Book> findNext10BookBySellerId(int exits, Long id);
}