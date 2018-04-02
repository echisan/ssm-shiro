<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/19
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<rapid:override name="title">修改</rapid:override>
<rapid:override name="head">
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css">
    <style>
        body {
            overflow-y: auto;
        }

        .goods-info {
            width: 100%;
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

        .img-list li {
            list-style-type: none;
            padding-bottom: 15px;
        }


    </style>
</rapid:override>
<rapid:override name="content">
    <div class="container">
        <div class="page-header">
            <h3>物品编辑</h3>
        </div>

        <div class="col-md-2">
            <h4>信息更新</h4>
        </div>
        <div class="col-md-10">
            <div class="goods-info">
                <script>
                    function update_goods_info() {
                        var form = $("#update_goods_form");
                        $.ajax({
                            url:"${ctx}/gm/goods/${goodsVO.goods.id}",
                            type:"put",
                            data:form.serializeArray(),
                            success:function (data) {
                                if (data==="success"){
                                    swal("修改成功",{
                                        icon:"success"
                                    });
                                }else if (data==="failed"){
                                    swal("修改失败！",{
                                        icon:"error"
                                    });
                                }else if (data===null){
                                    swal("请先选择图片再上传！",{
                                        icon:"error"
                                    });
                                }
                            }
                        });
                    }
                </script>
                <form action="#" id="update_goods_form">
                    <div style="padding-top: 20px;width: 400px">
                        <p>物品名称</p>
                        <input type="text" name="name" value="${goodsVO.goods.name}" title="物品名称" class="form-control"/>
                    </div>
                    <div style="width: 400px;padding-top: 10px">
                        <p>物品价格</p>
                        <input type="text" name="price" value="${goodsVO.goods.price}" title="物品价格" class="form-control"/>
                    </div>
                    <div style="padding-top: 10px">
                        <p>物品类别</p>
                        <div class="container" style="padding-left: 0;width: 100%">
                            <c:forEach items="${goodsVO.updateGoodsCategoryVOList}" var="item">
                                <div class="col-md-2" style="height: 30px">
                                    <div class='checkbox'>
                                        <input type='checkbox' <c:if test="${item.select}">checked="checked"</c:if> id='${item.category.name}' name='categoryIds' value="${item.category.id}">
                                        <label for='${item.category.name}' style="padding-left: 0">${item.category.name}</label>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div style="padding-top: 10px">
                        <p>物品描述</p>
                        <textarea name="description" id="description" cols="20" rows="5" class="form-control"
                                  style="resize: none">${goodsVO.goods.description}</textarea>
                    </div>

                    <input type="hidden" name="user_id" value="${goodsVO.goods.userId}">

                    <div class="pull-right" style="padding-top: 10px">
                        <a href="javascript:update_goods_info()" class="btn btn-info">确认修改</a>
                    </div>
                </form>
            </div>
        </div>

    </div>

    <div class="container" style="border-top: 1px solid #eeeeee;padding-top: 35px;margin-top: 30px">
        <div class="col-md-2">
            <h4>图片更新</h4>
        </div>
        <div class="col-md-10">
            <div style="border-bottom: 1px solid #eeeeee; padding-bottom: 5px;">
                <script>
                    function upload_image() {
                        var formData = new FormData(document.getElementById("update_image"));
                        $.ajax({
                            url:"${ctx}/upload/image",
                            type:"post",
                            data:formData,
                            async: true,
                            cache: false,
                            contentType: false,
                            processData: false,
                            success:function (data) {
                                console.log(data);
                                $.ajax({
                                    url:"${ctx}/gm/goods/${goodsVO.goods.id}/image",
                                    type:"post",
                                    success:function (data) {
                                        if (data==="success"){
                                            swal("修改成功",{
                                                icon:"success"
                                            });
                                            window.location.reload();
                                        }else{
                                            swal("请先选择图片！",{
                                                icon:"error"
                                            });
                                        }
                                    }
                                });
                            }
                        });
                    }
                </script>
                <form action="#" enctype="multipart/form-data" id="update_image">
                    <label for="newImageInputId" class="btn btn-default">选择新图片</label>
                    <input type="file" id="newImageInputId" style="display: none" multiple name="uploadImage">
                    <a href="javascript:upload_image()" class="btn btn-primary" style="margin-left: 15px">添加图片</a>
                </form>
            </div>

            <script>
                function delete_image(ojb) {
                    var  thisOjb = $(ojb);
                    var imageId = thisOjb.attr("imageId");
                    console.log(imageId);

                    swal({
                        title:"确认删除？",
                        text:"一旦删除无法恢复",
                        buttons:true,
                        icon:"warning",
                        dangerModel:true
                    }).then(function (willDelete) {
                        if (willDelete){
                            $.ajax({
                                url:"${ctx}/gm/goods/image/"+imageId,
                                type:"delete",
                                success:function (data) {
                                    if (data==="success"){
                                        swal("删除成功！",{
                                            icon:"success"
                                        }).then(function () {
                                            window.location.reload();
                                        })

                                    }else if (data==="failed"){
                                        swal("删除失败！",{
                                            icon:"error"
                                        })
                                    }
                                }
                            });
                        }
                    })



                }
            </script>

            <div class="img-list" style="margin-bottom: 300px">
                <ul class="container" style="width: 100%">
                    <c:forEach items="${goodsVO.images}" var="image">
                        <li class="col-md-5">
                            <div class="img-item">
                                <img src="${image.url}" alt="" class="img-responsive"
                                     style="padding: 10px;border-radius: 15px">
                                <div class="pull-right" style="padding-right: 10px">
                                    <a href="javascript:void(0)" onclick="delete_image(this)" class="btn btn-danger" imageId="${image.id}">删除</a>
                                    <%--<a href="#" class="btn btn-warning">更换</a>--%>
                                </div>

                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</rapid:override>

<%@include file="../base.jsp" %>