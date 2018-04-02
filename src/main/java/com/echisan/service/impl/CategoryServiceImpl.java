package com.echisan.service.impl;

import com.echisan.dao.CategoryMapper;
import com.echisan.dao.GoodsCategoryMapper;
import com.echisan.model.po.Category;
import com.echisan.model.po.CategoryExample;
import com.echisan.model.po.GoodsCategory;
import com.echisan.model.po.GoodsCategoryExample;
import com.echisan.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;


    @Override
    public void saveCategory(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void deleteCategoryById(Short categoryId) {
        categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public void deleteCategoryByIds(List<Short> categoryIds) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdIn(categoryIds);
        categoryMapper.deleteByExample(categoryExample);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public Category getCategoryById(Short categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public List<Category> listCategories() {

        CategoryExample categoryExample = new CategoryExample();
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public Category getCategoryByGoodsId(Long goodsId) {
        GoodsCategoryExample example = new GoodsCategoryExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByExample(example).get(0);

        return categoryMapper.selectByPrimaryKey(goodsCategory.getCategoryId());
    }

    @Override
    public List<Category> listCategoryByGoodsId(Long goodsId) {
        GoodsCategoryExample example = new GoodsCategoryExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.selectByExample(example);
        List<Short> categoryIds = new ArrayList<Short>();
        for (GoodsCategory gc:goodsCategoryList){
            categoryIds.add(gc.getCategoryId());
        }
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdIn(categoryIds);
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public List<Category> listHotCategoryList() {

        return null;
    }
}
