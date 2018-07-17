package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.TowerFollowDao;
import com.telecom.entity.TowerFollow;
import com.telecom.service.TowerFollowService;

/**
 * Service实现类 - 关注
 */

@Service("towerFollowServiceImpl")
public class TowerFollowServiceImpl extends
	BaseServiceImpl<TowerFollow, String> implements TowerFollowService {
    
    @Resource(name = "towerFollowDaoImpl")
    TowerFollowDao towerFollowDao;

    @Resource(name = "towerFollowDaoImpl")
    public void setBaseDao(TowerFollowDao towerFollowDao) {
	super.setBaseDao(towerFollowDao);
    }
}
