<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>审核活动</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {
	var $tab = $("#tab");

	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
	
		var $offerProcessButton = $("#offerProcessButton");
	
	// 重发
	$offerProcessButton.click( function() {
		var $this = $(this);
		var smsId = "${sms.id}";
		$.dialog({type: "warn", content: "重新发送短信?", ok: "确 定", cancel: "取 消", width: 420, modal: true, okCallback: smsOffer});
		function smsOffer() {
			$.ajax({
				url: "sms!offer.action",
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
<body class="input activity">
	<div class="bar">
		发送短信
	</div>
	<div class="body">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="短信信息" hidefocus />
			</li>
		</ul>
		<table id="prizeTable" class="inputTable tabContent">
			<tr>
				<th>
					号码
				</th>
				<td>
					${sms.account}
				</td>
			</tr><tr>
				<th>
					内容
				</th>
				<td>
					${sms.text}
				</td>
			</tr>
			<tr>
				<th>
					发送时间
				</th>
				<td>
					${sms.sendTime}
				</td>
			</tr>
			<tr>
				<th>
					状态描述
				</th>
				<td>
					${(sms.middleDesc)!},${(sms.gatewayDesc)!}
				</td>
			</tr>
			<tr>
				<th>
					状态
				</th>
				<td>
					${action.getText("SMSStatus." + sms.status)}
				</td>
			</tr>
		</table>
		<div class="buttonArea">
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
		</div>
	</div>
</body>
</html>