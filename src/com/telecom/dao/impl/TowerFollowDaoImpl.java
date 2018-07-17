package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.TowerFollowDao;
import com.telecom.entity.TowerFollow;

/**
 * Dao实现类 - 关注
 */

@Repository("towerFollowDaoImpl")
public class TowerFollowDaoImpl extends BaseDaoImpl<TowerFollow, String>
	implements TowerFollowDao {

}
