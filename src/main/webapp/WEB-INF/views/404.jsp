<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/26
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<rapid:override name="content">
    <div class="container">
        <div class="col-md-5">
            <img src="../static/img/404.png" alt="" class="img-responsive">
        </div>
        <div class="col-md-7">
            <h2>404啦</h2>
        </div>
    </div>

</rapid:override>

<%@include file="base.jsp"%>
