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
		塔兮内容列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="tower_content!list.action" method="post">
			<div class="listBar">
			 	<input type="button" class="formButton" onclick="location.href='tower_content!add.action'" value="添加" hidefocus />
            	&nbsp;&nbsp;
                <label>查找: </label>
                <select name="pager.searchBy">
                	<option value="title"<#if pager.searchBy == "title"> selected</#if>>
                        	标题
                    </option>
                    <option value="towerUser.name"<#if pager.searchBy == "towerUser.name"> selected</#if>>
                        	发布者
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
								        <th class="check">
                                                <input type="checkbox" class="allCheck" />
                                        </th>
                                        <th>
                                                <a href="#" class="sort" name="title" hidefocus>标题</a>
                                        </th>
                                        <th>
                                                <a href="#" class="sort" name="contentType" hidefocus>内容类型</a>
                                        </th>
                                        <th>
                                                <a href="#" class="sort" name="name" hidefocus>发布者名称</a>
                                        </th>
                                        <th>
                                                <a href="#" class="sort" name="phone" hidefocus>发布者手机号</a>
                                        </th>
                                        <th>
                                                <a href="#" class="sort" name="imgUrl_1" hidefocus>图片1</a>
                                        </th>
                                         <th>
                                                <a href="#" class="sort" name="imgUrl_2" hidefocus>图片2</a>
                                        </th>
                                        <th>
                                                <a href="#" class="sort" name="imgUrl_3" hidefocus>图片3</a>
                                        </th>  
                                        <th>
                                                <a href="#" class="sort" name="videoImgUrl" hidefocus>视频第一帧</a>
                                        </th>                                      
                                       	<!--<th>
                                                <span>设置</span>
                                        </th>-->
				</tr>
				<#list pager.result as item>
					<tr>
										        <td>
                                                        <input type="checkbox" name="ids" value="${item.id}"/>
                                                </td>
                                                 <td>
                                                       	${(item.title)!}
                                                </td>   
                                                <td>
                                                    	<#if item.contentType=='0'>
                                                           	 文本
                                                		<#elseif item.contentType=='1'>
 															 图文
 														<#elseif item.contentType=='2'>
 															 小视频
 														<#elseif item.contentType=='3'>
 															 教学视频
 														<#else>
 															未分类
                                                		</#if>
                                                </td>                                             
                                                <td>
                                                        ${(item.towerUser.name)!}
                                                </td>
                                                <td>
                                                        ${(item.towerUser.phone)!}
                                                </td>
                                                <td>
                                                    	<#if item.imgUrl_1==''||item.imgUrl_1==null>
                                                           	 无
                                                		<#else>
 															<a href="${base}${item.imgUrl_1}" target="_blank"> <img src="${base}${item.imgUrl_1}" width="64" height="64" /></a>
                                                		</#if>
                                                </td>   
                                                <td>
                                                    	<#if item.imgUrl_2==''||item.imgUrl_2==null>
                                                           	 无
                                                		<#else>
 															<a href="${base}${item.imgUrl_2}" target="_blank"> <img src="${base}${item.imgUrl_2}" width="64" height="64" /></a>
                                                		</#if>
                                                </td>   
                                                <td>
                                                    	<#if item.imgUrl_3==''||item.imgUrl_3==null>
                                                           	 无
                                                		<#else>
 															<a href="${base}${item.imgUrl_3}" target="_blank"> <img src="${base}${item.imgUrl_3}" width="64" height="64" /></a>
                                                		</#if>
                                                </td>
                                                <td>
                                                    	<#if item.videoImgUrl==''||item.videoImgUrl==null>
                                                           	 无
                                                		<#else>
 															<a href="${base}${item.videoImgUrl}" target="_blank"> <img src="${base}${item.videoImgUrl}" width="64" height="64" /></a>
                                                		</#if>
                                                </td>                                             
                                               	 <!--<td>
                                                        <a href="tower_content!edit.action?id=${item.id}" title="编辑">[编辑]</a>
                                                </td>-->
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				<div class="pagerBar">
					<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="tower_content!delete.action" value="删 除" disabled hidefocus />
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