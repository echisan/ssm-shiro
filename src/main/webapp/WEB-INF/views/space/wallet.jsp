<%--
  Created by IntelliJ IDEA.
  User: E73AN
  Date: 2018/3/20
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="menu-content">
    <span style="font-weight: lighter;font-size: 17px;padding-right: 15px">
        余额: <strong>${wallet.balance}</strong>
    </span>

    <button class="btn btn-default" data-toggle="modal" data-target="#myModal">
        充值
    </button>
    <!-- 模态框（Modal） -->
    <div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        充值
                    </h4>
                </div>
                <script>
                    function add_balance() {
                        $.ajax({
                            url: "/space/wallet",
                            type: "post",
                            data: $("#balance_form").serializeArray(),
                            success: function (data) {
                                if (data === "success") {
                                    swal("充值成功!",{
                                        icon:"success"
                                    }).then(function () {
                                        window.location.reload();
                                    });

                                } else {
                                    swal("充值失败!",{
                                        icon:"error"
                                    }).then(function () {
                                        window.location.reload();
                                    });

                                }
                            }

                        })
                    }

                    function wallet_page(obj, event) {
                        event.preventDefault();
                        var obj = $(obj);
                        var obj_href = obj.attr("href");
                        var menuContainer = $("#menu-container");
                        menuContainer.load(obj_href);
                    }

                </script>
                <div class="modal-body">
                    <form action="#" id="balance_form">
                        <input type="text" name="add_balance" placeholder="充值金额" class="form-control">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" onclick="add_balance()">
                        充值
                    </button>

                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>


    <div class="page-header">
        <h4>余额明细</h4>
    </div>
    <c:choose>
        <c:when test="${walletRecordPageInfo!=null && walletRecordPageInfo.size!=0}">
            <div>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>类型</th>
                        <th>金额</th>
                        <th>时间</th>
                        <th>备注</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${walletRecordPageInfo.list}" var="item" varStatus="i">
                        <c:if test="${item.behaviourType==0}" var="flag"/>

                        <tr>

                            <td>${i.index+1}</td>
                            <td>
                                <c:if test="${item.behaviourType==0}">
                                    支出
                                </c:if>
                                <c:if test="${item.behaviourType==1}">
                                    收入
                                </c:if>
                            </td>

                            <td>${item.money}</td>
                            <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${item.remarks}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <nav aria-label="Page navigation" style="text-align: center">
                    <ul class="pagination">
                        <li><a href="${ctx}/space/wallet?page=1" onclick="wallet_page(this,event)">首页</a></li>
                        <li>
                            <c:if test="${walletRecordPageInfo.hasPreviousPage}">
                                <a href="${ctx}/space/wallet?page=${walletRecordPageInfo.pageNum-1}"
                                   aria-label="Previous" onclick="wallet_page(this,event)">
                                    <span aria-hidden="true">上一页</span>
                                </a>
                            </c:if>
                        </li>
                        <c:forEach items="${walletRecordPageInfo.navigatepageNums}" var="page_num">
                            <c:if test="${page_num == walletRecordPageInfo.pageNum}">
                                <li class="active" onclick="wallet_page(this,event)"><a href="#">${page_num}</a></li>
                            </c:if>
                            <c:if test="${page_num != walletRecordPageInfo.pageNum}">
                                <li><a href="${ctx}/space/wallet?page=${page_num}"
                                       onclick="wallet_page(this,event)"> ${page_num}</a></li>
                            </c:if>
                        </c:forEach>
                        <li>
                            <a href="${ctx}/space/wallet?page=${walletRecordPageInfo.pages}"
                               onclick="wallet_page(this,event)">尾页</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <h4>暂无明细信息！</h4>
            </div>
        </c:otherwise>
    </c:choose>


</div>

