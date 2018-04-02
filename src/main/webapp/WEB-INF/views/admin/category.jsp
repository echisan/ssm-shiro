<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/24
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script>
    function add_category() {

        $.ajax({
            url:"${ctx}/admin/category",
            type:"post",
            data:$("#new_category_form").serializeArray(),
            success:function (data) {
                if(data==="success"){
                    alert("添加成功");
                    window.location.reload();
                }else {
                    alert("失败！"+data);
                }
            }

        })
    }

    function delete_category() {
        var i = confirm("确认删除？");
        if (i!==false){
            $.ajax({
                url:"${ctx}/admin/category/delete",
                type:"post",
                data:$("#category_form").serializeArray(),
                success:function (data) {
                    if (data==="success"){
                        alert("删除成功!!");
                        window.location.reload();
                    }else if (data==="error"){
                        alert("请先选择要删除的类别！！");
                    }else {
                        alert("failed "+data);
                    }
                }
            })
        }
    }
</script>


<div class="menu-content">

    <h4>添加分类</h4>

    <form action="" class="form-inline" id="new_category_form">
        <input type="text" style="width: 400px" name="category_name" placeholder="分类名称" class="form-control">
        <a href="#" class="btn btn-primary" style="color: #eeeeee;width: 100px" onclick="add_category()">添加</a>
    </form>

    <div class="page-header">
        <h4>删除分类 <small><a href="javascript:void (0)" onclick="delete_category()" class="btn btn-danger pull-right" style="color: #eeeeee;width: 100px">删除</a></small></h4>
    </div>
    <div class="container" style="padding-left: 0;width: 100%">
        <form action="" id="category_form">
            <c:forEach items="${categories}" var="item">
                <div class="col-md-2" style="height: 30px">
                    <div class='checkbox'>
                        <input type='checkbox' id='${item.name}' name='categoryIds' value="${item.id}">
                        <label for='${item.name}' style="padding-left: 0">${item.name}</label>
                    </div>
                </div>
            </c:forEach>
        </form>
    </div>
</div>