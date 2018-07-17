package com.telecom.dao;

import com.telecom.entity.MessageTemplate;

/**
 * Dao接口 - 通知模板
 */

public interface MessageTemplateDao extends BaseDao<MessageTemplate, String> {

	/**
	 * 判断唯一编号
	 * 
	 * @param sign
	 *            编号
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
