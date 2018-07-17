package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.telecom.dao.ScenicSpotDao;
import com.telecom.entity.ScenicSpot;
import com.telecom.service.ScenicSpotService;

/**
 * Service实现类 - 景点
 */

@Service("scenicSpotServiceImpl")
public class ScenicSpotServiceImpl extends BaseServiceImpl<ScenicSpot, String>
		implements ScenicSpotService {
	@Resource(name = "scenicSpotDaoImpl")
	ScenicSpotDao scenicSpotDao;

	@Resource(name = "scenicSpotDaoImpl")
	public void setBaseDao(ScenicSpotDao scenicSpotDao) {
		super.setBaseDao(scenicSpotDao);
	}
}
