<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>月帐单管理</title>
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
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出月帐单数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/terminal/terBillMonth/export");
                        $("#searchForm").submit();
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
            $("#btnImport").click(function () {
                $.jBox($("#importBox").html(), {
                    title: "导入数据", buttons: {"关闭": true},
                    bottomText: "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
                });
            });
        });
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
                <li class="active"><a href="${ctx}/terminal/terBillMonth/">月帐单列表</a></li>
                <%--<shiro:hasPermission name="terminal:terBillMonth:edit"><li><a href="${ctx}/terminal/terBillMonth/form">月帐单添加</a></li></shiro:hasPermission>--%>
            </ul>
            <form:form id="searchForm" modelAttribute="terBillMonth" action="${ctx}/terminal/terBillMonth/"
                       method="post" class="breadcrumb form-search">
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <ul class="ul-form">
                    <li><label>清算日期：</label>
                        <input name="clearDate" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate"
                               value="<fmt:formatDate value="${terBillMonth.clearDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
                    </li>
                    <li><label>商户编号：</label>
                        <form:input path="merchantNum" htmlEscape="false" maxlength="20" class="input-medium"/>
                    </li>
                    <li><label>终端号：</label>
                        <form:input path="terminalNum" htmlEscape="false" maxlength="20" class="input-medium"/>
                    </li>
                    <li class="clearfix"></li>
                    <li><label>商户名称：</label>
                        <form:input path="merchantName" htmlEscape="false" maxlength="100" class="input-medium"/>
                    </li>
                    <li><label>维护公司：</label>
                        <form:input path="maintenanceCompany" htmlEscape="false" maxlength="10" class="input-medium"/>
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
        <th>清算日期</th>
        <th>商户编号</th>
        <th>终端号</th>
        <th>收单行</th>
        <th>收单行简称</th>
        <th class="merchant-name">商户名称</th>
        <th>维护公司代码</th>
        <th>维护公司简称</th>
        <th>总笔数</th>
        <th>总金额</th>
        <th>IC卡笔数</th>
        <th>IC卡金额</th>
        <th>非接联机笔数</th>
        <th>非接联机金额</th>
        <th>Appl金额</th>
        <th>非接脱机笔数</th>
        <th>非接脱机金额</th>
        <th>云闪付笔数</th>
        <th>云闪付金额</th>
        <th>Appl笔数</th>
        <th>HCE笔数</th>
        <th>HCE金额</th>
        <th>Sams笔数</th>
        <th>Sams金额</th>
        <%--<shiro:hasPermission name="terminal:terBillMonth:edit">
            <th>操作</th>
        </shiro:hasPermission>--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="terBillMonth">
        <tr>
            <td>
                <fmt:formatDate value="${terBillMonth.clearDate}" pattern="yyyyMM"/>
            </td>
            <td>
                    ${terBillMonth.merchantNum}
            </td>
            <td>
                    ${terBillMonth.terminalNum}
            </td>
            <td>
                    ${terBillMonth.acquiringBank}
            </td>
            <td>
                    ${terBillMonth.acquiringNick}
            </td>
            <td>
                    ${terBillMonth.merchantName}
            </td>
            <td>
                    ${terBillMonth.maintenanceCompany}
            </td>
            <td>
                    ${terBillMonth.maintenanceCompanyNick}
            </td>
            <td>
                    ${terBillMonth.totalTimes}
            </td>
            <td>
                    ${terBillMonth.totalAmount}
            </td>
            <td>
                    ${terBillMonth.icTimes}
            </td>
            <td>
                    ${terBillMonth.icAmount}
            </td>
            <td>
                    ${terBillMonth.nonOnlineTimes}
            </td>
            <td>
                    ${terBillMonth.nonOnlineAmount}
            </td>
            <td>
                    ${terBillMonth.nonOfflineTimes}
            </td>
            <td>
                    ${terBillMonth.nonOfflineAmount}
            </td>
            <td>
                    ${terBillMonth.cloudTimes}
            </td>
            <td>
                    ${terBillMonth.cloudAmount}
            </td>
            <td>
                    ${terBillMonth.applTimes}
            </td>
            <td>
                    ${terBillMonth.applAmount}
            </td>
            <td>
                    ${terBillMonth.hceTimes}
            </td>
            <td>
                    ${terBillMonth.hceAmount}
            </td>
            <td>
                    ${terBillMonth.samsTimes}
            </td>
            <td>
                    ${terBillMonth.samsAmount}
            </td>
            <%--<shiro:hasPermission name="terminal:terBillMonth:edit">
                <td>
                    <a href="${ctx}/terminal/terBillMonth/form?id=${terBillMonth.id}">修改</a>
                    <a href="${ctx}/terminal/terBillMonth/delete?id=${terBillMonth.id}"
                       onclick="return confirmx('确认要删除该月帐单吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!--import box-->
<div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/terminal/terBillMonth/import" method="post" enctype="multipart/form-data"
          class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
        <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
        <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
        <a href="${ctx}/terminal/terBillMonth/import/template">下载模板</a>
    </form>
</div>
<div class="pagination">${page}</div>
</body>
</html>