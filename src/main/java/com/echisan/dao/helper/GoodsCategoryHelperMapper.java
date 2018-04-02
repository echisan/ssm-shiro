package com.echisan.dao.helper;

import com.echisan.model.po.GoodsCategory;

import java.util.List;

/**
 * @author E73AN
 */
public interface GoodsCategoryHelperMapper {

    /**
     * 批量插入
     * @param goodsCategories 列表
     */
    void insertByBatch(List<GoodsCategory> goodsCategories);
}
