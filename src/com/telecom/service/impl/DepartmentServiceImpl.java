package com.telecom.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.telecom.dao.DepartmentDao;
import com.telecom.entity.Admin;
import com.telecom.entity.Department;
import com.telecom.service.DepartmentService;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springmodules.cache.annotations.Cacheable;

/**
 * Service实现类 - 部门
 */

@Service("departmentServiceImpl")
public class DepartmentServiceImpl extends BaseServiceImpl<Department, String> implements DepartmentService {

	@Resource(name = "departmentDaoImpl")
	private DepartmentDao departmentDao;
	
	@Resource(name = "departmentDaoImpl")
	public void setBaseDao(DepartmentDao departmentDao) {
		super.setBaseDao(departmentDao);
	}
	
	@Transactional(readOnly = true)
	public boolean isExistBySign(String sign) {
		return departmentDao.isExistBySign(sign);
	}
	
	@Transactional(readOnly = true)
	public boolean isUniqueBySign(String oldSign, String newSign) {
		if (StringUtils.equalsIgnoreCase(oldSign, newSign)) {
			return true;
		} else {
			if (departmentDao.isExistBySign(newSign)) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	@Transactional(readOnly = true)
	public Department getDepartmentBySign(String sign) {
		return departmentDao.getDepartmentBySign(sign);
	}
	
	@Cacheable(modelId = "departmentCaching")
	@Transactional(readOnly = true)
	public List<Department> getDepartmentTree() {
		return departmentDao.getDepartmentTree();
	}
	
	@Cacheable(modelId = "departmentCaching")
	@Transactional(readOnly = true)
	public List<Department> getDepartmentList(String type) {
		return departmentDao.getDepartmentList(type);
	}
	
	@Cacheable(modelId = "departmentCaching")
	public List<Department> getDepartmentTreeList() {
		List<Department> allDepartmentList = this.getAllList();
		return recursivDepartmentTreeList(allDepartmentList, null, null);
	}
	
	// 递归父类排序部门树
	private List<Department> recursivDepartmentTreeList(List<Department> allDepartmentList, Department p, List<Department> temp) {
		if (temp == null) {
			temp = new ArrayList<Department>();
		}
		for (Department department : allDepartmentList) {
			Department parent = department.getParent();
			if ((p == null && parent == null) || (department != null && parent == p)) {
				temp.add(department);
				if (department.getChildren() != null && department.getChildren().size() > 0) {
					recursivDepartmentTreeList(allDepartmentList, department, temp);
				}
			}
		}
		return temp;
	}
	
	@Cacheable(modelId = "departmentCaching")
	@Transactional(readOnly = true)
	public List<Department> getRootDepartmentList(Integer maxResults) {
		List<Department> rootDepartmentList = departmentDao.getRootDepartmentList(maxResults);
		if (rootDepartmentList != null) {
			for (Department rootDepartment : rootDepartmentList) {
				if (!Hibernate.isInitialized(rootDepartment)) {
					Hibernate.initialize(rootDepartment);
				}
			}
		}
		return rootDepartmentList;
	}
	
	@Cacheable(modelId = "departmentCaching")
	@Transactional(readOnly = true)
	public List<Department> getRootDepartmentList(String type, Integer maxResults) {
		List<Department> rootDepartmentList = departmentDao.getRootDepartmentList(type, maxResults);
		if (rootDepartmentList != null) {
			for (Department rootDepartment : rootDepartmentList) {
				if (!Hibernate.isInitialized(rootDepartment)) {
					Hibernate.initialize(rootDepartment);
				}
			}
		}
		return rootDepartmentList;
	}
	
	@Cacheable(modelId = "departmentCaching")
	@Transactional(readOnly = true)
	public List<Department> getParentDepartmentList(Department department, boolean isContainParent, Integer maxResults) {
		List<Department> parentDepartmentList = departmentDao.getParentDepartmentList(department, isContainParent, maxResults);
		if (parentDepartmentList != null) {
			for (Department parentDepartment : parentDepartmentList) {
				if (!Hibernate.isInitialized(parentDepartment)) {
					Hibernate.initialize(parentDepartment);
				}
			}
		}
		return parentDepartmentList;
	}
	
	public List<Department> getParentDepartmentList(Admin admin, boolean isContainParent, Integer maxResults) {
		Department department = admin.getDepartment();
		List<Department> parentDepartmentList = new ArrayList<Department>();
		List<Department> departmentList = this.getParentDepartmentList(department, isContainParent, maxResults);
		if (departmentList != null) {
			parentDepartmentList.addAll(departmentList);
		}
		parentDepartmentList.add(department);
		return parentDepartmentList;
	}
	
	@Transactional(readOnly = true)
	public List<Department> getChildrenDepartmentList(Department department, boolean isContainChildren, Integer maxResults) {
		List<Department> childrenDepartmentList = departmentDao.getChildrenDepartmentList(department, isContainChildren, maxResults);
		if (childrenDepartmentList != null) {
			for (Department childrenDepartment : childrenDepartmentList) {
				if (!Hibernate.isInitialized(childrenDepartment)) {
					Hibernate.initialize(childrenDepartment);
				}
			}
		}
		return childrenDepartmentList;
	}
	
	public List<Department> getDepartmentPathList(Department department) {
		List<Department> departmentPathList = new ArrayList<Department>();
		List<Department> parentDepartmentList = this.getParentDepartmentList(department, true, null);
		if (parentDepartmentList != null) {
			departmentPathList.addAll(parentDepartmentList);
		}
		departmentPathList.add(department);
		return departmentPathList;
	}

	@Override
	public void delete(Department department) {
		departmentDao.delete(department);
	}

	@Override
	public void delete(String id) {
		departmentDao.delete(id);
	}

	@Override
	public void delete(String[] ids) {
		departmentDao.delete(ids);
	}

	@Override
	public String save(Department department) {
		return departmentDao.save(department);
	}

	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}

}