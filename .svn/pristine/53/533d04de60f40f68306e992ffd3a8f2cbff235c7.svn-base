package com.telecom.action.admin;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.telecom.entity.Route;
import com.telecom.service.RouteService;

/**
 * 后台Action类 - 路线
 */
@ParentPackage("admin")
public class RouteAction extends BaseAdminAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3442139441279966552L;

	@Resource(name = "routeServiceImpl")
	private RouteService routeService;

	private Route route;

	// 列表
	public String list() {
		pager = routeService.findPager(pager);
		return LIST;
	}

	// 编辑
	public String edit() {
		route = routeService.load(id);
		return INPUT;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
}
