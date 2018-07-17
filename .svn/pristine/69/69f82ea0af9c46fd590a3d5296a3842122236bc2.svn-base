<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>地区列表</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $deleteArea = $("#listTable .deleteArea");
	
	// 删除地区
	$deleteArea.click( function() {
		if (confirm("您确定要删除此地区吗?") == false) {
			return false;
		}
	});
	
})
</script>
</head>
<body class="list area">
	<div class="bar">
		地区列表&nbsp;<span class="pageInfo">总记录数: ${areaList?size}
	</div>
	<div class="body">
		<form id="listForm" action="area!list.action" method="post">
			<input type="hidden" name="pid" value="${pid!}" />
			
			<div class="listBar">
			<@sec.authorize ifAnyGranted="ROLE_AREA_ADD">
				<input type="button" class="formButton" onclick="location.href='area!add.action<#if pid??>?pid=${pid}</#if>'" value="添加地区" hidefocus />
			</@sec.authorize>
			
			<#if (pathList?size > 0) >
			&nbsp;&nbsp;地区路径  ：&nbsp;&nbsp;<a href="area!list.action">全国</a>&nbsp;&nbsp;->&nbsp;&nbsp;
			</#if>
			
			<#list pathList as area>
			<#if (area_index > 0)>
				&nbsp;&nbsp;->&nbsp;&nbsp;
			</#if>	
				<a href="area!list.action?pid=${area.id}">${area.name}</a>
			</#list>
			
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th width="40%">
						<span>地区名称</span>
					</th>
					<th width="20%">
						<span>所属地区</span>
					</th>
					<th width="20%">
						<span>排序</span>
					</th>
					<th width="20%">
						<span>操作</span>
					</th>
				</tr>
				<#list areaList as area>
					<tr>
						<td>
							<a href="area!list.action?pid=${area.id}">${area.name}</a>
						</td>
						<td>
							<#if area.grade == 0>中国<#else>${area.parent.name}</#if>
						</td>
						<td>
							${area.orderList}
						</td>
						<td>
							<a href="area!edit.action?id=${area.id}<#if pid??>&pid=${pid}</#if>" title="编辑">[编辑]</a>
							<#if (area.children?size > 0)>
								<span title="无法删除">[删除]</span>
							<#else>
								<a href="area!delete.action?id=${area.id}<#if pid??>&pid=${pid}</#if>" class="deleteArea" title="删除">[删除]</a>
							</#if>
						</td>
					</tr>
				</#list>
			</table>
			<#if areaList?size == 0>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>
		</form>
	</div>
</body>
</html>