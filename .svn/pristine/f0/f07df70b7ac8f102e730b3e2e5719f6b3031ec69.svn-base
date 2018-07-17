package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.RouteEntrantsRecordDao;
import com.telecom.entity.RouteEntrantsRecord;
import com.telecom.service.RouteEntrantsRecordService;

/**
 * Dao实现类 - 路线
 */

@Service("routeEntrantsRecordServiceImpl")
public class RouteEntrantsRecordServiceImpl extends
		BaseServiceImpl<RouteEntrantsRecord, String> implements
		RouteEntrantsRecordService {

	@Resource(name = "routeEntrantsRecordDaoImpl")
	RouteEntrantsRecordDao routeEntrantsRecordDao;

	@Resource(name = "routeEntrantsRecordDaoImpl")
	public void setBaseDao(RouteEntrantsRecordDao routeEntrantsRecordDao) {
		super.setBaseDao(routeEntrantsRecordDao);
	}
}
