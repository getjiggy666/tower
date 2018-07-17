package com.telecom.service;

import com.telecom.entity.Serial;

public interface SerialService extends BaseService<Serial, String> {


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
	
	/**
	 * 转换唯一编号
	 * @param serial 标识类
	 * @return 编号
	 */
	public String toSign(Serial serial);
	
}
