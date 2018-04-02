package com.echisan.service.impl;

import com.echisan.dao.GoodsCategoryMapper;
import com.echisan.dao.GoodsImageMapper;
import com.echisan.dao.GoodsMapper;
import com.echisan.dao.helper.GoodsCategoryHelperMapper;
import com.echisan.model.bo.GoodsBO;
import com.echisan.model.po.*;
import com.echisan.service.GoodsService;
import com.echisan.service.ImageService;
import com.echisan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author E73AN
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private GoodsCategoryHelperMapper goodsCategoryHelperMapper;

    @Autowired
    private ImageService imageService;

    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Autowired
    private UserService userService;


    @Override
    public void saveGoods(GoodsBO goodsBO) {
        Goods goods = new Goods();
        goods.setName(goodsBO.getName());
        goods.setPrice(goodsBO.getPrice());
        goods.setDescription(goodsBO.getDescription());
        goodsMapper.insertSelective(goods);


        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setGoodsId(goods.getId());

        goodsCategoryMapper.insertSelective(goodsCategory);
    }

    @Override
    public void saveGoods(Goods goods, Short[] categoriesIds) {
        goodsMapper.insertSelective(goods);
        List<GoodsCategory> goodsCategoryList = new ArrayList<GoodsCategory>();
        for (Short id:categoriesIds){
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setGoodsId(goods.getId());
            goodsCategory.setCategoryId(id);
            goodsCategoryList.add(goodsCategory);
        }

        goodsCategoryHelperMapper.insertByBatch(goodsCategoryList);

    }

    /**
     * 删除的时候顺带把goods_category表中的数据删了
     * @param goodsId id
     */
    @Override
    public void deleteGoodsById(Long goodsId) {
        goodsMapper.deleteByPrimaryKey(goodsId);

        GoodsCategoryExample goodsCategoryExample = new GoodsCategoryExample();
        GoodsCategoryExample.Criteria criteria = goodsCategoryExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        goodsCategoryMapper.deleteByExample(goodsCategoryExample);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public void updateGoodsViewsNum(Long goodsId) {
        Goods preGoods = goodsMapper.selectByPrimaryKey(goodsId);
        Long viewNum = preGoods.getViewNumber();

        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setViewNumber(viewNum+1L);

        goodsMapper.updateByPrimaryKeySelective(goods);

    }

    @Override
    public void updateGoodsCategory(Long goodsId, Short[] categoriesIds) {
        // 新的分类信息
        List<GoodsCategory> goodsCategoryList = new ArrayList<GoodsCategory>();
        for (Short categoryId :
                categoriesIds) {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setGoodsId(goodsId);
            goodsCategory.setCategoryId(categoryId);
            goodsCategoryList.add(goodsCategory);
        }

        // 删除目前该物品的分类
        GoodsCategoryExample example = new GoodsCategoryExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        goodsCategoryMapper.deleteByExample(example);

        // 添加新的
        goodsCategoryHelperMapper.insertByBatch(goodsCategoryList);

    }

    @Override
    public void updateGoodsLastChangeTime(Long goodsId) {
        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setLastChangeTime(new Date());
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public Goods getGoodsById(Long goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public List<Goods> listGoods() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause("create_time desc");
        return goodsMapper.selectByExampleWithBLOBs(goodsExample);
    }

    @Override
    public List<Goods> listGoodsByUserId(Long userId) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        goodsExample.setOrderByClause("id desc");
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public List<Goods> listHotGoods() {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andIsSellEqualTo(GoodsService.HAVE_NOT_SELL);
        goodsExample.setOrderByClause("view_number desc");
        return goodsMapper.selectByExampleWithBLOBs(goodsExample);
    }

    @Override
    public List<Goods> listNewGoods() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause("create_time desc");
        goodsExample.createCriteria().andIsSellEqualTo(GoodsService.HAVE_NOT_SELL);
        return goodsMapper.selectByExampleWithBLOBs(goodsExample);
    }

    @Override
    public List<Goods> listGoodsByCategoryId(Short categoryId) {

        GoodsCategoryExample goodsCategoryExample = null;
        try {
            goodsCategoryExample = new GoodsCategoryExample();
            GoodsCategoryExample.Criteria criteria = goodsCategoryExample.createCriteria();
            criteria.andCategoryIdEqualTo(categoryId);

            List<GoodsCategory> goodsCategories = goodsCategoryMapper.selectByExample(goodsCategoryExample);
            List<Long> goodsIds = new ArrayList<Long>();

            for (GoodsCategory gc :
                    goodsCategories) {
                goodsIds.add(gc.getGoodsId());
            }

            GoodsExample goodsExample = new GoodsExample();
            goodsExample.setOrderByClause("create_time desc");
            GoodsExample.Criteria criteria1 = goodsExample.createCriteria();
            criteria1.andIdIn(goodsIds).andIsSellEqualTo((byte)0);
            return goodsMapper.selectByExampleWithBLOBs(goodsExample);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteGoodsOneImageByImageId(Long imageId) {

        GoodsImageExample example = new GoodsImageExample();
        example.createCriteria().andImageIdEqualTo(imageId);
        goodsImageMapper.deleteByExample(example);
        imageService.deleteImage(Collections.singletonList(imageId));
    }

    @Override
    public List<Goods> listGoodsByIds(List<Long> goodsIds) {

        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdIn(goodsIds);
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public List<Goods> listGoodsByKeyWords(String keywords) {

        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause("create_time desc");
        goodsExample.createCriteria().andNameLike("%"+keywords+"%");
        List<Goods> goodsList = null;
        try {
            goodsList = goodsMapper.selectByExample(goodsExample);
            return goodsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Goods> listGoodsByUsername(String username) {

        User user = userService.getUserByName(username);
        if (user!=null){
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.setOrderByClause("create_time desc");
            goodsExample.createCriteria().andUserIdEqualTo(user.getId());
            return goodsMapper.selectByExample(goodsExample);
        }
        return null;

    }
}
