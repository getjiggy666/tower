package com.telecom.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telecom.dao.MessageTemplateDao;
import com.telecom.entity.MessageTemplate;

/**
 * Dao实现类 - 通知模板
 */

@Repository("messageTemplateDaoImpl")
public class MessageTemplateDaoImpl extends BaseDaoImpl<MessageTemplate, String> implements MessageTemplateDao {

	@Override
	public boolean isExistBySign(String sign) {
		String hql = "from MessageTemplate as messageTemplate where lower(messageTemplate.sign) = lower(:sign)";
		MessageTemplate messageTemplate = (MessageTemplate) getSession().createQuery(hql).setParameter("sign", sign).uniqueResult();
		if(messageTemplate == null){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public MessageTemplate getMessageTemplateBySign(String sign) {
		Criteria criteria = getSession().createCriteria(MessageTemplate.class);
		criteria.add(Restrictions.eq("sign", sign));
		if(criteria.list().size() > 0){
			return (MessageTemplate) criteria.list().get(0);
		}else{
			return null;
		}
	}
	
}
