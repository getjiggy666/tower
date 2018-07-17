package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.bean.Pager;
import com.telecom.dao.MessageDao;
import com.telecom.entity.Admin;
import com.telecom.entity.Message;
import com.telecom.service.MessageService;

/**
 * Service实现类 - 通知
 */

@Service("messageServiceImpl")
public class MessageServiceImpl extends BaseServiceImpl<Message, String> implements MessageService {
	
	@Resource(name = "messageDaoImpl")
	private MessageDao messageDao;
	
	@Resource(name = "messageDaoImpl")
	public void setBaseDao(MessageDao messageDao) {
		super.setBaseDao(messageDao);
	}
	
	@Transactional(readOnly = true)
	public Pager getAdminInboxPager(Admin admin, Pager pager) {
		return messageDao.getAdminInboxPager(admin, pager);
	}
	
	@Transactional(readOnly = true)
	public Pager getAdminOutboxPager(Admin admin, Pager pager) {
		return messageDao.getAdminOutboxPager(admin, pager);
	}
	
	@Transactional(readOnly = true)
	public Long getUnreadMessageCount() {
		return messageDao.getUnreadMessageCount();
	}

	@Override
	public boolean isExistMessage(String id) {
		return messageDao.isExistMessage(id);
	}

}