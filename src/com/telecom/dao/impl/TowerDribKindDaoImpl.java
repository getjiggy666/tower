package com.telecom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.telecom.bean.TowerDribKindBean;
import com.telecom.dao.TowerDribKindDao;
import com.telecom.entity.TowerDribKind;

/**
 * Dao实现类 - 点滴分类
 */

@Repository("towerDribKindDaoImpl")
public class TowerDribKindDaoImpl extends BaseDaoImpl<TowerDribKind, String> implements TowerDribKindDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerDribKindBean> getKind(String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "from TowerDribKind as towerDribKind where towerDribKind.towerUser.id = :towerUserId";
		List<TowerDribKind> towerDribKinds = getSession().createQuery(hql).setParameter("towerUserId", towerUserId)
				.list();
		List<TowerDribKindBean> towerDribKindBeans = new ArrayList<TowerDribKindBean>();
		for (int i = 0; i < towerDribKinds.size(); i++) {
			TowerDribKindBean towerDribKindBean = new TowerDribKindBean();
			// id
			towerDribKindBean.setDribKindId(towerDribKinds.get(i).getId());
			// 分类名称
			towerDribKindBean.setDribKindName(towerDribKinds.get(i).getName());
			// 分类描述
			towerDribKindBean.setDribKindDetail(towerDribKinds.get(i).getDetail());
			// 分类类别
			towerDribKindBean.setDribKindType(towerDribKinds.get(i).getKindType());
			// 分类内容数量
			String sql = "select count(*) from ct_tower_content where tower_drib_kind_id = :dribKindId";
			towerDribKindBean.setDribKindNum(String.valueOf(getSession().createSQLQuery(sql)
					.setParameter("dribKindId", towerDribKinds.get(i).getId()).uniqueResult()));
			towerDribKindBeans.add(towerDribKindBean);
		}
		return towerDribKindBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TowerDribKind isKindExist(String towerUserId, String towerDribKindName) {
		// TODO Auto-generated method stub
		String hql = "from TowerDribKind as towerDribKind where towerDribKind.towerUser.id = :towerUserId and towerDribKind.name = :towerDribKindName";
		List<TowerDribKind> towerDribKinds = getSession().createQuery(hql).setParameter("towerUserId", towerUserId)
				.setParameter("towerDribKindName", towerDribKindName).list();
		if (towerDribKinds.size() > 0) {
			return towerDribKinds.get(0);
		}
		return null;
	}
}
