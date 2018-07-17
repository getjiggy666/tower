package com.telecom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.telecom.dao.AreaDao;
import com.telecom.entity.Area;

/**
 * Dao实现类 - 地区
 */

@Repository("areaDaoImpl")
public class AreaDaoImpl extends BaseDaoImpl<Area, String> implements AreaDao {

	@SuppressWarnings("unchecked")
	public List<Area> getRootList() {
		Criteria criteria = getSession().createCriteria(Area.class);
		criteria.add(Restrictions.eq("grade", new Integer(0)));
		List<Area> list = criteria.list();
		if(list!=null && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	public Area getAreaByName(String name) {
		String hql = "from Area as area where area.name = :name";
		Area area = (Area) getSession().createQuery(hql).setParameter("name", name).uniqueResult();
		if (area != null) {
			return area;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Area> getPathList(String path) {
		String[] array = path.split(",");
		Criteria criteria = getSession().createCriteria(Area.class);
		criteria.add(Restrictions.in("id", array));
		criteria.addOrder(Order.asc("grade"));
		List<Area> list = criteria.list();
		if(list!=null && list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	
	public String save(Area area){
		area.setPath("path");
		String id = super.save(area);
		String path = "";
		List<String> tempList = new ArrayList<String>();
		// 循环判断父节点是否存在
		tempList.add(id);
		Area tempArea = area.getParent();
		while (tempArea != null) {
			tempList.add(0, tempArea.getId());
			tempArea = tempArea.getParent();
		}

		for (int i = 0; i < tempList.size(); i++) {
			if (i > 0) {
				path += ",";
			}
			path += tempList.get(i);
		}
		area.setPath(path);
		super.update(area);
		return area.getId();
	}

}
