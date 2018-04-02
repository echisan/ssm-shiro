package com.echisan.controller.order;

import com.echisan.model.po.*;
import com.echisan.model.vo.OrderVO;
import com.echisan.service.GoodsService;
import com.echisan.service.OrderService;
import com.echisan.service.UserService;
import com.echisan.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author E73AN
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    private static final int SAVE_ORDER_LATER = 0;
    private static final int SAVE_ORDER_IMMEDIATELY = 1;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private WalletService walletService;

    @GetMapping(value = "/goods/{goods_id}")
    public String renderOrderPage(@PathVariable(value = "goods_id") Long goodsId, Model model,
                                  HttpSession session) {

        User buyerUser = (User) session.getAttribute("activeUser");

        // 卖家的物品以及个人信息
        Goods goods = goodsService.getGoodsById(goodsId);
        User sellerUser = userService.getUserById(goods.getUserId());

        UserInfo userInfo = userService.getUserInfoByUserId(buyerUser.getId());

        OrderVO orderVO = new OrderVO();
        orderVO.setGoods(goods);
        orderVO.setSellerUser(sellerUser);
        orderVO.setBuyerUser(buyerUser);
        orderVO.setUserInfo(userInfo);

        model.addAttribute("orderVO", orderVO);
        return "order";
    }

    @PostMapping(value = "/goods/{goods_id}/tag/{tag}")
    @ResponseBody
    public String createOrderLater(@PathVariable(value = "goods_id") Long goodsId,
                                   @PathVariable(value = "tag") Integer tag,
                                   @RequestParam(value = "real_name") String realName,
                                   @RequestParam(value = "phone") String phone,
                                   @RequestParam(value = "address") String address,
                                   HttpSession session) {

        User user = (User) session.getAttribute("activeUser");
        Goods goods = goodsService.getGoodsById(goodsId);
        Order order = new Order();

        // 立即购买
        if (tag.equals(SAVE_ORDER_IMMEDIATELY)) {

            Wallet userWallet = walletService.getWalletByUserId(user.getId());
            BigDecimal userBalance = userWallet.getBalance();
            BigDecimal goodsPrice = goods.getPrice();

            // 如果用户余额大于商品价格
            if (userBalance.compareTo(goodsPrice) > 0) {

                // 将订单设置为 已完成
                order.setOrderStatus(OrderService.ORDER_STATUS_FINISHED);
                order.setFinishTime(new Date());

                // 将物品设置为 已售出
                goods.setIsSell((byte)1);

                // 数据库操作 买家，余额 - 物品价格
                BigDecimal balance = userBalance.subtract(goodsPrice);
                walletService.updateWalletBalanceByUserId(user.getId(), balance);

                // 卖家
                walletService.updateWalletBalanceByUserId(goods.getUserId(),balance);


                // 添加买家记录
                WalletRecord walletRecord = new WalletRecord();
                walletRecord.setBehaviourType(WalletService.PAY);
                walletRecord.setMoney(goodsPrice);
                walletRecord.setUserId(user.getId());
                walletRecord.setRemarks(goods.getName());

                // 添加卖家记录
                WalletRecord walletRecord1 = new WalletRecord();
                walletRecord1.setUserId(goods.getUserId());
                walletRecord1.setRemarks(goods.getName());
                walletRecord1.setMoney(goodsPrice);
                walletRecord1.setBehaviourType(WalletService.INCOME);

                // 数据库操作
                goodsService.updateGoods(goods);
                orderService.updateOrder(order);
                walletService.saveWalletRecord(walletRecord);
                walletService.saveWalletRecord(walletRecord1);

            }else {
                return "error";
            }


        }else {
            // 将订单设置为未完成
            order.setOrderStatus(OrderService.ORDER_STATUS_UN_FINISH);
        }


        // 完成购买，生成订单
        order.setGoodsId(goodsId);
        order.setAddress(address);
        order.setPhone(phone);
        order.setSellerUserId(goods.getUserId());
        order.setBuyerUserId(user.getId());
        order.setOrderNumber(String.valueOf(System.currentTimeMillis()));
        order.setRecipients(realName);
        order.setGoodsName(goods.getName());
        order.setGoodsPrice(goods.getPrice());


        try {
            // 将订单存到数据库
            orderService.saveOrder(order);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed message:"+e.getMessage();
        }

    }


    @PostMapping(value = "/buy/goods/{goods_id}")
    @ResponseBody
    public String buyGoods(@PathVariable(value = "goods_id")Long goodsId, HttpSession session){


        User user = (User) session.getAttribute("activeUser");
        Goods goods = goodsService.getGoodsById(goodsId);
        Order order = orderService.getOrderByGoodsId(goodsId);

        Wallet userWallet = walletService.getWalletByUserId(user.getId());


        if (walletService!=null){
            // 如果够钱买
            if (userWallet.getBalance().compareTo(goods.getPrice())>=0){

                // 更新一下数据
                BigDecimal balance = userWallet.getBalance().subtract(goods.getPrice());
                userWallet.setBalance(balance);
                goods.setIsSell((byte)1);
                order.setOrderStatus(OrderService.ORDER_STATUS_FINISHED);
                order.setFinishTime(new Date());

                // 把数据跟新到数据库

                walletService.updateWalletBalanceByUserId(user.getId(),balance);
                goodsService.updateGoods(goods);
                orderService.updateOrder(order);

                // 添加记录

                WalletRecord walletRecord = new WalletRecord();
                walletRecord.setBehaviourType(WalletService.PAY);
                walletRecord.setMoney(goods.getPrice());
                walletRecord.setUserId(user.getId());
                walletRecord.setRemarks(goods.getName());

                walletService.saveWalletRecord(walletRecord);


                // 更新完毕
                return "success";
            }else {
                // 如果不够钱买
                return "error";
            }
        }else {
            return "error";
        }
    }

}
