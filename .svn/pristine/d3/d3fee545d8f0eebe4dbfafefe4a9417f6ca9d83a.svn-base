<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看抢红包活动详情</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/ajaxUtil.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/template/common/datePicker/WdatePicker.js"></script>
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
<body class="input contract">
	<div class="bar">
		查看抢红包活动详情
	</div>
	<div class="body">
		<input type="hidden" name="id" value="${id}" />
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" hidefocus />
			</li>
		</ul>
		<table class="inputTable tabContent">
			<tr>
				<th>
					操作员: 
				</th>
				<td>
					${log.operator}
				</td>
			</tr>
			<tr>
				<th>
					操作类别: 
				</th>
				<td>
					${log.logDesc}
				</td>
			</tr>		
			<tr>
				<th>
					操作名称: 
				</th>
				<td>
					${log.operation}
				</td>
			</tr>
			<tr>
				<th>
					访问来源: 
				</th>
				<td>
					${log.accesssSource}
				</td>
			</tr>
			<tr>
				<th>
					操作IP: 
				</th>
				<td>
					${log.ip}
				</td>
			</tr>
			<tr>
				<th>
					日志信息: 
				</th>
				<td>
					${log.info}
				</td>
			</tr>
			<tr>
				<th>
					记录时间: 
				</th>
				<td>
					${log.createDate?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
		</table>
		<div class="buttonArea">
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
		</div>
	</div>
</body>
</html>