package com.echisan.model.vo;

import com.echisan.model.po.Goods;
import com.echisan.model.po.User;
import com.echisan.model.po.UserInfo;

/**
 * @author E73AN
 */
public class OrderVO {

    private Goods goods;
    private User sellerUser;
    private User buyerUser;
    private UserInfo userInfo;

    public OrderVO() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public User getSellerUser() {
        return sellerUser;
    }

    public void setSellerUser(User sellerUser) {
        this.sellerUser = sellerUser;
    }

    public User getBuyerUser() {
        return buyerUser;
    }

    public void setBuyerUser(User buyerUser) {
        this.buyerUser = buyerUser;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
