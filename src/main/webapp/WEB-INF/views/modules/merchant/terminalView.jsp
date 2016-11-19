<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class=""><a href="${ctx}/merchant/terMerchant/list">商户列表</a></li>
    <li class=""><a href="${ctx}/merchant/terMerchant/view?id=${terMerchant.id}">商户详情</a></li>
    <li class="active"><a href="${ctx}/merchant/terMerchant/terminal/view?id=${terMerchant.id}">终端信息</a></li>
</ul>
<br/>

<form class="container">
    <sys:message content="${message}"/>
    <c:forEach items="${terminals}" var="terTerminal">
        <div class="row">
            <table class="table table-striped table-condensed table-bordered">
                <tr>
                    <td>终端号</td>
                    <td>${terTerminal.terminalNum}</td>
                    <td>下机日期：</td>
                    <td><fmt:formatDate value="${terTerminal.downDate}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>机型号：</td>
                    <td>${terTerminal.deviceType}</td>
                    <td>机身号：</td>
                    <td>${terTerminal.deviceNum}</td>
                </tr>
                <tr>
                    <td>装机电话：</td>
                    <td>${terTerminal.installPhone}</td>
                    <td>机具类型：</td>
                    <td>${terTerminal.machineType}</td>
                </tr>
                <tr>
                    <td>装机人：</td>
                    <td>${terTerminal.installPerson}</td>
                    <td>通讯卡号：</td>
                    <td>${terTerminal.commCardNum}</td>
                </tr>
                <tr>
                    <td>备注信息：</td>
                    <td colspan="3">${terTerminal.terminalRemarks}</td>
                </tr>
                <tr>
                    <td>操作：</td>
                    <td colspan="3">
                        <shiro:hasRole name="employee">
                            <shiro:hasPermission name="terminal:terTerminal:edit">
                                <a class="btn btn-default btn-sm" href="${ctx}/terminal/terTerminal/form?id=${terTerminal.id}"><i class="fa fa-cog"></i></a>
                                <a class="btn btn-danger btn-sm" href="${ctx}/terminal/terTerminal/delete?id=${terTerminal.id}"
                                   onclick="return confirmx('确认要删除该终端信息吗？', this.href)"><i class="fa fa-trash-o fa-lg"></i></a>
                            </shiro:hasPermission>
                        </shiro:hasRole>
                    </td>
                </tr>
            </table>
        </div>
    </c:forEach>
    <br/>
    <div class="row">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>