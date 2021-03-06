<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.springframework.security.BadCredentialsException"%>
<%@page import="com.telecom.util.SpringUtil"%>
<%@page import="com.telecom.service.AdminService"%>
<%@page import="com.telecom.entity.Admin"%>
<%@page import="com.telecom.util.SettingUtil"%>
<%@page import="com.telecom.bean.Setting"%>
<%@page import="org.springframework.security.DisabledException"%>
<%@page import="org.springframework.security.LockedException"%>
<%@page import="org.springframework.security.AccountExpiredException"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%
	response.setHeader("progma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);

	final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

	String base = request.getContextPath();
	ApplicationContext applicationContext = SpringUtil.getApplicationContext();

	AdminService adminService = (AdminService) SpringUtil.getBean("adminServiceImpl");
	Setting setting = SettingUtil.getSetting();
	String adminLoginProcessingUrl = setting.getAdminLoginProcessingUrl();
	String message = null;
	String username = null;
	
	String error = StringUtils.trim(request.getParameter("error"));
	if (StringUtils.equalsIgnoreCase(error, "captcha")) {
		message = "验证码错误,请重新输入!";
	} else {
		Exception springSecurityLastException = (Exception) session.getAttribute(SPRING_SECURITY_LAST_EXCEPTION);
		if (springSecurityLastException != null) {
			if (springSecurityLastException instanceof BadCredentialsException) {
				username = ((String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME")).toLowerCase();
				Admin admin = adminService.getAdminByUsername(username);
				if (admin != null) {
					int loginFailureLockCount = setting.getLoginFailureLockCount();
					int loginFailureCount = admin.getLoginFailureCount();
					if (setting.getIsLoginFailureLock() && loginFailureLockCount - loginFailureCount <= 3) {
						message = "若连续" + loginFailureLockCount + "次密码输入错误,您的账号将被锁定!";
					} else {
						message = "您的用户名或密码错误!";
					}
				} else {
					message = "您的用户名或密码错误!" + username;
				}
			} else if (springSecurityLastException instanceof DisabledException) {
				message = "您的账号已被禁用,无法登录!";
			} else if (springSecurityLastException instanceof LockedException) {
				message = "您的账号已被锁定,无法登录!";
			} else if (springSecurityLastException instanceof AccountExpiredException) {
				message = "您的账号已过期,无法登录!";
			} else {
				message = "出现未知错误,无法登录!";
			}
			session.removeAttribute(SPRING_SECURITY_LAST_EXCEPTION);
		}
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>TowerX后台管理系统登录</title>
<link href="<%=base%>/template/admin/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>/template/common/js/jquery.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $loginForm = $("#loginForm");
	var $username = $("#username");
	var $password = $("#password");

	// 提交表单验证,记住登录用户名
	$loginForm.submit( function() {
		if ($username.val() == "") {
			alert("请输入您的用户名!");
			return false;
		}
		if ($password.val() == "") {
			alert("请输入您的密码!");
			return false;
		}
	});

	<%if (message != null) {%>
		alert("<%=message%>");
	<%}%>
	
	$("#submitBtn").click(function(){
		$loginForm.submit();
	});
	
	// 回车即提交
	document.onkeydown = function (e) { 
		var theEvent = window.event || e; 
		var code = theEvent.keyCode || theEvent.which; 
		if (code == 13) { 
			$("#submitBtn").click();
		} 
	}; 
	
	$("#resetBtn").click(function(){
		$username.val("");
		$password.val("");
	});
	
});
</script>
</head>
<body>
	<script type="text/javascript">

		// 登录页面若在框架内，则跳出框架
		if (self != top) {
			top.location = self.location;
		};

	</script>
	<form id="loginForm" action="<%=base%><%=adminLoginProcessingUrl%>" method="post">
	<img class="bgimg" src="<%=base%>/template/admin/images/bg.jpg" width="100%" />
	<div class="logincontent animation" id="logincontent">
	 <div class="top">
	 	<div style="color: white; font-size: 25px; padding-top: 25px; margin:0 auto; width: 300px; text-align:center">
			TowerX后台管理系统
		</div>
	 </div>
	 <div class="con">
	  <span class="font">用户名：</span>
	  <span class="input"><input id="username" name="j_username" placeholder="请输入用户名" type="text" /></span><br />
	  <span class="font">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
	  <span class="input"><input id="password" name="j_password" placeholder="请输入密码" type="password" /></span><br />
	  <br />
	 </div>
	 <div class="bottombtn">
	  <div class="brnlogin" id="submitBtn">登录</div>
	 </div>
	</div>
	<div class="copy">© 个人版权所有 </div>
	</form>
</body>
</html>