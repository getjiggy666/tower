package com.telecom.dao;

import com.telecom.bean.Pager;
import com.telecom.entity.Admin;
import com.telecom.entity.Message;

/**
 * Dao接口 - 通知
 */

public interface MessageDao extends BaseDao<Message, String> {
	
	/**
	 * 根据Admin、Pager获取会员发件箱分页对象
	 * 
	 * @param admin
	 *            Admin对象
	 *            
	 * @param pager
	 *            Pager对象
	 *            
	 * @return 发件箱页对象
	 */
	public Pager getAdminOutboxPager(Admin admin, Pager pager);
	
	/**
	 * 根据Admin、Pager获取会员收件箱分页对象
	 * 
	 * @param admin
	 *            Admin对象
	 *            
	 * @param pager
	 *            Pager对象
	 *            
	 * @return 发件箱页对象
	 */
	public Pager getAdminInboxPager(Admin admin, Pager pager);
	
	/**
	 * 获取管理员未读通知数
	 * 
	 * @return 未读通知数量
	 */
	public Long getUnreadMessageCount();

	/**
	 * 是否存在这条信息
	 * 
	 */
	public boolean isExistMessage(String id);
}