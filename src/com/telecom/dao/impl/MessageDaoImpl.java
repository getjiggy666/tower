package com.telecom.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telecom.bean.Pager;
import com.telecom.dao.MessageDao;
import com.telecom.entity.Admin;
import com.telecom.entity.Message;
import com.telecom.entity.Message.DeleteStatus;

/**
 * Dao实现类 - 通知
 */

@Repository("messageDaoImpl")
public class MessageDaoImpl extends BaseDaoImpl<Message, String> implements MessageDao{
	
	public Pager getAdminInboxPager(Admin admin, Pager pager) {
		return super.findPager(pager, Restrictions.eq("toAdmin", admin), Restrictions.eq("isSaveDraftbox", false), Restrictions.ne("deleteStatus", DeleteStatus.toDelete));
	}

	public Pager getAdminOutboxPager(Admin admin, Pager pager) {
		return super.findPager(pager, Restrictions.eq("fromAdmin", admin), Restrictions.eq("isSaveDraftbox", false), Restrictions.ne("deleteStatus", DeleteStatus.fromDelete));
	}
	
	public Long getUnreadMessageCount() {
		String hql = "select count(*) from Message as message where message.toMember is null and message.isRead = :isRead and message.isSaveDraftbox = :isSaveDraftbox and message.deleteStatus != :deleteStatus";
		return (Long) getSession().createQuery(hql).setParameter("isRead", false).setParameter("isSaveDraftbox", false).setParameter("deleteStatus", DeleteStatus.toDelete).uniqueResult();
	}

	@Override
	public boolean isExistMessage(String id) {
		String hql = "from Message as message where message.id = '"+id+"'";
		if(getSession().createQuery(hql).uniqueResult()!=null){
			return true;
		}else{
			return false;
		}
	}

}