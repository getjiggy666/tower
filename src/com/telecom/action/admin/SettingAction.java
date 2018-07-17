package com.telecom.action.admin;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.telecom.bean.Setting;
import com.telecom.bean.Setting.RoundType;
import com.telecom.service.CacheService;
import com.telecom.service.MailService;
import com.telecom.util.JsonUtil;
import com.telecom.util.SettingUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台Action类 - 系统配置
 */

@ParentPackage("admin")
public class SettingAction extends BaseAdminAction {

	private static final long serialVersionUID = -6200425957233641240L;
	private final Logger log = Logger.getLogger(getClass());

	private Setting setting;
	private String smtpToMail;
	
	@Resource(name = "mailServiceImpl")
	private MailService mailService;
	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;
	
	// 发送SMTP测试邮件
	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "setting.smtpFromMail", message = "发件人邮箱不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpFromMailNick", message = "发件人昵称不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpHost", message = "SMTP服务器地址不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpUsername", message = "SMTP用户名不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpPassword", message = "SMTP密码不允许为空!"),
			@RequiredStringValidator(fieldName = "smtpToMail", message = "收件人邮箱不允许为空!")
		},
		requiredFields = {
			@RequiredFieldValidator(fieldName = "setting.smtpPort", message = "SMTP服务器端口不允许为空!")
		},
		intRangeFields = {
			@IntRangeFieldValidator(fieldName = "setting.smtpPort", min = "0", message = "SMTP端口必须为零正整数!")
		},
		emails = {
			@EmailValidator(fieldName = "setting.smtpFromMail", message = "发件人邮箱格式错误!"),
			@EmailValidator(fieldName = "smtpToMail", message = "收件人邮箱格式错误!")
		}
	)
	
	@InputConfig(resultName = "ajaxError")
	public String ajaxSendSmtpTest() {
		try {
			mailService.sendSmtpTestMail(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), setting.getSmtpFromMailNick(), smtpToMail);
		} catch (MailAuthenticationException e) {
			return ajax(Status.error, "权限验证失败,请检查SMTP用户名、密码!");
		} catch (MailSendException e) {
			return ajax(Status.error, "邮件发送失败,请检查发件人邮箱、SMTP服务器地址、端口!");
		} catch (Exception e) {
			return ajax(Status.error, "邮件发送失败!");
		}
		return ajax(Status.success, "测试邮件发送成功,请查收邮件!");
	}

	// 编辑
	public String edit() {
		setting = SettingUtil.getSetting();
		return INPUT;
	}

	// 更新
	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "setting.systemName", message = "系统名称不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.systemUrl", message = "系统网址不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpFromMail", message = "发件人邮箱不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpHost", message = "SMTP服务器地址不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.smtpUsername", message = "SMTP用户名不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.currencySign", message = "货币符号不允许为空!"),
			@RequiredStringValidator(fieldName = "setting.currencyUnit", message = "货币单位不允许为空!"),
		}, 
		requiredFields = {
			@RequiredFieldValidator(fieldName = "setting.isLoginFailureLock", message = "是否开启自动锁定账号功能不允许为空!"),
			@RequiredFieldValidator(fieldName = "setting.loginFailureLockCount", message = "连续登录失败最大次数不允许为空!"),
			@RequiredFieldValidator(fieldName = "setting.loginFailureLockTime", message = "自动解锁时间不允许为空!"),
			@RequiredFieldValidator(fieldName = "setting.smtpPort", message = "SMTP服务器端口不允许为空!"),
			@RequiredFieldValidator(fieldName = "setting.isGzipEnabled", message = "是否开启GZIP功能不允许为空!"),
			@RequiredFieldValidator(fieldName = "setting.isUpdating", message = "是否开启接口升级提示不允许为空!")
		},
		intRangeFields = {
			@IntRangeFieldValidator(fieldName = "setting.loginFailureLockCount", min = "1", message = "连续登录失败最大次数必须为正整数!"),
			@IntRangeFieldValidator(fieldName = "setting.loginFailureLockTime", min = "0", message = "自动解锁时间必须为零或正整数!"),
			@IntRangeFieldValidator(fieldName = "setting.smtpPort", min = "0", message = "SMTP端口必须为零或正整数!"),
		},
		emails = {
			@EmailValidator(fieldName = "setting.email", message = "E-mail格式错误!"),
			@EmailValidator(fieldName = "setting.smtpFromMail", message = "发件人邮箱格式错误!"),
		}
	)
	@InputConfig(resultName = "error")
	public String update() throws Exception {		
		Setting persistent = SettingUtil.getSetting();
		
		StringBuffer logInfoStringBuffer = new StringBuffer("编辑前系统设置: " + JsonUtil.toJson(persistent) + "<br>");
		
		
		if (StringUtils.isNotEmpty(setting.getSmtpPassword())) {
			persistent.setSmtpPassword(setting.getSmtpPassword());
		}
		if (!setting.getIsLoginFailureLock()) {
			persistent.setLoginFailureLockCount(3);
			persistent.setLoginFailureLockTime(10);
		}
		BeanUtils.copyProperties(setting, persistent, new String[] {"adminLoginUrl", "adminLoginProcessingUrl", "isInstantMessagingEnabled", "instantMessagingPosition", "instantMessagingTitle", "isLeaveMessageEnabled", "isLeaveMessageCaptchaEnabled", "leaveMessageDisplayType", "isCommentEnabled", "isCommentCaptchaEnabled", "commentAuthority", "commentDisplayType", "smtpPassword"});
		SettingUtil.updateSetting(persistent);
		
		logInfoStringBuffer.append("编辑后系统设置: " + JsonUtil.toJson(persistent));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		cacheService.flushAllPageCache(getRequest());
		
		redirectUrl = "setting!edit.action";
		return SUCCESS;
	}
	
	// 获取小数位精确方式集合
	public List<RoundType> getRoundTypeList() {
		return Arrays.asList(RoundType.values());
	}
	
	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public String getSmtpToMail() {
		return smtpToMail;
	}

	public void setSmtpToMail(String smtpToMail) {
		this.smtpToMail = smtpToMail;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

}