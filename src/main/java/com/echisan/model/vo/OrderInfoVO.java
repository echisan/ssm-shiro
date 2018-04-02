package com.echisan.model.vo;

import com.echisan.model.po.Goods;
import com.echisan.model.po.Order;
import com.echisan.model.po.User;

/**
 * @author E73AN
 */
public class OrderInfoVO {

    private Order order;
    private Goods goods;
    private User buyerUser;
    private User sellerUser;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public User getBuyerUser() {
        return buyerUser;
    }

    public void setBuyerUser(User buyerUser) {
        this.buyerUser = buyerUser;
    }

    public User getSellerUser() {
        return sellerUser;
    }

    public void setSellerUser(User sellerUser) {
        this.sellerUser = sellerUser;
    }

}
