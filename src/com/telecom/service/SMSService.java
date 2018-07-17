package com.telecom.service;

import java.util.List;

import com.telecom.bean.Pager;
import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;

/**
 * Service接口 - 短信
 */
public interface SMSService extends BaseService<SMS, String> {
	
	public List<SMS> getSMSList(SMSStatus status, Integer orderList);
	
	public List<SMS> getSMSList(String middleStatus, Integer orderList);

	public Pager findPager(Pager pager, SMSStatus status);
	
}
