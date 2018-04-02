<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/15
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    ul{
        list-style-type: none;
        padding-left: 0;
    }

    .goods-item{
        border: 1px solid #d5d5d5;
        border-radius: 5px;
        margin-bottom: 30px;
        width: 80%;
    }


    .item-img{
        width: 200px;
        height: 150px;
    }

    .item-img img{
        height: 100%;
    }

    .item-info{
        padding-left: 0;
        padding-top: 10px;
    }
    .item-info a{
        text-decoration: inherit;
        font-size: 16px;
        font-weight: lighter;

    }

    .price{
        font-weight: 400;
        font-size: 18px;
    }

    .pull-right > a{
        font-size: 13px;
    }
</style>
<script>
    function delete_goods(obj) {
        var obj = $(obj);
        var goodsId = obj.attr("goodsId");


        $.ajax({
            url:"${ctx}/goods/"+goodsId,
            type:"delete",
            success:function (data) {
                console.log(data);
                if (data==="success"){
                    window.location.reload();
                }else if(data==="failed"){
                    $("#crid").html( "<div class=\"alert alert-danger\" role=\"alert\"><span>删除失败！</span></div>");

                }
            }
        })
    }
</script>
<div class="published-container">
    <ul>
        <c:if test="${publishedGoodsVoList!=null}">
            <c:forEach items="${publishedGoodsVoList}" var="item">
                <li class="goods-item">
                    <div class="item row">
                        <div class="item-img col-md-4">
                            <c:if test="${item.image.url!=null}">
                                <img src="${item.image.url}" alt="" class="img-responsive" style="border-radius: 3px">
                            </c:if>
                            <c:if test="${item.image.url==null}">
                                <img src="../../static/img/published_alt_img.png" alt="" class="img-responsive">
                            </c:if>

                        </div>
                        <div class="item-info col-md-8">
                            <div class="block-inline">
                                <a href="${ctx}/goods/${item.goods.id}" target="_blank">${item.goods.name}</a>
                                <span class="price pull-right">出价:${item.goods.price}</span>
                            </div>
                            <br>
                            <div class="pull-right">
                                <span>状态:</span>
                                <c:if test="${item.goods.isSell==0}">
                                    <span style="color: indianred">未售出</span>
                                </c:if>
                                <c:if test="${item.goods.isSell==1}">
                                    <span style="color: cornflowerblue">已售出</span>
                                </c:if>
                            </div>
                            <p>创建时间: <span><fmt:formatDate value="${item.goods.createTime}" pattern="YYYY-MM-dd hh:mm:ss"/></span></p>
                            <p>最后修改时间: <span><fmt:formatDate value="${item.goods.lastChangeTime}" pattern="YYYY-MM-dd hh:mm:ss"/></span></p>
                            <div class="item-btn">
                                <div class="pull-right">
                                    <a href="${ctx}/gm/goods/${item.goods.id}" target="_blank" <c:if test="${item.goods.isSell==1}"> style="pointer-events: none" </c:if> class="btn btn-xs btn-default">编辑</a>
                                    <a href="javascript:void (0);"  onclick="delete_goods(this)" goodsId="${item.goods.id}" class="btn btn-xs btn-danger">删除</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </c:forEach>

            <%-- 分页 --%>

            <nav aria-label="Page navigation" style="text-align: center">
                <ul class="pagination">
                    <li><a href="${ctx}/gm/published?page=1">首页</a></li>
                    <li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <a href="${ctx}/gm/published?page=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </c:if>
                    </li>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                        <c:if test="${page_num == pageInfo.pageNum}">
                            <li class="active"><a href="#">${page_num}</a></li>
                        </c:if>
                        <c:if test="${page_num != pageInfo.pageNum}">
                            <li><a href="${ctx}/gm/published?page=${page_num}"> ${page_num}</a></li>
                        </c:if>
                    </c:forEach>
                    <li>
                        <a href="${ctx}/gm/published?page=${pageInfo.pages}">尾页</a>
                    </li>
                </ul>
            </nav>

        </c:if>
        <c:if test="${publishedGoodsVoList==null}">
            <li style="text-align: center">
                <h2>暂无发布的物品！</h2>
            </li>
        </c:if>

    </ul>
</div>