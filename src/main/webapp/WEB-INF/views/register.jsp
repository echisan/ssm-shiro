<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/11
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<rapid:override name="title">注册</rapid:override>
<rapid:override name="head">
    <link type="text/css" href="../static/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../static/css/smarket.css">
    <script type="text/javascript" src="../static/js/jquery-3.3.1.min.js"></script>

    <style type="text/css">
        .register-container{
            padding-top: 30px;
            border: 1px solid #e5e9ef;
            border-radius: 3px;
        }


        .smarket-input{
            border-radius: 3px;
            width: 230px;
            height: 30px;
        }

        .smarket-button{
            width: 230px;
            height: 30px;
            border-radius: 3px;
        }

        .form-label{
            width: 50%;
            padding-bottom: 20px;
            text-align: right;
        }

        .form-label label{
            font-size: 14px;
            font-weight: inherit;
            width: 60px;
        }

        .form-label input{
           margin-left: 10px;
        }

        .form-label img{
            border-radius: 3px;
        }

        .error-msg{
            color: red;
            font-weight: lighter;
        }

    </style>
    
    <script type="text/javascript">
        $(document).ready(function () {

            var verify_flag;

            $("#kaptchaImage").click(function () {
                var time = new Date().getTime();
                $("#kaptchaImage").attr("src","${ctx}/captcha?t="+time);
            });


            <%--var registerForm = $("form[name='registerForm']");--%>
            <%--$("#registerBtnId").click(function () {--%>
                <%--$.ajax({--%>
                    <%--url:"${ctx}/register",--%>
                    <%--type:"post",--%>
                    <%--dataType:"json",--%>
                    <%--data:registerForm.serializeArray(),--%>
                    <%--success:function (data) {--%>
                        <%--console.log(data);--%>
                    <%--}--%>
                <%--});--%>
            <%--});--%>

            <%--$("#verifyCodeId").blur(function () {--%>
                <%--var verify_code = $("#verifyCodeId").val();--%>

                <%--$.ajax({--%>
                    <%--url:"${ctx}/captcha",--%>
                    <%--type:"POST",--%>
                    <%--data:{"verifyCode": verify_code},--%>
                    <%--success:function (data) {--%>
                        <%--console.log("verify_code:"+data);--%>
                        <%--var verify_span = $("#verifyCheckSpan");--%>
                        <%--if (data===true){--%>
                            <%--verify_flag = true;--%>

                            <%--if (!verify_span.hasClass("glyphicon glyphicon-ok")){--%>
                                <%--verify_span.addClass("glyphicon glyphicon-ok");--%>
                                <%--verify_span.css("color","limegreen");--%>
                            <%--}--%>

                        <%--}else {--%>
                            <%--verify_flag = false;--%>
                            <%--if (!verify_span.hasClass("glyphicon glyphicon-remove")){--%>
                                <%--verify_span.addClass("glyphicon glyphicon-remove");--%>
                                <%--verify_span.css("color","indianred");--%>
                            <%--}--%>
                        <%--}--%>

                    <%--}--%>
                <%--});--%>
            <%--});--%>
        });
    </script>
</rapid:override>

<rapid:override name="content">
    <div class="register-container">
        <div class="register-form container" style="width: 80%">
            <div style="padding-left: 50px;padding-bottom: 10px">
                <p>已有帐号？<a href="${ctx}/login">立即登陆</a></p>
                <%--<span class="glyphicon glyphicon-ok" style="color: limegreen"></span>--%>
                <%--<span class="glyphicon glyphicon-remove" style="color: indianred"></span>--%>
            </div>
            <form action="${ctx}/register" style="padding-left: 0" name="registerForm" modelAttribute="registerForm" method="post">
                <div class="form-label">
                    <label for="usernameId" class="">帐号</label>
                    <input id="usernameId" name="username" type="text" class="smarket-input" value="${usernameTemp}"><br>
                    <span class="error-msg"><c:out value="${errors.get('USERNAME_IS_EXIST')}" default=""/></span>
                    <span class="error-msg"><c:out value="${errors.get('USERNAME_IS_NULL')}" default=""/></span>

                </div>
                <div class="form-label">
                    <label for="passwordId">密码</label>
                    <input id="passwordId" name="password" type="password" class="smarket-input"><br>
                    <span class="error-msg"><c:out value="${errors.get('PASSWORD_IS_NULL')}" default=""/></span>
                </div>
                <div class="form-label">
                    <label for="rePasswordId">确认密码</label>
                    <input id="rePasswordId" name="rePassword" type="password" class="smarket-input"><br>
                    <span class="error-msg"><c:out value="${errors.get('PASSWORD_NOT_SAME')}" default=""/></span>
                </div>
                <div class="form-label" id="verifyCodeLabel">
                    <label for="verifyCodeId">验证码</label>
                    <input id="verifyCodeId" name="verifyCode" type="text" class="smarket-input" style="width: 125px">
                    <img src="${ctx}/captcha" alt="" width="100px" height="30px" id="kaptchaImage" title="看不清楚？换一张！"><br>
                    <span class="error-msg"><c:out value="${errors.get('VERIFY_CODE_ERROR')}" default=""/></span>

                </div>
                <div class="form-label">
                    <button type="submit" class="smarket-button" id="registerBtnId">注册</button>
                </div>
            </form>
        </div>

    </div>
</rapid:override>
<%@include file="base.jsp"%>
