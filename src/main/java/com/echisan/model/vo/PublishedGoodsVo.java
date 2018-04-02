package com.echisan.model.vo;

import com.echisan.model.po.Goods;
import com.echisan.model.po.Image;

/**
 * @author E73AN
 */
public class PublishedGoodsVo {

    private Goods goods;
    private Image image;

    public PublishedGoodsVo() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PublishedGoodsVo{" +
                "goods=" + goods +
                ", image=" + image +
                '}';
    }
}
