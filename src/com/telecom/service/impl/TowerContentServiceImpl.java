package com.telecom.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.bean.TowerCommentBean;
import com.telecom.bean.TowerContentBean;
import com.telecom.dao.TowerContentDao;
import com.telecom.entity.TowerContent;
import com.telecom.service.TowerContentService;

/**
 * Service实现类 - 内容
 */

@Service("towerContentServiceImpl")
public class TowerContentServiceImpl extends BaseServiceImpl<TowerContent, String> implements TowerContentService {

	@Resource(name = "towerContentDaoImpl")
	TowerContentDao towerContentDao;

	@Resource(name = "towerContentDaoImpl")
	public void setBaseDao(TowerContentDao towerContentDao) {
		super.setBaseDao(towerContentDao);
	}

	@Override
	public List<TowerContentBean> getList(String pageNum) {
		// TODO Auto-generated method stub
		return towerContentDao.getList(pageNum);
	}

	@Override
	public List<TowerContentBean> getAllListByUser(String towerUserId, String pageNum) {
		// TODO Auto-generated method stub
		return towerContentDao.getAllListByUser(towerUserId, pageNum);
	}

	@Override
	public boolean no_collection(String towerContentId, String towerUserId) {
		// TODO Auto-generated method stub
		return towerContentDao.no_collection(towerContentId, towerUserId);
	}

	@Override
	public boolean no_follow(String towerUserId, String towerUserId_fans) {
		// TODO Auto-generated method stub
		return towerContentDao.no_follow(towerUserId, towerUserId_fans);
	}

	@Override
	public List<TowerContentBean> search(String towerUserId, String keyword) {
		// TODO Auto-generated method stub
		return towerContentDao.search(towerUserId, keyword);
	}

	@Override
	public List<TowerContentBean> getdribhomepage(String towerUserId) {
		// TODO Auto-generated method stub
		return towerContentDao.getdribhomepage(towerUserId);
	}

	@Override
	public List<TowerContentBean> getdrib(String dribKindId) {
		// TODO Auto-generated method stub
		return towerContentDao.getdrib(dribKindId);
	}

	@Override
	public boolean no_praise(String towerContentId, String towerUserId) {
		// TODO Auto-generated method stub
		return towerContentDao.no_praise(towerContentId, towerUserId);
	}

	@Override
	public List<TowerCommentBean> getCommentListByContentId(String towerContentId) {
		// TODO Auto-generated method stub
		return towerContentDao.getCommentListByContentId(towerContentId);
	}

	@Override
	public List<TowerContentBean> getMomentsByUser(String towerUserId) {
		// TODO Auto-generated method stub
		return towerContentDao.getMomentsByUser(towerUserId);
	}

	@Override
	public List<TowerContentBean> getCollectionByUser(String towerUserId) {
		// TODO Auto-generated method stub
		return towerContentDao.getCollectionByUser(towerUserId);
	}

	@Override
	public List<TowerContentBean> getTestA(String towerUserId) {
		// TODO Auto-generated method stub
		return towerContentDao.getTestA(towerUserId);
	}

	@Override
	public List<TowerContentBean> getTestB(String towerUserId) {
		// TODO Auto-generated method stub
		return towerContentDao.getTestB(towerUserId);
	}

	@Override
	public boolean deleteDribKind(String dribKindId) {
		// TODO Auto-generated method stub
		return towerContentDao.deleteDribKind(dribKindId);
	}

	@Override
	public boolean modifyDribKind(String towerContentId, String dribKindId) {
		// TODO Auto-generated method stub
		return towerContentDao.modifyDribKind(towerContentId, dribKindId);
	}
}
