package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.RouteCreatorRecordDao;
import com.telecom.entity.RouteCreatorRecord;

/**
 * Dao实现类 - 路线
 */

@Repository("routeCreatorRecordDaoImpl")
public class RouteCreatorRecordDaoImpl extends
		BaseDaoImpl<RouteCreatorRecord, String> implements
		RouteCreatorRecordDao {

}
