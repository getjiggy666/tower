package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.MessageTemplateDao;
import com.telecom.entity.MessageTemplate;
import com.telecom.service.MessageTemplateService;

/**
 * Service实现类 - 通知模板
 */

@Service("messageTemplateServiceImpl")
public class MessageTemplateServiceImpl extends BaseServiceImpl<MessageTemplate, String> implements MessageTemplateService {

	@Resource(name = "messageTemplateDaoImpl")
	private MessageTemplateDao messageTemplateDao;
	
	@Resource(name = "messageTemplateDaoImpl")
	public void setBaseDao(MessageTemplateDao messageTemplateDao) {
		super.setBaseDao(messageTemplateDao);
	}

	@Override
	public boolean isExistBySign(String sign) {
		return false;
	}
	
	@Override
	public MessageTemplate getMessageTemplateBySign(String sign) {
		return messageTemplateDao.getMessageTemplateBySign(sign);
	}

}
