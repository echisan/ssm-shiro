<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/14
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">物品管理</rapid:override>
<rapid:override name="head">
    <link type="text/css" href="../../static/css/bootstrap.css">
    <link rel="stylesheet" href="../../static/css/smarket.css">

    <style type="text/css">
        .checkbox {
            position: relative;
            height: 30px;
        }

        .checkbox input[type='checkbox'] {
            position: absolute;
            left: 0;
            top: 0;
            width: 20px;
            height: 20px;
            opacity: 0;
        }

        .checkbox label {
            position: absolute;
            left: 30px;
            top: 0;
            height: 20px;
            line-height: 20px;
        }

        .checkbox label:before {
            content: '';
            position: absolute;
            left: -30px;
            top: 0;
            width: 20px;
            height: 20px;
            border: 1px solid #ddd;
            border-radius: 50%;
            transition: all 0.3s ease;
            -webkit-transition: all 0.3s ease;
            -moz-transition: all 0.3s ease;
        }

        .checkbox label:after {
            content: '';
            position: absolute;
            left: -22px;
            top: 3px;
            width: 6px;
            height: 12px;
            border: 0;
            border-right: 1px solid #fff;
            border-bottom: 1px solid #fff;
            background: #fff;
            transform: rotate(45deg);
            -webkit-transform: rotate(45deg);
            -moz-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
            transition: all 0.3s ease;
            -webkit-transition: all 0.3s ease;
            -moz-transition: all 0.3s ease;
        }

        .checkbox input[type='checkbox']:checked + label:before {
            background: #4cd764;
            border-color: #4cd764;
        }

        .checkbox input[type='checkbox']:checked + label:after {
            background: #4cd764;
        }



    </style>

    <script>

        var data = {};
        var imageUrls = [];

        function ajaxFileUpload() {
            var ajaxFormData = new FormData(document.getElementById("imageUploadForm"));
            var listContent = $("div[class='list-content']");
            $.ajax({
                url: "${ctx}/upload/image",
                type: "POST",
                data: ajaxFormData,
                async: true,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    if (data) {
                        alert("添加成功");
                        console.log(data);
                        for (var i = 0; i < data.length; i++) {
                            listContent.append("<img src='" + data[i] + "' class='img-responsive' style='height: 250px'/>");
                        }
                    }
                },
                error: function (e) {
                    alert("添加失败");
                }
            });
        }

        function uploadImage() {
            var imageFormData = new FormData(document.getElementById("imageForm"));
            $.ajax({
                url: "${ctx}/upload/image",
                type: "POST",
                data: imageFormData,
                async: true,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
//                    imageUrls = data.serializeArray();
//                    console.log(imageUrls);
                    for (var i = 0; i < data.length; i++) {
                        imageUrls.push(data[i]);
                    }
                    console.log(imageUrls);
                }
            });
        }


        function post_goods_form() {
            var name = $("input[name='name']").val();
            var price = $("input[name='price']").val();
            var description = $("textarea[name='description']").val();

            var data = {
                "name": name,
                "price": price,
                "description": description,
                "imageUrls": imageUrls
            };

            console.log(data);

            $.ajax({
                url: "/gm/goods2",
                type: "POST",
                data: {},
                success: function (data) {
                    console.log(data);
                }
            });
        }

    </script>
</rapid:override>

<rapid:override name="content">
    <div class="container row" id="crid">
        <div class="page-header">
            <h1>物品管理
                <c:if test="${TEMPLATE_PAGE_KEY=='add_goods_page'}">
                    <small><a href="javascript:post_goods_form();" class="btn btn-success pull-right">发布</a></small>
                </c:if>
            </h1>
        </div>
        <div class="col-md-3">
            <ul class="nav nav-pills nav-stacked">

                <li <c:if test="${TEMPLATE_PAGE_KEY=='add_goods_page'}">class="active"</c:if> >
                    <a href="${ctx}/gm">发布物品</a>
                </li>
                <li <c:if test="${TEMPLATE_PAGE_KEY=='published_goods'}">class="active"</c:if>>
                    <a href="${ctx}/gm/published">已发布物品</a>
                </li>
                <%--<li><a href="#">管理物品</a></li>--%>
            </ul>
        </div>
        <div class="col-md-9">
            <c:if test="${TEMPLATE_PAGE_KEY!=null}">
                <jsp:include page="${TEMPLATE_PAGE_KEY}.jsp"/>
            </c:if>
        </div>
    </div>
</rapid:override>
<%@include file="../base.jsp" %>
