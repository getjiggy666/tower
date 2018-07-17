package com.telecom.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telecom.dao.AccessStrategyDao;
import com.telecom.entity.AccessStrategy;

/**
 * Dao实现类 - IP过滤
 */

@Repository("accessStrategyDaoImpl")
public class AccessStrategyDaoImpl extends BaseDaoImpl<AccessStrategy, String> implements AccessStrategyDao {

	@Override
	public boolean isExistBySign(String sign) {
		String hql = "from AccessStrategy as accessStrategy where accessStrategy.sign = :sign";
		AccessStrategy accessStrategy = (AccessStrategy) getSession().createQuery(hql).setParameter("sign", sign).uniqueResult();
		if(accessStrategy == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public AccessStrategy getAccessStrategyBySign(String sign) {
		Criteria criteria = getSession().createCriteria(AccessStrategy.class);
		criteria.add(Restrictions.eq("sign", sign));
		if(criteria.list().size() > 0){
			return (AccessStrategy) criteria.list().get(0);
		}else{
			return null;
		}
	}

}
