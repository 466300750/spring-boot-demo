package com.example.order;

import com.example.BaseApiTest;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OrderApiTest extends BaseApiTest {

    @Test
    public void should_create_order_successfully() throws Exception {

    }

    @Test
    public void should_get_order_list_successfully() throws Exception {
        ResponseEntity<Map> response = testRestTemplate.exchange("/orders", HttpMethod.GET, constructEntity(admin_token, null), Map.class);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void should_get_order_detail_successfully() throws Exception {
    }
}
