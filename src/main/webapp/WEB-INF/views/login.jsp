<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/11
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<rapid:override name="title">登陆</rapid:override>
<rapid:override name="head">
    <link type="text/css" href="../static/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../static/css/smarket.css">
    <script type="text/javascript" src="../static/js/jquery-3.3.1.min.js"></script>

    <style type="text/css">
        .login-container{
            padding-top: 30px;
            border: 1px solid #e5e9ef;
            border-radius: 3px;
        }

        .error-msg{
            color: red;
            font-weight: lighter;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#kaptchaImage").click(function () {
                var time = new Date().getTime();
                $("#kaptchaImage").attr("src","${ctx}/captcha?t="+time);
            });
        });
    </script>
</rapid:override>
<rapid:override name="content">
    <c:if test="${LOGIN_STATUS_FAILED!=null}" var="msg">
        <div class="alert alert-danger" role="alert"><c:out value="${LOGIN_STATUS_FAILED}" default=""/> </div>
    </c:if>
    <c:if test="${LOGIN_STATUS_SUCCEED!=null}" var="msg">
        <div class="alert alert-success" role="alert"><c:out value="${LOGIN_STATUS_SUCCEED}" default=""/> </div>
    </c:if>


    <div class="login-container">
        <div class="login-form container" style="width: 80%">
            <div style="padding-left: 50px;padding-bottom: 10px">
                <p>没有帐号？<a href="${ctx}/register">立即注册</a></p>
                <%--<span class="glyphicon glyphicon-ok" style="color: limegreen"></span>--%>
                <%--<span class="glyphicon glyphicon-remove" style="color: indianred"></span>--%>
            </div>
            <form action="${ctx}/login" style="padding-left: 0" method="post" modelAttribute="loginForm">
                <div class="form-label">
                    <label for="usernameId" class="">帐号</label>
                    <input id="usernameId" name="username" type="text" class="smarket-input" value="<c:out value="${username}" default=""/>"><br>
                    <span class="error-msg"><c:out value="${errors.get('USERNAME_IS_NULL')}" default=""/></span>
                </div>
                <div class="form-label">
                    <label for="passwordId">密码</label>
                    <input id="passwordId" name="password" type="password" class="smarket-input"><br>
                    <span class="error-msg"><c:out value="${errors.get('PASSWORD_IS_NULL')}" default=""/></span>
                </div>
                <div class="form-label">
                    <label for="verifyCodeId">验证码</label>
                    <input id="verifyCodeId" name="verifyCode" type="text" class="smarket-input" style="width: 125px">
                    <img id="kaptchaImage" src="${ctx}/captcha" alt="" width="100px" height="30px"><br>
                    <span class="error-msg"><c:out value="${errors.get('VERIFY_CODE_ERROR')}" default=""/></span>
                </div>
                <div class="form-label">
                    <button type="submit" class="smarket-button">登陆</button>
                </div>
            </form>
        </div>
    </div>
</rapid:override>
<%@include file="base.jsp"%>
