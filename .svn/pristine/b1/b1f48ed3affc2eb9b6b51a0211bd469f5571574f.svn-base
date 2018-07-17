package com.telecom.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.telecom.dao.DepartmentDao;
import com.telecom.entity.Department;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 部门
 */

@Repository("departmentDaoImpl")
public class DepartmentDaoImpl extends BaseDaoImpl<Department, String> implements DepartmentDao {
	
	public boolean isExistBySign(String sign) {
		String hql = "from Department as department where lower(department.sign) = lower(:sign)";
		Department department = (Department) getSession().createQuery(hql).setParameter("sign", sign).uniqueResult();
		if (department != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Department getDepartmentBySign(String sign) {
		String hql = "from Department as department where lower(department.sign) = lower(:sign)";
		Department department = (Department) getSession().createQuery(hql).setParameter("sign", sign).uniqueResult();
		return department;
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> getDepartmentTree() {
		String hql = "from Department as department where department.parent is null order by department.orderList asc";
		List<Department> departmentTreeList = getSession().createQuery(hql).list();
		initializeDepartmentList(departmentTreeList);
		return departmentTreeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> getDepartmentList(String type) {
		String hql = "from Department as department where type = :type order by department.orderList asc";
		List<Department> departmentList = getSession().createQuery(hql).setParameter("type", type).list();
		initializeDepartmentList(departmentList);
		return departmentList;
	}
	
	// 递归实例化部门对象
	private void initializeDepartmentList(List<Department> departmentList) {
		for (Department department : departmentList) {
			Set<Department> children = department.getChildren();
			if (children != null && children.size() > 0) {
				if (!Hibernate.isInitialized(children)) {
					Hibernate.initialize(children);
				}
				initializeDepartmentList(new ArrayList<Department>(children));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> getRootDepartmentList(Integer maxResults) {
		String hql = "from Department as department where department.parent is null order by department.orderList asc";
		Query query = getSession().createQuery(hql);
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> getRootDepartmentList(String type, Integer maxResults) {
		String hql = "from Department as department where department.parent is null and type = :type order by department.orderList asc";
		Query query = getSession().createQuery(hql).setParameter("type", type);
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> getParentDepartmentList(Department department, boolean isContainParent, Integer maxResults) {
		Query query = null;
		if (department != null) {
			if (isContainParent) {
				if (department.getParent() == null) {
					return null;
				}
				String parentPath = StringUtils.substringBeforeLast(department.getPath(), Department.PATH_SEPARATOR);
				String[] ids = parentPath.split(Department.PATH_SEPARATOR);
				String hql = "from Department as department where department.id in(:ids) order by department.grade asc, department.orderList asc";
				query = getSession().createQuery(hql);
				query.setParameterList("ids", ids);
			} else {
				List<Department> parentDepartmentList = null;
				Department parent = department.getParent();
				if (maxResults > 0 && parent != null) {
					parentDepartmentList = new ArrayList<Department>();
					parentDepartmentList.add(parent);
				}
				return parentDepartmentList;
			}
		} else {
			String hql = "from Department as department order by department.grade asc, department.orderList asc";
			query = getSession().createQuery(hql);
		}
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> getChildrenDepartmentList(Department department, boolean isContainChildren, Integer maxResults) {
		Query query = null;
		if (department != null) {
			if (isContainChildren) {
				String hql = "from Department as department where department.path like :path order by department.grade asc, department.orderList asc";
				query = getSession().createQuery(hql);
				query.setParameter("path", department.getPath() + Department.PATH_SEPARATOR + "%");
			} else {
				String hql = "from Department as department where department.parent = :department order by department.grade asc, department.orderList asc";
				query = getSession().createQuery(hql);
				query.setParameter("department", department);
			}
		} else {
			String hql = "from Department as department order by department.grade asc, department.orderList asc";
			query = getSession().createQuery(hql);
		}
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		return query.list();
	}
	
	// 自动设置path、grade
	@Override
	public String save(Department department) {
		department.setPath(department.getName());
		department.setGrade(0);
		super.save(department);
		fillDepartment(department);
		super.update(department);
		return department.getId();
	}
	
	// 自动设置path、grade
	@Override
	public void update(Department department) {
		fillDepartment(department);
		super.update(department);
		List<Department> childrenDepartmentList = getChildrenDepartmentList(department, true, null);
		if (childrenDepartmentList != null) {
			for (int i = 0; i < childrenDepartmentList.size(); i ++) {
				Department childrenCategory = childrenDepartmentList.get(i);
				fillDepartment(childrenCategory);
				super.update(childrenCategory);
				if(i % 20 == 0) {
					flush();
					clear();
				}
			}
		}
	}
	
	private void fillDepartment(Department department) {
		Department parent = department.getParent();
		if (parent == null) {
			department.setPath(department.getId());
		} else {
			department.setPath(parent.getPath() + Department.PATH_SEPARATOR + department.getId());
		}
		department.setGrade(department.getPath().split(Department.PATH_SEPARATOR).length - 1);
	}

}