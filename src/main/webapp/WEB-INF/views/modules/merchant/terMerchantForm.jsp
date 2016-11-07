<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
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
		<li><a href="${ctx}/merchant/terMerchant/">商户列表</a></li>
		<li class="active"><a href="${ctx}/merchant/terMerchant/form?id=${terMerchant.id}">商户<shiro:hasPermission name="merchant:terMerchant:edit">${not empty terMerchant.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="merchant:terMerchant:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="terMerchant" action="${ctx}/merchant/terMerchant/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商户号：</label>
			<div class="controls">
				<form:input path="merchantNum" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信二维码：</label>
			<div class="controls">
				<form:input path="wechatUrl" htmlEscape="false" maxlength="512" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">营业执照：</label>
			<div class="controls">
				<form:input path="businessLicense" htmlEscape="false" maxlength="512" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商户名称：</label>
			<div class="controls">
				<form:input path="merchantName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:input path="merchantAddress" htmlEscape="false" maxlength="512" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">法人：</label>
			<div class="controls">
				<form:input path="merchantLegalPerson" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入帐人：</label>
			<div class="controls">
				<form:input path="bookingPerson" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="telphone" htmlEscape="false" maxlength="18" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借记卡费率：</label>
			<div class="controls">
				<form:input path="debitRate" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">贷记卡费率：</label>
			<div class="controls">
				<form:input path="creditRate" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">外币卡费率：</label>
			<div class="controls">
				<form:input path="foreignRate" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡号：</label>
			<div class="controls">
				<form:input path="bankCard" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡开户行：</label>
			<div class="controls">
				<form:input path="bankCardAccountBank" htmlEscape="false" maxlength="18" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属用户：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${terMerchant.user.id}" labelName="user.name" labelValue="${terMerchant.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详情：</label>
			<div class="controls">
				<form:input path="merchatDesc" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasRole name="employee"><shiro:hasPermission name="merchant:terMerchant:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission></shiro:hasRole>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>