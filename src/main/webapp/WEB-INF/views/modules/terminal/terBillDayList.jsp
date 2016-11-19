<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>消费日流水管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/terminal/terBillDay/list");
            $("#searchForm").submit();
            return false;
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出日帐单数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/terminal/terBillDay/export");
                        $("#searchForm").submit();
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
            $("#btnImport").click(function () {
                $.jBox($("#importBox").html(), {
                    title: "导入数据", buttons: {"关闭": true},
                    bottomText: "导入文件不能超过5M，日帐单仅允许导入“csv”格式文件！"
                });
            });
        });
    </script>
    <style>
        .merchant-name {
            min-width: 250px;
        }

        .date {
            min-width: 80px;
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
                <li class="active"><a href="${ctx}/terminal/terBillDay/">消费日流水列表</a></li>
                <%--<shiro:hasPermission name="terminal:terBillDay:edit"><li><a href="${ctx}/terminal/terBillDay/form">消费日流水添加</a></li></shiro:hasPermission>--%>
            </ul>
            <form:form id="searchForm" modelAttribute="terBillDay" action="${ctx}/terminal/terBillDay/" method="post"
                       class="breadcrumb form-search">
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <ul class="ul-form">
                    <li><label>清算日期：</label>
                        <input name="beginClearDate" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate"
                               value="<fmt:formatDate value="${terBillDay.beginClearDate}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
                        <input name="endClearDate" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate"
                               value="<fmt:formatDate value="${terBillDay.endClearDate}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                    </li>
                    <li><label>终端号：</label>
                        <form:input path="terminalNum" htmlEscape="false" maxlength="20" class="input-medium"/>
                    </li>
                    <li class="clearfix"></li>
                    <li><label>商户编号：</label>
                        <form:input path="merchantNum" htmlEscape="false" maxlength="20" class="input-medium"/>
                    </li>
                    <li><label>商户名称：</label>
                        <form:input path="merchantName" htmlEscape="false" maxlength="100" class="input-medium"/>
                    </li>
                    <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                        <shiro:hasRole name="employee">
                            <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
                            <input id="btnImport" class="btn btn-primary" type="button" value="导入"/></shiro:hasRole>
                    </li>
                    <li class="clearfix"></li>
                </ul>
            </form:form>
        </td>
    </tr>
    <tr>
        <th class="date">清算日期</th>
        <th>交易代码</th>
        <th>受理流水</th>
        <th>交易日期时间</th>
        <th>卡号</th>
        <th>交易金额</th>
        <th>参考号</th>
        <th>授权码</th>
        <th>终端号</th>
        <th>商户编号</th>
        <th class="merchant-name">商户名称</th>
        <th>借记手续费</th>
        <th>贷记手续费</th>
        <%--<th>更新时间</th>--%>
        <shiro:hasPermission name="terminal:terBillDay:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="terBillDay">
        <tr>
            <td><a href="${ctx}/terminal/terBillDay/form?id=${terBillDay.id}">
                <fmt:formatDate value="${terBillDay.clearDate}" pattern="yyyy-MM-dd"/>
            </a></td>
            <td>
                    ${terBillDay.tranCode}
            </td>
            <td>
                    ${terBillDay.handleCode}
            </td>
            <td>
                    ${terBillDay.tranDateTime}
            </td>
            <td>
                    ${terBillDay.cardNo}
            </td>
            <td>
                    ${terBillDay.tranAmount}
            </td>
            <td>
                    ${terBillDay.referCode}
            </td>
            <td>
                    ${terBillDay.grantCode}
            </td>
            <td>
                    ${terBillDay.terminalNum}
            </td>
            <td>
                    ${terBillDay.merchantNum}
            </td>
            <td>
                    ${terBillDay.merchantName}
            </td>
            <td>
                    ${terBillDay.debitFee}
            </td>
            <td>
                    ${terBillDay.creditFee}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!--import box-->
<div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/terminal/terBillDay/import" method="post" enctype="multipart/form-data"
          class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
        <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
        <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
    </form>
</div>
<div class="pagination">${page}</div>
</body>
</html>