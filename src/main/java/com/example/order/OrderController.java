package com.example.order;

import com.example.order.command.CreateOrderCommand;
import com.example.security.jwt.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @PreAuthorize(value = "hasRole('USER')")
    public long createOrder(@RequestBody CreateOrderCommand orderCommand,
                            @AuthenticationPrincipal UserPrincipal principal) {
        return orderApplicationService.createOrder(orderCommand, principal.getId());
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('USER')")
    public Order getOrderById(@PathVariable long id) {
        return orderApplicationService.getOrder(id);
    }


    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @PreAuthorize(value = "hasAnyRole('USER')")
    public Page<Order> getOrders(@RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "20") int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return orderApplicationService.getOrderList(pageable);
    }
}
