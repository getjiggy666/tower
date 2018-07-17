package com.telecom.service;

import com.telecom.entity.MessageTemplate;

/**
 * Service接口 - 通知模板
 */
public interface MessageTemplateService extends BaseService<MessageTemplate, String> {
	
	/**
	 * 判断唯一编号
	 * @param sign 编号
	 * @return 布尔值
	 */
	public boolean isExistBySign(String sign);
	
	/**
	 * 根据标识获取通知模板
	 * 
	 * @param sign
	 *            标识
	 * @return 通知模板
	 */
	public MessageTemplate getMessageTemplateBySign(String sign);
	
}
