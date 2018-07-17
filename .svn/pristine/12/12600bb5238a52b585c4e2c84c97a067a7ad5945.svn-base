package com.telecom.util;

import java.util.ArrayList;
import java.util.List;

public class PagerUtil {
	/**
	 * 根据页码获取当前页面的数据列表
	 * 
	 * @param page
	 *            当前页码
	 * @param per
	 *            每页显示数量
	 * @param list
	 *            列表
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getListDataByPage(int page, int per, List list) {
		if (list != null && list.size() > 0) {
			// 定义开始索引
			int indexStart = (page - 1) * per;
			// 定义结束索引
			int indexEnd = page * per - 1;
			// 定义新的列表存放结果集
			List resultList = new ArrayList();
			if (list.size() - 1 < indexEnd) {
				indexEnd = list.size() - 1;
			}
			for (int i = indexStart; i <= indexEnd; i++) {

				resultList.add(list.get(i));
			}
			return resultList;
		}
		return null;
	}

	/**
	 * 根据数据总量和每页显示数量进行分页
	 * 
	 * @param count
	 *            数据总量
	 * @param per
	 *            每页显示数量
	 * @return page 
	 * 			      总页码
	 */
	public static int getPageForDataList(int count, int per) {
		int page = count / per;
		if (count % per > 0) {
			page += 1;
		}
		return page;
	}

	/**
	 * 根据总页码数设置页码列表
	 * 
	 * @param count
	 * 			数据总量
	 * @param per
	 * 			每页显示数据量
	 * @return
	 */
	public static List<Integer> getPageList(int count, int per) {
		int page = getPageForDataList(count, per);
		List<Integer> pageList = new ArrayList<Integer>();
		for (int i = 1; i <= page; i++) {
			pageList.add(i);
		}
		return pageList;
	}
}
