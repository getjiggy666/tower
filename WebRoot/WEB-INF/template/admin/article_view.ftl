<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看文章</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
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
<body class="input activity">
	<div class="bar">
		查看文章
	</div>
	<div class="body">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" hidefocus />
			</li>
		</ul>
		<table class="inputTable tabContent">
			<tr>
				<th>
					标题: 
				</th>
				<td>
					${(article.title)!}
				</td>
			</tr>
			<tr class="dateTr">
				<th>
					作者: 
				</th>
				<td>
					${(article.author)!}
				</td>
			</tr>
			<tr class="dateTr">
				<th>
					分类: 
				</th>
				<td>
					${(article.articleCategory.name)!}
				</td>
			</tr>
			<tr class="dateTr">
				<th>
					是否发布: 
				</th>
				<td>
					<span class="${article.isPublication?string('true','false')}Icon">&nbsp;</span>
				</td>
			</tr>
			<tr>
				<th>
					是否推荐: 
				</th>
				<td>
					<span class="${article.isRecommend?string('true','false')}Icon">&nbsp;</span>
				</td>
			</tr>
			<tr>
				<th>
					是否置顶: 
				</th>
				<td>
					<span class="${article.isTop?string('true','false')}Icon">&nbsp;</span>
				</td>
			</tr>
			<tr>
				<th>
					点击数: 
				</th>
				<td>
					${(article.hits)!}
				</td>
			</tr>
			<tr>
				<th>
					页面描述: 
				</th>
				<td>
					${(article.metaDescription)!}
				</td>
			</tr>
			<tr>
				<th>
					页面关键字: 
				</th>
				<td>
					${(article.metaKeywords)!}
				</td>
			</tr>
			<tr>
				<th>
					文章页数: 
				</th>
				<td>
					${(article.pageCount)!}
				</td>
			</tr>
			<tr>
				<th>
					外链: 
				</th>
				<td>
					${(article.articleUrl)!}
				</td>
			</tr>
			<tr>
				<th>
					创建时间: 
				</th>
				<td>
					${(article.createDate?string("yyyy-MM-dd HH:mm:ss"))!}
				</td>
			</tr>
			<tr>
				<th>
					内容: 
				</th>
				<td>
					<div style="overflow-y:scroll;height:300px;">
						${(article.content)!}
					</div>
				</td>
			</tr>
		</table>
		<div class="buttonArea">
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
		</div>
	</div>
</body>
</html>