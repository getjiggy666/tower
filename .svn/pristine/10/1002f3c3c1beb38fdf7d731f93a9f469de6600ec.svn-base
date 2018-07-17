package com.telecom.dao;

import com.telecom.entity.AccessStrategy;

/**
 * Dao接口 - IP过滤
 */

public interface AccessStrategyDao extends BaseDao<AccessStrategy, String> {

	/**
	 * 判断唯一编号
	 * 
	 * @param sign
	 *            编号
	 * @return 布尔值
	 */
	public boolean isExistBySign(String sign);

	/**
	 * 根据标识获取访问策略
	 * 
	 * @param sign
	 *            标识
	 * @return 访问策略
	 */
	public AccessStrategy getAccessStrategyBySign(String sign);
}
