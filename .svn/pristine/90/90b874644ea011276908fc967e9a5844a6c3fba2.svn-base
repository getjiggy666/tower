<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看访问策略</title>
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

	var $tab = $("#tab");
	
	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
})
</script>
</head>
<body class="input">
	<div class="bar">
		查看访问策略
	</div>
	<div class="body">
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
					标识: 
				</th>
				<td>
					${(accessStrategy.sign)!}
				</td>
			</tr>
			<tr>
				<th>
					名称: 
				</th>
				<td>
					${(accessStrategy.name)!}
				</td>
			</tr>
		</table>
		<div class="tabContent">
			<table id="ipTable" class="inputTable">
				<tr class="title">
					<th>
						名称
					</th>
					<th>
						IP
					</th>
				</tr>
				<#if !isAddAction>
					<#list accessStrategy.accessStrategyItemSet as accessStrategyItem>
						<tr>
							<td>
								${(accessStrategyItem.accessName)!}
							</td>
							<td>
								${(accessStrategyItem.accessIp)!}
							</td>
						</tr>
					</#list>
				</#if>
			</table>
		</div>
		<div class="buttonArea">
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
		</div>
	</div>
</body>
</html>