package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.RouteCreatorRecordDao;
import com.telecom.entity.RouteCreatorRecord;
import com.telecom.service.RouteCreatorRecordService;

/**
 * Dao实现类 - 路线
 */

@Service("routeCreatorRecordServiceImpl")
public class RouteCreatorRecordServiceImpl extends
		BaseServiceImpl<RouteCreatorRecord, String> implements
		RouteCreatorRecordService {

	@Resource(name = "routeCreatorRecordDaoImpl")
	RouteCreatorRecordDao routeCreatorRecordDao;

	@Resource(name = "routeCreatorRecordDaoImpl")
	public void setBaseDao(RouteCreatorRecordDao routeCreatorRecordDao) {
		super.setBaseDao(routeCreatorRecordDao);
	}
}
