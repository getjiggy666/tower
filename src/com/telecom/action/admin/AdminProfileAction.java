package com.telecom.action.admin;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;

import sun.misc.BASE64Encoder;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.telecom.entity.Admin;
import com.telecom.service.AdminService;
import com.telecom.util.JsonUtil;

/**
 * 后台Action类 - 用户个人资料
 */

@ParentPackage("admin")
public class AdminProfileAction extends BaseAdminAction {

	private static final long serialVersionUID = -7731533592958843271L;
	private final Logger log = Logger.getLogger(getClass());

	private Admin admin;
	private String currentPassword;

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	// ajax验证当前密码是否正确
	public String checkCurrentPassword() {
		Admin admin = adminService.loadLoginAdmin();
		if (StringUtils.equals(DigestUtils.md5Hex(currentPassword), admin.getPassword())) {
			return ajax("true");
		} else {
			return ajax("false");
		}
	}

	// 编辑用户资料
	public String edit() {
		admin = adminService.loadLoginAdmin();
		return INPUT;
	}

	// 更新个人资料
	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "admin.mobile", message = "手机不允许为空!")
		}, 
		stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "admin.password", minLength = "4", maxLength = "20", message = "新密码长度允许在{1}-{2}之间!") 
		}
	)
	@InputConfig(resultName = "error")
	public String update() {
		Admin persistent = adminService.loadLoginAdmin();
		
		StringBuffer logInfoStringBuffer = new StringBuffer("编辑前登录用户: ");
		logInfoStringBuffer.append(JsonUtil.toJson(persistent) + "<br>");
		
		if (StringUtils.isNotEmpty(currentPassword) && StringUtils.isNotEmpty(admin.getPassword())) {
			if (!StringUtils.equals(DigestUtils.md5Hex(currentPassword), persistent.getPassword())) {
				addActionError("当前密码输入错误!");
				return ERROR;
			}
			persistent.setPasswordBase64(new BASE64Encoder().encode(admin.getPassword().getBytes()));
			persistent.setPassword(DigestUtils.md5Hex(admin.getPassword()));
		}
		persistent.setMobile(admin.getMobile());
		adminService.update(persistent);
		
		logInfoStringBuffer.append("编辑后用户: " + JsonUtil.toJson(persistent));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		redirectUrl = "admin_profile!edit.action";
		return SUCCESS;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

}