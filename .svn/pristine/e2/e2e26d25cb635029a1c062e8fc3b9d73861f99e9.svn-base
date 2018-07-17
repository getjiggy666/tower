package com.telecom.dao;

import com.telecom.entity.Admin;

/**
 * Dao接口 - 用户
 */

public interface AdminDao extends BaseDao<Admin, String> {
	
	/**
	 * 根据用户名判断此用户是否存在（不区分大小写）
	 * 	
	 */
	public boolean isExistByUsername(String username);
	
	/**
	 * 根据用户名获取用户,若用户不存在,则返回null（不区分大小写）
	 * 
	 */
	public Admin getAdminByUsername(String username);
	
	public Admin getAdminByMobile(String mobile);
}