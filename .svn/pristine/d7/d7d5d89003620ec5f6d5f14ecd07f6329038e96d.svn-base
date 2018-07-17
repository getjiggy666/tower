package com.telecom.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telecom.dao.AdminDao;
import com.telecom.entity.Admin;

/**
 * Dao实现类 - 用户
 */

@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, String> implements AdminDao {
	
	public boolean isExistByUsername(String username) {
		String hql = "from Admin as admin where lower(admin.username) = lower(:username)";
		Admin admin = (Admin) getSession().createQuery(hql).setParameter("username", username).uniqueResult();
		if (admin != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Admin getAdminByUsername(String username) {
		String hql = "from Admin as admin where lower(admin.username) = lower(:username)";
		return (Admin) getSession().createQuery(hql).setParameter("username", username).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public Admin getAdminByMobile(String mobile) {
		Criteria criteria = getSession().createCriteria(Admin.class);
		criteria.add(Restrictions.eq("mobile", mobile));
		List<Admin> list = criteria.list();
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}