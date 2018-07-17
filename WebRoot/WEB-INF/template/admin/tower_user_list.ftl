<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>销售经理列表</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript" src="${base}/template/common/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
function GetContextPath(){  
    var localObj = window.location;  
    var contextPath = localObj.pathname.split("/")[1];  
    var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;  
    return basePath;  
}
</script>
</head>

<body class="list">
	<div class="bar">
		塔兮用户列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="tower_user!list.action" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='tower_user!add.action'" value="添加" hidefocus />
            	&nbsp;&nbsp;
                <label>查找: </label>
                <select name="pager.searchBy">
                	<option value="name"<#if pager.searchBy == "name"> selected</#if>>
                        	昵称
                    </option>
                    <option value="phone"<#if pager.searchBy == "phone"> selected</#if>>
                        	手机号
                    </option>
                </select>
                <input type="text" name="pager.keyword" value="${pager.keyword!}" />
                &nbsp;&nbsp;
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
								        <!--<th class="check">
                                                <input type="checkbox" class="allCheck" />
                                        </th>-->
                                        <th>
                                                <a href="#" class="sort" name="name" hidefocus>用户名称</a>
                                        </th>
                                        <th>
                                                <a href="#" class="sort" name="phone" hidefocus>手机号</a>
                                        </th>
                                       	<th>
                                                <a href="#" class="sort" name="iconUrl" hidefocus>icon</a>
                                        </th>
                                        <th>
                                                <a href="#" class="sort" name="university" hidefocus>学校</a>
                                        </th>                                        
                                       	<th>
                                                <span>设置</span>
                                        </th>
				</tr>
				<#list pager.result as item>
					<tr>
										        <!--<td>
                                                        <input type="checkbox" name="ids" value="${item.id}"/>
                                                </td>-->                                                
                                                <td>
                                                        ${(item.name)!}
                                                </td>                                               
                                                <td>
                                                        ${(item.phone)!}
                                                </td>
                                                <td>
                                                    	<#if item.iconUrl==''||item.iconUrl==null>
                                                           	 无
                                                		<#else>
 															<a href="${base}${item.iconUrl}" target="_blank"> <img src="${base}${item.iconUrl}" width="64" height="64" /></a>
                                                		</#if>
                                                </td> 
                                                <td>
                                                        ${(item.university)!}
                                                </td>
                                               	<td>
                                                        <a href="tower_user!edit.action?id=${item.id}" title="编辑">[编辑]</a>
                                                </td>
					</tr>
				</#list>
			</table>
			<!--<#if (pager.result?size > 0)>
				<div class="pagerBar">
					<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="tower_user!delete.action" value="删 除" disabled hidefocus />
					</div>
					<div class="pager">
						<#include "/WEB-INF/template/admin/pager.ftl" />
					</div>
				<div>
			<#else>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>-->
		</form>
	</div>
</body>
</html>