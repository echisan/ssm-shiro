package com.echisan.service;

import com.echisan.model.bo.GoodsBO;
import com.echisan.model.po.Goods;

import java.util.List;

/**
 * @author E73AN
 */
public interface GoodsService {

    Byte HAVE_NOT_SELL = 0;
    Byte HAD_SELL = 1;

    /**
     * 添加物品
     * @param goodsBO 物品的包装类
     */
    void saveGoods(GoodsBO goodsBO);

    /**
     * 添加物品
     * @param goods 物品
     * @param categoriesIds 分类
     */
    void saveGoods(Goods goods, Short[] categoriesIds);

    /**
     * 删除物品
     * @param goodsId id
     */
    void deleteGoodsById(Long goodsId);

    /**
     * 更新物品
     * @param goods 物品
     */
    void updateGoods(Goods goods);

    void updateGoodsViewsNum(Long goodsId);

    void updateGoodsCategory(Long goodsId, Short[] categoriesIds);

    void updateGoodsLastChangeTime(Long goodsId);

    /**
     * 获取物品
     * @param goodsId id
     * @return 物品
     */
    Goods getGoodsById(Long goodsId);

    /**
     * 获取所有物品
     * @return 物品列表
     */
    List<Goods> listGoods();

    List<Goods> listGoodsByUserId(Long userId);

    /**
     * 获取热门物品
     * @return 热门物品列表
     */
    List<Goods> listHotGoods();

    /**
     * 获取新上架的物品
     * @return 新上架物品的列表
     */
    List<Goods> listNewGoods();

    /**
     * 获取某个类别的商品
     * @param categoryId 类别id
     * @return
     */
    List<Goods> listGoodsByCategoryId(Short categoryId);


    void deleteGoodsOneImageByImageId(Long imageId);

    List<Goods> listGoodsByIds(List<Long> goodsIds);

    List<Goods> listGoodsByKeyWords(String keywords);

    List<Goods> listGoodsByUsername(String username);
}
