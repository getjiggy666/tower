package com.telecom.action.admin;

import java.util.List;

import javax.annotation.Resource;

import com.telecom.bean.TemplateConfig;
import com.telecom.util.TemplateConfigUtil;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.views.freemarker.FreemarkerManager;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

import freemarker.template.TemplateException;

/**
 * 后台Action类 - 模板
 */

@ParentPackage("admin")
public class TemplateAction extends BaseAdminAction {

	private static final long serialVersionUID = -1556710151369333272L;
	
	private TemplateConfig templateConfig;
	private String templateFileContent;
	
	@Resource(name = "freemarkerManager")
	private FreemarkerManager freemarkerManager;

	// 列表
	public String list() {
		return LIST;
	}

	// 编辑
	public String edit() {
		templateConfig = TemplateConfigUtil.getTemplateConfig(templateConfig.getName());
		templateFileContent = TemplateConfigUtil.readTemplateFileContent(templateConfig);
		return INPUT;
	}

	// 更新
	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "templateFileContent", message = "模板内容不允许为空!")
		}
	)
	@InputConfig(resultName = "error")
	public String update() {
		templateConfig = TemplateConfigUtil.getTemplateConfig(templateConfig.getName());
		TemplateConfigUtil.writeTemplateFileContent(templateConfig, templateFileContent);
		freemarkerManager.getConfiguration(getServletContext()).clearTemplateCache();
		redirectUrl = "template!list.action";
		return SUCCESS;
	}
	
	// 获取邮件模板配置集合
	public List<TemplateConfig> getAllTemplateConfigList() {
		return TemplateConfigUtil.getAllTemplateConfigList();
	}

	public TemplateConfig getTemplateConfig() {
		return templateConfig;
	}

	public void setTemplateConfig(TemplateConfig templateConfig) {
		this.templateConfig = templateConfig;
	}

	public String getTemplateFileContent() {
		return templateFileContent;
	}

	public void setTemplateFileContent(String templateFileContent) {
		this.templateFileContent = templateFileContent;
	}

}