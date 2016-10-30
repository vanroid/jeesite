<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>POS终端管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/terminal/posTerminal/">POS终端列表</a></li>
		<shiro:hasPermission name="terminal:posTerminal:edit"><li><a href="${ctx}/terminal/posTerminal/form">POS终端添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="posTerminal" action="${ctx}/terminal/posTerminal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>终端号</th>
				<th>进件日期</th>
				<th>下机日期</th>
				<th>所属用户</th>
				<th>装机日期</th>
				<th>交件支行</th>
				<th>机子类型</th>
				<th>商户号</th>
				<th>机身号</th>
				<%--<th>微信二维码</th>
				<th>营业执照</th>--%>
				<th>商户名称</th>
				<th>地址</th>
				<th>法人</th>
				<th>入帐人</th>
				<th>联系电话</th>
				<%--<th>装机电话</th>
				<th>MCC码</th>--%>
				<th>借记卡费率</th>
				<th>贷记卡费率</th>
				<th>外币卡费率</th>
				<th>身份证号</th>
				<th>机具类型</th>
				<th>银行卡号</th>
				<%--<th>银行卡开户行</th>--%>
				<th>业务员</th>
				<th>详情</th>

				<shiro:hasPermission name="terminal:posTerminal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="posTerminal">
			<tr>
				<td><a href="${ctx}/terminal/posTerminal/form?id=${posTerminal.id}">${posTerminal.termialNum}</a></td>
				<td><fmt:formatDate value="${posTerminal.importDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${posTerminal.downDate}" pattern="yyyy-MM-dd"/></td>
				<td>${posTerminal.user.name}</td>
				<td><fmt:formatDate value="${posTerminal.installDate}" pattern="yyyy-MM-dd"/></td>
				<td>${posTerminal.transBank}</td>
				<td>${posTerminal.deviceType}</td>
				<td>${posTerminal.merchantNum}</td>
				<td>${posTerminal.deviceNum}</td>
				<%--<td>${posTerminal.wechatUrl}</td>
				<td>${posTerminal.businessLicense}</td>--%>
				<td>${posTerminal.merchantName}</td>
				<td>${posTerminal.merchantAddress}</td>
				<td>${posTerminal.merchantLegalPerson}</td>
				<td>${posTerminal.bookingPerson}</td>
				<td>${posTerminal.telphone}</td>
				<%--<td>${posTerminal.installPhone}</td>
				<td>${posTerminal.deviceMcc}</td>--%>
				<td>${posTerminal.debitRate}</td>
				<td>${posTerminal.creditRate}</td>
				<td>${posTerminal.foreignRate}</td>
				<td>${posTerminal.idCard}</td>
				<td>${posTerminal.machineType}</td>
				<td>${posTerminal.bankCard}</td>
				<%--<td>${posTerminal.bankCardAccountBank}</td>--%>
				<td>${posTerminal.salesman}</td>
				<td>${posTerminal.terminalDesc}</td>
				<shiro:hasPermission name="terminal:posTerminal:edit"><td>
    				<a href="${ctx}/terminal/posTerminal/form?id=${posTerminal.id}">修改</a>
					<a href="${ctx}/terminal/posTerminal/delete?id=${posTerminal.id}" onclick="return confirmx('确认要删除该POS终端吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>