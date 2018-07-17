package com.telecom.service;

import com.telecom.entity.Admin;

/**
 * Service接口 - 用户
 */

public interface AdminService extends BaseService<Admin, String> {

	/**
	 * 获取当前登录用户,若未登录则返回null.
	 * 
	 * @return 当前登录用户对象
	 */
	public Admin getLoginAdmin();
	
	/**
	 * 获取当前登录用户(从数据库中加载),若未登录则返回null.
	 * 
	 * @return 当前登录用户对象
	 */
	public Admin loadLoginAdmin();
	
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
	
	public Admin getAdminByMd5Username(String md5Username);

	public Admin getAdminByMobile(String mobile);
}