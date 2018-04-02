package com.echisan.service;

import com.echisan.model.po.Order;

import java.util.List;

/**
 * @author E73AN
 */
public interface OrderService {

    /**
     * 0:未完成 1:已完成 2:已关闭
     */
    Byte ORDER_STATUS_UN_FINISH = 0;
    Byte ORDER_STATUS_FINISHED = 1;
    Byte ORDER_STATUS_CLOSED = 2;


    /**
     * 添加订单
     * @param order 订单实体
     */
    void saveOrder(Order order);

    /**
     * 删除订单
     * @param orderId 订单id
     */
    void deleteOrderById(Long orderId);

    /**
     * 更新订单
     * @param order 订单实体
     */
    void updateOrder(Order order);

    /**
     * 获取订单
     * @param orderId 订单id
     * @return 订单
     */
    Order getOrderById(Long orderId);


    Order getOrderByGoodsId(Long goodsId);

    /**
     * 获取某用户所购买的所有订单
     * @param buyerUserId 该用户的id
     * @return 订单列表
     */
    List<Order> listOrdersByBuyerUserId(Long buyerUserId);

    /**
     * 获取某用户所售出的所有订单
     * @param sellerUserId 该用户的id
     * @return 订单列表
     */
    List<Order> listOrdersBySellerUserId(Long sellerUserId);

    /**
     * 获取改用户所有的订单
     * @param userId 该用户id
     * @return 获取改用户所有的订单列表
     */
    List<Order> listOrdersByUserId(Long userId);

    List<Order> listUnFinishOrderByUserId(Long userId);

    List<Order> listFinishOrderByUserId(Long userId);

    List<Order> listClosedOrderByUserId(Long userId);

    List<Order> listOrder(Long userId, Byte orderType);

    List<Order> listOrderByIds(List<Long> orderIds);

}
