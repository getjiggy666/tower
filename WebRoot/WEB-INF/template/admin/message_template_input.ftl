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
<script type="text/javascript" src="${base}/template/common/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $validateForm = $("#validateForm");
	var $tab = $("#tab");

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
			"messageTemplate.name" : "required",
			"messageTemplate.title" : "required",
			"messageTemplate.content" : "required"
		},
		messages: {
			"messageTemplate.name" : "请填写名称",
			"messageTemplate.title" : "请填写标题",
			"messageTemplate.content" : "请填写内容"
		},
		submitHandler: function(form) {
			//$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});
	
	// 标签
	$(".tag-content").click(function(){
		var content = $(this).attr("tag-content");
		var old = $("#content").val();
		$("#content").val(old + content);
	});
	
});
</script>
</head>
<body class="input messageTemplate">
	<div class="bar">
		<#if isAddAction>添加短信模板<#else>编辑短信模板</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>message_template!save.action<#else>message_template!update.action</#if>" method="post">
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
						<input type="text" name="messageTemplate.name" class="formText" title="模板名称" value="${(messageTemplate.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						标题: 
					</th>
					<td>
						<input type="text" name="messageTemplate.title" class="formText" title="模板标题" value="${(messageTemplate.title)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						内容: 
					</th>
					<td>
						标签：&nbsp;
						<a class="tag-content" tag-content="#member#" href="javascript:void(0);">【会员名称】</a>
						<br />
						<textarea id="content" name="messageTemplate.content" class="formTextarea">${(messageTemplate.content)!}</textarea>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						设置: 
					</th>
					<td>
						<label>
							<@checkbox name="messageTemplate.isSystem" value="${(messageTemplate.isSystem)!true}" />系统通知&nbsp;&nbsp;
							<@checkbox name="messageTemplate.isEnabled" value="${(messageTemplate.isEnabled)!true}" />启用
						</label>
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