package com.echisan.service;

import com.echisan.model.po.Image;

import java.util.List;

/**
 * @author E73AN
 */
public interface ImageService {

    /**
     * 保存照片地址
     * @param imageUrls 地址列表
     * @param goodsId 商品id
     */
    void saveImage(List<String> imageUrls,Long goodsId);

    /**
     * 根据照片id删除照片
     * @param imageIds image ids
     */
    void deleteImage(List<Long> imageIds);

    void updateImage(Long id, String newUrl);

    List<Image> listImagesByGoodsId(Long goodsId);

    /**
     * 获取某个物品的一张图片
     * @param goodsId 物品的id
     * @return 获取某个物品的一张图片
     */
    Image getGoodsImage(Long goodsId);

    void deleteImagesByGoodsId(Long goodsId);
}
