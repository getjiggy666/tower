package com.telecom.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.bean.TowerUserBean;
import com.telecom.dao.TowerUserDao;
import com.telecom.entity.TowerUser;
import com.telecom.service.TowerUserService;

/**
 * Service实现类 - 角色
 */

@Service("towerUserServiceImpl")
public class TowerUserServiceImpl extends BaseServiceImpl<TowerUser, String> implements TowerUserService {

	@Resource(name = "towerUserDaoImpl")
	TowerUserDao towerUserDao;

	@Resource(name = "towerUserDaoImpl")
	public void setBaseDao(TowerUserDao towerUserDao) {
		super.setBaseDao(towerUserDao);
	}

	@Override
	public String getTowerUserIdByPhone(String phone) {
		// TODO Auto-generated method stub
		return towerUserDao.getTowerUserIdByPhone(phone);
	}

	@Override
	public boolean login(String phone, String password) {
		// TODO Auto-generated method stub
		return towerUserDao.login(phone, password);
	}

	@Override
	public List<TowerUserBean> getgreatmanlist(String greatManType) {
		// TODO Auto-generated method stub
		return towerUserDao.getgreatmanlist(greatManType);
	}

	@Override
	public List<TowerUserBean> getgreatmanlist(String towerUserId, String greatManType) {
		// TODO Auto-generated method stub
		return towerUserDao.getgreatmanlist(towerUserId, greatManType);
	}

	@Override
	public String getFollowNum(String towerUserId) {
		// TODO Auto-generated method stub
		return towerUserDao.getFollowNum(towerUserId);
	}

	@Override
	public String getFansNum(String towerUserId) {
		// TODO Auto-generated method stub
		return towerUserDao.getFansNum(towerUserId);
	}

	@Override
	public List<TowerUserBean> getFollow(String towerUserId) {
		// TODO Auto-generated method stub
		return towerUserDao.getFollow(towerUserId);
	}

	@Override
	public List<TowerUserBean> getFans(String towerUserId) {
		// TODO Auto-generated method stub
		return towerUserDao.getFans(towerUserId);
	}

	@Override
	public String isFollow(String towerUserId_star, String towerUserId) {
		// TODO Auto-generated method stub
		return towerUserDao.isFollow(towerUserId_star, towerUserId);
	}
}
