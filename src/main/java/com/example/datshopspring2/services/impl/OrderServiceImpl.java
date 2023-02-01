package com.example.datshopspring2.services.impl;

import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Order;
import com.example.datshopspring2.repositories.AccountRepository;
import com.example.datshopspring2.repositories.OrderRepository;
import com.example.datshopspring2.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Order> findAllOrderByUserId(Long id) {
        Account account = accountRepository.findAccountByAccountId(id);
        return orderRepository.findAllByUser(account);
    }

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

}
