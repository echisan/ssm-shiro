<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/18
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<rapid:override name="title">${goodsInfo.goods.name}</rapid:override>
<rapid:override name="head">
    <link rel="stylesheet" href="../static/css/smarket.css">
    <style>

        .carousel-inner {
            height: 350px;
            border-radius: 8px;
        }

        .img-container {
            width: 500px;
            height: 350px;
        }

        .seller-info {
            border-left: 1px solid #e0e0e0;
            padding-left: 10px;
        }

        .goods-info span {
            font-size: 16px;
            font-weight: lighter;
        }

        .comment-input {
            padding-top: 30px;
        }

        .comment-list ul {
            padding-left: 0;
        }

        .comment-list li {
            list-style-type: none;
            padding-bottom: 5px;
            border-bottom: 1px solid #eeeeee;
            margin-bottom: 23px;
        }

        .comment-content {
            padding-top: 5px;
            padding-left: 10px;
        }

        .comment-content span {

        }
    </style>
</rapid:override>
<rapid:override name="content">
    <div class="container">
        <div class="page-header">
            <h1>物品详情

                <c:choose>
                    <c:when test="${activeUser.id.equals(goodsInfo.goods.userId) && goodsInfo.goods.isSell==0}">
                        <small class="pull-right"><a href="${ctx}/gm/goods/${goodsInfo.goods.id}" class="btn btn-default"
                                                     style="background-color: #FFE4D0;
                                                 color: #E5511D;border-color: #F0CAB6;">编辑物品</a></small>
                    </c:when>
                    <c:when test="${goodsInfo.goods.isSell==1}">
                        <small style="color: indianred">该物品已出售！</small>
                    </c:when>
                    <c:otherwise>
                        <script>
                            function buy_goods_now(obj,event) {
                                event.preventDefault();
                                var thisObj = $(obj);
                                var flag = thisObj.attr("flag");
                                if (flag){
                                    window.location.href = thisObj.attr("href");
                                }else {
                                    swal("请先登陆后再进行购买！",{
                                        icon:"error"
                                    })
                                }
                            }
                        </script>

                        <small class="pull-right">
                            <a href="${ctx}/order/goods/${goodsInfo.goods.id}" class="btn btn-default"
                               style="background-color: #FFE4D0;color: #E5511D;border-color: #F0CAB6;"
                                flag="<c:if test="${activeUser!=null}" var="flag">${flag}</c:if>" onclick="buy_goods_now(this,event)">
                                立即购买
                            </a>
                        </small>
                    </c:otherwise>
                </c:choose>
            </h1>
        </div>
        <div class="goods-content row">
            <div class="col-md-6">
                <div class="img-container">
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <c:forEach begin="0" step="1" end="${goodsInfo.images.size()}" var="i">
                                <li data-target="#carousel-example-generic" data-slide-to="${i}"
                                    <c:if test="${i==0}">class="active"</c:if>></li>
                            </c:forEach>
                        </ol>
                        <!-- Wrapper for slides -->

                        <div class="carousel-inner" role="listbox">
                            <c:choose>
                                <c:when test="${goodsInfo.images.size()!=0}">
                                    <c:forEach items="${goodsInfo.images}" var="image" varStatus="status">
                                        <div class="item <c:if test="${status.index==0}">active</c:if>">
                                            <a href="${image.url}" target="_blank">
                                                <img src="${image.url}" alt="..." style="height: 100%">
                                            </a>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="item active">
                                        <a href="javascript:void(0);">
                                            <img src="../static/img/published_alt_img.png" alt="..." style="height: 100%">
                                        </a>
                                    </div>
                                </c:otherwise>
                            </c:choose>


                        </div>
                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button"
                           data-slide="prev" style="border-radius: 8px">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button"
                           data-slide="next" style="border-radius: 8px">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="goods-info">
                    <h4>${goodsInfo.goods.name} </h4>
                    <div class="container" style="width: 100%;padding-left: 0;margin-left: 0">
                        <div class="col-xs-2" style="padding-left: 0">
                            <span>分类: </span>
                        </div>
                        <div class="col-xs-10" style="padding-left: 0">
                            <ul class="list-inline">
                                <c:forEach items="${goodsInfo.goodsCategoryList}" var="category">
                                    <li style="list-style-type: none">
                                        <a href="${ctx}/goods/category/${category.id}" target="_blank">${category.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <span>价格: </span><span
                        style="font-weight: bold;font-size: 16px;color: #ff5700">${goodsInfo.goods.price}</span><br>
                    <div style="padding-top: 15px">
                        <span>商品描述:</span><br>
                        <div style="padding-top: 10px">
                            <p>${goodsInfo.goods.description}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="seller-info">
                    卖家: <br><c:out value="${goodsInfo.user.username}"/><br><br>
                    发布时间: <br><fmt:formatDate value="${goodsInfo.goods.createTime}" pattern="YYYY-MM-dd hh:mm:ss"/> <br>
                    最后更新时间: <br><fmt:formatDate value="${goodsInfo.goods.lastChangeTime}" pattern="YYYY-MM-dd hh:mm:ss"/>
                </div>
            </div>
        </div>
    </div>

    <div style="padding-top: 10px;margin-bottom: 400px" class="container">

        <div class="comment-input">
            <div class="page-header">
                <h3><c:out value="${goodsInfo.comments.size()}" default="0"/> 评论</h3>
            </div>
            <script>
                function post_comment() {
                    var comment_form = $("form[name='commentForm']");

                    $.ajax({
                        url: "${ctx}/comment",
                        type: "post",
                        data: comment_form.serializeArray(),
                        success: function (data) {
                            if (data === "success") {
                                window.location.reload();
                            }
                        }
                    });
                }
            </script>
            <form action="${ctx}/comment" class="container row" name="commentForm" method="post">
                <div class="col-md-11 row">

                    <textarea placeholder="<c:if test="${activeUser!=null}">此处输入评论</c:if>" name="content" cols="20"
                              rows="3" class="form-control"
                              <c:if test="${activeUser==null}">disabled</c:if>
                            <c:if test="${goodsInfo.goods.isSell==1}">disabled</c:if>
                              style="resize: none"><c:if test="${activeUser==null}">请先登陆后再进行评论！</c:if><c:if test="${goodsInfo.goods.isSell==1}">该物品已出售！无法评论！</c:if></textarea>
                </div>
                <input type="hidden" value="${goodsInfo.goods.id}" name="goodsId">
                <div class="col-md-1" style="padding-right: 0">
                    <input type="button" onclick="post_comment()" value="评论" class="smarket-button"
                           <c:if test="${activeUser==null}">disabled</c:if>
                        <c:if test="${goodsInfo.goods.isSell==1}">disabled</c:if>
                           style="width: 100%;height: 74px;margin-right: 0;border-radius: 4px">
                </div>
            </form>
        </div>
        <div class="container row" style="border-top: 1px solid #eeeeee;padding-top: 20px;margin-top: 20px">
            <div class="comment-list">
                <ul>
                    <c:choose>
                        <c:when test="${goodsInfo.comments.size()!=0 && goodsInfo.comments!=null}">
                            <c:forEach items="${goodsInfo.comments}" var="comment">
                                <script>
                                    function delete_comment() {
                                        $.ajax({
                                            url: "/comment/${comment.comment.id}",
                                            type: "delete",
                                            success: function (data) {
                                                if (data === "success") {
                                                    window.location.reload();
                                                }
                                            }
                                        })
                                    }
                                </script>
                                <li>
                                    <div class="comment-item container row">
                                        <div class="user-info">
                                <span style="font-size: 17px;font-weight: lighter">${comment.replyUsername}
                                    <c:if test="${comment.replyUsername.equals(activeUser.username) || activeUser.id==goodsInfo.goods.userId || role.id==1}">
                                        <div class="pull-right">
                                            <a href="javascript:delete_comment()">
                                                <span class="glyphicon glyphicon-remove" title="删除评论"
                                                      style="color: indianred"></span>
                                            </a>
                                    </div>
                                    </c:if>
                                </span>
                                        </div>
                                        <div class="comment-content">
                                            <span>${comment.comment.content}</span>
                                        </div>
                                        <div class="pull-right"
                                             style="padding-top: 5px;color: #9d9d9d;padding-right: 20px">
                                            <span><fmt:formatDate value="${comment.comment.createTime}"
                                                                  pattern="YYYY-MM-dd hh:mm:ss"/> </span>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <div style="margin: 0 auto">
                                    <h3>暂无评论！</h3>
                                </div>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>

        </div>

    </div>
</rapid:override>
<%@include file="base.jsp" %>
