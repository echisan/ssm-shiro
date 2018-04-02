<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/13
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">个人中心</rapid:override>

<rapid:override name="head">
    <link type="text/css" href="../../static/css/bootstrap.css">
    <link rel="stylesheet" href="../../static/css/smarket.css">
    <script src="../../static/js/jquery-3.3.1.min.js"></script>

    <script>
        $(document).ready(function () {
            var menuContainer = $("#menu-container");
            menuContainer.load("${ctx}/space/wallet");

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

    <style type="text/css">


        #menu-list{
            border: 1px solid white;
            border-radius: 3px;
        }

        #menu-list>ul{
            border-radius: 4px;
        }

        #menu-list a:hover,a:active,a:focus,a:visited{
            color: #222222;
            text-decoration: none;
            font-weight: 400;
        }

        #menu-container{
            border: 1px #a6e1ec;
            /*background-color: #eeeeee;*/
            border-radius: 4px;
        }

        .menu-content{
            padding: 10px;
        }
    </style>
</rapid:override>

<rapid:override name="content">
    <div class="container row">
        <div class="page-header">
            <h1>个人中心</h1>
        </div>
        <div id="space-container" class="container row">
            <div id="menu-list" class="col-md-3">
                <ul class="nav nav-pills nav-stacked" >
                    <li class="active"><a id="default_page" href="${ctx}/space/wallet" onclick="load_page(this,event)">钱包</a></li>
                    <li><a href="${ctx}/space/pwd" onclick="load_page(this,event)">修改密码</a></li>
                    <li><a href="${ctx}/space/info/${activeUser.id}" onclick="load_page(this,event)">个人信息</a></li>
                    <li><a href="${ctx}/space/comment" onclick="load_page(this,event)">评论管理</a></li>
                </ul>
            </div>
            <div id="menu-container" class="col-md-9">

            </div>
        </div>
    </div>

</rapid:override>
<%@include file="../base.jsp"%>
