package com.example.goods;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsCategoryApplicationService {
    private GoodsCategoryRepository goodsCategoryRepository;

    public GoodsCategoryApplicationService(GoodsCategoryRepository goodsCategoryRepository) {
        this.goodsCategoryRepository = goodsCategoryRepository;
    }


    public long addGoodsCategory(String goodsCategoryName) {
        GoodsCategory goodsCategory = new GoodsCategory(goodsCategoryName);
        return goodsCategoryRepository.save(goodsCategory).getId();
    }

    @Transactional
    public void deleteGoodsCategory(Long id) {
        goodsCategoryRepository.deleteById(id);
    }

    @Transactional
    public List<GoodsCategory> listGoodsCategory() {
        return goodsCategoryRepository.findAll();
    }
}
