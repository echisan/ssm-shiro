package com.echisan.model.vo;

import com.echisan.model.po.Goods;
import com.echisan.model.po.User;

public class AdminGoodsVO {

    private Goods goods;
    private User user;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
