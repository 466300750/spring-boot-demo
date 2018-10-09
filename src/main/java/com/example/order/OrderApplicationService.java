package com.example.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderApplicationService {

    private final OrderRepository orderRepository;

    public OrderApplicationService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrder(Long id) {
        return orderRepository.getOne(id);
    }

    public List<Order> byUserId(Long uid) {
        return orderRepository.findOrdersByUserId(uid);
    }

    public List<Order> byStatus(int status) {
        return orderRepository.findOrdersByStatus(status);
    }

    public Page<Order> getOrderList(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
