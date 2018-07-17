<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>日志列表</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript" src="${base}/template/common/datePicker/WdatePicker.js"></script>
<script>
$().ready( function() {
	var $listForm = $("#listForm"); // 列表表单
	var $searchButton = $("#searchButton");
	var $pageNumber = $("#pageNumber");// 当前页码
	
//	$searchButton.click( function() {
//    	$pageNumber.val("1");
//		$listForm.submit();
//	});
});
</script>
</head>
<body class="list">
	<div class="bar">
		日志列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="log!list.action" method="post">
			<div class="listBar">
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="operator"<#if pager.searchBy == "operator"> selected</#if>>
						操作员
					</option>
					<option value="operation"<#if pager.searchBy == "operation"> selected</#if>>
						操作名称
					</option>
					<option value="ip"<#if pager.searchBy == "ip"> selected</#if>>
						操作IP
					</option>					
					<option value="info"<#if pager.searchBy == "info"> selected</#if>>
						日志信息
					</option>
				</select>
				<input type="text" name="pager.keyword" value="${pager.keyword!}" />
				&nbsp;&nbsp;
				 操作类别:
				<input type="hidden" name="pager.searchByExtra_1" value="logDesc"/>
				<select name="pager.valueExtra_1">
					<option value="" selected="selected">全部</option>
					<#list logTypeList as logType>
					<option value="${logType}" <#if logType = pager.valueExtra_1>selected="selected"</#if>>${logType}</option>
					</#list>
				</select>
				&nbsp;&nbsp;
				访问来源:
				<input type="hidden" name="pager.searchByExtra_2" value="accesssSource"/>
				<select name="pager.valueExtra_2">
					<option value="" selected="selected">全部</option>
					<#list accesssSourceList as accesssSource>
					<option value="${accesssSource}" <#if accesssSource = pager.valueExtra_2>selected="selected"</#if>>${accesssSource}</option>
					</#list>
				</select>
				&nbsp;&nbsp;
				起始日期:<input type="text" id="beginDate" name="pager.beginDate" style="width:80px;padding-left:5px"
				 readonly="true" value="${(pager.beginDate?string("yyyy-MM-dd"))!}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				 
 			            结束日期:<input type="text" id="endDate" name="pager.endDate" style="width:80px;padding-left:5px"
				 readonly="true" value="${(pager.endDate?string("yyyy-MM-dd"))!}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
 				<input type="hidden" name="pager.timeBy" value="createDate"/>

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
						<a href="#" class="sort" name="operator" hidefocus>操作员</a>
					</th>
					<th>
					    <a href="#" class="sort" name="logDesc" hidefocus>操作类别</a>
					</th>
					<th>
						<a href="#" class="sort" name="operation" hidefocus>操作名称</a>
					</th>
					<th>
						<a href="#" class="sort" name="accesssSource" hidefocus>访问来源</a>
					</th>
					<th>
						<a href="#" class="sort" name="ip" hidefocus>操作IP</a>
					</th>
					<th>
						<a href="#" class="sort" name="info" hidefocus>日志信息</a>
					</th>
					<th>
						<a href="#" class="sort" name="createDate" hidefocus>记录时间</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as log>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${log.id}" />
						</td>
						<td>
							${log.operator}
						</td>
						<td>
						    ${log.logDesc}
						</td>
						<td>
							${log.operation}
						</td>
						<td>
							${log.accesssSource}
						</td>
						<td>
							${log.ip}
						</td>
						<td>
							<span title="${((log.info)!'')?xhtml}">
								${(substring(log.info, 100, "..."))!}
							</span>
						</td>
						<td>
							<span>${log.createDate?string("yyyy-MM-dd HH:mm:ss")}</span>
						</td>
						<td>
							<a href="log!view.action?id=${log.id}" title="查看">[查看]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				<div class="pagerBar">
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