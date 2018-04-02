<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/16
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    function post_goods_form() {
        var form_data = $("#goodsForm").serializeArray();
        console.log(form_data);

        $.ajax({
            url: "${ctx}/gm/goods",
            type: "POST",
            data: form_data,
            success: function (data) {
                console.log(data);
                if (data === "success") {
                    swal("添加成功！",{
                        icon:"success"
                    }).then(function () {
                        window.location.href = "gm/published";
                    });
                } else {
                    console.log("failed");
                    swal("失败！",{
                        icon:"error"
                    })
                }
            }
        });
    }
</script>
<div class="list-content">
    <form id="goodsForm" action="${ctx}/gm/goods" method="post" modelAttribute="newGoods">
        <label>物品名称:<input type="text" class="form-control" name="name"></label><br>
        <label>物品类别:</label><br>
        <div class="container" style="height: auto;padding-left: 0;width: 100%">
            <c:forEach items="${categories}" var="category">
                <div class="col-md-2">
                    <div class='checkbox'>
                        <input type='checkbox' id='${category.name}' name='categoryIds' value="${category.id}">
                        <label for='${category.name}' style="padding-left: 0"><c:out value="${category.name}"/></label>
                    </div>
                </div>
            </c:forEach>
        </div>


        <label>出售价格:<input type="text" class="form-control" name="price"></label><br>
        <label>物品描述:</label>
        <textarea rows="5" cols="10" class="form-control" title="物品描述" name="description"></textarea><br>

    </form>

    <form action="#" id="imageUploadForm">
        <label for="uploadInputId" class="btn btn-primary">选择图片</label>
        <input type="file" class="form-control" size="3" name="uploadImage" multiple id="uploadInputId"
               style="display: none">
        <a href="javascript:ajaxFileUpload();" class="btn btn-info pull-right">上传</a>
    </form>

</div>
