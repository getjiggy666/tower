package com.telecom.action.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.telecom.bean.LogConstant;
import com.telecom.entity.Log;
import com.telecom.service.LogService;

/**
 * 后台Action类 - 日志
 */

@ParentPackage("admin")
public class LogAction extends BaseAdminAction {

	private static final long serialVersionUID = 8784555891643520648L;

	private Log log;

	@Resource(name = "logServiceImpl")
	private LogService logService;

	// 列表
	public String list() {
		pager = logService.findPager(pager);
		return LIST;
	}
	
	public String view() {
		log = logService.get(id);
		return VIEW;
	}
	
	// 获取所有订购状态列表
	public List<String> getLogTypeList(){
		List<String> logTypeList = new ArrayList<String>();
		Set<String> keySet = LogConstant.logTypeMap.keySet();
		for (String key : keySet) {
			String value = (String)LogConstant.logTypeMap.get(key);
			logTypeList.add(value);
		}
		return logTypeList;
	}
	
	// 获取所有来源列表
	public List<String> getAccesssSourceList(){
		List<String> accesssSourceList = new ArrayList<String>();
		accesssSourceList.add(LogConstant.AccesssSource.REQUEST);
		accesssSourceList.add(LogConstant.AccesssSource.ADMIN);
		accesssSourceList.add(LogConstant.AccesssSource.SERVICE);
		accesssSourceList.add(LogConstant.AccesssSource.PC);
		accesssSourceList.add(LogConstant.AccesssSource.WAP);
		return accesssSourceList;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

}