package com.telecom.action.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.telecom.entity.Message;
import com.telecom.entity.Message.DeleteStatus;
import com.telecom.service.AdminService;
import com.telecom.service.MessageService;
import com.telecom.util.JsonUtil;

/**
 * 后台Action类 - 通知
 */

@ParentPackage("admin")
public class MessageAction extends BaseAdminAction {

	private static final long serialVersionUID = -8841506249589763663L;
	private final Logger log = Logger.getLogger(getClass());

	private Message message; // 保存时用的通知对象
	private Message sendMessage; // 回复时显示的发件通知对象
	private String toAdminName;
	private String fromAdminName;

	@Resource(name = "messageServiceImpl")
	private MessageService messageService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	// 检查用户名是否存在
	public String checkUsername() {
		String toAdminName = getParameter("toAdminName");
		if (adminService.isExistByUsername(toAdminName)) {
			ajax("true");
		} else {
			ajax("false");
		}
		return NONE;
	}

	// 发送通知界面
	public String send() {
		return "send";
	}

	// 回复通知界面
	public String reply() {
		sendMessage = messageService.get(id);
		return "reply";
	}

	// 收件箱
	public String inbox() {
		pager = messageService.getAdminInboxPager(adminService.getLoginAdmin(),
				pager);
		return "inbox";
	}

	// 发件箱
	public String outbox() {
		pager = messageService.getAdminOutboxPager(
				adminService.getLoginAdmin(), pager);
		return "outbox";
	}

	// 发送通知
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "message.title", message = "标题不允许为空!"),
			@RequiredStringValidator(fieldName = "message.content", message = "通知内容不允许为空!") }, stringLengthFields = { @StringLengthFieldValidator(fieldName = "message.content", maxLength = "10000", message = "通知内容长度超出限制!") })
	@InputConfig(resultName = "error")
	public String sendSave() {
		StringBuffer logInfoStringBuffer = new StringBuffer("添加邮件通知: ");
		message.setToAdmin(adminService.getAdminByUsername(toAdminName));
		message.setFromAdmin(adminService.getLoginAdmin());
		message.setDeleteStatus(DeleteStatus.nonDelete);
		message.setIsRead(false);
		message.setIsSaveDraftbox(false);
		messageService.save(message);
		logInfoStringBuffer.append(JsonUtil.toJson(message));

		// 保存日志
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());

		redirectUrl = "message!outbox.action";
		return SUCCESS;
	}

	// 回复通知
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "message.title", message = "标题不允许为空!"),
			@RequiredStringValidator(fieldName = "message.content", message = "通知内容不允许为空!") }, stringLengthFields = { @StringLengthFieldValidator(fieldName = "message.content", maxLength = "10000", message = "通知内容长度超出限制!") })
	@InputConfig(resultName = "error")
	public String replySave() {
		StringBuffer logInfoStringBuffer = new StringBuffer("添加邮件通知: ");
		message.setToAdmin(messageService.get(id).getFromAdmin());
		message.setFromAdmin(messageService.get(id).getToAdmin());
		message.setDeleteStatus(DeleteStatus.nonDelete);
		message.setIsRead(false);
		message.setIsSaveDraftbox(false);
		messageService.save(message);
		logInfoStringBuffer.append(JsonUtil.toJson(message));

		// 保存日志
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());

		redirectUrl = "message!outbox.action";
		return SUCCESS;
	}

	// 删除
	public String delete() {
		StringBuffer logInfoStringBuffer = new StringBuffer("删除邮件通知: ");
		for (String id : ids) {
			Message message = messageService.load(id);
			if (!message.getIsSaveDraftbox()) {
				if (message.getDeleteStatus() == DeleteStatus.nonDelete) {
					message.setDeleteStatus(DeleteStatus.fromDelete);
					messageService.update(message);
					logInfoStringBuffer.append(JsonUtil.toJson(message));
				} else if (message.getDeleteStatus() == DeleteStatus.toDelete) {
					messageService.delete(message);
					logInfoStringBuffer.append(JsonUtil.toJson(message));
				}
			}
		}

		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());

		return ajax(Status.success, "删除成功!");
	}

	// AJAX获取通知内容
	@InputConfig(resultName = "ajaxError")
	public String ajaxShowMessage() {
		Message message = messageService.load(id);
		if (!message.getIsRead()) {
			message.setIsRead(true);
			messageService.update(message);
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUS_PARAMETER_NAME, Status.success);
		jsonMap.put("content", message.getContent());
		return ajax(jsonMap);
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getToAdminName() {
		return toAdminName;
	}

	public void setToAdminName(String toAdminName) {
		this.toAdminName = toAdminName;
	}

	public String getFromAdminName() {
		return fromAdminName;
	}

	public void setFromAdminName(String fromAdminName) {
		this.fromAdminName = fromAdminName;
	}

	public Message getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(Message sendMessage) {
		this.sendMessage = sendMessage;
	}

}