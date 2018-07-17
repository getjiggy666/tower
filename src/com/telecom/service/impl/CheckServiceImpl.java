package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.CheckDao;
import com.telecom.entity.Check;
import com.telecom.service.CheckService;

/**
 * Service实现类 - 审核
 */

@Service("checkServiceImpl")
public class CheckServiceImpl extends BaseServiceImpl<Check, String> implements CheckService {

	@Resource(name = "checkDaoImpl")
	private CheckDao checkDao;
	
	@Resource(name = "checkDaoImpl")
	public void setBaseDao(CheckDao checkDao) {
		super.setBaseDao(checkDao);
	}
	
}
