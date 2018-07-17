package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.ScenicSpotDao;
import com.telecom.entity.ScenicSpot;

/**
 * Dao实现类 - 景点
 */

@Repository("scenicSpotDaoImpl")
public class ScenicSpotDaoImpl extends BaseDaoImpl<ScenicSpot, String>
		implements ScenicSpotDao {

}
