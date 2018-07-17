package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.TowerCollectionDao;
import com.telecom.entity.TowerCollection;

/**
 * Dao实现类 - 收藏
 */
@Repository("towerCollectionDaoImpl")
public class TowerCollectionDaoImpl extends
	BaseDaoImpl<TowerCollection, String> implements TowerCollectionDao {

}
