package com.telecom.action.admin;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.telecom.entity.Admin;
import com.telecom.entity.MessageTemplate;
import com.telecom.service.AdminService;
import com.telecom.service.MessageTemplateService;
import com.telecom.util.SerialNumberUtil;

/**
 * 后台Action类 - 通知模板
 */

@ParentPackage("admin")
public class MessageTemplateAction extends BaseAdminAction {

	// ---------------------定义常量--------------------
	private static final long serialVersionUID = -5432388727608015282L;
	private static final String ACTION_NAME = "message_template"; // Action前缀名称

	// ---------------------定义前台变量--------------------
	private MessageTemplate messageTemplate; // 短信模板对象
	private Admin loginAdmin; // 当前登陆账户

	// ---------------------定义Service映射--------------------
	@Resource(name = "messageTemplateServiceImpl")
	private MessageTemplateService messageTemplateService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	// ---------------------定义Action方法--------------------
	// 添加
	public String add() {
		return INPUT;
	}

	// 编辑
	public String edit() {
		messageTemplate = messageTemplateService.load(id);
		return INPUT;
	}

	// 查看
	public String view() {
		messageTemplate = messageTemplateService.load(id);
		return VIEW;
	}

	// 列表
	public String list() {
		pager = messageTemplateService.findPager(pager);
		return LIST;
	}

	// 删除
	public String delete() {
		MessageTemplate messageTemplate = messageTemplateService.load(id);
		messageTemplateService.delete(messageTemplate);
		logInfo = "删除通知模板: " + messageTemplate.getName();
		redirectUrl = ACTION_NAME + "!list.action";
		return SUCCESS;
	}

	// 保存
	@InputConfig(resultName = "error")
	public String save() {
		// 自动生成编号
		messageTemplate.setSign(SerialNumberUtil.buildMessageTemplateSn());
		// 保存
		messageTemplateService.save(messageTemplate);
		logInfo = "添加通知模板: " + messageTemplate.getName();
		redirectUrl = ACTION_NAME + "!list.action";
		return SUCCESS;
	}

	// 更新
	@InputConfig(resultName = "error")
	public String update() {
		MessageTemplate persistent = messageTemplateService.load(id);
		BeanUtils.copyProperties(messageTemplate, persistent, new String[] { "id",
				"createDate", "modifyDate", "sign" });
		messageTemplateService.update(persistent);
		logInfo = "编辑通知模板: " + persistent.getName();
		redirectUrl = ACTION_NAME + "!list.action";
		return SUCCESS;
	}

	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public Admin getLoginAdmin() {
		return loginAdmin;
	}

	public void setLoginAdmin(Admin loginAdmin) {
		this.loginAdmin = loginAdmin;
	}
	
}
