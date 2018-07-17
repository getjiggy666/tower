package com.telecom.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telecom.bean.Pager;
import com.telecom.bean.Pager.Order;
import com.telecom.dao.SMSDao;
import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;

@Repository("smsDaoImpl")
public class SMSDaoImpl extends BaseDaoImpl<SMS, String> implements SMSDao {
	
	@SuppressWarnings("unchecked")
	public List<SMS> getSMSList(SMSStatus status, Integer orderList){
		Criteria criteria = getSession().createCriteria(SMS.class);
		criteria.add(Restrictions.eq("status", status));
		criteria.add(Restrictions.gt("orderList", orderList));
		Pager pager = new Pager();
		pager.setOrderBy("orderList");
		pager.setOrder(Order.asc);
		pager = super.findPager(pager, criteria);
		return (List<SMS>)pager.getResult();
	}

	public Pager findPager(Pager pager, SMSStatus status) {
		Criteria criteria = getSession().createCriteria(SMS.class);
		criteria.add(Restrictions.eq("status", status));
		pager = super.findPager(pager, criteria);
		return pager;
	}

	@SuppressWarnings("unchecked")
	public List<SMS> getSMSList(String middleStatus, Integer orderList) {
		Criteria criteria = getSession().createCriteria(SMS.class);
		criteria.add(Restrictions.eq("middleStatus", middleStatus));
		criteria.add(Restrictions.isNull("gatewayStatus"));
		criteria.add(Restrictions.gt("orderList", orderList));
		Pager pager = new Pager();
		pager.setOrderBy("orderList");
		pager.setOrder(Order.asc);
		pager = super.findPager(pager, criteria);
		return (List<SMS>)pager.getResult();
	}

}
