package com.example.datshopspring2.repositories;

import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.user = ?1 order by o.id")
    List<Order> findAllByUser(Account account);

    @Query("select o from Order o order by o.id")
    List<Order> findAll();

}