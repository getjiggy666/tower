package com.telecom.dao;

import com.telecom.entity.Serial;

public interface SerialDao extends BaseDao<Serial, String> {

	/**
	 * 根据条件获取唯一编号
	 * @param sign 标识
	 * @return 唯一编号
	 */
	public Serial getSerialBySign(String sign);
	
	/**
	 * 更新唯一编号(index+1)
	 * @param serial 需要更新的编号
	 */
	public void nextSign(Serial serial);
}
