package com.telecom.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.bean.Pager;
import com.telecom.dao.SMSDao;
import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;
import com.telecom.service.SMSService;

/**
 * Service实现类 - 短信
 */

@Service("smsServiceImpl")
public class SMSServiceImpl extends
		BaseServiceImpl<SMS, String> implements SMSService {

	@Resource(name = "smsDaoImpl")
	private SMSDao smsDao;
	
	@Resource(name = "smsDaoImpl")
	public void setBaseDao(SMSDao messageDao) {
		super.setBaseDao(messageDao);
	}
	
	@Transactional(readOnly = true)
	public List<SMS> getSMSList(SMSStatus status, Integer orderList){
		return smsDao.getSMSList(status, orderList);
	}

	@Override
	public Pager findPager(Pager pager, SMSStatus status) {
		return smsDao.findPager(pager, status);
	}

	@Override
	public List<SMS> getSMSList(String middleStatus, Integer orderList) {
		return smsDao.getSMSList(middleStatus, orderList);
	}

}
