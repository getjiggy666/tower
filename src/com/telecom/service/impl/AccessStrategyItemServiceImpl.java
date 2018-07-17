package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.AccessStrategyItemDao;
import com.telecom.entity.AccessStrategyItem;
import com.telecom.service.AccessStrategyItemService;

/**
 * Service实现类 - IP过滤子表
 */

@Service("accessStrategyItemServiceImpl")
public class AccessStrategyItemServiceImpl extends BaseServiceImpl<AccessStrategyItem, String> implements AccessStrategyItemService {

	@Resource(name = "accessStrategyItemDaoImpl")
	private AccessStrategyItemDao accessStrategyItemDao;
	
	@Resource(name = "accessStrategyItemDaoImpl")
	public void setBaseDao(AccessStrategyItemDao accessStrategyItemDao){
		super.setBaseDao(accessStrategyItemDao);
	}
}
