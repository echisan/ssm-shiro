<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/21
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .menu-content label{
        display: block;
    }

</style>
<script>

    function save_info() {

        $.ajax({
            url:"${ctx}/space/info/${activeUser.id}",
            type:"post",
            data:$("#info_form").serializeArray(),
            success:function (data) {
                if (data==="success"){
                    swal("修改成功!",{
                        icon:"success"
                    })
                }else {
                    swal("失败",{
                        icon:"error"
                    });
                }
            }
        })
    }
</script>
<div class="menu-content">
    <form action="#" id="info_form">
        <div style="width:20%">
            <label>
                收件人姓名<input type="text" name="real_name" class="form-control" value="${userInfo.realName}">
            </label><br>
            <label>
                联系号码<input type="text" name="phone" class="form-control" value="${userInfo.phone}">
            </label><br>
        </div>
        <div style="width: 80%">
            <label>
                收件地址 <input type="text" name="address" class="form-control" value="${userInfo.address}">
            </label><br>
            <a href="javascript:void (0);" onclick="save_info()" class="btn btn-primary">保存修改</a>
        </div>
    </form>
</div>