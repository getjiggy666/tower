<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑部门</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.translate.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	
	var $validateForm = $("#validateForm");
	var $departmentName = $("#departmentName");
	var $departmentSign = $("#departmentSign");
	var $departmentSignLoadingIcon = $("#departmentSignLoadingIcon");

	$departmentName.change( function() {
		var $this = $(this);
		$this.translate("zh", "en", {
			data: true,
			replace: false,
			start: function() {
				$departmentSignLoadingIcon.show();
			},
			complete: function() {
				var departmentSignValue = $.trim($this.data("translation").en.value.toLowerCase()).replace(/\s+/g, "_").replace(/[^\w]+/g, "");
				$departmentSign.val(departmentSignValue);
				$departmentSignLoadingIcon.hide();
			},
			error: function() {
				$departmentSignLoadingIcon.hide();
			}
		});

	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"department.name": "required",
			"department.type": "required",
			"department.sign": {
				required: true,
				alphanumeric: true,
				<#if isAddAction>
					remote: "department!checkSign.action"
				<#else>
					remote: "department!checkSign.action?oldValue=${department.sign?url}"
				</#if>
			},
			"department.orderList": "digits"
		},
		messages: {
			"department.name": "请填写名称",
			"department.type": "请选择类别",
			"department.sign": {
				required: "请填写标识",
				alphanumeric: "标识只允许包含英文、数字和下划线",
				remote: "标识已存在"
			},
			"department.orderList": "排序必须为零或正整数"
		},
		submitHandler: function(form) {
			//$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});

});
</script>
</head>
<body class="input">
	<div class="bar">
		<#if isAddAction>添加部门<#else>编辑部门</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>department!save.action<#else>department!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<table class="inputTable">
				<tr>
					<th>
						名称: 
					</th>
					<td>
						<input type="text" id="departmentName" name="department.name" class="formText" value="${(department.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						类别: 
					</th>
					<td>
						<#if isAddAction>
						<select name="department.type">
							<option value="">请选择...</option>
							<option value="0">内部部门</option>
							<option value="1">合作方</option>
						</select>
						<#else>
						<input type="hidden" name="department.type" value="${department.type}" />
						<#if department.type == '0'>内部部门</#if><#if department.type == '1'>合作方</#if>
						</#if>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						上级部门: 
					</th>
					<td>
						<#if isAddAction>
							<select name="parentId">
								<option value="">顶级部门</option>
								<#list departmentTreeList as departmentTree>
									<option value="${departmentTree.id}">
										<#if departmentTree.grade != 0>
											<#list 1..departmentTree.grade as i>
												&nbsp;&nbsp;
											</#list>
										</#if>
										${departmentTree.name}
									</option>
								</#list>
							</select>
						<#else>
							${(department.parent.name)!'顶级部门'}
						</#if>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						标识: 
					</th>
					<td>
						<input type="text" id="departmentSign" name="department.sign" class="formText" value="${(department.sign)!}" title="该部门的唯一标识,用于部门路径和模板标识" />
						<label class="requireField">*</label>
						<span id="departmentSignLoadingIcon" class="loadingIcon hidden">&nbsp;</span>
					</td>
				</tr>
				<tr>
					<th>
						排序: 
					</th>
					<td>
						<input type="text" name="department.orderList" class="formText" value="${(department.orderList)!}" title="只允许输入零或正整数" />
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