package com.example.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Orders getOrderById(@PathVariable long id) {
        return orderService.getOrder(id);
    }


    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public List<Orders> getOrderByUserId(@PathVariable long id) {
        return orderService.getListByUser(id);
    }

    // todo:  "/orders", post生成订单的逻辑

}
