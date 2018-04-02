<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/20
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    function update_password() {
        var input = $("input[name='new_password']");
        if (input.val()===""){
            alert("密码不能为空！");
            return;
        }

        $.ajax({
            url:"space/pwd",
            type:"post",
            data:$("#pwd_form").serializeArray(),
            success:function (data) {
                if (data==="success"){
                    alert("修改成功！");
                }else {
                    alert(data);
                }
            }
        });
    }

</script>
<div class="menu-content">
    <h5>密码修改</h5>
    <form action="#" style="width: 30%" id="pwd_form">
        <input type="password" title="新密码" placeholder="输入新密码" name="new_password" class="form-control">
        <a href="javascript:void(0);" onclick="update_password()"
           class="btn btn-warning pull-right" style="margin-top: 10px">修改密码</a>
    </form>
</div>