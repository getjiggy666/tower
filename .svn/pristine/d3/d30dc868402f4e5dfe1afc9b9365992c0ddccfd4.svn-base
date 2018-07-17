<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑</title>
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
	// 查看图片
	$(".viewImageBtn").click(function(){
		var url = "${base}" + $(this).attr("location-url");
		window.open(url);
	});
</script>
</head>

<body class="input scenicSpot">
	<div class="bar">
		<#if isAddAction>添加<#else>编辑</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>scenic_spot!save.action<#else>scenic_spot!update.action</#if>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${id}" />
			<table class="inputTable">
				<tr>
					<th>
						名称: 
					</th>
					<td>
						<input type="text" name="scenicSpot.scenicName" class="formText" value="${(scenicSpot.scenicName)!}" disabled/>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						景点照片: 
					</th>
					<td>
						<input type="file" name="scenicImgFile" style="width:185px;height:23px;"/>
						<#if !isAddAction><a class="viewImageBtn" id="viewImageBtn" index="1" href="javascript:void(0);" location-url="${(scenicSpot.scenicImg)!}" target="_blank">[查看图片]</a><#else><label class="requireField">*</label></#if>
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