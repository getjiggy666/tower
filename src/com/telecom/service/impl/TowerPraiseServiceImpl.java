package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.telecom.dao.TowerPraiseDao;
import com.telecom.entity.TowerPraise;
import com.telecom.service.TowerPraiseService;

/**
 * Service实现类 - 点赞
 */

@Service("towerPraiseServiceImpl")
public class TowerPraiseServiceImpl extends
	BaseServiceImpl<TowerPraise, String> implements TowerPraiseService {

    @Resource(name = "towerPraiseDaoImpl")
    TowerPraiseDao towerPraiseDao;

    @Resource(name = "towerPraiseDaoImpl")
    public void setBaseDao(TowerPraiseDao towerPraiseDao) {
	super.setBaseDao(towerPraiseDao);
    }
}
