<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑角色</title>
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
	
	var $allChecked = $("#validateForm .allChecked");
	
	$allChecked.click( function() {
		var $this = $(this);
		var $thisCheckbox = $this.parent().parent().find(":checkbox");
		if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.attr("checked", false);
		} else {
			$thisCheckbox.attr("checked", true);
		}
		return false;
	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"role.name": "required"
		},
		messages: {
			"role.name": "请填写角色名称"
		},
		submitHandler: function(form) {
			//$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});
	
	$.validator.addMethod("roleAuthorityListRequired", $.validator.methods.required, "请选择管理权限");
	
	$.validator.addClassRules("roleAuthorityList", {
		roleAuthorityListRequired: true
	});
	
})
</script>
</head>
<body class="input role">
	<div class="bar">
		<#if isAddAction>添加角色<#else>编辑角色</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>role!save.action<#else>role!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<table class="inputTable">
				<tr>
					<th>
						角色名称: 
					</th>
					<td>
						<input type="text" name="role.name" class="formText" value="${(role.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						描述: 
					</th>
					<td>
						<textarea name="role.description" class="formTextarea">${(role.description)!}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr class="authorityList">
					<th>
						<a href="#" class="allChecked" title="点击全选此类权限">短信通知: </a>
					</th>
					<td>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_SMS_TEMPLATE"<#if (isAddAction || role.authorityList.contains("ROLE_SMS_TEMPLATE"))!> checked</#if> />短信模板列表
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_SMS_TEMPLATE_ADD"<#if (isAddAction || role.authorityList.contains("ROLE_SMS_TEMPLATE_ADD"))!> checked</#if> />添加短信模板
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_SMS_TEMPLATE_EDIT"<#if (isAddAction || role.authorityList.contains("ROLE_SMS_TEMPLATE_EDIT"))!> checked</#if> />编辑短信模板
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_SMS_TEMPLATE_DELETE"<#if (isAddAction || role.authorityList.contains("ROLE_SMS_TEMPLATE_DELETE"))!> checked</#if> />删除短信模板
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_SMS_TEMPLATE_CHECK"<#if (isAddAction || role.authorityList.contains("ROLE_SMS_TEMPLATE_CHECK"))!> checked</#if> />审核短信模板
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_SMS"<#if (isAddAction || role.authorityList.contains("ROLE_SMS"))!> checked</#if> />短信列表
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_MESSAGE"<#if (isAddAction || role.authorityList.contains("ROLE_MESSAGE"))!> checked</#if> />通知管理
						</label>
					</td>
				</tr>
				<tr class="authorityList">
					<th>
						<a href="#" class="allChecked" title="点击全选此类权限">文章管理: </a>
					</th>
					<td>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ARTICLE"<#if (isAddAction || role.authorityList.contains("ROLE_ARTICLE"))!> checked</#if> />文章列表
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ARTICLE_ADD"<#if (isAddAction || role.authorityList.contains("ROLE_ARTICLE_ADD"))!> checked</#if> />文章添加
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ARTICLE_EDIT"<#if (isAddAction || role.authorityList.contains("ROLE_ARTICLE_EDIT"))!> checked</#if> />文章编辑
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ARTICLE_DELETE"<#if (isAddAction || role.authorityList.contains("ROLE_ARTICLE_DELETE"))!> checked</#if> />文章删除
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ARTICLE_CATEGORY"<#if (isAddAction || role.authorityList.contains("ROLE_ARTICLE_CATEGORY"))!> checked</#if> />文章分类列表
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ARTICLE_CATEGORY_ADD"<#if (isAddAction || role.authorityList.contains("ROLE_ARTICLE_CATEGORY_ADD"))!> checked</#if> />文章分类添加
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ARTICLE_CATEGORY_EDIT"<#if (isAddAction || role.authorityList.contains("ROLE_ARTICLE_CATEGORY_EDIT"))!> checked</#if> />文章分类编辑
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ARTICLE_CATEGORY_DELETE"<#if (isAddAction || role.authorityList.contains("ROLE_ARTICLE_CATEGORY_DELETE"))!> checked</#if> />文章分类删除
						</label>
					</td>
				</tr>
				<tr class="authorityList">
					<th>
						<a href="#" class="allChecked" title="点击全选此类权限">权限管理: </a>
					</th>
					<td>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ADMIN"<#if (isAddAction || role.authorityList.contains("ROLE_ADMIN"))!> checked</#if> />用户管理
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_DEPARTMENT"<#if (isAddAction || role.authorityList.contains("ROLE_DEPARTMENT"))!> checked</#if> />部门管理
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ROLE"<#if (isAddAction || role.authorityList.contains("ROLE_ROLE"))!> checked</#if> />角色管理
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_LOG"<#if (isAddAction || role.authorityList.contains("ROLE_LOG"))!> checked</#if> />日志管理
						</label>
					</td>
				</tr>
				<tr class="authorityList">
					<th>
						<a href="#" class="allChecked" title="点击全选此类权限">系统设置: </a>
					</th>
					<td>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_SETTING"<#if (isAddAction || role.authorityList.contains("ROLE_SETTING"))!> checked</#if> />参数设置
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ACCESS_STRATEGY"<#if (isAddAction || role.authorityList.contains("ROLE_ACCESS_STRATEGY"))!> checked</#if> />访问策略
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ACCESS_STRATEGY_ADD"<#if (isAddAction || role.authorityList.contains("ROLE_ACCESS_STRATEGY_ADD"))!> checked</#if> />添加访问策略
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ACCESS_STRATEGY_DELETE"<#if (isAddAction || role.authorityList.contains("ROLE_ACCESS_STRATEGY_DELETE"))!> checked</#if> />删除访问策略
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_ACCESS_STRATEGY_EDIT"<#if (isAddAction || role.authorityList.contains("ROLE_ACCESS_STRATEGY_EDIT"))!> checked</#if> />修改访问策略
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_AREA"<#if (isAddAction || role.authorityList.contains("ROLE_AREA"))!> checked</#if> />地区列表
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_AREA_ADD"<#if (isAddAction || role.authorityList.contains("ROLE_AREA_ADD"))!> checked</#if> />添加地区
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_AREA_EDIT"<#if (isAddAction || role.authorityList.contains("ROLE_AREA_EDIT"))!> checked</#if> />修改地区
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_AREA_DELETE"<#if (isAddAction || role.authorityList.contains("ROLE_AREA_DELETE"))!> checked</#if> />删除地区
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_MAIL_TEMPLATE"<#if (isAddAction || role.authorityList.contains("ROLE_MAIL_TEMPLATE"))!> checked</#if> />邮件模板
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_MAIL_TEMPLATE_EDIT"<#if (isAddAction || role.authorityList.contains("ROLE_MAIL_TEMPLATE_EDIT"))!> checked</#if> />修改邮件模板
						</label>
						<label>
							<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_BUILD_HTML"<#if (isAddAction || role.authorityList.contains("ROLE_BUILD_HTML"))!> checked</#if> />页面静态化
						</label>
					</td>
				</tr>
				<#if (role.isSystem)!false>
					<tr>
						<th>
							&nbsp;
						</th>
						<td>
							<span class="warnInfo"><span class="icon">&nbsp;</span>系统提示: </b>系统内置角色不允许修改!</span>
						</td>
					</tr>
				</#if>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>