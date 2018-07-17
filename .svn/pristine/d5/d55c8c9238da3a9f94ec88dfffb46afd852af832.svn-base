package com.telecom.listener;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.Authentication;
import org.springframework.security.event.authentication.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.event.authentication.AuthenticationSuccessEvent;
import org.springframework.security.ui.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.telecom.bean.LogConstant;
import com.telecom.bean.Setting;
import com.telecom.entity.Admin;
import com.telecom.entity.Log;
import com.telecom.service.AdminService;
import com.telecom.service.LogService;
import com.telecom.util.JsonUtil;
import com.telecom.util.SettingUtil;

/**
 * 监听器 - 后台登录成功、登录失败处理
 */

@Component("adminSecurityListener")
public class AdminSecurityListener implements ApplicationListener {
	
	private final Logger log = Logger.getLogger(getClass());

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "logServiceImpl")
	private LogService logService;

	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		// 登录成功：记录登录IP、清除登录失败次数
		if (applicationEvent instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent authenticationSuccessEvent = (AuthenticationSuccessEvent) applicationEvent;
			Authentication authentication = (Authentication) authenticationSuccessEvent.getSource();
			String loginIp = ((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress();
			Admin admin = (Admin) authentication.getPrincipal();
			admin.setLoginIp(loginIp);
			admin.setLoginDate(new Date());
			Setting setting = SettingUtil.getSetting();
			if (setting.getIsLoginFailureLock() == false) {
				return;
			}
			admin.setLoginFailureCount(0);
			adminService.update(admin);
			
			// 保存日志
			StringBuffer logInfoStringBuffer = new StringBuffer("登录成功: " + JsonUtil.toJson(admin));
			
			String actionClass = this.getClass().getName();
			String actionMethod = "onApplicationEvent";
			
			String logInfo = logInfoStringBuffer.toString();
			String operator = admin.getName();
			String accessSource = LogConstant.AccesssSource.ADMIN;
			String operation = LogConstant.LogType.ADMINSECURITY;
			String logType = "ADMINSECURITY";
			String logDesc = LogConstant.LogType.ADMINSECURITY;
			
			Log loginfo = new Log();
			loginfo.setAccesssSource(accessSource);
			loginfo.setOperation(operation);
			loginfo.setOperator(operator);
			loginfo.setActionClass(actionClass);
			loginfo.setActionMethod(actionMethod);
			loginfo.setLogType(logType);
			loginfo.setLogDesc(logDesc);
			loginfo.setIp(loginIp.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : loginIp);
			loginfo.setInfo(logInfo);
			logService.save(loginfo);
		
			log.info(logInfoStringBuffer.toString());
			
		}

		// 登录失败：增加登录失败次数
		if (applicationEvent instanceof AuthenticationFailureBadCredentialsEvent) {
			AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent = (AuthenticationFailureBadCredentialsEvent) applicationEvent;
			Authentication authentication = (Authentication) authenticationFailureBadCredentialsEvent.getSource();
			String loginIp = ((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress();
			String loginUsername = authentication.getName();
			Setting setting = SettingUtil.getSetting();
			if (setting.getIsLoginFailureLock() == false) {
				return;
			}
			Admin admin = adminService.getAdminByUsername(loginUsername);
			if (admin != null) {
				int loginFailureCount = admin.getLoginFailureCount() + 1;
				if (loginFailureCount >= setting.getLoginFailureLockCount()) {
					admin.setIsAccountLocked(true);
					admin.setLockedDate(new Date());
				}
				admin.setLoginFailureCount(loginFailureCount);
				adminService.update(admin);
				
				// 保存日志
				StringBuffer logInfoStringBuffer = new StringBuffer("登录失败: " + JsonUtil.toJson(admin));
				
				String actionClass = this.getClass().getName();
				String actionMethod = "onApplicationEvent";
				
				String logInfo = logInfoStringBuffer.toString();
				String operator = admin.getName();
				String accessSource = LogConstant.AccesssSource.ADMIN;
				String operation = LogConstant.LogType.ADMINSECURITY;
				String logType = "ADMINSECURITY";
				String logDesc = LogConstant.LogType.ADMINSECURITY;
				
				Log loginfo = new Log();
				loginfo.setAccesssSource(accessSource);
				loginfo.setOperation(operation);
				loginfo.setOperator(operator);
				loginfo.setActionClass(actionClass);
				loginfo.setActionMethod(actionMethod);
				loginfo.setLogType(logType);
				loginfo.setLogDesc(logDesc);
				loginfo.setIp(loginIp.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : loginIp);
				loginfo.setInfo(logInfo);
				logService.save(loginfo);
			
				log.info(logInfoStringBuffer.toString());
			}
		}
	}

}