<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>页面静态化</title>
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.form.js"></script>
<script type="text/javascript" src="${base}/template/common/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {
	
	$("#inputForm").submit(function() {
		$("#maxResults").val($("#maxResultsInput").val());
	});

	var isInitialized = false;
	var buildCount = 0;
	var buildTime = 0;
	$("#inputForm").ajaxForm({
		dataType: "json",
		beforeSubmit: function(data) {
			var beginDateInputValue = $("#beginDateInput").val();
			var endDateInputValue = $("#endDateInput").val();
			var maxResultsInputValue = $("#maxResultsInput").val();
			if ($.trim(maxResultsInputValue) == "") {
				$.message({type: "warn", content: "请输入每次更新数!"});
				return false;
			}
			if (!/^[1-9]\d*$/.test(maxResultsInputValue)) {
				$.message({type: "warn", content: "每次更新数必须为正整数!"});
				return false;
			}
			
			if (!isInitialized) {
				isInitialized = true;
				$("#maxResultsInput").attr("disabled", true);
				$("#submitButton").attr("disabled", true);
				$("#statusTr").show();
				$("#status").text("正在自定义错误页,请稍后...");
			}
		},
		success: function(data) {
			buildCount += data.buildCount;
			buildTime += data.buildTime;
			if (data.status == "errorPageFinish") {
				$("#status").text("正在更新文章,请稍后...");
				$("#buildContent").val("article");
				$("#inputForm").submit();
			} else if (data.status == "articleBuilding") {
				var maxResults = Number($("#maxResults").val());
				var firstResult = data.firstResult;
				$("#status").text("正在更新文章[" + (firstResult + 1) + " - " + (firstResult + maxResults) + "],请稍后...");
				$("#buildContent").val("article");
				$("#firstResult").val(firstResult);
				$("#inputForm").submit();
			} else if (data.status == "articleFinish") {
				$("#buildContent").val("");
				$("#firstResult").val("0");
				$("#statusTr").hide();
				$("#maxResultsInput").attr("disabled", false);
				$("#submitButton").attr("disabled", false);
				
				var time;
				if (buildTime < 60000) {
					time = (buildTime / 1000).toFixed(2) + "秒";
				} else {
					time = (buildTime / 60000).toFixed(2) + "分";
				}
				$.dialog({type: "success", content: "网站更新成功! [更新总数: " + buildCount + " 耗时: " + time + "]", width: 380, modal: true, autoCloseTime: 3000});
				isInitialized = false;
				buildCount = 0;
				buildTime = 0;
			}
		}
	});

});
</script>
</head>
<body class="input">
	<div class="bar">
		一键网站更新
	</div>
	<div class="body">
		<form id="inputForm" action="build_html!all.action" method="post">
			<input type="hidden" id="buildType" name="buildType" value="" />
			<input type="hidden" id="maxResults" name="maxResults" value="" />
			<input type="hidden" id="firstResult" name="firstResult" value="0" />
			<input type="hidden" id="buildContent" name="buildContent" value="" />
			<table class="inputTable">
				<tr>
					<th>
						每次更新数
					</th>
					<td>
						<input type="text" id="maxResultsInput" name="" class="formText" value="50" />
					</td>
				</tr>
				<tr id="statusTr" class="hidden">
					<th>
						&nbsp;
					</th>
					<td>
						<span class="loadingBar">&nbsp;</span>
						<p id="status"></p>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" id="submitButton" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>