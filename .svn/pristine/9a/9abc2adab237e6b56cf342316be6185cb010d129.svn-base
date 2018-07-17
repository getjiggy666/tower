package com.telecom.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telecom.dao.SerialDao;
import com.telecom.entity.Serial;

@Repository("serialDaoImpl")
public class SerialDaoImpl extends BaseDaoImpl<Serial, String> implements SerialDao {

	@Override
	public Serial getSerialBySign(String sign) {
		Criteria criteria = getSession().createCriteria(Serial.class);
		criteria.add(Restrictions.eq("sign", sign));
		if(criteria.list().size() > 0){
			return (Serial) criteria.list().get(0);
		}else{
			return null;
		}
	}

	@Override
	public void nextSign(Serial serial) {
		serial.setValue(serial.getValue()+1);
		getSession().update(serial);
	}

}
