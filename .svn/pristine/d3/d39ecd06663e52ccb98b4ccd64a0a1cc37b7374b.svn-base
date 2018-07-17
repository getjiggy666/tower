package com.telecom.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.dao.AreaDao;
import com.telecom.entity.Area;
import com.telecom.service.AreaService;

/**
 * Service实现类 - 地区
 */

@Service("areaServiceImpl")
public class AreaServiceImpl extends BaseServiceImpl<Area, String> implements AreaService {

	@Resource(name = "areaDaoImpl")
	private AreaDao areaDao;
	
	@Resource(name = "areaDaoImpl")
	public void setBaseDao(AreaDao areaDao) {
		super.setBaseDao(areaDao);
	}

	@Transactional(readOnly = true)
	public List<Area> getRootList() {
		return areaDao.getRootList();
	}

	@Transactional(readOnly = true)
	public List<Area> getChildListByName(String name) {
		List<Area> list = new ArrayList<Area>();
		Area area = areaDao.getAreaByName(name);
		if(area != null){
			list = new ArrayList<Area>(area.getChildren());
			return list;
		}
		else{
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<Area> getPathList(String path) {
		return areaDao.getPathList(path);
	}
	
	@Override
	public String save(Area area) {
		return areaDao.save(area);
	}

}
