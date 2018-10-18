package com.example.order;

import com.example.goods.GoodsRepository;
import com.example.order.command.CreateOrderCommand;
import com.example.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final GoodsRepository goodsRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderApplicationService(OrderRepository orderRepository, GoodsRepository goodsRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.goodsRepository = goodsRepository;
        this.orderItemRepository = orderItemRepository;
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

    @Transactional
    public long createOrder(CreateOrderCommand orderCommand, long id) {
        User user = new User();
        user.setId(id);

        Order order = new Order();
        order.setUser(user);
        order.setStutas((short) 0);
        order.setAddress(orderCommand.getAddress());

        BigDecimal payment = orderCommand.getItems().entrySet()
            .stream()
            .map(it -> goodsRepository.findById(it.getKey()).map(goods -> goods.getPrice().multiply(BigDecimal.valueOf(it.getValue()))).orElse(BigDecimal.valueOf(0)))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setPayment(payment);

        long orderId = orderRepository.save(order).getId();

        List<OrderItem> items = orderCommand.getItems().entrySet()
            .stream()
            .map(it -> new OrderItem(orderId, it.getValue(), goodsRepository.findById(it.getKey()).orElseThrow(() -> new RuntimeException("The goods id not exist in system."))))
            .collect(Collectors.toList());

        items.forEach(orderItemRepository::save);

        return orderId;
    }
}
