package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.SerialDao;
import com.telecom.entity.Serial;
import com.telecom.service.SerialService;
import com.telecom.util.CommonUtil;

@Service("serialServiceImpl")
public class SerialServiceImpl extends BaseServiceImpl<Serial, String> implements SerialService {

	@Resource(name = "serialDaoImpl")
	private SerialDao serialDao;
	
	@Resource(name = "serialDaoImpl")
	public void setBaseDao(SerialDao serialDao){
		super.setBaseDao(serialDao);
	}

	@Override
	public Serial getSerialBySign(String sign) {
		Serial persistent = serialDao.getSerialBySign(sign);
		if(persistent == null) {
			Serial serial = new Serial();
			serial.setSign(sign);
			serial.setValue(1);
			super.save(serial);
			persistent = serial;
		}
		return persistent;
	}

	@Override
	public void nextSign(Serial serial) {
		serialDao.nextSign(serial);
	}

	@Override
	public String toSign(Serial serial) {
		String sign = serial.getSign()+CommonUtil.fillStr(serial.getValue(),12);
		return sign;
	}
	
}
