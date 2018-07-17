package com.telecom.dao;

import java.util.List;
import java.util.Map;

import com.telecom.entity.Route;
import com.telecom.entity.ScenicSpot;

/**
 * Dao接口 - 路线
 */

public interface RouteDao extends BaseDao<Route, String> {
	/**
	 * 根据线路id获取地区详情
	 */
	public List<ScenicSpot> getScenicSpotList(String routeId);

	/**
	 * 根据线路id获取地区详情
	 */
	public List<Map<String, Object>> getEntrantsRouteList(
			String routeCreatorRecordId);

	/**
	 * 判断经纬度是否在区间内
	 */
	public boolean regionBoolean(String scenicSpotId, String latitude,
			String longitude);

	/**
	 * 如果路线已完成，修改route_creator_record记录status
	 */
	public boolean setRouteStatus(String routeCreatorRecordId);

	/**
	 * 查看照片是否重复上传
	 */
	public boolean isUploadRepeat(String routeCreatorRecordId,
			String routeEntrants);
}
