package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.AccessStrategyItemDao;
import com.telecom.entity.AccessStrategyItem;

/**
 * Dao实现类 - IP过滤子表
 */

@Repository("accessStrategyItemDaoImpl")
public class AccessStrategyItemDaoImpl extends BaseDaoImpl<AccessStrategyItem, String> implements AccessStrategyItemDao {

	
}
