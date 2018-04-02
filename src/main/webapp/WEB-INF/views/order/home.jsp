<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/22
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<rapid:override name="title">订单管理</rapid:override>

<rapid:override name="head">
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css">
    <script src="../../static/js/jquery-3.3.1.min.js"></script>
    <script src="../../static/js/main.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
        $(document).ready(function () {
            var menuContainer = $("#menu-container");
            menuContainer.load("${ctx}/om/order/tag/0");
        });

        function update_active_class() {
            $("ul.nav > li").click(function (e) {
                e.preventDefault();
                $("ul.nav > li").removeClass("active");
                $(this).addClass("active");
            })
        }

        function load_page(obj,event) {
            event.preventDefault();
            var obj = $(obj);
            var obj_href = obj.attr("href");
            var menuContainer = $("#menu-container");
            menuContainer.load(obj_href);
            update_active_class();
        }
    </script>
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
</rapid:override>

<rapid:override name="content">

    <div class="container row">
        <div class="page-header">
            <h1>订单管理</h1>
        </div>
        <div id="space-container" class="container row">
            <div id="menu-list" class="col-md-3">
                <ul class="nav nav-pills nav-stacked" >
                    <li class="active"><a href="${ctx}/om/order/tag/0" onclick="load_page(this,event)">待完成</a></li>
                    <li><a href="${ctx}/om/order/tag/1" onclick="load_page(this,event)">已完成</a></li>
                    <li><a href="${ctx}/om/order/tag/2" onclick="load_page(this,event)">已关闭</a></li>
                    <li><a href="${ctx}/om/order/tag/3" onclick="load_page(this,event)">全部订单</a></li>
                </ul>
            </div>
            <div id="menu-container" class="col-md-9">

            </div>
        </div>
    </div>

</rapid:override>

<%@include file="../base.jsp"%>