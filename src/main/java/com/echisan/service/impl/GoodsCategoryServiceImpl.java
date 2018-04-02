package com.echisan.service.impl;

import com.echisan.dao.GoodsCategoryMapper;
import com.echisan.model.po.GoodsCategory;
import com.echisan.model.po.GoodsCategoryExample;
import com.echisan.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public List<GoodsCategory> listGoodsCategoryList(Short categoryId) {
        GoodsCategoryExample goodsCategoryExample = new GoodsCategoryExample();
        goodsCategoryExample.createCriteria().andCategoryIdEqualTo(categoryId);
        return goodsCategoryMapper.selectByExample(goodsCategoryExample);
    }
}
