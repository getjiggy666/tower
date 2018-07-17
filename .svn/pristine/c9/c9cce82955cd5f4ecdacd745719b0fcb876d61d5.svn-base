package com.telecom.bean;

import com.telecom.util.CommonUtil;

/**
 * Bean类 - 页面模板配置
 */

public class PageTemplateConfig {
	
	public static final String SHOP_INDEX = "shopIndex";// WEB首页
	public static final String WAP_INDEX = "wapIndex";// WAP首页
	public static final String SHOP_LOGIN = "shopLogin";// WEB登录
	public static final String WAP_LOGIN = "wapLogin";// WAP登录
	public static final String WAP_REGISTER_AGREEMENT = "wapRegisterAgreement";// WAP注册协议
	public static final String ADMIN_JS = "adminJs";// ADMIN.JS
	public static final String SHOP_JS = "shopJs";// SHOP.JS
	public static final String ARTICLE_CONTENT = "articleContent";// 文章内容
	public static final String ARTICLE_WAP_CONTENT = "articleWapContent";// wap文章内容
	public static final String GOODS_CONTENT = "goodsContent";// 奖品内容
	public static final String ERROR_PAGE = "errorPage";// 错误页
	public static final String ERROR_PAGE_ACCESS_DENIED = "errorPageAccessDenied";// 权限错误页
	public static final String ERROR_PAGE_500 = "errorPage500";// 错误页500
	public static final String ERROR_PAGE_404 = "errorPage404";// 错误页404
	public static final String ERROR_PAGE_403 = "errorPage403";// 错误页403
	
	private String name;// 配置名称
	private String description;// 描述
	private String templatePath;// Freemarker模板文件路径
	private String htmlPath;// 生成HTML静态文件路径
	private String wapHtmlPath;// 生成HTML静态文件路径
	private String wapName;// 配置名称
	private String wapDescription;// 描述
	private String wapTemplatePath;// Freemarker模板文件路径

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	
	public String getHtmlPath() {
		return htmlPath;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	public String getWapHtmlPath() {
		return wapHtmlPath;
	}

	public void setWapHtmlPath(String wapHtmlPath) {
		this.wapHtmlPath = wapHtmlPath;
	}

	public String getWapName() {
		return wapName;
	}

	public void setWapName(String wapName) {
		this.wapName = wapName;
	}

	public String getWapDescription() {
		return wapDescription;
	}

	public void setWapDescription(String wapDescription) {
		this.wapDescription = wapDescription;
	}

	public String getWapTemplatePath() {
		return wapTemplatePath;
	}

	public void setWapTemplatePath(String wapTemplatePath) {
		this.wapTemplatePath = wapTemplatePath;
	}

	// 获取生成HTML静态文件真实路径
	public String getHtmlRealPath() {
		return CommonUtil.getPreparedStatementPath(htmlPath);
	}
	
	// 获取生成HTML静态文件真实路径
	public String getWapHtmlRealPath() {
		return CommonUtil.getPreparedStatementPath(wapHtmlPath);
	}

}