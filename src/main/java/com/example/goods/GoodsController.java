package com.example.goods;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
    private GoodsApplicationService goodsApplicationService;

    public GoodsController(GoodsApplicationService goodsApplicationService) {
        this.goodsApplicationService = goodsApplicationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public long addGoods(@RequestBody Goods goods) {
        return goodsApplicationService.saveGoods(goods);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void deleteGoods(@PathVariable Long id) {
        goodsApplicationService.deleteGoods(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    public Goods getGoods(@PathVariable Long id) {
        return goodsApplicationService.byId(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    public List<Goods> getGoods() {
        return goodsApplicationService.goodsList();
    }

}
