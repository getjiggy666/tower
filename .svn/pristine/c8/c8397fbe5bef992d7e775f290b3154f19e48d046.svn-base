package com.telecom.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.telecom.entity.Area;
import com.telecom.service.AreaService;
import com.telecom.util.JsonUtil;

/**
 * 后台Action类 - 地区
 */

@ParentPackage("admin")
public class AreaAction extends BaseAdminAction {

	private static final long serialVersionUID = -2929806332040492581L;
	private final Logger log = Logger.getLogger(getClass());

	private List<Area> areaList; // 地区列表
	private String pid; // 父节点id
	private Area area; // 地区
	private Area parentArea; // 父地区

	@Resource(name = "areaServiceImpl")
	private AreaService areaService;

	// 列表
	public String list() {
		// 若有父节点id，则查询父节点的子列表
		if (StringUtils.isNotEmpty(pid)) {
			Area area = areaService.get(pid);
			if (area.getChildren() != null) {
				areaList = new ArrayList<Area>(area.getChildren());
			} else {
				areaList = new ArrayList<Area>();
			}
		}
		// 若无父节点id，则查询顶级列表
		else {
			if (areaService.getRootList() != null) {
				areaList = areaService.getRootList();
			} else {
				areaList = new ArrayList<Area>();
			}
		}
		return LIST;
	}

	// 添加
	public String add() {
		if (StringUtils.isNotEmpty(pid)) {
			parentArea = areaService.get(pid);
		}
		return INPUT;
	}

	// 编辑
	public String edit() {
		if (StringUtils.isNotEmpty(pid)) {
			parentArea = areaService.get(pid);
		}
		area = areaService.get(id);
		return INPUT;
	}

	// 删除
	public String delete() {
		StringBuffer logInfoStringBuffer = new StringBuffer("删除地区: ");

		Area deleteArea = areaService.get(id);
		areaService.delete(deleteArea);
		
		logInfoStringBuffer.append(JsonUtil.toJson(deleteArea));

		// 保存日志
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());

		if (StringUtils.isNotEmpty(pid)) {
			redirectUrl = "area!list.action?pid=" + pid;
		} else {
			redirectUrl = "area!list.action";
		}
		addActionMessage("删除成功");
		return SUCCESS;
	}

	// 保存
	@InputConfig(resultName = "error")
	public String save() {
		if (!StringUtils.isNotEmpty(area.getName())) {
			addActionError("请填写地区名称");
			return ERROR;
		}

		int grade = 0;
		if (StringUtils.isNotEmpty(pid)) {
			Area parentArea = areaService.get(pid);
			grade = parentArea.getGrade() + 1;
			area.setParent(parentArea);
			area.setDisplayName(parentArea.getDisplayName()+area.getName());
		}
		else{
			area.setDisplayName(area.getName());
		}
		area.setGrade(grade);
		
		areaService.save(area);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("添加地区: ");
		logInfoStringBuffer.append(JsonUtil.toJson(area));
		
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());

		if (StringUtils.isNotEmpty(pid)) {
			redirectUrl = "area!list.action?pid=" + pid;
		} else {
			redirectUrl = "area!list.action";
		}

		return SUCCESS;
	}

	// 修改
	@InputConfig(resultName = "error")
	public String update() {
		Area persistent = areaService.load(id);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("编辑前地区: ");
		logInfoStringBuffer.append(JsonUtil.toJson(persistent) + "<br>");
		
		if (!StringUtils.isNotEmpty(area.getName())) {
			addActionError("请填写地区名称");
			return ERROR;
		}
		
		if (StringUtils.isNotEmpty(pid)) {
			Area tempArea = areaService.get(pid);
			persistent.setDisplayName(tempArea.getDisplayName()+area.getName());
		}
		else{
			persistent.setDisplayName(area.getName());
		}

		BeanUtils.copyProperties(area, persistent, new String[] { "id",
				"createDate", "modifyDate", "grade", "path", "displayName" ,
				"parent" });
		areaService.update(persistent);
		
		logInfoStringBuffer.append("编辑后地区: " + JsonUtil.toJson(persistent));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		if (StringUtils.isNotEmpty(pid)) {
			redirectUrl = "area!list.action?pid=" + pid;
		} else {
			redirectUrl = "area!list.action";
		}

		return SUCCESS;
	}

	public List<Area> getPathList() {
		if (StringUtils.isNotEmpty(pid)) {
			// 获取父节点
			Area area = areaService.get(pid);
			String path = area.getPath();
			List<Area> list = areaService.getPathList(path);
			return list;
		} else {
			return new ArrayList<Area>();
		}
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Area getParentArea() {
		return parentArea;
	}

	public void setParentArea(Area parentArea) {
		this.parentArea = parentArea;
	}
}
