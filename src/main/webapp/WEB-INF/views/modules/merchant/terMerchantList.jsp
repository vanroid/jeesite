<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
    <style>
        .merchant-name {
            min-width: 210px;
        }

        .person-name {
            min-width: 40px;
        }

        .debit-rate {
            min-width: 80px;
        }

        .address {
            min-width: 180px;
        }

        .salesman {
            min-width: 80px;
        }

        .action {
            min-width: 100px;
        }

    </style>
</head>
<body>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <td colspan="30">
            <ul class="nav nav-tabs">
                <li class="active"><a href="${ctx}/merchant/terMerchant/">商户列表</a></li>
                <shiro:hasPermission name="merchant:terMerchant:edit">
                    <li><a href="${ctx}/merchant/terMerchant/form">商户添加</a></li>
                </shiro:hasPermission>
            </ul>
            <form:form id="searchForm" modelAttribute="terMerchant" action="${ctx}/merchant/terMerchant/" method="post"
                       class="breadcrumb form-search">
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <ul class="ul-form">
                    <li><label>商户号：</label>
                        <form:input path="merchantNum" htmlEscape="false" maxlength="20" class="input-medium"/>
                    </li>
                    <li><label>商户名称：</label>
                        <form:input path="merchantName" htmlEscape="false" maxlength="100" class="input-medium"/>
                    </li>
                    <li><label>联系电话：</label>
                        <form:input path="telphone" htmlEscape="false" maxlength="18" class="input-medium"/>
                    </li>
                    <li><label>业务员：</label>
                        <form:input path="salesman" htmlEscape="false" maxlength="20" class="input-medium"/>
                    </li>

                    <li class="btns"><button id="btnSubmit" class="btn btn-primary" type="submit"><i class="icon-search"></i>查询</button></li>
                    <li class="clearfix"></li>
                </ul>
            </form:form>
        </td>
    </tr>
    <tr>
        <th>商户号</th>
        <th class="merchant-name">商户名称</th>
        <%--<th>地址</th>--%>
        <th class="person-name">法人</th>
        <%--<th>入帐人</th>--%>
        <th>联系电话</th>
        <th class="debit-rate">借记卡费率</th>
        <th>贷记卡费率</th>
        <%--<th>外币卡费率</th>--%>
        <th>身份证号</th>
        <%--<th>银行卡号</th>--%>
        <%--<th>银行卡开户行</th>--%>
        <th>业务员</th>
        <%--<th>详情</th>--%>
        <th>所属机构</th>
        <shiro:hasPermission name="merchant:terMerchant:edit">
            <th class="action">操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="terMerchant">
        <tr>
            <td><a href="${ctx}/merchant/terMerchant/view?id=${terMerchant.id}">
                    ${terMerchant.merchantNum}
            </a></td>
            <td>
                    ${terMerchant.merchantName}
            </td>
                <%--<td>
                        ${terMerchant.merchantAddress}
                </td>--%>
                <td>
                        ${terMerchant.merchantLegalPerson}
                </td>
                <%--<td>
                        ${terMerchant.bookingPerson}
                </td>--%>
            <td>
                    ${terMerchant.telphone}
            </td>
            <td>
                    ${terMerchant.debitRate}
            </td>
            <td>
                    ${terMerchant.creditRate}
            </td>
                <%--<td>
                        ${terMerchant.foreignRate}
                </td>--%>
            <td>
                    ${terMerchant.idCard}
            </td>
            <%--<td>
                    ${terMerchant.bankCard}
            </td>--%>
                <%--<td>
                        ${terMerchant.bankCardAccountBank}
                </td>--%>
            <td>
                    ${terMerchant.salesman}
            </td>
                <%--<td>
                        ${terMerchant.merchatDesc}
                </td>--%>
            <td>
                    ${terMerchant.office.name}
            </td>
            <shiro:hasPermission name="merchant:terMerchant:edit">
                <td>
                    <a title="修改" class="btn btn-default btn-sm"
                       href="${ctx}/merchant/terMerchant/form?id=${terMerchant.id}"><i class="fa fa-cog"></i></a>
                    <a title="删除" class="btn btn-danger btn-sm"
                       href="${ctx}/merchant/terMerchant/delete?id=${terMerchant.id}"
                       onclick="return confirmx('确认要删除该商户吗？', this.href)"><i class="fa fa-trash-o fa-lg"></i></a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>