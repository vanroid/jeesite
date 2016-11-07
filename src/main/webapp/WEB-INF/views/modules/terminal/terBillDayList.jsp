<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消费日流水管理</title>
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
		<li class="active"><a href="${ctx}/terminal/terBillDay/">消费日流水列表</a></li>
		<%--<shiro:hasPermission name="terminal:terBillDay:edit"><li><a href="${ctx}/terminal/terBillDay/form">消费日流水添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="terBillDay" action="${ctx}/terminal/terBillDay/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>清算日期：</label>
				<input name="beginClearDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${terBillDay.beginClearDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endClearDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>清算日期</th>
				<th>交易代码</th>
				<th>受理流水</th>
				<th>交易日期时间</th>
				<th>卡号</th>
				<th>交易金额</th>
				<th>参考号</th>
				<th>授权码</th>
				<th>终端号</th>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>借记手续费</th>
				<th>贷记手续费</th>
				<%--<th>更新时间</th>--%>
				<shiro:hasPermission name="terminal:terBillDay:edit"><th>操作</th></shiro:hasPermission>
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
				<%--<td>
					<fmt:formatDate value="${terBillDay.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>--%>
				<%--<shiro:hasPermission name="terminal:terBillDay:edit"><td>
    				<a href="${ctx}/terminal/terBillDay/form?id=${terBillDay.id}">修改</a>
					<a href="${ctx}/terminal/terBillDay/delete?id=${terBillDay.id}" onclick="return confirmx('确认要删除该消费日流水吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>