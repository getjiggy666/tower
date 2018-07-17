package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.RouteEntrantsRecordDao;
import com.telecom.entity.RouteEntrantsRecord;

/**
 * Dao实现类 - 路线
 */

@Repository("routeEntrantsRecordDaoImpl")
public class RouteEntrantsRecordDaoImpl extends
		BaseDaoImpl<RouteEntrantsRecord, String> implements
		RouteEntrantsRecordDao {

}
