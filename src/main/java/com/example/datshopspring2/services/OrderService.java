package com.example.datshopspring2.services;

import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrderByUserId(Long accountId);

    List<Order> findAllOrder();

    long saveOrder(Order order);

    void saveInformationOrder(long orderId, List<Book> bookList, List<Integer> quantity);
}
