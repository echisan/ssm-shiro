package com.echisan.model.po;

public class GoodsImage {
    private Long id;

    private Long goodsId;

    private Long imageId;

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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "GoodsImage{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", imageId=" + imageId +
                '}';
    }
}