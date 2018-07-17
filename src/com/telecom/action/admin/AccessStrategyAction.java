package com.telecom.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.telecom.entity.AccessStrategy;
import com.telecom.entity.AccessStrategyItem;
import com.telecom.service.AccessStrategyItemService;
import com.telecom.service.AccessStrategyService;
import com.telecom.util.JsonUtil;

/**
 * 后台Action类 - IP过滤
 */

@ParentPackage("admin")
public class AccessStrategyAction extends BaseAdminAction {

	private static final long serialVersionUID = -6296393111270477663L;
	private final Logger log = Logger.getLogger(getClass());

	private AccessStrategy accessStrategy;
	private List<AccessStrategyItem> accessStrategyItemList;

	@Resource(name = "accessStrategyServiceImpl")
	private AccessStrategyService accessStrategyService;
	@Resource(name = "accessStrategyItemServiceImpl")
	private AccessStrategyItemService accessStrategyItemService;

	// 列表
	public String list() {
		pager = accessStrategyService.findPager(pager);
		return LIST;
	}

	// 添加
	public String add() {
		return INPUT;
	}

	// 查看
	public String view() {
		accessStrategy = accessStrategyService.load(id);
		return VIEW;
	}

	// 编辑
	public String edit() {
		accessStrategy = accessStrategyService.load(id);
		return INPUT;
	}

	// 删除
	public String delete() {
		StringBuffer logInfoStringBuffer = new StringBuffer("删除访问策略: ");
		for (String id : ids) {
			AccessStrategy accessStrategy = accessStrategyService.load(id);
			accessStrategyService.delete(accessStrategy);
			
			logInfoStringBuffer.append(JsonUtil.toJson(accessStrategy));
		}
		// 保存日志
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		return ajax(Status.success, "删除成功!");
	}

	// 保存
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "accessStrategy.name", message = "策略名称不允许为空!")
			}
		)
	@InputConfig(resultName = "error")
	public String save() {
		// 自动生成编号，直接保存
		accessStrategyService.save(accessStrategy);
		StringBuffer logInfoStringBuffer = new StringBuffer("添加访问策略: <br>");
		logInfoStringBuffer.append("基本信息: " + JsonUtil.toJson(accessStrategy) + "<br>");
		logInfoStringBuffer.append("策略信息: ");
		
		if (accessStrategyItemList != null) {
			// 循环添加子类
			for (AccessStrategyItem accessStrategyItem : accessStrategyItemList) {
				if (accessStrategyItem != null) {
					accessStrategyItem.setAccessStrategy(accessStrategy);
					accessStrategyItemService.save(accessStrategyItem);
					logInfoStringBuffer.append(JsonUtil.toJson(accessStrategyItem));
				} 
			}
		}
		
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		redirectUrl = "access_strategy!list.action";
		return SUCCESS;
	}

	// 修改
	@InputConfig(resultName = "error")
	public String update() {
		AccessStrategy persistent = accessStrategyService.load(id);
		StringBuffer logInfoStringBuffer = new StringBuffer("编辑前访问策略: <br>");
		logInfoStringBuffer.append("基本信息: " + JsonUtil.toJson(persistent) + "<br>");
		logInfoStringBuffer.append("策略信息: ");
		
		// 删除原表中的IP
		for (AccessStrategyItem persistentItem : persistent.getAccessStrategyItemSet()) {
			logInfoStringBuffer.append(JsonUtil.toJson(persistentItem));
			accessStrategyItemService.delete(persistentItem);
		}
		logInfoStringBuffer.append("<br>");

		// 映射表信息
		BeanUtils.copyProperties(accessStrategy, persistent, new String[] {
				"id", "createDate", "modifyDate", "sign" });
		accessStrategyService.update(persistent);
		logInfoStringBuffer.append("编辑后访问策略: <br>");
		logInfoStringBuffer.append("基本信息: " + JsonUtil.toJson(persistent) + "<br>");
		logInfoStringBuffer.append("策略信息: ");
		
		// 重新添加IP
		for (AccessStrategyItem accessStrategyItem : accessStrategyItemList) {
			if (accessStrategyItem != null) {
				accessStrategyItem.setAccessStrategy(persistent);
				accessStrategyItemService.save(accessStrategyItem);
				logInfoStringBuffer.append(JsonUtil.toJson(accessStrategyItem));
			}
		}
		
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
		
		redirectUrl = "access_strategy!list.action";
		return SUCCESS;
	}

	public AccessStrategy getAccessStrategy() {
		return accessStrategy;
	}

	public void setAccessStrategy(AccessStrategy accessStrategy) {
		this.accessStrategy = accessStrategy;
	}

	public List<AccessStrategyItem> getAccessStrategyItemList() {
		return accessStrategyItemList;
	}

	public void setAccessStrategyItemList(
			List<AccessStrategyItem> accessStrategyItemList) {
		this.accessStrategyItemList = accessStrategyItemList;
	}
}
