<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>通知模板列表</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
</head>
<body class="list message">
	<div class="bar">
		通知模板列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="message!inbox.action" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='message_template!add.action'" value="添加模板" hidefocus />
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="sign"<#if pager.searchBy == "sign"> selected</#if>>
						标识
					</option>
					<option value="name"<#if pager.searchBy == "name"> selected</#if>>
						名称
					</option>
					<option value="title"<#if pager.searchBy == "title"> selected</#if>>
						标题
					</option>
					<option value="content"<#if pager.searchBy == "content"> selected</#if>>
						内容
					</option>
				</select>
				<input type="text" name="pager.keyword" value="${pager.keyword!}" />
				<input type="button" id="searchButton" class="formButton" value="搜 索" hidefocus />
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
						<a href="#" class="sort" name="title" hidefocus>标题</a>
					</th>
					<th>
						<a href="#" class="sort" name="content" hidefocus>内容</a>
					</th>
					<th>
						<a href="#" class="sort" name="isSystem" hidefocus>系统通知</a>
					</th>
					<th>
						<a href="#" class="sort" name="createDate" hidefocus>日期</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as messageTemplate>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${messageTemplate.id}" />
						</td>
						<td>
							${messageTemplate.sign}
						</td>
						<td>
							${messageTemplate.name}
						</td>
						<td>
							${messageTemplate.title}
						</td>
						<td>
							${messageTemplate.content}
						</td>
						<td>
							<#if messageTemplate.isSystem>
								是
							<#else>
								<span class="red">否</span>
							</#if>
						</td>
						<td>
							${messageTemplate.createDate?string("yyyy-MM-dd HH: mm")}
						</td>
						<td>
							<a href="message_template!edit.action?id=${messageTemplate.id}">[编辑]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				<div class="pagerBar">
					<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="message!delete.action" value="删 除" disabled hidefocus />
					</div>
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