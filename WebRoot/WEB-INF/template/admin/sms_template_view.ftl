<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看短信模板</title>
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
	var $tab = $("#tab");

	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});

});
</script>
</head>
<body class="input smsTemplate">
	<div class="bar">
		查看短信模板
	</div>
	<div class="body">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" hidefocus />
			</li>
			<#if smsTemplate.member?? >
			<li>
				<input type="button" value="审核流程" hidefocus />
			</li>
			</#if>
		</ul>
		<table class="inputTable tabContent">
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
		<#if smsTemplate.member?? >
		<div class="tabContent">
			<table id="checkTable" class="inputTable">
				<tr id="checkTitle" class="title">				
					<th>
						审核人
					</th>
					<th>
						时间
					</th>
					<th>
						动作
					</th>
					<th>
						备注
					</th>
				</tr>
				<#list (smsTemplate.checkSet)! as check>
					<tr class="checkTr" checkIndex="${check_index}">
						<td>
							${check.admin.name}
						</td>
						<td>
							${(check.checkTime?string("yyyy-MM-dd HH:mm"))!}
						</td>
						<td>
							${action.getText("CheckAction." + check.checkAction)}
						</td>
						<td>
							${check.comment}
						</td>
					</tr>
				</#list>
			</table>
		</div>
		</#if>
		<div class="buttonArea">
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
		</div>
	</div>
</body>
</html>