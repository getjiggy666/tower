package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.TowerPraiseDao;
import com.telecom.entity.TowerPraise;

/**
 * Dao实现类 - 点赞
 */
@Repository("towerPraiseDaoImpl")
public class TowerPraiseDaoImpl extends BaseDaoImpl<TowerPraise, String>
	implements TowerPraiseDao {

}
