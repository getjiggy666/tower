package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.TowerCollectionDao;
import com.telecom.entity.TowerCollection;
import com.telecom.service.TowerCollectionService;

/**
 * Service实现类 - 收藏
 */

@Service("towerCollectionServiceImpl")
public class TowerCollectionServiceImpl extends
	BaseServiceImpl<TowerCollection, String> implements
	TowerCollectionService {

    @Resource(name = "towerCollectionDaoImpl")
    TowerCollectionDao towerCollectionDao;

    @Resource(name = "towerCollectionDaoImpl")
    public void setBaseDao(TowerCollectionDao towerCollectionDao) {
	super.setBaseDao(towerCollectionDao);
    }
}
