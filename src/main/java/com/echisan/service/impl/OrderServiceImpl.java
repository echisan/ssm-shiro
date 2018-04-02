package com.echisan.service.impl;

import com.echisan.dao.OrderMapper;
import com.echisan.model.po.Order;
import com.echisan.model.po.OrderExample;
import com.echisan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author E73AN
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void saveOrder(Order order) {
        orderMapper.insertSelective(order);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderMapper.deleteByPrimaryKey(orderId);

    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public Order getOrderByGoodsId(Long goodsId) {
        OrderExample example = new OrderExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        List<Order> orderList = orderMapper.selectByExample(example);
        if (orderList != null && orderList.size() != 0) {
            return orderList.get(0);
        }
        return null;
    }

    @Override
    public List<Order> listOrdersByBuyerUserId(Long buyerUserId) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andBuyerUserIdEqualTo(buyerUserId);
        orderExample.setOrderByClause("create_time desc");
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public List<Order> listOrdersBySellerUserId(Long sellerUserId) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andSellerUserIdEqualTo(sellerUserId);

        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public List<Order> listOrdersByUserId(Long userId) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andSellerUserIdEqualTo(userId);
        orderExample.or(orderExample.createCriteria().andBuyerUserIdEqualTo(userId));
        orderExample.setOrderByClause("create_time desc");
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public List<Order> listUnFinishOrderByUserId(Long userId) {
        OrderExample orderExample = new OrderExample();
        orderExample.or(orderExample.createCriteria()
                .andBuyerUserIdEqualTo(userId)
                .andOrderStatusEqualTo(OrderService.ORDER_STATUS_UN_FINISH));
        orderExample.or(orderExample.createCriteria()
                .andSellerUserIdEqualTo(userId)
                .andOrderStatusEqualTo(OrderService.ORDER_STATUS_UN_FINISH));
        orderExample.setOrderByClause("create_time desc");
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public List<Order> listFinishOrderByUserId(Long userId) {
        OrderExample orderExample = new OrderExample();
        orderExample.or(orderExample.createCriteria()
                .andBuyerUserIdEqualTo(userId)
                .andOrderStatusEqualTo(OrderService.ORDER_STATUS_FINISHED));
        orderExample.or(orderExample.createCriteria()
                .andSellerUserIdEqualTo(userId)
                .andOrderStatusEqualTo(OrderService.ORDER_STATUS_FINISHED));
        orderExample.setOrderByClause("finish_time desc");
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public List<Order> listClosedOrderByUserId(Long userId) {
        OrderExample orderExample = new OrderExample();
        orderExample.or(orderExample.createCriteria()
                .andBuyerUserIdEqualTo(userId)
                .andOrderStatusEqualTo(OrderService.ORDER_STATUS_CLOSED));
        orderExample.or(orderExample.createCriteria()
                .andSellerUserIdEqualTo(userId)
                .andOrderStatusEqualTo(OrderService.ORDER_STATUS_CLOSED));
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public List<Order> listOrder(Long userId, Byte orderType) {
        OrderExample orderExample = new OrderExample();
        orderExample.or(orderExample.createCriteria()
                .andBuyerUserIdEqualTo(userId)
                .andOrderStatusEqualTo(orderType));
        orderExample.or(orderExample.createCriteria()
                .andSellerUserIdEqualTo(userId)
                .andOrderStatusEqualTo(orderType));
        orderExample.setOrderByClause("create_time desc");
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public List<Order> listOrderByIds(List<Long> orderIds) {

        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andGoodsIdIn(orderIds);
        return orderMapper.selectByExample(orderExample);

    }
}
