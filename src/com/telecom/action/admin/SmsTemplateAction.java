package com.telecom.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.telecom.entity.Admin;
import com.telecom.entity.Check;
import com.telecom.entity.Check.CheckAction;
import com.telecom.entity.Check.CheckStatus;
import com.telecom.entity.SmsTemplate;
import com.telecom.entity.SmsTemplate.TemplateType;
import com.telecom.service.AdminService;
import com.telecom.service.CheckService;
import com.telecom.service.SmsTemplateService;
import com.telecom.util.JsonUtil;
import com.telecom.util.SerialNumberUtil;

/**
 * 后台Action类 - 短信模板
 */

@ParentPackage("admin")
public class SmsTemplateAction extends BaseAdminAction {

	// ---------------------定义常量--------------------
	private static final long serialVersionUID = -5432388727608015282L;
	private static final String ACTION_NAME = "sms_template"; // Action前缀名称
	private final Logger log = Logger.getLogger(getClass());

	// ---------------------定义前台变量--------------------
	private SmsTemplate smsTemplate; // 短信模板对象
	private Check check; // 审核动作实体对象
	private Admin loginAdmin; // 当前登陆账户
	private String memberId;// 会员ID

	// ---------------------定义Service映射--------------------
	@Resource(name = "smsTemplateServiceImpl")
	private SmsTemplateService smsTemplateService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "checkServiceImpl")
	private CheckService checkService;

	// ---------------------定义Action方法--------------------
	// 添加
	public String add() {
		return INPUT;
	}

	// 编辑
	public String edit() {
		smsTemplate = smsTemplateService.load(id);
		return INPUT;
	}

	// 查看
	public String view() {
		smsTemplate = smsTemplateService.load(id);
		return VIEW;
	}

	// 列表
	public String list() {
		pager = smsTemplateService.findPager(pager);
		return LIST;
	}

	// 删除
	public String delete() {
		SmsTemplate smsTemplate = smsTemplateService.load(id);
		smsTemplateService.delete(smsTemplate);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("删除短信模板: ");
		logInfoStringBuffer.append(JsonUtil.toJson(smsTemplate));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		redirectUrl = ACTION_NAME + "!list.action";
		return SUCCESS;
	}

	// 保存
	@InputConfig(resultName = "error")
	public String save() {
		// 获取后台登陆用户
		Admin loginAdmin = adminService.loadLoginAdmin();
		// 设置创建该短信模板的用户为当前后台登陆用户
		smsTemplate.setAdmin(loginAdmin);
		// 为用户短信模版时设置短信模板审核状态为【未处理】否则为【通过】
		if(smsTemplate.getTemplateType() == TemplateType.activity){
			smsTemplate.setCheckStatus(CheckStatus.unprocessed);
			smsTemplate.setIsUse(false);
		}else{
			smsTemplate.setCheckStatus(CheckStatus.passed);
			smsTemplate.setIsUse(true);
		}
		smsTemplate.setMemo(null);
		// 自动生成编号
		smsTemplate.setSign(SerialNumberUtil.buildSmsTemplateSn());

		// 保存
		smsTemplateService.save(smsTemplate);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("添加短信模板: ");
		logInfoStringBuffer.append(JsonUtil.toJson(smsTemplate));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		redirectUrl = ACTION_NAME + "!list.action";
		return SUCCESS;
	}

	// 更新
	@InputConfig(resultName = "error")
	public String update() {
		SmsTemplate persistent = smsTemplateService.load(id);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("编辑前短信模板: ");
		logInfoStringBuffer.append(JsonUtil.toJson(persistent) + "<br>");
		 
		BeanUtils.copyProperties(smsTemplate, persistent, new String[] { "id",
				"createDate", "modifyDate", "admin", "member", "checkStatus",
				"memo", "sign", "isUse" });
		// 为用户短信模版时需要重新审核
		if(persistent.getTemplateType() == TemplateType.activity){
			persistent.setCheckStatus(CheckStatus.unprocessed);
			persistent.setIsUse(false);
		}
		smsTemplateService.update(persistent);
		
		logInfoStringBuffer.append("编辑后短信模板: " + JsonUtil.toJson(persistent));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		redirectUrl = ACTION_NAME + "!list.action";
		return SUCCESS;
	}
	
	// 审核页面
	public String checkInput() {
		smsTemplate = smsTemplateService.load(id);
		loginAdmin = adminService.loadLoginAdmin();
		return "checkInput";
	}
	
	// 待审核列表
	public String checkList() {
		pager = smsTemplateService.findCheckListPager(pager);
		return "checkList";
	}
	
	// 通过
	public String pass() {
		smsTemplate = smsTemplateService.load(id);
		loginAdmin = adminService.loadLoginAdmin();
		if(smsTemplate.getCheckStatus() != CheckStatus.unprocessed){
			return ajax(Status.error, "该短信模板审核状态异常,无法审核");
		}
		
		// 更新短信模板的审核信息
		smsTemplate.setCheckStatus(CheckStatus.passed);
		smsTemplate.setMemo(check.getComment());
		smsTemplate.setIsUse(true);
		smsTemplateService.update(smsTemplate);
		
		// 新建审核记录
		check.setCheckAction(CheckAction.pass);
		check.setAdmin(loginAdmin);
		check.setCheckTime(new Date());
		check.setSmsTemplate(smsTemplate);
		checkService.save(check);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("审核通过短信模板: ");
		logInfoStringBuffer.append(JsonUtil.toJson(smsTemplate));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		return ajax(Status.success, "您的操作已成功,页面将在2秒后跳转!");
	}
	
	// 驳回
	public String back() {
		smsTemplate = smsTemplateService.load(id);
		loginAdmin = adminService.loadLoginAdmin();
		if(smsTemplate.getCheckStatus() != CheckStatus.unprocessed){
			return ajax(Status.error, "该短信模板审核状态异常,无法审核");
		}
		
		// 更新短信模板的审核信息
		smsTemplate.setCheckStatus(CheckStatus.back);
		smsTemplate.setMemo(check.getComment());
		smsTemplateService.update(smsTemplate);
		
		// 新建审核记录
		check.setCheckAction(CheckAction.back);
		check.setAdmin(loginAdmin);
		check.setCheckTime(new Date());
		check.setSmsTemplate(smsTemplate);
		checkService.save(check);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("审核驳回短信模板: ");
		logInfoStringBuffer.append(JsonUtil.toJson(smsTemplate));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		return ajax(Status.success, "您的操作已成功,页面将在2秒后跳转!");
	}
	
	// 作废
	public String invalid() {
		smsTemplate = smsTemplateService.load(id);
		loginAdmin = adminService.loadLoginAdmin();
		if(smsTemplate.getCheckStatus() != CheckStatus.unprocessed){
			return ajax(Status.error, "该短信模板审核状态异常,无法审核");
		}
		
		// 更新短信模板的审核信息
		smsTemplate.setCheckStatus(CheckStatus.invalid);
		smsTemplate.setMemo(check.getComment());
		smsTemplateService.update(smsTemplate);
		
		// 新建审核记录
		check.setCheckAction(CheckAction.invalid);
		check.setAdmin(loginAdmin);
		check.setCheckTime(new Date());
		check.setSmsTemplate(smsTemplate);
		checkService.save(check);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("审核作废短信模板: ");
		logInfoStringBuffer.append(JsonUtil.toJson(smsTemplate));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		return ajax(Status.success, "您的操作已成功,页面将在2秒后跳转!");
	}
	
	// 获取短信模板类型列表
	public List<String> getTemplateTypeList(){
		List<String> templateTypeList = new ArrayList<String>();
		TemplateType[] templateTypes = TemplateType.values();
		for (TemplateType templateType : templateTypes) {
			templateTypeList.add(templateType.toString());
		}
		return templateTypeList;
	}

	// ---------------------定义前台变量get/set方法--------------------
	public SmsTemplate getSmsTemplate() {
		return smsTemplate;
	}

	public void setSmsTemplate(SmsTemplate smsTemplate) {
		this.smsTemplate = smsTemplate;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public Admin getLoginAdmin() {
		return loginAdmin;
	}

	public void setLoginAdmin(Admin loginAdmin) {
		this.loginAdmin = loginAdmin;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
