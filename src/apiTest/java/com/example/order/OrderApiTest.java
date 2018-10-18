package com.example.order;

import com.example.BaseApiTest;
import com.example.order.command.CreateOrderCommand;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OrderApiTest extends BaseApiTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void should_test_order_successfully() throws Exception {

        Map<Long, Integer> items = new HashMap<>();
        items.put(1L, 1);
        items.put(2L, 1);
        CreateOrderCommand createOrderCommand = new CreateOrderCommand("address", items);

        ResponseEntity<Long> response = testRestTemplate.postForEntity("/orders", constructEntity(user_token, createOrderCommand), Long.class);
        int status = response.getStatusCodeValue();
        assertEquals(200, status);

        orderRepository.deleteById(2L);
        orderItemRepository.deleteById(3L);
    }

    @Test
    public void should_get_order_list_successfully() throws Exception {

        ResponseEntity<Map> response = testRestTemplate.exchange("/orders", HttpMethod.GET, constructEntity(user_token, null), Map.class);
        assertEquals(200, response.getStatusCodeValue());
        List<Order> orders = (List<Order>) response.getBody().get("content");
        assertThat(orders.size(), is(1));
    }
}
