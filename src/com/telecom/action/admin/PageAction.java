package com.telecom.action.admin;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.telecom.service.AdminService;

/**
 * 后台Action类 - 后台页面
 */

@ParentPackage("admin")
public class PageAction extends BaseAdminAction {

	private static final long serialVersionUID = 3148667965663281403L;
	
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
	// 后台主页面
	public String main() {
		return "main";
	}
	
	// 后台Header
	public String header() {
		return "header";
	}
	
	// 后台菜单
	public String menu() {
		return "menu";
	}
	
	// 后台中间(显示/隐藏菜单)
	public String middle() {
		return "middle";
	}
	
	// 后台首页
	public String index() {
		return "index";
	}
	
	// 获取JAVA版本
	public String getJavaVersion() {
		return System.getProperty("java.version");
	}
	
	// 获取系统名称
	public String getOsName() {
		return System.getProperty("os.name");
	}
	
	// 获取系统构架
	public String getOsArch() {
		return System.getProperty("os.arch");
	}
	
	// 获取系统版本
	public String getOsVersion() {
		return System.getProperty("os.version");
	}

	public String ajaxThirdPartyStoreAlertCount(){
		Integer thirdPartyCount = 0;
		return ajax(thirdPartyCount);
	}
	
	// 获取Server信息
	public String getServerInfo() {
		return StringUtils.substring(getServletContext().getServerInfo(), 0, 30);
	}
	
	// 获取Servlet版本
	public String getServletVersion() {
		return getServletContext().getMajorVersion() + "." + getServletContext().getMinorVersion();
	}

}