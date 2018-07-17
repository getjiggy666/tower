package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.bean.Pager;
import com.telecom.dao.SmsTemplateDao;
import com.telecom.entity.SmsTemplate;
import com.telecom.service.SmsTemplateService;

/**
 * Service实现类 - 短信模板
 */

@Service("smsTemplateServiceImpl")
public class SmsTemplateServiceImpl extends BaseServiceImpl<SmsTemplate, String> implements SmsTemplateService {

	@Resource(name = "smsTemplateDaoImpl")
	private SmsTemplateDao smsTemplateDao;
	
	@Resource(name = "smsTemplateDaoImpl")
	public void setBaseDao(SmsTemplateDao smsTemplateDao) {
		super.setBaseDao(smsTemplateDao);
	}

	@Transactional(readOnly = true)
	public Pager findCheckListPager(Pager pager) {
		return smsTemplateDao.findCheckListPager(pager);
	}

	@Override
	public boolean isExistBySign(String sign) {
		return false;
	}

	@Override
	public SmsTemplate getDefaultSmsTemplate() {
		return smsTemplateDao.getSmsTemplate("DXMB_694949FC70F8");
	}
	
	@Override
	public SmsTemplate getSmsTemplate(String sign) {
		return smsTemplateDao.getSmsTemplate(sign);
	}

}
