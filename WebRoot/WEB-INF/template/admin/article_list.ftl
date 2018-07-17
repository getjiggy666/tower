<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>文章列表</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {
	var $listTableTr = $("#listTable tr:gt(0)");
	var $deleteButton = $("deleteButton");// 删除按钮
	var $idsCheck = $("#listTable input[name='ids']");// ID复选框
	var $allCheck = $("#listTable input.allCheck");// 全选复选框
	
	// 选中
	$idsCheck.click(function(){
		var flag = false;
		if($(this).attr("checked") == undefined){
			$allCheck.attr("checked",false);
		}
		$idsCheck.each(function(){
			$this = $(this);
			if($this.attr("checked") == "checked"){
				flag = true
			}
		});
		if(flag){
			$deleteButton.attr("disabled",false);
		}else{
			$deleteButton.attr("disabled","disabled");
		}
	});
	
	// 全选
	$allCheck.click( function() {
		var $this = $(this);
		if ($this.attr("checked")) {
			$idsCheck.attr("checked", true);
			$listTableTr.addClass("checked");
			var $idsChecked = $("#listTable input[name='ids']:checked");
			var flag = false;
			$idsChecked.each(function(index){
				if(!($(this).attr("checkStatus") == "completed")){
					flag = true;
				}
			});
			if(flag){
				$deleteButton.attr("disabled", true);
			}else{
				$deleteButton.attr("disabled", false);
			}
		} else {
			$idsCheck.attr("checked", false);
			$deleteButton.attr("disabled", true);
			$listTableTr.removeClass("checked");
		}
	});
	

})
</script>
</head>
<body class="list">
	<div class="bar">
		文章列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="article!list.action" method="post">
			<div class="listBar">
				<@sec.authorize ifAnyGranted="ROLE_ARTICLE_ADD">
					<input type="button" class="formButton" onclick="location.href='article!add.action'" value="添加文章" hidefocus />
				</@sec.authorize>
				&nbsp;&nbsp;
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="title"<#if pager.searchBy == "title"> selected</#if>>
						标题
					</option>
					<option value="author"<#if pager.searchBy == "author"> selected</#if>>
						作者
					</option>
					<option value="articleCategory.name"<#if pager.searchBy == "articleCategory.name"> selected</#if>>
						分类
					</option>
				</select>
				<input type="text" name="pager.keyword" value="${pager.keyword!}" />
				<input type="button" id="searchButton" class="formButton" value="搜 索" hidefocus />
				&nbsp;&nbsp;
				<label>每页显示: </label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10"<#if pager.pageSize == 10> selected</#if>>
						10
					</option>
					<option value="20"<#if pager.pageSize == 20> selected</#if>>
						20
					</option>
					<option value="50"<#if pager.pageSize == 50> selected</#if>>
						50
					</option>
					<option value="100"<#if pager.pageSize == 100> selected</#if>>
						100
					</option>
				</select>
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<a href="#" class="sort" name="title" hidefocus>标题</a>
					</th>
					<th>
						<a href="#" class="sort" name="author" hidefocus>作者</a>
					</th>
					<th>
						<a href="#" hidefocus>分类</a>
					</th>
					<th>
						<a href="#" class="sort" name="articleCategory" hidefocus>类别</a>
					</th>
					<th>
						<a href="#" class="sort" name="isPublication" hidefocus>是否发布</a>
					</th>
					<th>
						<a href="#" class="sort" name="isRecommend" hidefocus>是否推荐</a>
					</th>
					<th>
						<a href="#" class="sort" name="isTop" hidefocus>是否置顶</a>
					</th>
					<th>
						<a href="#" class="sort" name="hits" hidefocus>点击量</a>
					</th>
					<th>
						<a href="#" class="sort" name="createDate" hidefocus>创建时间</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as article>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${article.id}"/>
						</td>
						<td>
							${article.title}
						</td>
						<td>
							${(article.author)!}
						</td>
						<td>
							<#if article.articleCategory?? >
								${article.articleCategory.name}
							<#else>
								未分类
							</#if>
						</td>
						<td>
							${action.getText("ArticleType." + article.articleType)}
						</td>
						<td>
							<span class="${article.isPublication?string('true','false')}Icon">&nbsp;</span>
						</td>
						<td>
							<span class="${article.isRecommend?string('true','false')}Icon">&nbsp;</span>
						</td>
						<td>
							<span class="${article.isTop?string('true','false')}Icon">&nbsp;</span>
						</td>
						<td>
							${article.hits}
						</td>
						<td>
							<span title="${article.createDate?string("yyyy-MM-dd HH:mm:ss")}">${article.createDate?string("yyyy-MM-dd HH:mm:ss")}</span>
						</td>
						<td>
							<a href="${base}${article.htmlPath}" target="_blank" title="查看">[查看]</a>
							<@sec.authorize ifAnyGranted="ROLE_ARTICLE_EDIT">
								<a href="article!edit.action?id=${article.id}" title="编辑">[编辑]</a>
							</@sec.authorize>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				<div class="pagerBar">
					<@sec.authorize ifAnyGranted="ROLE_ARTICLE_DELETE">
					<div class="delete">
						<input type="button" id="deleteButton" url="article!delete.action" class="formButton" value="删除" disabled />
					</div>
					</@sec.authorize>
					<div class="pager">
						<#include "/WEB-INF/template/admin/pager.ftl" />
					</div>
				<div>
			<#else>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>
		</form>
	</div>
</body>
</html>