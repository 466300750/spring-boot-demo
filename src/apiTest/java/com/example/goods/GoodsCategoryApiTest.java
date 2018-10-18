package com.example.goods;

import com.example.BaseApiTest;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GoodsCategoryApiTest extends BaseApiTest {

    @Test
    public void should_test_goods_category_successfully() {
        ResponseEntity<Long> response = testRestTemplate.postForEntity("/goods-category", constructEntity(admin_token, "category"), Long.class);
        int status = response.getStatusCodeValue();
        assertEquals(200, status);

        ResponseEntity<List<GoodsCategory>> response1 = testRestTemplate.exchange("/goods-category", HttpMethod.GET, constructEntity(admin_token, null), new ParameterizedTypeReference<List<GoodsCategory>>() {});
        assertEquals(200, response1.getStatusCodeValue());
        List<GoodsCategory> goodsCategoryList = response1.getBody();
        assertThat(goodsCategoryList.size(), is(2));

        long newGoodsCategoryId = goodsCategoryList.get(1).getId();
        ResponseEntity<Void> response2 = testRestTemplate.exchange("/goods-category/{id}", HttpMethod.DELETE, constructEntity(admin_token, null), new ParameterizedTypeReference<Void>() {}, newGoodsCategoryId);
        assertEquals(200, response2.getStatusCodeValue());
    }
}
