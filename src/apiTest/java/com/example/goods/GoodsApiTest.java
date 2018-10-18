package com.example.goods;

import com.example.BaseApiTest;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GoodsApiTest extends BaseApiTest {

    @Test
    public void should_test_goods_successfully() {
        Goods goods = new Goods();
        goods.setGoodsCategory(new GoodsCategory(1, "goodsCategory"));
        goods.setName("goods");
        goods.setPrice(new BigDecimal(5.5));
        goods.setIntroduce("introduce");

        ResponseEntity<Long> response = testRestTemplate.postForEntity("/goods", constructEntity(admin_token, goods), Long.class);
        int status = response.getStatusCodeValue();
        assertEquals(200, status);

        ResponseEntity<List<Goods>> response1 = testRestTemplate.exchange("/goods", HttpMethod.GET, constructEntity(admin_token, null), new ParameterizedTypeReference<List<Goods>>() {});
        assertEquals(200, response1.getStatusCodeValue());
        List<Goods> userList = response1.getBody();
        assertThat(userList.size(), is(4));

        long newUserId = userList.get(3).getId();
        ResponseEntity<Void> response2 = testRestTemplate.exchange("/goods/{id}", HttpMethod.DELETE, constructEntity(admin_token, null), new ParameterizedTypeReference<Void>() {}, newUserId);
        assertEquals(200, response2.getStatusCodeValue());
    }
}
