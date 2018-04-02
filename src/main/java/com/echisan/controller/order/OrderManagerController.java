package com.echisan.controller.order;

import com.echisan.model.po.Goods;
import com.echisan.model.po.Order;
import com.echisan.model.po.User;
import com.echisan.model.vo.OrderInfoVO;
import com.echisan.model.vo.OrderListVO;
import com.echisan.service.GoodsService;
import com.echisan.service.ImageService;
import com.echisan.service.OrderService;
import com.echisan.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author E73AN
 */
@Controller
@RequestMapping(value = "/om")
public class OrderManagerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public String renderOrderManager(){
        return "order/home";
    }

    @GetMapping(value = "/unfinish")
    public String renderUnFinish(Model model, HttpSession session){
        User user = (User) session.getAttribute("activeUser");

        List<OrderListVO> orderListVOList = new ArrayList<OrderListVO>();

        List<Order> orderList = orderService.listUnFinishOrderByUserId(user.getId());

        for (Order order : orderList){
            OrderListVO orderListVO = new OrderListVO();
            orderListVO.setGoods(goodsService.getGoodsById(order.getGoodsId()));
            orderListVO.setOrder(order);
            orderListVO.setBuyerUser(user);
            orderListVO.setSellerUser(userService.getUserById(order.getSellerUserId()));
            orderListVO.setImage(imageService.getGoodsImage(order.getGoodsId()));
            orderListVOList.add(orderListVO);
        }


        model.addAttribute("orderListVOList",orderListVOList);
        return "order/order_list";
    }


    @GetMapping(value = "/order/tag/{tag}")
    public String renderOrderPage(@PathVariable(value = "tag")Byte tag,
                                  @RequestParam(value = "page", defaultValue = "1", required = false)Integer page,
                                  Model model, HttpSession session){

        User user = (User) session.getAttribute("activeUser");
        List<OrderListVO> orderListVOList = new ArrayList<OrderListVO>();
        List<Order> orderList = null;
        List<Byte> orderTypeList = new ArrayList<Byte>();
        PageInfo<Order> pageInfo = null;
        orderTypeList.add(OrderService.ORDER_STATUS_FINISHED);
        orderTypeList.add(OrderService.ORDER_STATUS_UN_FINISH);
        orderTypeList.add(OrderService.ORDER_STATUS_CLOSED);


        if (orderTypeList.contains(tag)){
            PageHelper.startPage(page,10);
            orderList = orderService.listOrder(user.getId(),tag);
             pageInfo = new PageInfo<Order>(orderList);
        }else {
            PageHelper.startPage(page,10);
            orderList = orderService.listOrdersByUserId(user.getId());
            pageInfo = new PageInfo<Order>(orderList);
        }


        // 未完成订单
//        if (tag.equals(OrderService.ORDER_STATUS_UN_FINISH)){
//            orderList = orderService.listUnFinishOrderByUserId(user.getId());
//
//        }else if (tag.equals(OrderService.ORDER_STATUS_FINISHED)){
//            orderList = orderService.listFinishOrderByUserId(user.getId());
//
//        }else if (tag.equals(OrderService.ORDER_STATUS_CLOSED)){
//            orderList = orderService.listClosedOrderByUserId(user.getId());
//
//        } else {
//            orderList = orderService.listOrdersByUserId(user.getId());
//        }

        if (orderList!=null){
            for (Order order : orderList){
                OrderListVO orderListVO = new OrderListVO();
                orderListVO.setGoods(goodsService.getGoodsById(order.getGoodsId()));
                orderListVO.setOrder(order);
                orderListVO.setBuyerUser(user);
                orderListVO.setSellerUser(userService.getUserById(order.getSellerUserId()));
                orderListVO.setImage(imageService.getGoodsImage(order.getGoodsId()));
                orderListVOList.add(orderListVO);
            }
            model.addAttribute("orderListVOList",orderListVOList);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("tag",tag);
            return "order/order_list";
        }

        return "order";
    }


    @GetMapping(value = "/order/{order_id}")
    public String renderOrderInfo(@PathVariable(value = "order_id")Long orderId,Model model){


        OrderInfoVO orderInfo = new OrderInfoVO();
        Order order = orderService.getOrderById(orderId);
        Goods goods = goodsService.getGoodsById(order.getGoodsId());
        User sellerUser = userService.getUserById(order.getSellerUserId());
        User buyerUser = userService.getUserById(order.getBuyerUserId());

        orderInfo.setOrder(order);
        orderInfo.setGoods(goods);
        orderInfo.setSellerUser(sellerUser);
        orderInfo.setBuyerUser(buyerUser);


        model.addAttribute("orderInfo",orderInfo);

        return "order/orderInfo";
    }


    @GetMapping(value = "/order/cancel/{order_id}")
    @ResponseBody
    public String cancelOrder(@PathVariable(value = "order_id")Long orderId,
                              HttpSession session){

        User user = (User) session.getAttribute("activeUser");
        Order order = orderService.getOrderById(orderId);
        // 如果是本人操作
        if (user.getId().equals(order.getBuyerUserId())){
            Goods goods = goodsService.getGoodsById(order.getGoodsId());
            // 更新信息
            goods.setIsSell(GoodsService.HAVE_NOT_SELL);
            order.setFinishTime(new Date());
            order.setOrderStatus(OrderService.ORDER_STATUS_CLOSED);

            // 存储到数据库
            goodsService.updateGoods(goods);
            orderService.updateOrder(order);
            return "success";
        }else {
            return "error";
        }

    }

    @GetMapping(value = "/order/delete/{order_id}")
    @ResponseBody
    public String deleteOrder(@PathVariable(value = "order_id")Long orderId,
                              HttpSession session){

        User user = (User) session.getAttribute("activeUser");
        Order order = orderService.getOrderById(orderId);
        // 如果是本人操作
        if (user.getId().equals(order.getBuyerUserId())){
            orderService.deleteOrderById(orderId);
            return "success";
        }else {
            return "error";
        }
    }
}
