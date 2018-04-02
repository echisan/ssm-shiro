package com.echisan.model.vo;

import com.echisan.model.po.Goods;
import com.echisan.model.po.Image;

/**
 * @author E73AN
 */
public class IndexGoodsVO {

    private Image image;
    private Goods goods;
    private Long commentNum;

    public IndexGoodsVO() {
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }
}
