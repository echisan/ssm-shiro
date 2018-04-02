<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/20
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<rapid:override name="title">新订单</rapid:override>
<rapid:override name="head">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <script src="/static/js/jquery-3.3.1.min.js"></script>
    <script>
        function buy_later(obj,event) {
            event.preventDefault();
            var obj = $(obj);
            swal({
                text:"确定下单？",
                icon:"warning",
                buttons:true
            }).then(function (willAddOrder) {
                if (willAddOrder){
                    $.ajax({
                        url:obj.attr("href"),
                        type:"post",
                        data:$("#order_user_info").serializeArray(),
                        success:function (data) {
                            if (data==="success"){
                                swal("下单成功，请尽快付款！",{
                                    icon:"success"
                                }).then(function () {
                                    window.location.href = "/om";
                                })
                            }else {
                                swal("失败"+data,{
                                    icon:"error"
                                });
                            }
                        }
                    });
                }
            });


        }

        function buy_now(obj, event) {
            event.preventDefault();
            var obj = $(obj);
            swal({
                text:"确定购买？",
                icon:"warning",
                buttons:true
            }).then(function (willBuy) {
                if (willBuy){
                    $.ajax({
                        url:obj.attr("href"),
                        type:"post",
                        data:$("#order_user_info").serializeArray(),
                        success:function (data) {
                            if (data==="success"){
                                swal("购买成功！",{
                                    icon:"success"
                                }).then(function () {
                                    window.location.href = "/om";
                                });
                            }else {
                                swal("余额不足！请先充值！！",{
                                    icon:"error"
                                })
                            }
                        }
                    });
                }
            });
        }
    </script>
</rapid:override>
<rapid:override name="content">
    <div class="page-header">
        <h1>新订单</h1>
    </div>
    <div class="container">
        <p style="font-size: 17px;font-weight: lighter">商品信息</p>
        <p>商品名称: <strong><a href="${ctx}/goods/${orderVO.goods.id}" target="_blank">${orderVO.goods.name}</a></strong>
        </p>
        <p>商品价格: <strong style="color: #ff5700;">${orderVO.goods.price}</strong></p>
        <p>卖家: <strong>${orderVO.sellerUser.username}</strong></p>
        <p style="border-bottom: 1px solid #eeeeee"></p>
        <p style="font-size: 17px;font-weight: lighter">买家信息</p>
        <p>买家: <strong>${activeUser.username}</strong></p>
        <form action="#" id="order_user_info">
            <div style="width: 20%">
                <p>收件人</p>
                <input type="text" name="real_name" class="form-control" title="收件人" value="${orderVO.userInfo.realName}">
                <p>联系号码</p>
                <input type="text" name="phone" class="form-control" title="联系号码" value="${orderVO.userInfo.phone}">
            </div>
            <div style="width: 80%">
                <p>收件地址</p>
                <input type="text" name="address" class="form-control" value="${orderVO.userInfo.address}">
            </div>
        </form>

        <div style="padding-top: 15px">
            <a href="${ctx}/order/goods/${orderVO.goods.id}/tag/0" goodsId="${orderVO.goods.id}"
               class="btn btn-default" onclick="buy_later(this,event)">稍后购买</a>
            <a href="${ctx}/order/goods/${orderVO.goods.id}/tag/1" class="btn btn-primary"
            onclick="buy_now(this,event)">立即购买</a>
        </div>
    </div>
</rapid:override>
<%@include file="base.jsp" %>