package com.echisan.model.po;

public class GoodsCategory {
    private Long id;

    private Long goodsId;

    private Short categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Short getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Short categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", categoryId=" + categoryId +
                '}';
    }
}