package com.echisan.service;

import com.echisan.model.po.Category;

import java.util.List;

/**
 * @author E73AN
 */
public interface CategoryService {

    /**
     * 添加分类
     * @param category 分类实体
     */
    void saveCategory(Category category);

    /**
     * 根据分类的id删除该分类
     * @param categoryId 分类的id
     */
    void deleteCategoryById(Short categoryId);

    void deleteCategoryByIds(List<Short> categoryIds);

    /**
     * 更新该分类
     * @param category 分类的实体
     */
    void updateCategory(Category category);

    /**
     * 获取分类
     * @param categoryId 该分类的id
     * @return 单个分类实体
     */
    Category getCategoryById(Short categoryId);

    /**
     * 获取所有的分类
     * @return 分类列表
     */
    List<Category> listCategories();

    Category getCategoryByGoodsId(Long goodsId);

    List<Category> listCategoryByGoodsId(Long goodsId);

    List<Category> listHotCategoryList();
}
