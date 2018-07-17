package com.telecom.dao;

import com.telecom.entity.AuthCode;

public interface AuthCodeDao extends BaseDao<AuthCode, String> {

	/**
	 * 根据号码查询验证码
	 * @param mobile 号码
	 * @return
	 */
	public AuthCode getAuthcode(String mobile);
	
	
}
