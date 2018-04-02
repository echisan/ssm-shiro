package com.echisan.service.impl;

import com.echisan.dao.GoodsImageMapper;
import com.echisan.dao.ImageMapper;
import com.echisan.dao.helper.GoodsImageHelperMapper;
import com.echisan.model.po.*;
import com.echisan.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author E73AN
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private GoodsImageHelperMapper goodsImageHelperMapper;

    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Override
    public void saveImage(List<String> imageUrls, Long goodsId) {

        // 添加成image列表
        List<Image> imageList = new ArrayList<Image>();
        for (String url:imageUrls){
            Image image = new Image();
            image.setUrl(url);
            imageMapper.insertSelective(image);
            imageList.add(image);
        }

        List<GoodsImage> goodsImageList = new ArrayList<GoodsImage>();
        for (Image image : imageList){
            GoodsImage goodsImage = new GoodsImage();
            goodsImage.setGoodsId(goodsId);
            goodsImage.setImageId(image.getId());
            goodsImageList.add(goodsImage);
        }
        goodsImageHelperMapper.insertByBatch(goodsImageList);
    }

    @Override
    public void deleteImage(List<Long> imageIds) {

    }

    @Override
    public void updateImage(Long id, String newUrl) {

    }

    @Override
    public List<Image> listImagesByGoodsId(Long goodsId) {
        GoodsImageExample goodsImageExample = new GoodsImageExample();
        goodsImageExample.createCriteria().andGoodsIdEqualTo(goodsId);
        List<GoodsImage> goodsImageList = goodsImageMapper.selectByExample(goodsImageExample);
        if (goodsImageList.size() == 0){
            return null;
        }
        List<Long> imageIds = new ArrayList<Long>();
        for (GoodsImage goodsImage : goodsImageList){
            imageIds.add(goodsImage.getImageId());
        }
        ImageExample imageExample = new ImageExample();
        imageExample.createCriteria().andIdIn(imageIds);
        return imageMapper.selectByExample(imageExample);
    }

    @Override
    public Image getGoodsImage(Long goodsId) {
        GoodsImageExample goodsImageExample = new GoodsImageExample();
        GoodsImageExample.Criteria criteria = goodsImageExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);

        Image image = null;
        GoodsImage goodsImage = null;
        try {
            goodsImage = goodsImageMapper.selectByExample(goodsImageExample).get(0);
            image = imageMapper.selectByPrimaryKey(goodsImage.getImageId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("id为:"+goodsId+"的物品没有图片!");
            return null;
        }
        return image;
    }

    @Override
    public void deleteImagesByGoodsId(Long goodsId) {
        GoodsImageExample example = new GoodsImageExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        goodsImageMapper.deleteByExample(example);
    }
}
