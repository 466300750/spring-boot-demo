package com.example.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsApplicationService {

    private GoodsRepository goodsRepository;
    private GoodsCategoryRepository goodsCategoryRepository;

    @Autowired
    public GoodsApplicationService(GoodsRepository goodsRepository, GoodsCategoryRepository goodsCategoryRepository) {
        this.goodsRepository = goodsRepository;
        this.goodsCategoryRepository = goodsCategoryRepository;
    }


    public long saveGoods(Goods goods) {
        return goodsRepository.save(goods).getId();
    }

    public void deleteGoods(Long id) {
        goodsRepository.deleteById(id);
    }

    public Goods byId(Long id) {
        return goodsRepository.getOne(id);
    }

    public List<Goods> goodsList() {
        return goodsRepository.findAll();
    }

}
