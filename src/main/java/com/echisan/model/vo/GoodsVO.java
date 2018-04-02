package com.echisan.model.vo;

import com.echisan.model.po.Category;
import com.echisan.model.po.Goods;
import com.echisan.model.po.Image;
import com.echisan.model.po.User;

import java.util.List;

/**
 * @author E73AN
 */
public class GoodsVO {

    private Goods goods;
    private List<CommentVO> comments;
    private List<Image> images;
    private User user;
    private Category category;
    private List<Category> categoryList;
    private List<Category> goodsCategoryList;
    private List<UpdateGoodsCategoryVO> updateGoodsCategoryVOList;

    public GoodsVO() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<CommentVO> getComments() {
        return comments;
    }

    public void setComments(List<CommentVO> comments) {
        this.comments = comments;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getGoodsCategoryList() {
        return goodsCategoryList;
    }

    public void setGoodsCategoryList(List<Category> goodsCategoryList) {
        this.goodsCategoryList = goodsCategoryList;
    }

    public List<UpdateGoodsCategoryVO> getUpdateGoodsCategoryVOList() {
        return updateGoodsCategoryVOList;
    }

    public void setUpdateGoodsCategoryVOList(List<UpdateGoodsCategoryVO> updateGoodsCategoryVOList) {
        this.updateGoodsCategoryVOList = updateGoodsCategoryVOList;
    }
}
