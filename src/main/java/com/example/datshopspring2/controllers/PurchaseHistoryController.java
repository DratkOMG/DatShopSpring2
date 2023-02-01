package com.example.datshopspring2.controllers;

import com.example.datshopspring2.models.Book;
import com.example.datshopspring2.models.Order;
import com.example.datshopspring2.models.OrderInformation;
import com.example.datshopspring2.services.OrderInformationService;
import com.example.datshopspring2.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/purchase-history")
@SessionAttributes({"account", "seller", "admin"})
public class PurchaseHistoryController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderInformationService orderInformationService;

    @GetMapping
    private String getPurchaseHistoryPage(Model model) {

        List<Order> orderList = orderService.findAllOrderByUserId((Long) model.getAttribute("account"));

        model.addAttribute("listOrders", orderList);
        return "views/history";
    }

    @GetMapping("/details")
    private String getDetailHistory(Model model, Long orderId) {
        List<OrderInformation> orderInformations = orderInformationService.findInformationOrderByOrderId(orderId);

        model.addAttribute("informations", orderInformations);
        return "views/orderInformation";
    }

    @GetMapping("/global-history")
    private String getGlobalHistory(Model model) {
        List<Order> orderList = orderService.findAllOrder();

        model.addAttribute("listOrders", orderList);
        model.addAttribute("global", true);

        return "views/history";
    }


}
