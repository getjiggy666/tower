<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑短信模板</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $validateForm = $("#validateForm");
	var $tab = $("#tab");
	var $smsTemplateType = $("#smsTemplateType");
	var $memberId = $("#memberId");

	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"smsTemplate.name" : "required",
			"smsTemplate.content" : "required",
			"smsTemplate.templateType" : "required",
			"memberId": "required"
		},
		messages: {
			"smsTemplate.name" : "请填写名称",
			"smsTemplate.content" : "请填写内容",
			"smsTemplate.templateType" : "请选择模板类别",
			"memberId": "请填写所属会员"
		},
		submitHandler: function(form) {
			//$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});
	
	$smsTemplateType.live("change",function(){
		if($(this).val() == "activity") {
			$memberId.attr("disabled",false); 
			$("#memberTr").show();
		}else{
			$memberId.attr("disabled",true);
			$("#memberTr").hide(); 
		}
	});
	<#if !isAddAction && smsTemplate.templateType == 'activity'>
		$memberId.attr("disabled",false); 
		$("#memberTr").show();
	</#if>
	
});
</script>
</head>
<body class="input smsTemplate">
	<div class="bar">
		<#if isAddAction>添加短信模板<#else>编辑短信模板</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>sms_template!save.action<#else>sms_template!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<ul id="tab" class="tab">
				<li>
					<input type="button" value="基本信息" hidefocus />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						名称: 
					</th>
					<td>
						<input type="text" name="smsTemplate.name" class="formText" title="模板名称" value="${(smsTemplate.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						内容: 
					</th>
					<td>
						<textarea name="smsTemplate.content" class="formTextarea">${(smsTemplate.content)!}</textarea>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						模板类别: 
					</th>
					<td>
						<select id="smsTemplateType" name="smsTemplate.templateType" style="width:192px;">
							<option value="">请选择...</option>
							<#list templateTypeList as templateType>
								<option value="${templateType}" <#if !isAddAction && smsTemplate.templateType == templateType>selected</#if>>${action.getText("TemplateType." + templateType)}</option>
							</#list>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr id="memberTr" style="display: none">
					<th>
						所属会员: 
					</th>
					<td>
						<input type="text" id="memberId" name="memberId" class="formText" value="${(smsTemplate.member.memberId)!}" <#if !isAddAction && smsTemplate.templateType == 'system'>disabled="disabled"</#if> />
						<label class="requireField">*</label>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>