<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/9
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="user" value="${activeUser}"/>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><rapid:block name="title">标题</rapid:block></title>
    <link rel="icon" href="/static/img/smarket_ico.ico">

    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/sweetalert.min.js"></script>
    <%--<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>--%>
    <%--<link rel="stylesheet" href="../static/css/bootstrap.css">--%>

    <rapid:block name="head"/>


    <style type="text/css">


        #smarket-brand {
            text-decoration: none;
            color: #a6e1ec;
        }

        .input-group-btn {
            width: 0;
            padding-top: 10px;
        }

        #userBtn {
            border: hidden;
            font-weight: bold;
            font-size: 15px;
            background-color: inherit;
        }

        .user-type-span {
            display: block;
            padding: 3px 20px;
            clear: both;
            font-weight: normal;
            line-height: 1.42857143;
            color: #333;
            white-space: nowrap
        }

        .dropdown-menu {
            min-width: 100%;
        }

    </style>
</head>
<body>
<div class="container">
    <nav class="nav">
        <div class="container-fluid">
            <a class="navbar-brand" href="${ctx}/" id="smarket-brand">SMARKET</a>

            <ul class="list-inline pull-right">

                <c:choose>
                    <c:when test="${activeUser!=null}">
                        <li>
                            <div class="input-group-btn">
                                <button id="userBtn" type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${activeUser.username} <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li><span class="user-type-span">${role.description} </span></li>
                                        <%--<li><a href="#">超级管理员</a></li>--%>
                                    <li role="separator" class="divider"></li>
                                    <c:if test="${role.id==1 || role.id==2}">
                                        <li><a href="${ctx}/admin">管理中心</a></li>
                                        <li role="separator" class="divider"></li>
                                    </c:if>
                                    <li><a href="${ctx}/space">个人中心</a></li>
                                    <li><a href="${ctx}/gm">物品管理</a></li>
                                    <li><a href="${ctx}/om">我的订单</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="${ctx}/logout">登出</a></li>
                                </ul>
                            </div><!-- /btn-group -->
                        </li>

                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${ctx}/register" class="btn btn-default navbar-btn" style="border: hidden">注册</a>
                        </li>
                        <li>
                            <a href="${ctx}/login" class="btn btn-default navbar-btn" style="border: hidden">登陆</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
    <rapid:block name="content">主体部分</rapid:block>
</div>
<rapid:block name="sourcefile"/>
</body>
</html>
