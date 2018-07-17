package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.AuthCodeDao;
import com.telecom.entity.AuthCode;

@Repository("authCodeDaoImpl")
public class AuthCodeDaoImpl extends BaseDaoImpl<AuthCode, String> implements AuthCodeDao {

	@Override
	public AuthCode getAuthcode(String mobile) {
		String hql = "from AuthCode as authCode where authCode.mobile = :mobile";
		return (AuthCode) getSession().createQuery(hql).setParameter("mobile", mobile).uniqueResult();
	}

}
