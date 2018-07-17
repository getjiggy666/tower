<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>部门列表</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $deleteDepartment = $("#listTable .deleteDepartment");
	var $departmentName = $("#listTable .departmentName");
	
	// 删除部门
	$deleteDepartment.click( function() {
		if (confirm("您确定要删除此部门吗?") == false) {
			return false;
		}
	});
	
	// 树折叠
	$departmentName.click( function() {
		var grade = $(this).parent().attr("grade");
		var isHide;
		$(this).parent().nextAll("tr").each(function() {
			$this = $(this);
			var level = $this.attr("grade");
			if(level <= grade) {
				return false;
			}
			if(isHide == null) {
				if($this.css("display") == "none") {
					isHide = true;
				} else {
					isHide = false;
				}
			}
			if(isHide) {
				$this.show();
			} else {
				$this.hide();
			}
		});
	});

})
</script>
</head>
<body class="list department">
	<div class="bar">
		部门列表&nbsp;<span class="pageInfo">总记录数: ${departmentTreeList?size}
	</div>
	<div class="body">
		<form id="listForm" action="department!list.action" method="post">
			<@sec.authorize ifAnyGranted="ROLE_DEPARTMENT">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='department!add.action'" value="添加部门" hidefocus />
			</div>
			</@sec.authorize>
			<table id="listTable" class="listTable">
				<tr>
					<th>
						<span>部门名称</span>
					</th>
					<th>
						<span>类别</span>
					</th>
					<th>
						<span>标识</span>
					</th>
					<th>
						<span>排序</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list departmentTreeList as department>
					<tr grade="${department.grade}">
						<td class="departmentName">
							<#if department.grade == 0>
								<span class="pointer firstCategory" style="margin-left: ${department.grade * 20}px;">
									${department.name}
								</span>
							<#else>
								<span class="pointer category" style="margin-left: ${department.grade * 20}px;">
									${department.name}
								</span>
							</#if>
						</td>
						<td>
							<#if department.type == '0'>内部部门<#elseif department.type == '1'>合作方</#if>
						</td>
						<td>
							${department.sign}
						</td>
						<td>
							${department.orderList}
						</td>
						<td>
							<#if (department.children?size > 0)>
								<span title="无法删除">[删除]</span>
							<#else>
								<a href="department!delete.action?id=${department.id}" class="deleteDepartment" title="删除">[删除]</a>
							</#if>
							<a href="department!edit.action?id=${department.id}" title="编辑">[编辑]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if departmentTreeList?size == 0>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>
		</form>
	</div>
</body>
</html>