package com.telecom.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.RouteDao;
import com.telecom.entity.Route;
import com.telecom.entity.ScenicSpot;
import com.telecom.service.RouteService;

/**
 * Dao实现类 - 路线
 */

@Service("routeServiceImpl")
public class RouteServiceImpl extends BaseServiceImpl<Route, String> implements
		RouteService {

	@Resource(name = "routeDaoImpl")
	RouteDao routeDao;

	@Resource(name = "routeDaoImpl")
	public void setBaseDao(RouteDao routeDao) {
		super.setBaseDao(routeDao);
	}

	/**
	 * 根据线路id获取地区详情
	 */
	@Override
	public List<ScenicSpot> getScenicSpotList(String routeId) {
		// TODO Auto-generated method stub
		return routeDao.getScenicSpotList(routeId);
	}

	/**
	 * 根据线路id获取地区详情
	 */
	@Override
	public List<Map<String, Object>> getEntrantsRouteList(
			String routeCreatorRecordId) {
		// TODO Auto-generated method stub
		return routeDao.getEntrantsRouteList(routeCreatorRecordId);
	}

	/**
	 * 判断经纬度是否在区间内
	 */
	@Override
	public boolean regionBoolean(String scenicSpotId, String latitude,
			String longitude) {
		// TODO Auto-generated method stub
		return routeDao.regionBoolean(scenicSpotId, latitude, longitude);
	}

	/**
	 * 如果路线已完成，修改route_creator_record记录status
	 */
	@Override
	public boolean setRouteStatus(String routeCreatorRecordId) {
		// TODO Auto-generated method stub
		return routeDao.setRouteStatus(routeCreatorRecordId);
	}

	/**
	 * 查看照片是否重复上传
	 */
	@Override
	public boolean isUploadRepeat(String routeCreatorRecordId,
			String routeEntrants) {
		// TODO Auto-generated method stub
		return routeDao.isUploadRepeat(routeCreatorRecordId, routeEntrants);
	}
}
