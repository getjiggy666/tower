package com.telecom.dao;

import java.util.List;

import com.telecom.bean.Pager;
import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;

public interface SMSDao extends BaseDao<SMS, String> {

	public List<SMS> getSMSList(SMSStatus status, Integer orderList);
	
	public List<SMS> getSMSList(String middleStatus, Integer orderList);

	public Pager findPager(Pager pager, SMSStatus status);
	
}
