<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端信息管理</title>
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
		<li class="active"><a href="${ctx}/terminal/terTerminal/">终端信息列表</a></li>
		<shiro:hasPermission name="terminal:terTerminal:edit"><li><a href="${ctx}/terminal/terTerminal/form">终端信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="terTerminal" action="${ctx}/terminal/terTerminal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>终端号：</label>
				<form:input path="terminalNum" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>商户号：</label>
				<form:input path="merchantNum" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><button id="btnSubmit" class="btn btn-primary" type="submit"><i class="icon-search"></i>查询</button></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>下机日期</th>
				<th>终端号</th>
				<th>机型号</th>
				<th>机身号</th>
				<th>装机电话</th>
				<th>机具类型</th>
				<th>装机人</th>
				<th>通讯卡号</th>
				<th>备注</th>
				<th>商户号</th>
				<shiro:hasPermission name="terminal:terTerminal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="terTerminal">
			<tr>
				<td><a href="${ctx}/terminal/terTerminal/form?id=${terTerminal.id}">
					<fmt:formatDate value="${terTerminal.downDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${terTerminal.terminalNum}
				</td>
				<td>
					${terTerminal.deviceType}
				</td>
				<td>
					${terTerminal.deviceNum}
				</td>
				<td>
					${terTerminal.installPhone}
				</td>
				<td>
					${terTerminal.machineType}
				</td>
				<td>
					${terTerminal.installPerson}
				</td>
				<td>
					${terTerminal.commCardNum}
				</td>
				<td>
					${terTerminal.terminalRemarks}
				</td>
				<td>
					${terTerminal.merchantNum}
				</td>
				<shiro:hasPermission name="terminal:terTerminal:edit"><td>
    				<a href="${ctx}/terminal/terTerminal/form?id=${terTerminal.id}">修改</a>
					<a href="${ctx}/terminal/terTerminal/delete?id=${terTerminal.id}" onclick="return confirmx('确认要删除该终端信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>