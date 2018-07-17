<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加访问策略</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $validateForm = $("#validateForm");
	
	var $tab = $("#tab");
	var $addIPButton = $("#addIPButton");
	var $ipTable = $("#ipTable");
	var $deleteIP = $(".deleteIP");
	
	
	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
	
	// 添加IP
	var ipIndex = ${(accessStrategy.accessStrategyItemSet?size)!0};
	$addIPButton.click(function(){
		var ipTrHtml = '<tr class="ipTr" ipIndex="' + ipIndex + '">' +
			'<td>' +
				'<input type="text" name="accessStrategyItemList[' + ipIndex + '].accessName" class="formText" value="" />' +
			'</td>' +
			'<td>' +
				'<input type="text" name="accessStrategyItemList[' + ipIndex + '].accessIp" class="formText accessIp" value="" />' +
			'</td>' +
			'<td>' +
				'<span class="deleteIcon deleteIP" title="删 除">&nbsp;</span>' +
			'</td>' +
		'</tr>';
		$ipTable.append(ipTrHtml);
		ipIndex++;
	});
	
	// 删除IP
	$deleteIP.live("click", function() {
		var $this = $(this);
		$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteIP});
		function deleteIP(){
			$this.parent().parent().remove();
		}
	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		ignore: ".ignoreValidate",
		rules: {
			"accessStrategy.name": "required"
		},
		messages: {
			"accessStrategy.name": "策略名称不能为空"
		},
		submitHandler: function(form) {
			//$(form).find(":submit").attr("disabled", true);
			$.dialog({type: "warn", content: "确定<#if isAddAction>添加<#else>编辑</#if>访问策略?", ok: "确 定", cancel: "取 消", modal: true, okCallback: submitIp});
			function submitIp(){
				var length = $(".accessIp").size();
			    if (length > 0) {
 					form.submit();
 				} else {
 					$.message({type: "warn", content: "请填写访问策略信息!"});
 					return;
 				}
			}
		}
	});
	
	
	$.validator.addMethod("accessIpRequired", $.validator.methods.required, "请填写IP");
	$.validator.addMethod("accessIpDigits", $.validator.methods.ip, "IP格式不正确");
	$.validator.addClassRules("accessIp", {
		accessIpRequired: true,
		accessIpDigits: 0
	});
	
})
</script>
</head>
<body class="input">
	<div class="bar">
		<#if isAddAction>添加访问策略<#else>编辑访问策略</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" class="form" <#if isAddAction>action="access_strategy!save.action"<#else>action="access_strategy!update.action"</#if> enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" value="${(id)!}" />
			
			<ul id="tab" class="tab">
				<li>
					<input type="button" value="基本信息" hidefocus />
				</li>
				<li>
					<input type="button" value="策略信息" hidefocus />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						名称: 
					</th>
					<td>
						<input type="text" name="accessStrategy.name" class="formText" value="${(accessStrategy.name)!}" title="" />
						<label class="requireField">*</label>
					</td>
				</tr>
			</table>
			<div class="tabContent">
				<table class="inputTable">
					<tr class="noneHover">
						<td colspan="5">
							<input type="button" id="addIPButton" class="formButton" style="" value="增加策略" hidefocus />
						</td>
					</tr>
				</table>
				<table id="ipTable" class="inputTable">
					<tr class="title">
						<th>
							名称
						</th>
						<th>
							IP
						</th>
						<th>
							操作
						</th>
					</tr>
					<#if !isAddAction>
						<#list accessStrategy.accessStrategyItemSet as accessStrategyItem>
							<tr>
								<td>
									<input type="text" name="accessStrategyItemList[${accessStrategyItem_index}].accessName" class="formText" value="${(accessStrategyItem.accessName)!}" />
								</td>
								<td>
									<input type="text" name="accessStrategyItemList[${accessStrategyItem_index}].accessIp" class="formText accessIp" value="${(accessStrategyItem.accessIp)!}" />
								</td>
								<td>
									<span class="deleteIcon deleteIP" title="删 除">&nbsp;</span>
								</td>
							</tr>
						</#list>
					</#if>
				</table>
			</div>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>