<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/22
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    function buy(event, obj) {
        event.preventDefault();
        var thisObj = $(obj);

        swal({
            text:"确认购买？",
            icon:"warning",
            buttons:true
        }).then(function (willBuy) {
            if(willBuy){
                $.ajax({
                    url: thisObj.attr("href"),
                    type: "post",
                    success: function (data) {
                        if (data === "success") {
                            swal("购买成功!",{
                                icon:"success"
                            });
//                            window.location.reload();
                        } else if (data === "error") {
                            swal("余额不足！请先充值！",{
                                icon:"warning"
                            })
                        }
                    }
                });
            }
        });

    }

    function cancel(event, obj) {
        event.preventDefault();
        var thisObj = $(obj);
        swal({
            text:"确认取消该订单？",
            icon:"warning",
            buttons:true
        }).then(function (willCancel) {
            if(willCancel){
                $.ajax({
                    url: thisObj.attr("href"),
                    type: "get",
                    success: function (data) {
                        if (data === "success") {
                            swal("取消成功!",{
                                icon:"success"
                            })
                        } else if (data === "error") {
                            swal("暂不支持该操作",{
                                icon:"error"
                            })
                        }
                    }
                })
            }
        });
    }

    function delete_order(event, obj) {
        event.preventDefault();
        var thisObj = $(obj);
        swal({
            text:"确认删除该订单？",
            icon:"warning",
            buttons:true
        }).then(function (willDelete) {
            if (willDelete){
                $.ajax({
                    url: thisObj.attr("href"),
                    type: "get",
                    success: function (data) {
                        if (data === "success") {
                            swal("取消成功!",{
                                icon:"success"
                            }).then(function () {
                                window.location.reload();
                            });
                        } else {
                            swal("别删别人的订单啊！！",{
                                icon:"error"
                            })
                        }
                    }
                })
            }
        });

    }

    function current_order_page(obj, event) {
        event.preventDefault();
        var obj = $(obj);
        var obj_href = obj.attr("href");
        var menuContainer = $("#menu-container");
        menuContainer.load(obj_href);
    }
</script>

<div class="published-container">

    <ul>
        <c:choose>
            <c:when test="${orderListVOList.size()!=0 && orderListVOList!=null}">
                <c:forEach var="item" items="${orderListVOList}">
                    <li class="goods-item">
                        <div class="item row">
                            <div class="item-img col-md-3">
                                <a href="${ctx}/goods/${item.goods.id}" target="_blank">
                                    <img src="${item.image.url}" alt="" class="img-responsive">
                                </a>
                            </div>
                            <div class="item-info col-md-8" style="padding-top: 20px">
                                <div class="block-inline">
                                    <a href="${ctx}/om/order/${item.order.id}"
                                       target="_blank">订单号: ${item.order.orderNumber}</a>
                                    <span class="price pull-right">价格: ${item.goods.price}</span>
                                </div>
                                <br>
                                <div class="pull-right">
                                    <span>状态:</span>
                                    <!-- 0: 未完成 1: 完成 2: 已取消 -->
                                    <c:choose>
                                        <c:when test="${item.order.orderStatus==0}">
                                            <span style="color: indianred">待付款</span>
                                        </c:when>
                                        <c:when test="${item.order.orderStatus==1}">
                                            <span style="color: indianred">交易成功</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: indianred">已关闭</span>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                                <p>创建时间: <span><fmt:formatDate value="${item.order.createTime}"
                                                               pattern="yyyy-MM-dd HH:mm:ss"/> </span></p>
                                <p>订单类型:
                                    <c:choose>
                                        <c:when test="${item.order.buyerUserId==activeUser.id}">
                                            <span><strong>购入</strong></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span><strong>出售</strong></span>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <div class="item-btn">
                                    <div class="pull-right">
                                        <a href="${ctx}/om/order/${item.order.id}" target="_blank"
                                           class="btn btn-xs btn-default">查看订单</a>
                                        <c:if test="${item.order.orderStatus==0 && item.goods.userId!=activeUser.id}">
                                            <a href="${ctx}/order/buy/goods/${item.goods.id}"
                                               class="btn btn-xs btn-info" onclick="buy(event,this)">付款</a>
                                        </c:if>
                                        <c:if test="${item.order.orderStatus==0 && item.goods.userId!=activeUser.id}">
                                            <a href="${ctx}/om/order/cancel/${item.order.id}"
                                               class="btn btn-xs btn-danger"
                                               onclick="cancel(event,this)">取消订单</a>
                                        </c:if>
                                        <c:if test="${item.order.orderStatus!=0 && item.order.sellerUserId!=activeUser.id}">
                                            <a href="${ctx}/om/order/delete/${item.order.id}"
                                               class="btn btn-xs btn-danger"
                                               onclick="delete_order(event,this)">删除订单</a>
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <li>
                    <div style="margin: 0 auto">
                        <h2>暂无订单信息！</h2>
                    </div>

                </li>

            </c:otherwise>
        </c:choose>
    </ul>

    <%-- 是否有下一页 --%>
    <c:if test="${pageInfo.hasNextPage || pageInfo.hasPreviousPage}">
        <nav aria-label="Page navigation" style="text-align: center">
            <ul class="pagination">
                <li><a href="${ctx}/om/order/tag/${tag}?page=1" onclick="current_order_page(this,event)">首页</a></li>
                <li>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <a href="${ctx}/om/order/tag/${tag}?page=${pageInfo.pageNum-1}"
                           onclick="current_order_page(this,event)" aria-label="Previous">
                            <span aria-hidden="true">上一页</span>
                        </a>
                    </c:if>
                </li>
                <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                    <c:if test="${page_num == pageInfo.pageNum}">
                        <li class="active"><a href="#">${page_num}</a></li>
                    </c:if>
                    <c:if test="${page_num != pageInfo.pageNum}">
                        <li><a href="${ctx}/om/order/tag/${tag}?page=${page_num}"
                               onclick="current_order_page(this,event)"> ${page_num}</a></li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="${ctx}/om/order/tag/${tag}?page=${pageInfo.pages}"
                       onclick="current_order_page(this,event)">尾页</a>
                </li>
            </ul>
        </nav>
    </c:if>

</div>