<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>访问策略列表</title>
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
		访问策略列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="access_strategy!list.action" method="post">
			<div class="listBar">
				<@sec.authorize ifAnyGranted="ROLE_ACCESS_STRATEGY_ADD">
					<input type="button" class="formButton" onclick="location.href='access_strategy!add.action'" value="添加策略" hidefocus />
				</@sec.authorize>
				&nbsp;&nbsp;
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="sign"<#if pager.searchBy == "sign"> selected</#if>>
						标识
					</option>
					<option value="name"<#if pager.searchBy == "name"> selected</#if>>
						名称
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
						<a href="#" class="sort" name="sign" hidefocus>标识</a>
					</th>
					<th>
						<a href="#" class="sort" name="name" hidefocus>名称</a>
					</th>
					<th>
						<a href="#" class="sort" hidefocus>策略</a>
					</th>
					<th>
						<a href="#" class="sort" name="createDate" hidefocus>创建时间</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as accessStrategy>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${accessStrategy.id}"/>
						</td>
						<td>
							${accessStrategy.sign}
						</td>
						<td>
							${(accessStrategy.name)!}
						</td>
						<td style="width:40%">
							<#assign accessStrategyItemSetStr = '' />
							<#list accessStrategy.accessStrategyItemSet as accessStrategyItem>
								<#assign accessStrategyItemSetStr = accessStrategyItemSetStr + accessStrategyItem.accessIp + '&nbsp;&nbsp;' />
							</#list>
							<span title="${accessStrategyItemSetStr}">
								${substring(accessStrategyItemSetStr, 100, "...")}
							</span>
						</td>
						<td>
							<span title="${accessStrategy.createDate?string("yyyy-MM-dd HH:mm")}">${accessStrategy.createDate?string("yyyy-MM-dd HH:mm")}</span>
						</td>
						<td>
							<a href="access_strategy!view.action?id=${accessStrategy.id}" title="查看">[查看]</a>
							<@sec.authorize ifAnyGranted="ROLE_ACCESS_STRATEGY_EDIT">
								<a href="access_strategy!edit.action?id=${accessStrategy.id}" title="编辑">[编辑]</a>
							</@sec.authorize>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				<div class="pagerBar">
					<@sec.authorize ifAnyGranted="ROLE_ACCESS_STRATEGY_DELETE">
					<div class="delete">
						<input type="button" id="deleteButton" url="access_strategy!delete.action" class="formButton" value="删除" disabled />
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