<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>POS终端管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出终端详情数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/terminal/posTerminal/export");
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
        /*function page(n, s) {
         if (n) $("#pageNo").val(n);
         if (s) $("#pageSize").val(s);
         $("#searchForm").attr("action", "${ctx}/terminal/posTerminal/list");
         $("#searchForm").submit();
         return false;
         }*/
    </script>
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
    <style type="text/css">
        .merchant-td {
            min-width: 210px;
        }

        .address-td {
            min-width: 180px;
        }

        .terminal-num-td {
            min-width: 85px;
        }

        .terminal-type-td {
            min-width: 100px;
        }

        .salesman-td {
            min-width: 80px;
        }

        .date-td {
            min-width: 80px;
        }

        .action-td {
            min-width: 120px;
        }
        .user-td {
            min-width: 40px;
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
                <li class="active"><a href="${ctx}/terminal/posTerminal/">POS终端列表</a></li>
                <shiro:hasPermission name="terminal:posTerminal:edit">
                    <li><a href="${ctx}/terminal/posTerminal/form">POS终端添加</a></li>
                </shiro:hasPermission>
            </ul>
            <form:form id="searchForm" modelAttribute="posTerminal" action="${ctx}/terminal/posTerminal/" method="post"
                       class="breadcrumb form-search">
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <ul class="ul-form">
                    <li><label>终端号：</label>
                        <form:input path="terminalNum" htmlEscape="false" maxlength="20" class="input-medium"/>
                    </li>
                    <li><label>商户编号：</label>
                        <form:input path="merchantNum" htmlEscape="false" maxlength="20" class="input-medium"/>
                    </li>
                    <li class="clearfix"></li>
                    <li><label>商户名称：</label>
                        <form:input path="merchantName" htmlEscape="false" maxlength="100" class="input-medium"/>
                    </li>
                    <%--<li><label>银行卡号：</label>
                        <form:input path="bankCard" htmlEscape="false" maxlength="100" class="input-medium"/>
                    </li>--%>

                    <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                        <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
                        <input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
                    <li class="clearfix"></li>
                </ul>
            </form:form>
        </td>
    </tr>
    <tr>
        <th class="terminal-num-td">终端号</th>
        <%--<th>进件日期</th>
        <th>下机日期</th>--%>
        <th class="date-td">装机日期</th>
        <th>交件支行</th>
        <th class="terminal-type-td">机子类型</th>
        <th>商户号</th>
        <th>机身号</th>
        <%--<th>微信二维码</th>
        <th>营业执照</th>--%>
        <th class="merchant-td">商户名称</th>
        <%--<th>地址</th>--%>
        <%--<th>法人</th>--%>
        <th>入帐人</th>
        <th>联系电话</th>
        <%--<th>装机电话</th>
        <th>MCC码</th>--%>
        <%--<th>借记卡费率</th>
        <th>贷记卡费率</th>--%>
        <%--<th>外币卡费率</th>--%>
        <%--<th>身份证号</th>--%>
        <th>机具类型</th>
        <%--<th>银行卡号</th>--%>
        <%--<th>银行卡开户行</th>--%>
        <th class="salesman-td">业务员</th>
        <th>所属机构</th>
        <%--<th>详情</th>--%>

        <shiro:hasPermission name="terminal:posTerminal:edit">
            <th class="action-td">操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="posTerminal">
        <tr>
            <td><a href="${ctx}/terminal/posTerminal/form?id=${posTerminal.id}">${posTerminal.terminalNum}</a></td>
                <%--<td><fmt:formatDate value="${posTerminal.importDate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${posTerminal.downDate}" pattern="yyyy-MM-dd"/></td>--%>
            <td><fmt:formatDate value="${posTerminal.installDate}" pattern="yyyy-MM-dd"/></td>
            <td>${posTerminal.transBank}</td>
            <td>${posTerminal.deviceType}</td>
            <td>${posTerminal.merchantNum}</td>
            <td>${posTerminal.deviceNum}</td>
                <%--<td>${posTerminal.wechatUrl}</td>
                <td>${posTerminal.businessLicense}</td>--%>
            <td>${posTerminal.merchantName}</td>
                <%--<td>${posTerminal.merchantAddress}</td>--%>
                <%--<td>${posTerminal.merchantLegalPerson}</td>--%>
            <td>${posTerminal.bookingPerson}</td>
            <td>${posTerminal.telphone}</td>
                <%--<td>${posTerminal.installPhone}</td>
                <td>${posTerminal.deviceMcc}</td>--%>
                <%--<td>${posTerminal.debitRate}</td>
                <td>${posTerminal.creditRate}</td>--%>
                <%--<td>${posTerminal.foreignRate}</td>--%>
                <%--<td>${posTerminal.idCard}</td>--%>
            <td>${posTerminal.machineType}</td>
            <%--<td>${posTerminal.bankCard}</td>--%>
                <%--<td>${posTerminal.bankCardAccountBank}</td>--%>
            <td>${posTerminal.salesman}</td>
            <td>${posTerminal.user.name}</td>
                <%--<td>${posTerminal.terminalDesc}</td>--%>
            <shiro:hasPermission name="terminal:posTerminal:edit">
                <td>
                    <a title="修改" class="btn btn-default btn-sm" href="${ctx}/terminal/posTerminal/form?id=${posTerminal.id}"><i class="fa fa-cog"></i></a>
                    <a title="删除" class="btn btn-danger btn-sm" href="${ctx}/terminal/posTerminal/delete?id=${posTerminal.id}"
                       onclick="return confirmx('确认要删除该POS终端吗？', this.href)"><i class="fa fa-trash-o fa-lg"></i></a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>

<!--import box-->
<div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/terminal/posTerminal/import" method="post" enctype="multipart/form-data"
          class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
        <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
        <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
        <a href="${ctx}/terminal/posTerminal/import/template">下载模板</a>
    </form>
</div>
</body>
</html>