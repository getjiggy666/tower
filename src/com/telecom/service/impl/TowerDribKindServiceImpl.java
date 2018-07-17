package com.telecom.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.bean.TowerDribKindBean;
import com.telecom.dao.TowerDribKindDao;
import com.telecom.entity.TowerDribKind;
import com.telecom.service.TowerDribKindService;
import com.telecom.service.impl.BaseServiceImpl;

/**
 * Service实现类 - 点滴类别
 */

@Service("towerDribKindServiceImpl")
public class TowerDribKindServiceImpl extends
	BaseServiceImpl<TowerDribKind, String> implements TowerDribKindService {

    @Resource(name = "towerDribKindDaoImpl")
    TowerDribKindDao towerDribKindDao;

    @Resource(name = "towerDribKindDaoImpl")
    public void setBaseDao(TowerDribKindDao towerDribKindDao) {
	super.setBaseDao(towerDribKindDao);
    }

    public List<TowerDribKindBean> getKind(String towerUserId) {
	// TODO Auto-generated method stub
	return towerDribKindDao.getKind(towerUserId);
    }

    @Override
    public TowerDribKind isKindExist(String towerUserId,
	    String towerDribKindName) {
	// TODO Auto-generated method stub
	return towerDribKindDao.isKindExist(towerUserId, towerDribKindName);
    }

}
