package com.telecom.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.telecom.entity.Admin;
import com.telecom.entity.Department;
import com.telecom.service.AdminService;
import com.telecom.service.DepartmentService;
import com.telecom.util.JsonUtil;

/**
 * 后台Action类 - 部门
 */

@ParentPackage("admin")
public class DepartmentAction extends BaseAdminAction {

	private static final long serialVersionUID = 3066159260207583127L;
	private final Logger log = Logger.getLogger(getClass());
	
	private String parentId;
	private Department department;

	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	@Resource(name="adminServiceImpl")
	private AdminService adminService;
	
	// 是否已存在标识 ajax验证
	public String checkSign() {
		String oldSign = getParameter("oldValue");
		String newSign = department.getSign();
		if (departmentService.isUniqueBySign(oldSign, newSign)) {
			return ajax("true");
		} else {
			return ajax("false");
		}
	}

	// 添加
	public String add() {
		return INPUT;
	}

	// 编辑
	public String edit() {
		department = departmentService.load(id);
		return INPUT;
	}

	// 列表
	public String list() {
		return LIST;
	}
	
	// 根据部门ID获取用户
	@InputConfig(resultName = "ajaxError")
	public String ajaxAdmin() {
		List<Map<String, String>> optionList = new ArrayList<Map<String, String>>();
		if(!StringUtils.isEmpty(id)){
			Department department = departmentService.load(id);
			Set<Admin> adminSet = department.getAdminSet();
			for(Iterator<Admin> iterator = adminSet.iterator();iterator.hasNext();){
				Admin admin = iterator.next();
				Map<String, String> map = new HashMap<String, String>();
				map.put("title", admin.getName());
				map.put("value", admin.getId());
				optionList.add(map);
			}
		}else{
			List<Department> departmentList = departmentService.getDepartmentTreeList();
			for (Department department : departmentList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("title", department.getName());
				map.put("value", department.getId());
				optionList.add(map);
			}
		}
		return ajax(JsonUtil.toJson(optionList));
	}
	
	// 根据部门ID获取部门
	@InputConfig(resultName = "ajaxError")
	public String ajaxDepartment() {
		List<Department> departmentList = new ArrayList<Department>();
		List<Map<String, String>> optionList = new ArrayList<Map<String, String>>();
		if(!StringUtils.isEmpty(id)){
			Department department = departmentService.load(id);
			Set<Department> children = department.getChildren();
			if (children != null) {
				departmentList = new ArrayList<Department>(children);
			}
		}else{
			String type = "1";//支撑服务单位
			departmentList = departmentService.getRootDepartmentList(type, null);
		}
		for(Department department : departmentList){
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", department.getName());
			map.put("value", department.getId());
			optionList.add(map);
		}
		return ajax(JsonUtil.toJson(optionList));
	}
	
	// 删除
	public String delete() {
		Department department = departmentService.load(id);
		Set<Department> childrenDepartmentSet = department.getChildren();
		redirectUrl = "department!list.action";
		if (childrenDepartmentSet != null && childrenDepartmentSet.size() > 0) {
			addActionError("此部门存在下级部门,删除失败!");
			return ERROR;
		}
		Set<Admin> adminSet = department.getAdminSet();
		if (adminSet != null && adminSet.size() > 0) {
			addActionError("此部门下存在人员,删除失败!");
			return ERROR;
		}
		departmentService.delete(id);
		
		// 保存日志
		StringBuffer logInfoStringBuffer = new StringBuffer("删除部门: ");
		logInfoStringBuffer.append(JsonUtil.toJson(department));
		
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
				
		return SUCCESS;
	}

	// 保存
	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "department.name", message = "部门名称不允许为空!"),
			@RequiredStringValidator(fieldName = "department.sign", message = "标识不允许为空!")
		},
		intRangeFields = {
			@IntRangeFieldValidator(fieldName = "department.orderList", min = "0", message = "排序必须为零或正整数!")
		},
		regexFields = { 
			@RegexFieldValidator(fieldName = "department.sign", regexExpression = "^\\w+$", message = "标识只允许包含英文、数字和下划线!") 
		}
	)
	@InputConfig(resultName = "error")
	public String save() {
		if (departmentService.isExistBySign(department.getSign())) {
			addActionError("标识已存在!");
			return ERROR;
		}
		if (StringUtils.isNotEmpty(parentId)) {
			Department parent = departmentService.load(parentId);
			if(!parent.getType().equals(department.getType())){
				addActionError("类别要与上级部门一致!");
				return ERROR;
			}
			department.setParent(parent);
		} else {
			department.setParent(null);
		}
		departmentService.save(department);
		// 保存日志
		StringBuffer logInfoStringBuffer = new StringBuffer("添加部门: ");
		logInfoStringBuffer.append(JsonUtil.toJson(department));
		
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
				
		redirectUrl = "department!list.action";
		return SUCCESS;
	}

	// 更新
	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "department.name", message = "部门名称不允许为空!"),
			@RequiredStringValidator(fieldName = "department.sign", message = "标识不允许为空!")
		},
		intRangeFields = {
			@IntRangeFieldValidator(fieldName = "department.orderList", min = "0", message = "排序必须为零或正整数!")
		},
		regexFields = { 
			@RegexFieldValidator(fieldName = "department.sign", regexExpression = "^\\w+$", message = "标识只允许包含英文、数字和下划线!") 
		}
	)
	@InputConfig(resultName = "error")
	public String update() {
		Department persistent = departmentService.load(id);
		
		StringBuffer logInfoStringBuffer = new StringBuffer("编辑前部门: ");
		logInfoStringBuffer.append(JsonUtil.toJson(persistent) + "<br>");
		
		if (!departmentService.isUniqueBySign(persistent.getSign(), department.getSign())) {
			addActionError("标识已存在!");
			return ERROR;
		}
		BeanUtils.copyProperties(department, persistent, new String[]{"id", "createDate", "modifyDate", "path", "parent", "children", "activitySet"});
		departmentService.update(persistent);
		
		// 保存日志
		logInfoStringBuffer.append("编辑后用户: " + JsonUtil.toJson(persistent));
		logInfo = logInfoStringBuffer.toString();
		log.info(logInfoStringBuffer.toString());
				
		redirectUrl = "department!list.action";
		return SUCCESS;
	}
	
	// 获取部门树
	public List<Department> getDepartmentTreeList() {
		return departmentService.getDepartmentTreeList();
	}
	
	//获取用户集合
	public List<Admin> getAdminList(){
		return adminService.getAllList();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}