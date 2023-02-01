package com.example.datshopspring2.services;

import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrderByUserId(Long accountId);

    List<Order> findAllOrder();
}
