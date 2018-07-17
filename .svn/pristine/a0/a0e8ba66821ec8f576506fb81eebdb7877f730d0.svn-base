package com.telecom.dao;

import com.telecom.bean.Pager;
import com.telecom.entity.SmsTemplate;

/**
 * Dao接口 - 短信模板
 */

public interface SmsTemplateDao extends BaseDao<SmsTemplate, String> {
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
	 * 获取短信模版
	 * @param sign
	 * @return
	 */
	public SmsTemplate getSmsTemplate(String sign);

}
