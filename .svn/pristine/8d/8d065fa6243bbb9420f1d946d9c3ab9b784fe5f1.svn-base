package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.AccessStrategyDao;
import com.telecom.entity.AccessStrategy;
import com.telecom.service.AccessStrategyService;

/**
 * Service实现类 - IP过滤
 */

@Service("accessStrategyServiceImpl")
public class AccessStrategyServiceImpl extends BaseServiceImpl<AccessStrategy, String> implements AccessStrategyService {

	@Resource(name = "accessStrategyDaoImpl")
	private AccessStrategyDao accessStrategyDao;
	
	@Resource(name = "accessStrategyDaoImpl")
	public void setBaseDao(AccessStrategyDao accessStrategyDao){
		super.setBaseDao(accessStrategyDao);
	}

	@Override
	public boolean isExistBySign(String sign) {
		return accessStrategyDao.isExistBySign(sign);
	}

	@Override
	public AccessStrategy getAccessStrategyBySign(String sign) {
		return accessStrategyDao.getAccessStrategyBySign(sign);
	}
}
