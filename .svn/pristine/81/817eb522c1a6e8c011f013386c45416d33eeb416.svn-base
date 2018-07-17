<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>短信列表</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $offerProcessButton = $(".offerProcessButton");
	
	// 重发
	$offerProcessButton.click( function() {
		var $this = $(this);
		var smsId = $this.attr("smsId");
		$.dialog({type: "warn", content: "重新发送短信?", ok: "确 定", cancel: "取 消", width: 420, modal: true, okCallback: smsOffer});
		function smsOffer() {
			$.ajax({
				url: "sms!send.action",
				data: {"id": smsId},
				type: "POST",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$this.attr("disabled", true);
				},
				success: function(data) {
					$.message({type: data.status, content: data.message});
					if (data.status == "success") {
						$this.attr("disabled", "disable");
					} else {
						$this.attr("disabled", true);
					}
				}
			});
		}
	});
});
</script>
</head>
<body class="list">
	<div class="bar">
		短信列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="sms!list.action" method="post">
			<div class="listBar">
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="account"<#if pager.searchBy == "account"> selected</#if>>
						手机号码
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
						<a href="#" class="sort" hidefocus>订单标识</a>
					</th>
					<th>
						<a href="#" class="sort" hidefocus>产品</a>
					</th>
					<th>
						<a href="#" class="sort" name="account" hidefocus>号码</a>
					</th>
					<th>
						<a href="#" class="sort" name="sendTime" hidefocus>短信发送时间</a>
					</th>
					<th>
						<a href="#" class="sort" name="status" hidefocus>短信发送结果</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as sms>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="sms_index" />
						</td>
						<td>
							<#if sms.sendee??>
								${(sms.sendee.flowOrder.orderSn)!}
							<#else>
								暂无标识
							</#if>
						</td>
						<td>
							<#if sms.sendee??>
								${(sms.sendee.flowOrder.product.name)!}
							<#else>
								暂无产品
							</#if>
						</td>
						<td>
							${sms.account}
						</td>
						<td>
							${sms.sendTime}
						</td>
						<td>
							<#if sms.status != 'success'>
								${sms.middleDesc}<#if sms.gatewayDesc??>,${sms.gatewayDesc}</#if>
							<#else>
								${action.getText("SMSStatus." + sms.status)}
							</#if>
						</td>
						<td>
							<a href="sms!view.action?id=${sms.id}" title="查看">[查看]</a>
							<#if sms.status == "failure">
								<a href="javascript:void(0)" class="offerProcessButton" smsId="${sms.id}" title="重发">[重发]</a>
							</#if>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				<div class="pagerBar">
					<div class="pager">
						<#include "/WEB-INF/template/admin/pager.ftl" />
					</div>
				</div>
			<#else>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>
		</form>
	</div>
</body>
</html>