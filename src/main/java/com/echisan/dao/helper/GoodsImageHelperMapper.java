package com.echisan.dao.helper;

import com.echisan.model.po.GoodsImage;

import java.util.List;

/**
 * @author E73AN
 */
public interface GoodsImageHelperMapper {

    void insertByBatch(List<GoodsImage> goodsImages);

}
