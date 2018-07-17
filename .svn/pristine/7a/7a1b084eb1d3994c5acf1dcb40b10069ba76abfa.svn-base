package com.telecom.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telecom.bean.Pager;
import com.telecom.dao.SmsTemplateDao;
import com.telecom.entity.Check.CheckStatus;
import com.telecom.entity.SmsTemplate;

/**
 * Dao实现类 - 短信模板
 */

@Repository("smsTemplateDaoImpl")
public class SmsTemplateDaoImpl extends BaseDaoImpl<SmsTemplate, String> implements SmsTemplateDao {

	public Pager findCheckListPager(Pager pager){
		Criteria criteria = getSession().createCriteria(SmsTemplate.class);
		criteria.add(Restrictions.eq("checkStatus", CheckStatus.unprocessed));
		pager = super.findPager(pager, criteria);
		return pager;
	}

	@Override
	public boolean isExistBySign(String sign) {
		String hql = "from SmsTemplate as smsTemplate where lower(smsTemplate.sign) = lower(:sign)";
		SmsTemplate smsTemplate = (SmsTemplate) getSession().createQuery(hql).setParameter("sign", sign).uniqueResult();
		if(smsTemplate == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public SmsTemplate getSmsTemplate(String sign) {
		String hql = "from SmsTemplate as smsTemplate where smsTemplate.sign = :sign";
		return (SmsTemplate) getSession().createQuery(hql).setParameter("sign", sign).uniqueResult();
	}

}
