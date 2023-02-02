package com.example.datshopspring2.services.impl;

import com.example.datshopspring2.models.Account;
import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Order;
import com.example.datshopspring2.models.OrderInformation;
import com.example.datshopspring2.repositories.AccountRepository;
import com.example.datshopspring2.repositories.BookRepository;
import com.example.datshopspring2.repositories.OrderInformationRepository;
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
    @Autowired
    private OrderInformationRepository orderInformationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Order> findAllOrderByUserId(Long id) {
        Account account = accountRepository.findAccountByAccountId(id);
        return orderRepository.findAllByUser(account);
    }

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public long saveOrder(Order order) {
        return orderRepository.save(order).getId();
    }

    @Override
    public void saveInformationOrder(long orderId, List<Book> bookList, List<Integer> quantity) {
        for (int i = 0; i < bookList.size(); i++) {
            OrderInformation orderInformation = OrderInformation.builder()
                    .order(orderRepository.findById(orderId).get())
                    .title(bookList.get(i).getTitle())
                    .image(bookList.get(i).getImage())
                    .price(bookList.get(i).getPrice() * quantity.get(i))
                    .quantity(quantity.get(i))
                    .build();
            orderInformationRepository.save(orderInformation);

            bookList.get(i).setQuantitySold(bookList.get(i).getQuantitySold() + quantity.get(i));
            bookRepository.save(bookList.get(i));
        }
    }
}
