<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>待审核列表</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
</head>
<body class="list">
	<div class="bar">
		待审核列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="sms_template!checkList.action" method="post">
			<div class="listBar">
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="name"<#if pager.searchBy == "name"> selected</#if>>
						模板名称
					</option>
					<option value="member.name"<#if pager.searchBy == "member.name"> selected</#if>>
						创建者
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
					<th>
						<a href="#" class="sort" name="sign" hidefocus>标识</a>
					</th>
					<th>
						<a href="#" class="sort" name="name" hidefocus>名称</a>
					</th>
					<th>
						<a href="#" class="sort" name="content" hidefocus>内容</a>
					</th>
					<th>
						<a href="#" hidefocus>模板类别</a>
					</th>
					<th>
						<a href="#" hidefocus>是否启用</a>
					</th>
					<th>
						<a href="#" hidefocus>创建者</a>
					</th>
					<th>
						<a href="#" hidefocus>创建者类型</a>
					</th>
					<th>
						<a href="#" class="sort" name="createDate" hidefocus>创建时间</a>
					</th>
					<th>
						<a href="#" class="sort" name="checkStatus" hidefocus>审核状态</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as smsTemplate>
					<tr>
						<td>
							${smsTemplate.sign}
						</td>
						<td>
							${smsTemplate.name}
						</td>
						<td>
							<span title="${smsTemplate.content}">
								${substring(smsTemplate.content, 20, "...")}
							</span>
						</td>
						<td>
							${action.getText("TemplateType." + smsTemplate.templateType)}
						</td>
						<td>
							<#if smsTemplate.isUse>
								已启用
							<#else>
								未启用
							</#if>
						</td>
						<td>
							<#if smsTemplate.admin?? >
								${smsTemplate.admin.name}
							<#else>
								<#if smsTemplate.member?? >
								${smsTemplate.member.username}
								</#if>
							</#if>
						</td>
						<td>
							<#if smsTemplate.admin?? >
								管理员
							<#else>
								<#if smsTemplate.member?? >
									会员
								</#if>
							</#if>
						</td>
						<td>
							${smsTemplate.createDate}
						</td>
						<td>
							${action.getText("CheckStatus." + smsTemplate.checkStatus)}
						</td>
						<td>
							<a href="sms_template!view.action?id=${smsTemplate.id}" title="查看">[查看]</a>
							<@sec.authorize ifAnyGranted="ROLE_SMS_TEMPLATE_CHECK">
								<a href="sms_template!checkInput.action?id=${smsTemplate.id}" title="审核">[审核]</a>
							</@sec.authorize>
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