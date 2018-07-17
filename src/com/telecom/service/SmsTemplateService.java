package com.telecom.service;

import com.telecom.bean.Pager;
import com.telecom.entity.SmsTemplate;

/**
 * Service接口 - 短信模板
 */
public interface SmsTemplateService extends BaseService<SmsTemplate, String> {
	/**
	 * 查询待审核列表pager
	 * 
	 * @param pager
	 * @return
	 */
	public Pager findCheckListPager(Pager pager);

	/**
	 * 判断唯一编号
	 * 
	 * @param sign
	 *            编号
	 * @return 布尔值
	 */
	public boolean isExistBySign(String sign);

	/**
	 * 获取默认短信模版
	 * 
	 * @return 短信模版
	 */
	public SmsTemplate getDefaultSmsTemplate();

	/**
	 * 根据标识获取短信模版
	 * 
	 * @param sign
	 *            模版标识
	 * @return
	 */
	public SmsTemplate getSmsTemplate(String sign);

}
