package com.telecom.service;

import java.util.List;

import com.telecom.entity.Area;

/**
 * Service接口 - 地区
 */
public interface AreaService extends BaseService<Area, String> {
	/**
	 * 获取所有顶级节点列表
	 * 
	 * @return
	 */
	public List<Area> getRootList();

	/**
	 * 根据地区名称获取对应对象的子节点列表
	 * 
	 * @param name
	 *            地区名称
	 * @return
	 */
	public List<Area> getChildListByName(String name);
	
	/**
	 * 获取路径列表
	 * 
	 * @param path
	 *            路径
	 * @return
	 */
	public List<Area> getPathList(String path);
}
