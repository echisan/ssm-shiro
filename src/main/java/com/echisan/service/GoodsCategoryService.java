package com.echisan.service;

import com.echisan.model.po.GoodsCategory;

import java.util.List;

public interface GoodsCategoryService {

    List<GoodsCategory> listGoodsCategoryList(Short categoryId);
}
