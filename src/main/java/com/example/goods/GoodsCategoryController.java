package com.example.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/good-category")
public class GoodsCategoryController {
    @Autowired
    private GoodsCategoryApplicationService goodsCategoryApplicationService;


    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public long addGoodsCategory(@RequestBody String goodsCategoryName) {
        return goodsCategoryApplicationService.addGoodsCategory(goodsCategoryName);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteGoodsCategory(@PathVariable Long id) {
        goodsCategoryApplicationService.deleteGoodsCategory(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<GoodsCategory> goodsCategoryList() {
        return goodsCategoryApplicationService.listGoodsCategory();
    }


}
