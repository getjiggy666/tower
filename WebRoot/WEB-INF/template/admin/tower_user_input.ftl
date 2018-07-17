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
	// 若尚未上传图片，则不展示上传图片的按钮
	$(".viewImageBtn").each(function(){
		if( $(this).attr("location-url") == null || $(this).attr("location-url") == "" ){
			$(this).hide();
			var index = $(this).attr("index");
			$("#deleteImageBtn"+index).hide();
		}
	});
	
	// 查看图片
	$(".viewImageBtn").click(function(){
		var url = "${base}" + $(this).attr("location-url");
		window.open(url);
	});	
})

</script>
</head>

<body class="input tower_user">
	<div class="bar">
		<#if isAddAction>添加<#else>编辑</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>tower_user!save.action<#else>tower_user!update.action</#if>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${id}" />
			<table class="inputTable">
				<tr>
					<th>
						昵称: 
					</th>
					<td>
						<input type="text" name="towerUser.name" class="formText" value="${(towerUser.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>	
				
				<tr>
					<th>
						头像: 
					</th>
					<td>
						<input type="file" name="icon" style="width:185px;height:23px;"/>
						<#if !isAddAction><a class="viewImageBtn" id="viewImageBtn1" index="1" href="javascript:void(0);" location-url="${(towerUser.iconUrl)!}" target="_blank">[查看图片]</a><#else><label class="requireField">*</label></#if>
					</td>
				</tr>
				
				<tr>
					<th>
						手机号: 
					</th>
					<td>
						<input type="text" name="towerUser.phone" class="formText" value="${(towerUser.phone)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>					
				
				<tr>
					<th>
						学校: 
					</th>
					<td>
						<input type="text" name="towerUser.university" class="formText" value="${(towerUser.university)!}" />
						<label class="requireField">*</label>
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