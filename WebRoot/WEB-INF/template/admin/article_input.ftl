<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑文章分类</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.translate.js"></script>
<script type="text/javascript" src="${base}/template/common/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {

	<#if !isAddAction>
		/*
		if($("#articleTypeSel").find("option:selected").text() == "活动"){
			// 隐藏分类所在的tr
			$("#hiddenRow").hide();
		}
		*/
	</#if>
	
	var $tab = $("#tab");
	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	
	var $validateForm = $("#validateForm");
	
	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"article.title": "required",
			//"article.articleCategory.id": "required",
			"article.articleType": "required"
		},
		messages: {
			"article.title": "请填写标题",
			//"article.articleCategory.id": "请选择分类",
			"article.articleType": "请选择类别"
		},
		submitHandler: function(form) {
			//$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});
	
	// 选择类型
	/*
	$("#articleTypeSel").change(function(){
		if($(this).find("option:selected").text() == "活动"){
			// 清空选中的分类
			$("#articleCategory").get(0).selectedIndex = 0;
			// 隐藏分类所在的tr
			$("#hiddenRow").hide();
		}
		else{
			$("#hiddenRow").show();
		}
	})
	*/
	
	// 若尚未上传图片，则不展示上传图片的按钮
	if( $("#viewImageBtn").attr("location-url") == null || $("#viewImageBtn").attr("location-url") == "" ){
		$("#viewImageBtn").hide();
	}
	
	// 查看图片
	$("#viewImageBtn").click(function(){
		var url = "${base}" + $(this).attr("location-url");
		window.open(url);
	})

});
</script>
</head>
<body class="input">
	<div class="bar">
		<#if isAddAction>添加文章<#else>编辑文章</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>article!save.action<#else>article!update.action</#if>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${(article.id)!}" />
			<ul id="tab" class="tab">
				<li>
					<input type="button" value="<#if isAddAction>添加文章<#else>编辑文章</#if>" hidefocus />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						标题: 
					</th>
					<td>
						<input type="text" id="articleTitle" name="article.title" class="formText" value="${(article.title)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						作者: 
					</th>
					<td>
						<input type="text" id="articleAuthor" name="article.author" class="formText" value="${(article.author)!}" />
					</td>
				</tr>
				<tr>
					<th>
						类别: 
					</th>
					<td>
						<select id="articleTypeSel" name="article.articleType">
							<option value="">请选择...</option>
							<#list articleTypeAllList as articleType>
								<option value="${articleType}" <#if !isAddAction && articleType==article.articleType> selected</#if>>${action.getText("ArticleType." + articleType)}</option>
							</#list>
						</select>
						<label class="requireField">*</label>
					</td>
				</tr>				
				<tr id="hiddenRow">
					<th>
						分类: 
					</th>
					<td>
						<select id="articleCategory" name="article.articleCategory.id">
							<option value="">请选择...</option>
							<#list articleCategoryTreeList as articleCategoryTree>
								<option value="${articleCategoryTree.id}" <#if !isAddAction && articleCategoryTree==article.articleCategory> selected</#if>>
									<#if articleCategoryTree.grade != 0>
										<#list 1..articleCategoryTree.grade as i>
											&nbsp;&nbsp;
										</#list>
									</#if>
									${articleCategoryTree.name}
								</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						是否推荐: 
					</th>
					<td>
						<input type="checkbox" id="articleIsRecommend" name="article.isRecommend" <#if (article.isRecommend)!>checked="checked"</#if> />
					</td>
				</tr>
				<tr>
					<th>
						是否置顶: 
					</th>
					<td>
						<input type="checkbox" id="articleIsTop" name="article.isTop" <#if (article.isTop)!>checked="checked"</#if> />
					</td>
				</tr>
				<tr>
					<th>
						关键字: 
					</th>
					<td>
						<input type="text" id="articleMetaKeywords" name="article.metaKeywords" class="formText" value="${(article.metaKeywords)!}" />
					</td>
				</tr>
				<tr>
					<th>
						图片: 
					</th>
					<td>
						<input type="file" name="imageFile" id="imageFile" value="${(article.imageUrl)!}" />
						<#if !isAddAction><a id="viewImageBtn" href="javascript:void(0);" location-url="${(article.imageUrl)!}" target="_blank">[查看图片]</a></#if>
					</td>
				</tr>
				<tr>
					<th>
						web外链: 
					</th>
					<td>
						<input type="text" name="article.articleUrl" id="articleUrl" class="formText" value="${(article.articleUrl)!}" />
					</td>
				</tr>
				<tr>
					<th>
						wap外链: 
					</th>
					<td>
						<input type="text" name="article.articleWapUrl" id="articleWapUrl" class="formText" value="${(article.articleWapUrl)!}" />
					</td>
				</tr>
				<tr>
					<th>
						描述: 
					</th>
					<td>
						<textarea name="article.metaDescription" style="width:300px;height:100px;">${(article.metaDescription)!}</textarea>
					</td>
				</tr>
				<tr>
					<th>
						web内容: 
					</th>
					<td>
						<textarea id="editor" name="article.content" class="editor">${(article.content)!}</textarea>
					</td>
				</tr>
				<tr>
					<th>
						wap内容: 
					</th>
					<td>
						<textarea id="editor2" name="article.wapContent" class="editor">${(article.wapContent)!}</textarea>
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