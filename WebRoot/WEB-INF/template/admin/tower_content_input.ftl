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

<body class="input tower_content">
	<div class="bar">
		<#if isAddAction>添加<#else>编辑</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>tower_content!save.action<#else>tower_content!update.action</#if>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${id}" />
			<table class="inputTable">
				<tr>
					<th>
						名称: 
					</th>
					<td>
						<input type="text" name="towerContent.title" class="formText" value="${(towerContent.title)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>					
				
				<tr>
					<th>
						内容介绍: 
					</th>
					<td>
						<input style="width:1000px;" type="text" name="towerContent.content" class="formText" value="${(towerContent.content)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>		
				
				<!--<tr>
					<th>
						内容类型: 
					</th>
					<td>
						<input type="text" name="towerContent.contentType" class="formText" value="${(towerContent.contentType)!}" />
						<label class="requireField">*</label>
						填0,1,2,3(0纯文本,1图或图文,2小视频,3长视频)
					</td>
				</tr>-->	
				<tr>
					<th>
						内容类型: 
					</th>
					<td>
						<select name="towerContent.contentType">
							<option value="">请选择...</option>
							<option value="0">纯文本</option>
							<option value="1">图文</option>
							<option value="2">小视频</option>
							<option value="3">长视频</option>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						分类: 
					</th>
					<td>
						<input style="width:1000px;" type="text" name="towerContent.towerDribKind.name" class="formText" value="${(towerContent.towerDribKind.name)!}" />
						<label class="requireField">*</label>
						技能才干,生活日常等
					</td>
				</tr>	
				
				<tr>
					<th>
						图片1: 
					</th>
					<td>
						<input type="file" name="img_1" style="width:185px;height:23px;"/>
						<#if !isAddAction><a class="viewImageBtn" id="viewImageBtn1" index="1" href="javascript:void(0);" location-url="${(towerContent.imgUrl_1)!}" target="_blank">[查看图片]</a><#else><label class="requireField">*</label></#if>
					</td>
				</tr>
				
				<tr>
					<th>
						图片2: 
					</th>
					<td>
						<input type="file" name="img_2" style="width:185px;height:23px;"/>
						<#if !isAddAction><a class="viewImageBtn" id="viewImageBtn2" index="2" href="javascript:void(0);" location-url="${(towerContent.imgUrl_2)!}" target="_blank">[查看图片]</a><#else><label class="requireField">*</label></#if>
					</td>
				</tr>
				
				<tr>
					<th>
						图片3: 
					</th>
					<td>
						<input type="file" name="img_3" style="width:185px;height:23px;"/>
						<#if !isAddAction><a class="viewImageBtn" id="viewImageBtn3" index="3" href="javascript:void(0);" location-url="${(towerContent.imgUrl_3)!}" target="_blank">[查看图片]</a><#else><label class="requireField">*</label></#if>
					</td>
				</tr>
				
				<tr>
					<th>
						视频: 
					</th>
					<td>
						<input type="file" name="video" style="width:185px;height:23px;"/>
						<#if !isAddAction><a class="viewImageBtn" id="viewImageBtn4" index="4" href="javascript:void(0);" location-url="${(towerContent.videoUrl)!}" target="_blank">[观看视频]</a><#else><label class="requireField">*</label></#if>
					</td>
				</tr>
				
				<tr>
					<th>
						视频第一帧: 
					</th>
					<td>
						<input type="file" name="video_img" style="width:185px;height:23px;"/>
						<#if !isAddAction><a class="viewImageBtn" id="viewImageBtn5" index="5" href="javascript:void(0);" location-url="${(towerContent.videoImgUrl)!}" target="_blank">[查看图片]</a><#else><label class="requireField">*</label></#if>
					</td>
				</tr>	
				
				<tr>
					<th>
						发布者: 
					</th>
					<td>
						<input type="text" name="mobile" class="formText" value="${(mobile)!}" />
						<label class="requireField">*</label>
						不填默认为当前管理员账号
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