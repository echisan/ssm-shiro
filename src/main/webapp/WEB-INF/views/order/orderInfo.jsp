<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/23
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<rapid:override name="title">订单详情</rapid:override>
<rapid:override name="head">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</rapid:override>
<rapid:override name="content">
    <div class="page-header">
        <h1>订单详情 <small>订单号: ${orderInfo.order.orderNumber}</small></h1>
    </div>
    <div class="container">
        <p style="font-size: 17px;font-weight: lighter">订单信息</p>
        <p>订单号: <span>${orderInfo.order.orderNumber}</span></p>
        <p>创建时间: <span><fmt:formatDate value="${orderInfo.order.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/> </span></p>
        <c:choose>
            <c:when test="${orderInfo.order.orderStatus==0}">
                <p>订单状态: <span style="color: indianred">待付款</span></p>
            </c:when>
            <c:when test="${orderInfo.order.orderStatus==1}">
                <p>订单状态: <span style="color: cornflowerblue">交易成功</span></p>
                <p>订单完成时间: <span><fmt:formatDate value="${orderInfo.order.finishTime}" pattern="yyyy-MM-dd hh:mm:ss"/></span></p>
            </c:when>
            <c:otherwise>
                <p>订单状态: <span style="color: darkslategrey">交易关闭</span></p>
            </c:otherwise>
        </c:choose>
        <p style="border-bottom: 1px solid #eeeeee"></p>

        <p style="font-size: 17px;font-weight: lighter">商品信息</p>
        <p>商品名称: <strong><a href="${ctx}/goods/${orderInfo.goods.id}" target="_blank">${orderInfo.order.goodsName}</a></strong>
        </p>
        <p>商品价格: <strong style="color: #ff5700;">${orderInfo.order.goodsPrice}</strong></p>
        <p>卖家: <strong>${orderInfo.sellerUser.username}</strong></p>
        <p style="border-bottom: 1px solid #eeeeee"></p>
        <p style="font-size: 17px;font-weight: lighter">买家信息</p>
        <p>买家: <strong>${orderInfo.buyerUser.username}</strong></p>
        <div style="width: 20%">
            <p>收件人: <span>${orderInfo.order.recipients}</span></p>
            <p>联系号码: <span>${orderInfo.order.phone}</span></p>
            <p>收件地址: <span>${orderInfo.order.address}</span></p>
        </div>

    </div>
</rapid:override>
<%@include file="../base.jsp" %>