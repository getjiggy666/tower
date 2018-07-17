package com.telecom.dao;

import java.util.List;

import com.telecom.entity.Area;

/**
 * Dao接口 - 地区
 */

public interface AreaDao extends BaseDao<Area, String> {
	/**
	 * 获取所有顶级节点列表
	 * 
	 * @return
	 */
	public List<Area> getRootList();

	/**
	 * 根据地区名称获取地区对象
	 * 
	 * @param name
	 *            地区名称
	 * @return
	 */
	public Area getAreaByName(String name);

	/**
	 * 获取路径列表
	 * 
	 * @param path
	 *            路径
	 * @return
	 */
	public List<Area> getPathList(String path);
}
