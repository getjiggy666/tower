package com.telecom.service.impl;

import javax.annotation.Resource;

import com.telecom.dao.LogDao;
import com.telecom.entity.Log;
import com.telecom.service.LogService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 日志
 */

@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, String> implements LogService {

	@Resource(name = "logDaoImpl")
	public void setBaseDao(LogDao logDao) {
		super.setBaseDao(logDao);
	}

}