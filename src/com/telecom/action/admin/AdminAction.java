package com.telecom.action.admin;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import sun.misc.BASE64Encoder;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.telecom.entity.Admin;
import com.telecom.entity.Department;
import com.telecom.entity.Role;
import com.telecom.service.AdminService;
import com.telecom.service.DepartmentService;
import com.telecom.service.RoleService;
import com.telecom.util.JsonUtil;

/**
 * 后台Action类 - 用户
 */

@ParentPackage("admin")
public class AdminAction extends BaseAdminAction {

	private static final long serialVersionUID = -6296393115370477663L;
	private final Logger log = Logger.getLogger(getClass());
	
	private String username;
	private Admin admin;
	private List<Role> roleList;
	

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	
	// 是否已存在 ajax验证
	public String checkUsername() {
		String username = admin.getUsername();
		if (adminService.isExistByUsername(username)) {
			return ajax("false");
		} else {
			return ajax("true");
		}
	}

	// 添加
	public String add() {
		return INPUT;
	}

	// 编辑
	public String edit() {
		admin = adminService.load(id);
		return INPUT;
	}

	// 列表
	public String list() {
		pager = adminService.findPager(pager);
		return LIST;
	}
	
	// 删除
	public String delete() {
		if (ids.length >= adminService.getTotalCount()) {
			return ajax(Status.error, "请至少保留一个用户,删除失败!");
		}
		StringBuffer logInfoStringBuffer = new StringBuffer("删除用户: ");
		for (String id : ids) {
			Admin admin = adminService.load(id);
			adminService.delete(admin);
			logInfoStringBuffer.append(JsonUtil.toJson(admin));
		}
		
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		return ajax(Status.success, "删除成功!");
	}

	// 保存
	@Validations(
		requiredStrings = {
			@RequiredStringValidator(fieldName = "admin.username", message = "用户名不允许为空!"),
			@RequiredStringValidator(fieldName = "admin.password", message = "密码不允许为空!")
		},
		stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "admin.username", minLength = "2", maxLength = "20", message = "用户名长度必须在${minLength}到${maxLength}之间!"),
			@StringLengthFieldValidator(fieldName = "admin.password", minLength = "4", maxLength = "20", message = "密码长度必须在${minLength}到${maxLength}之间!")
		},
		regexFields = {
			@RegexFieldValidator(fieldName = "admin.username", regexExpression = "^[0-9a-z_A-Z\u4e00-\u9fa5]+$", message = "用户名只允许包含中文、英文、数字和下划线!") 
		}
	)
	@InputConfig(resultName = "error")
	public String save() {
		if (adminService.isExistByUsername(admin.getUsername())) {
			addActionError("用户名已存在!");
			return ERROR;
		}
		
		if (roleList == null || roleList.size() == 0) {
			addActionError("管理角色不允许为空!");
			return ERROR;
		}
		admin.setUsername(admin.getUsername().toLowerCase());
		admin.setLoginFailureCount(0);
		admin.setIsAccountLocked(false);
		admin.setIsAccountExpired(false);
		admin.setIsCredentialsExpired(false);
		admin.setRoleSet(new HashSet<Role>(roleList));
		admin.setPasswordBase64(new BASE64Encoder().encode(admin.getPassword().getBytes()));//　用于登录使用
		admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
		adminService.save(admin);
		
		// 保存日志
		StringBuffer logInfoStringBuffer = new StringBuffer("添加用户: " + JsonUtil.toJson(admin));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		redirectUrl = "admin!list.action";
		return SUCCESS;
	}

	// 更新
	@Validations(
		requiredStrings = {
			@RequiredStringValidator(fieldName = "admin.username", message = "用户名不允许为空!"),
			@RequiredStringValidator(fieldName = "admin.mobile", message = "手机不允许为空!")
		},
		stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "admin.username", minLength = "2", maxLength = "20", message = "用户名长度必须在${minLength}到${maxLength}之间!"),
			@StringLengthFieldValidator(fieldName = "admin.password", minLength = "4", maxLength = "20", message = "密码长度必须在${minLength}到${maxLength}之间!") },
		regexFields = {
			@RegexFieldValidator(fieldName = "admin.username", regexExpression = "^[0-9a-z_A-Z\u4e00-\u9fa5]+$", message = "用户名只允许包含中文、英文、数字和下划线!") 
		}
	)
	@InputConfig(resultName = "error")
	public String update() {
		Admin persistent = adminService.load(id);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("编辑前用户: ");
		logInfoStringBuffer.append(JsonUtil.toJson(persistent) + "<br>");
		
		if (roleList == null || roleList.size() == 0) {
			addActionError("管理角色不允许为空!");
			return ERROR;
		}
		admin.setRoleSet(new HashSet<Role>(roleList));
		if (StringUtils.isNotEmpty(admin.getPassword())) {
			String passwordMd5 = DigestUtils.md5Hex(admin.getPassword());
			String passwordBase64 = new BASE64Encoder().encode(admin.getPassword().getBytes());
			persistent.setPassword(passwordMd5);
			persistent.setPasswordBase64(passwordBase64);
		}
		BeanUtils.copyProperties(admin, persistent, new String[] {"id", "createDate", "modifyDate", "username", "password", "passwordBase64", "isAccountLocked", "isAccountExpired", "isCredentialsExpired", "loginFailureCount", "lockedDate", "loginDate", "loginIp", "authorities"});
		adminService.update(persistent);
		
		logInfoStringBuffer.append("编辑后用户: " + JsonUtil.toJson(persistent));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		redirectUrl = "admin!list.action";
		return SUCCESS;
	}
	
	// 获取所有管理权限集合
	public List<Role> getAllRoleList() {
		return roleService.getAllList();
	}
	
	// 获取部门树
	public List<Department> getDepartmentTreeList() {
		return departmentService.getDepartmentTreeList();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}