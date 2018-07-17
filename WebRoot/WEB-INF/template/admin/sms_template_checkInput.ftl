<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>审核短信模板</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $tab = $("#tab");
	$backProcessButton = $("#backProcessButton");
	$passProcessButton = $("#passProcessButton");
	$invalidProcessButton = $("#invalidProcessButton");
	$checkComment = $("#checkComment");

	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
	
	// 通过
	$passProcessButton.click( function() {
		var $this = $(this);
		$.dialog({type: "warn", content: "短信模板通过,确认执行?", ok: "确 定", cancel: "取 消", width: 420, modal: true, okCallback: smsTemplatePass});
		function smsTemplatePass() {
			$.ajax({
				url: "sms_template!pass.action",
				data: {"id": "${smsTemplate.id}","check.comment": $checkComment.val()},
				type: "POST",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$this.attr("disabled", true);
				},
				success: function(data) {
					$.message({type: data.status, content: data.message});
					if (data.status == "success") {
						$backProcessButton.attr("disabled", true);
						$invalidProcessButton.attr("disabled", true);
						setTimeout("location.href = 'sms_template!checkList.action'",2500);
					} else {
						$this.attr("disabled", true);
					}
				}
			});
		}
	});
	
	// 驳回
	$backProcessButton.click( function() {
		if($checkComment.val() == "") {
			$.message({type: "warn", content: "请填写审核备注!"});
			return;
		}
		var $this = $(this);
		$.dialog({type: "warn", content: "短信模板驳回,确认执行?", ok: "确 定", cancel: "取 消", width: 420, modal: true, okCallback: smsTemplateBack});
		function smsTemplateBack() {
			$.ajax({
				url: "sms_template!back.action",
				data: {"id": "${smsTemplate.id}","check.comment": $checkComment.val()},
				type: "POST",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$this.attr("disabled", true);
				},
				success: function(data) {
					$.message({type: data.status, content: data.message});
					if (data.status == "success") {
						$passProcessButton.attr("disabled", true);
						$invalidProcessButton.attr("disabled", true);
						setTimeout("location.href = 'sms_template!checkList.action'",2500);
					} else {
						$this.attr("disabled", true);
					}
				}
			});
		}
	});
	
	// 作废
	$invalidProcessButton.click( function() {
		if($checkComment.val() == "") {
			$.message({type: "warn", content: "请填写审核备注!"});
			return;
		}
		var $this = $(this);
		$.dialog({type: "warn", content: "短信模板作废后将不允许对此短信模板进行任何操作,确认执行?", ok: "确 定", cancel: "取 消", width: 420, modal: true, okCallback: smsTemplateInvalid});
		function smsTemplateInvalid() {
			$.ajax({
				url: "sms_template!invalid.action",
				data: {"id": "${smsTemplate.id}","check.comment": $checkComment.val()},
				type: "POST",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$this.attr("disabled", true);
				},
				success: function(data) {
					$.message({type: data.status, content: data.message});
					if (data.status == "success") {
						$backProcessButton.attr("disabled", true);
						$passProcessButton.attr("disabled", true);
						setTimeout("location.href = 'sms_template!checkList.action'",2500);
					} else {
						$this.attr("disabled", true);
					}
				}
			});
		}
	});
});
</script>
</head>
<body class="input smsTemplate">
	<div class="bar">
		审核短信模板
	</div>
	<div class="body">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="审核处理" hidefocus />
			</li>
		</ul>
		<table class="inputTable tabContent">
			<tr>
				<th>
					审核备注: 
				</th>
				<td>
					<input type="text" id="checkComment" name="check.comment" class="formText" />	
				</td>
			</tr>
			<tr>
				<th>
					当前审核人: 
				</th>
				<td style="color:red">
					${loginAdmin.name}	
				</td>
			</tr>
			<tr>
				<th>
					审核操作: 
				</th>
				<td>
					<input type="button" id="passProcessButton" class="formButton" value="通过" hidefocus />
					<input type="button" id="backProcessButton" class="formButton" value="驳回" hidefocus />
					<input type="button" id="invalidProcessButton" class="formButton" value="作废" hidefocus />
				</td>
			</tr>
			<tr>
				<td colspan="4">
					&nbsp;
				</td>
			</tr>
			<tr>
				<th>
					标识: 
				</th>
				<td>
					${(smsTemplate.sign)!}
				</td>
			</tr>
			<tr>
				<th>
					名称: 
				</th>
				<td>
					${(smsTemplate.name)!}
				</td>
			</tr>
			<tr>
				<th>
					内容: 
				</th>
				<td>
					${(smsTemplate.content)!}
				</td>
			</tr>
			<tr>
				<th>
					模板类别: 
				</th>
				<td>
					${action.getText("TemplateType." + smsTemplate.templateType)}
				</td>
			</tr>
			<tr>
				<th>
					是否启用: 
				</th>
				<td>
					<#if smsTemplate.isUse>
						已启用
					<#else>
						未启用
					</#if>
				</td>
			</tr>
			<tr>
				<th>
					创建者: 
				</th>
				<td>
					<#if smsTemplate.admin?? >
						${smsTemplate.admin.name}
					<#else>
						<#if smsTemplate.member?? >
						${smsTemplate.member.name}
						</#if>
					</#if>
				</td>
			</tr>
			<tr>
				<th>
					创建者类型: 
				</th>
				<td>
					<#if smsTemplate.admin?? >
						管理员
					<#else>
						<#if smsTemplate.member?? >
						会员
						</#if>
					</#if>
				</td>
			</tr>
			<#if smsTemplate.member?? >
			<tr>
				<th>
					销售商: 
				</th>
				<td>
					${(smsTemplate.member.username)!}
				</td>
			</tr>
			<tr>
				<th>
					审核状态: 
				</th>
				<td style="color:red">
					${action.getText("CheckStatus." + smsTemplate.checkStatus)}
				</td>
			</tr>
			</#if>
		</table>
		<div class="buttonArea">
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
		</div>
	</div>
</body>
</html>