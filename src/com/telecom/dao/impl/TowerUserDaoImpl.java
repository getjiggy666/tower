package com.telecom.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.telecom.bean.TowerUserBean;
import com.telecom.dao.TowerUserDao;
import com.telecom.entity.TowerFollow;
import com.telecom.entity.TowerUser;
import com.telecom.util.SettingUtil;

/**
 * Dao实现类 - 用户
 */

@Repository("towerUserDaoImpl")
public class TowerUserDaoImpl extends BaseDaoImpl<TowerUser, String> implements TowerUserDao {

	@Override
	public String getTowerUserIdByPhone(String phone) {
		// TODO Auto-generated method stub
		String sql = "select id from ct_tower_user where phone = :phone";
		@SuppressWarnings("unchecked")
		List<String> ids = getSession().createSQLQuery(sql).setParameter("phone", phone).list();
		String id;
		if (ids != null && ids.size() != 0) {
			id = ids.get(0);
		} else {
			id = null;
		}
		return id;
	}

	@Override
	public boolean login(String phone, String password) {
		// TODO Auto-generated method stub
		String sql = "select password from ct_tower_user where phone = :phone";
		@SuppressWarnings("unchecked")
		List<String> passwords = getSession().createSQLQuery(sql).setParameter("phone", phone).list();
		String passwordSql = passwords.get(0);
		if (password.equals(passwordSql)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerUserBean> getgreatmanlist(String greatManType) {
		// TODO Auto-generated method stub
		String hql = "from TowerUser as towerUser where towerUser.category = :greatManType";
		List<TowerUser> towerUsers = getSession().createQuery(hql).setParameter("greatManType", greatManType).list();
		List<TowerUserBean> towerUserBeans = new ArrayList<TowerUserBean>();
		for (int i = 0; i < towerUsers.size(); i++) {
			TowerUserBean towerUserBean = new TowerUserBean();
			// 用户id
			towerUserBean.setTowerUserId(towerUsers.get(i).getId());
			// 头像
			towerUserBean.setIconUrl(SettingUtil.getSetting().getUpFilePath() + towerUsers.get(i).getIconUrl());
			// 昵称
			towerUserBean.setName(towerUsers.get(i).getName());
			// 公司
			towerUserBean.setCompany(towerUsers.get(i).getCompany());
			// 学校
			towerUserBean.setUniversity(towerUsers.get(i).getUniversity());
			// 是否关注
			towerUserBean.setFollow("0");
			// 关注人数
			String sqlString = "select count(*) from ct_tower_follow where star_id = :star_id";
			towerUserBean.setFollowNum(String.valueOf(getSession().createSQLQuery(sqlString)
					.setParameter("star_id", towerUsers.get(i).getId()).uniqueResult()));
			towerUserBeans.add(towerUserBean);
		}
		return towerUserBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerUserBean> getgreatmanlist(String towerUserId, String greatManType) {
		// TODO Auto-generated method stub
		String hql = "from TowerUser as towerUser where towerUser.category = :greatManType";
		List<TowerUser> towerUsers = getSession().createQuery(hql).setParameter("greatManType", greatManType).list();
		List<TowerUserBean> towerUserBeans = new ArrayList<TowerUserBean>();
		for (int i = 0; i < towerUsers.size(); i++) {
			TowerUserBean towerUserBean = new TowerUserBean();
			// 用户id
			towerUserBean.setTowerUserId(towerUsers.get(i).getId());
			// 头像
			towerUserBean.setIconUrl(SettingUtil.getSetting().getUpFilePath() + towerUsers.get(i).getIconUrl());
			// 昵称
			towerUserBean.setName(towerUsers.get(i).getName());
			// 公司
			towerUserBean.setCompany(towerUsers.get(i).getCompany());
			// 学校
			towerUserBean.setUniversity(towerUsers.get(i).getUniversity());
			// 我是否关注
			String sql = "select count(*) from ct_tower_follow where star_id = :star_id and fans_id = :fans_id";
			towerUserBean.setFollow(
					String.valueOf(getSession().createSQLQuery(sql).setParameter("star_id", towerUsers.get(i).getId())
							.setParameter("fans_id", towerUserId).uniqueResult()));
			// 关注人数
			String sqlString = "select count(*) from ct_tower_follow where star_id = :star_id";
			towerUserBean.setFollowNum(String.valueOf(getSession().createSQLQuery(sqlString)
					.setParameter("star_id", towerUsers.get(i).getId()).uniqueResult()));
			towerUserBeans.add(towerUserBean);
		}
		return towerUserBeans;
	}

	@Override
	public String getFollowNum(String towerUserId) {
		// TODO Auto-generated method stub
		// 关注人数
		String sqlString = "select count(*) from ct_tower_follow where fans_id = :fans_id";
		return String
				.valueOf(getSession().createSQLQuery(sqlString).setParameter("fans_id", towerUserId).uniqueResult());
	}

	@Override
	public String getFansNum(String towerUserId) {
		// TODO Auto-generated method stub
		// 粉丝人数
		String sqlString = "select count(*) from ct_tower_follow where star_id = :star_id";
		return String
				.valueOf(getSession().createSQLQuery(sqlString).setParameter("star_id", towerUserId).uniqueResult());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerUserBean> getFollow(String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "from TowerFollow as towerFollow where towerFollow.fans.id = :towerUserId";
		List<TowerFollow> towerFollows = getSession().createQuery(hql).setParameter("towerUserId", towerUserId).list();
		List<TowerUserBean> towerUserBeans = new ArrayList<TowerUserBean>();
		for (int i = 0; i < towerFollows.size(); i++) {
			TowerUserBean towerUserBean = new TowerUserBean();
			// 用户id
			towerUserBean.setTowerUserId(towerFollows.get(i).getStar().getId());
			// 头像
			towerUserBean
					.setIconUrl(SettingUtil.getSetting().getUpFilePath() + towerFollows.get(i).getStar().getIconUrl());
			// 昵称
			towerUserBean.setName(towerFollows.get(i).getStar().getName());
			// 公司
			towerUserBean.setCompany(towerFollows.get(i).getStar().getCompany());
			// 学校
			towerUserBean.setUniversity(towerFollows.get(i).getStar().getUniversity());
			// 我是否关注
			// String sql =
			// "select count(*) from ct_tower_follow where star_id = :star_id
			// and fans_id = :fans_id";
			towerUserBean.setFollow("1");
			// 关注人数
			String sqlString = "select count(*) from ct_tower_follow where star_id = :star_id";
			towerUserBean.setFollowNum(String.valueOf(getSession().createSQLQuery(sqlString)
					.setParameter("star_id", towerFollows.get(i).getStar().getId()).uniqueResult()));
			towerUserBeans.add(towerUserBean);
		}
		Collections.reverse(towerUserBeans);
		return towerUserBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerUserBean> getFans(String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "from TowerFollow as towerFollow where towerFollow.star.id = :towerUserId";
		List<TowerFollow> towerFollows = getSession().createQuery(hql).setParameter("towerUserId", towerUserId).list();
		List<TowerUserBean> towerUserBeans = new ArrayList<TowerUserBean>();
		for (int i = 0; i < towerFollows.size(); i++) {
			TowerUserBean towerUserBean = new TowerUserBean();
			// 用户id
			towerUserBean.setTowerUserId(towerFollows.get(i).getFans().getId());
			// 头像
			towerUserBean
					.setIconUrl(SettingUtil.getSetting().getUpFilePath() + towerFollows.get(i).getFans().getIconUrl());
			// 昵称
			towerUserBean.setName(towerFollows.get(i).getFans().getName());
			// 公司
			towerUserBean.setCompany(towerFollows.get(i).getFans().getCompany());
			// 学校
			towerUserBean.setUniversity(towerFollows.get(i).getFans().getUniversity());
			// 我是否关注
			String sql = "select count(*) from ct_tower_follow where star_id = :star_id and fans_id = :fans_id";
			towerUserBean.setFollow(String.valueOf(
					getSession().createSQLQuery(sql).setParameter("star_id", towerFollows.get(i).getFans().getId())
							.setParameter("fans_id", towerUserId).uniqueResult()));
			// 关注人数
			String sqlString = "select count(*) from ct_tower_follow where star_id = :star_id";
			towerUserBean.setFollowNum(String.valueOf(getSession().createSQLQuery(sqlString)
					.setParameter("star_id", towerFollows.get(i).getFans().getId()).uniqueResult()));
			towerUserBeans.add(towerUserBean);
		}
		Collections.reverse(towerUserBeans);
		return towerUserBeans;
	}

	@Override
	public String isFollow(String towerUserId_star, String towerUserId) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from ct_tower_follow where star_id = :star_id and fans_id = :fans_id";
		String isFollow = String.valueOf(getSession().createSQLQuery(sql).setParameter("star_id", towerUserId_star)
				.setParameter("fans_id", towerUserId).uniqueResult());
		if (Integer.valueOf(isFollow).intValue() >= 1) {
			return "1";
		}
		return "0";
	}

}
