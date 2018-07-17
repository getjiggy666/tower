<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>邮件模板列表</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
</head>
<body class="list">
	<div class="bar">页面模板列表</div>
	<div class="body">
		<div class="blank"></div>
		<table id="listTable" class="listTable">
			<tr>
				<th>
					<span>模板名称</span>
				</th>
				<th>
					<span>描述</span>
				</th>
				<th>
					<span>模板文件路径</span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list allMailTemplateConfigList as mailTemplateConfig>
				<tr>
					<td>
						${mailTemplateConfig.name}
					</td>
					<td>
						${mailTemplateConfig.description}
					</td>
					<td>
						${mailTemplateConfig.templatePath}
					</td>
					<td>
						<a href="mail_template!edit.action?mailTemplateConfig.name=${mailTemplateConfig.name}" title="[编辑]">[编辑]</a>
					</td>
				</tr>
			</#list>
		</table>
		<div class="blank"></div>
	</div>
</body>
</html>