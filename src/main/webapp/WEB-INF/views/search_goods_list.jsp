<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/25
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<rapid:override name="title">搜索列表</rapid:override>

<rapid:override name="head">
    <link type="text/css" href="../static/css/bootstrap.css">
    <link rel="stylesheet" href="../static/css/smarket.css">
    <script src="../static/js/jquery-3.3.1.min.js"></script>


    <style type="text/css">


        #search-goods{
            padding-top: 30px;
        }

        #keyword-input{
            width: 350px;
            height: 40px;
            border: 1px solid #a6e1ec;
            padding: 3px 3px 3px 10px;
        }

        #search-btn{
            border-radius: 0;
            border: 1px #66afe9;
            color:white;
            width: 100px;
            height: 40px;
            background-color: #a6e1ec;
        }

        #hot-category a:hover,a:link,a:visited,a:active{
            text-decoration: none;
            color: #a6e1ec;
        }

        #goods-container{
            padding-top: 30px;
            text-align: center;
            margin: 0 auto;
        }

        .goods-item-container{
            list-style-type: none;
            padding-bottom: 30px;
            padding-right: 30px;
        }

        .goods-item{
            border: 1px solid #e5e9ef;
            border-radius: 3px;
            height: 290px;
            width: 230px;
        }

        .list-group{
            margin-top: 39px;
            padding-top: 20px;
        }

        .goods-item .img{
            border-radius: 5px;
        }

        .goods-item .headline span{
        }

        .goods-item .info{
            text-align: left;
            padding: 10px;
        }

        .goods-item .info>a:hover,a:link,a:visited,a:active{
            text-decoration: none;
            color: black;
        }

        .goods-item .info .tags{
            padding: 3px;
            font-size: 13px;
            color: #99a2aa;
            text-align: left;
        }

        .tags{
            margin-top: 8px;
        }


        .tags span{
            padding-top: 5px;
            padding-right: 6px;
            padding-left: 3px;
        }

        .category-select {
            height: 40px;
            border: 1px solid #a6e1ec;
            padding-bottom: 1px;
        }
    </style>
    <script>

    </script>

</rapid:override>

<rapid:override name="content">

    <div style="margin: auto;text-align: center">
        <form action="${ctx}/goods/search" method="get">
            <label for="keyword-input"></label>
            <input type="text" id="keyword-input" name="keyword" placeholder="在此输入关键词" value="${keyword}">
            <select title="分类" name="categoryId" class="category-select">
                <option value="">选择分类</option>
                <c:forEach items="${categoryList}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
            <input id="search-btn" type="submit" value="搜索">
        </form>
    </div>


    <div id="goods-container">

            <%-- 这里应该有个广告的位置，先留着晚点做 --%>
        <div class="pull-left">
            <h4>搜索列表</h4>
        </div>
        <ul class="list-group container">
            <c:choose>
                <c:when test="${goodsList.size()!=0 && goodsList!=null}">
                    <c:forEach items="${goodsList}" var="item">
                        <li class="goods-item-container col-xs-3 ">
                            <div class="goods-item">
                                <a href="${ctx}/goods/${item.goods.id}">
                                    <c:choose>
                                        <c:when test="${item.image!=null}">
                                            <div class="img" style="height: 177.11px;width: 230px;background-color: #eeeeee">
                                                <img src="${item.image.url}" alt="" class="img-responsive" style="height: 100%">
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="img" style="height: 177.11px;width: 230px;background-color: #eeeeee">
                                                <img src="../static/img/published_alt_img.png" alt="" class="img-responsive" style="height: 100%">
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                </a>
                                <div class="info">
                                    <div class="headline">
                                        <a href="${ctx}/goods/${item.goods.id}">
                                            <span title="${item.goods.name}"><c:out value="${item.goods.name.trim()}"/></span>
                                        </a>
                                    </div>
                                    <div class="tags">
                                        <span class="glyphicon glyphicon-eye-open" title="观看"></span>${item.goods.viewNumber}
                                        <span class="glyphicon glyphicon-comment" title="评论" style="padding-left: 80px"></span>${item.commentNum}<br>
                                        <span class="glyphicon glyphicon-time" title="发布时间"></span>
                                        <fmt:formatDate value="${item.goods.createTime}" pattern="yyyy-MM-dd"/>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <li style="list-style-type: none">
                        <h4>暂无搜索结果</h4>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>

</rapid:override>
<%@include file="base.jsp"%>