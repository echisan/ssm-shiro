<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/25
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    td{
        font-size: 13px;
    }
</style>

<script>
    function delete_goods(obj,event) {
        var thisObj = $(obj);
        event.preventDefault();
        swal({
            title:"确定删除？",
            text:"请谨慎操作！删除后无法恢复！",
            icon:"warning",
            buttons:true,
            dangerModel:true
        }).then(function (willDelete) {
            if (willDelete){

                $.ajax({
                    url:thisObj.attr("href"),
                    type:"delete",
                    success:function (data) {
                        if (data==="success"){
                            swal("删除成功！",{
                                icon:"success"
                            }).then(function () {
                                window.location.reload();
                            });
                        }else{
                            swal({
                                text:"失败:"+data,
                                icon:"error"
                            })
                        }
                    }
                })

            }
        })
    }

    function load_this_page(obj,event) {
        event.preventDefault();
        var obj = $(obj);
        var obj_href = obj.attr("href");
        var menuContainer = $("#menu-container");
        menuContainer.load(obj_href);
    }

    function search_user_goods() {
        var username = $("input[name='username']").val();
        $("#menu-container").load("${ctx}/admin/goods/"+username);
    }
</script>

<div class="menu-content">

    <h4>物品管理</h4>

    <form action="#" class="form-inline" id="new_category_form">
        <input type="text" style="width: 400px" name="username" placeholder="用户名" class="form-control">
        <a href="#" class="btn btn-primary" style="color: #eeeeee;width: 100px" onclick="search_user_goods()">搜索</a>
    </form>



    <div class="page-header">
        <h4>物品列表</h4>
    </div>
    <table class="table table-hover" style="margin-top:30px">
        <thead>
        <tr>
            <th>ID</th>
            <th>名称</th>
            <th>价格</th>
            <th>状态</th>
            <th>发布时间</th>
            <th>发布者</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${adminGoodsVOList}" var="goodsVO">
            <tr>
                <td>${goodsVO.goods.id}</td>
                <td>${goodsVO.goods.name}</td>
                <td>${goodsVO.goods.price}</td>
                <c:choose>
                    <c:when test="${goodsVO.goods.isSell==0}">
                        <td><span style="color: indianred">待出售</span></td>
                    </c:when>
                    <c:otherwise>
                        <td> <span style="color: darkseagreen">已出售</span></td>
                    </c:otherwise>
                </c:choose>
                <td><fmt:formatDate value="${goodsVO.goods.createTime}" pattern="yyyy-MM-dd"/> </td>
                <td>${goodsVO.user.username}</td>
                <td>
                    <a href="${ctx}/goods/${goodsVO.goods.id}" target="_blank" class="btn btn-xs btn-default" style="margin-right: 5px">浏览</a>
                    <a href="${ctx}/admin/goods/${goodsVO.goods.id}" class="btn btn-xs btn-danger" style="color: #eeeeee" onclick="delete_goods(this,event)">删除</a>
                </td>
            </tr>
        </c:forEach>
        </thead>
    </table>

    <c:if test="${pageInfo.hasNextPage || pageInfo.hasPreviousPage}">
        <nav aria-label="Page navigation" style="text-align: center">
            <ul class="pagination">
                <li><a href="${ctx}/admin/goods?page=1" onclick="load_this_page(this,event)">首页</a></li>
                <li>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <a href="${ctx}/admin/goods?page=${pageInfo.pageNum-1}"
                           onclick="load_this_page(this,event)" aria-label="Previous">
                            <span aria-hidden="true">上一页</span>
                        </a>
                    </c:if>
                </li>
                <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                    <c:if test="${page_num == pageInfo.pageNum}">
                        <li class="active"><a href="#">${page_num}</a></li>
                    </c:if>
                    <c:if test="${page_num != pageInfo.pageNum}">
                        <li><a href="${ctx}/admin/goods?page=${page_num}"
                               onclick="load_this_page(this,event)"> ${page_num}</a></li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="${ctx}/admin/goods?page=${pageInfo.pages}"
                       onclick="load_this_page(this,event)">尾页</a>
                </li>
            </ul>
        </nav>
    </c:if>
</div>