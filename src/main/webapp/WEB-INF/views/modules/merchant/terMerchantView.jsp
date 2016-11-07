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
    <li class="active"><a href="${ctx}/merchant/terMerchant/view?id=${terMerchant.id}">商户详情</a></li>
</ul>
<br/>
<form id="inputForm" modelAttribute="terMerchant" class="container">
    <sys:message content="${message}"/>
    <div class="row">
        <table class="table">
            <tr>
                <td>商户号：</td>
                <td>${terMerchant.merchantNum}</td>
                <td>微信二维码：</td>
                <td>${terMerchant.wechatUrl}</td>
            </tr>
            <tr>
                <td>营业执照：</td>
                <td>${terMerchant.businessLicense}</td>
                <td>商户名称：</td>
                <td>${terMerchant.merchantName}</td>
            </tr>
            <tr>
                <td>地址：</td>
                <td>${terMerchant.merchantAddress}</td>
                <td>法人：</td>
                <td>${terMerchant.merchantLegalPerson}</td>
            </tr>
            <tr>
                <td>入帐人：</td>
                <td>${terMerchant.bookingPerson}</td>
                <td>联系电话：</td>
                <td>${terMerchant.telphone}</td>
            </tr>
            <tr>
                <td>借记卡费率：</td>
                <td>${terMerchant.debitRate}</td>
                <td>贷记卡费率：</td>
                <td>${terMerchant.creditRate}</td>
            </tr>
            <tr>
                <td>外币卡费率：</td>
                <td>${terMerchant.foreignRate}</td>
                <td>身份证号：</td>
                <td>${terMerchant.idCard}</td>
            </tr>
            <tr>
                <td>银行卡号：</td>
                <td>${terMerchant.bankCard}</td>
                <td>银行卡开户行：</td>
                <td>${terMerchant.bankCardAccountBank}</td>
            </tr>
            <tr>
                <td>所属用户：</td>
                <td>${terMerchant.user.name}</td>
                <td>详情：</td>
                <td>${terMerchant.merchatDesc}</td>
            </tr>
            <tr>
                <td>备注信息：</td>
                <td>${terMerchant.remarks}</td>
            </tr>
        </table>
        <div class="form-actions">
            <shiro:hasRole name="employee"><shiro:hasPermission name="merchant:terMerchant:edit"><a
                    class="btn btn-primary"
                    href="${ctx}/merchant/terMerchant/form?id=${terMerchant.id}">修
                改</a>&nbsp;</shiro:hasPermission></shiro:hasRole>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </div>

</form>
</body>
</html>