package com.telecom.dao;

import java.util.List;

import com.telecom.entity.Department;

/**
 * Dao接口 - 部门
 */

public interface DepartmentDao extends BaseDao<Department, String> {
	
	/**
	 * 判断标识是否存在（不区分大小写）
	 * 
	 * @param sign
	 *            标识
	 * 
	 */
	public boolean isExistBySign(String sign);
	
	/**
	 * 根据标识获取部门
	 * 
	 * @param sign
	 *            标识
	 * 
	 */
	public Department getDepartmentBySign(String sign);
	
	/**
	 * 获取部门树集合
	 * 
	 * @return 部门树集合
	 * 
	 */
	public List<Department> getDepartmentTree();
	
	/**
	 * 获取部门树集合
	 * 
	 * @return 部门树集合
	 * 
	 */
	public List<Department> getDepartmentList(String type);
	
	/**
	 * 获取顶级部门集合
	 * 
	 * @param maxResults
	 *            最大结果数,null表示无限制
	 * 
	 * @return 顶级部门集合
	 * 
	 */
	public List<Department> getRootDepartmentList(Integer maxResults);
	
	/**
	 * 获取顶级部门集合
	 * 
	 * @param type
	 *            部门类别
	 *            
	 * @param maxResults
	 *            最大结果数,null表示无限制
	 * 
	 * @return 顶级部门集合
	 * 
	 */
	public List<Department> getRootDepartmentList(String type, Integer maxResults);
	
	/**
	 * 根据Department对象获取所有父类集合,若无父类则返回null
	 * 
	 * @param department
	 *            department
	 *            
	 * @param isContainParent
	 *            是否包含所有父部门
	 * 
	 * @param maxResults
	 *            最大结果数,null表示无限制
	 * 
	 * @return 父类集合
	 * 
	 */
	public List<Department> getParentDepartmentList(Department department, boolean isContainParent, Integer maxResults);
	
	/**
	 * 根据Department对象获取所有子类集合,若无子类则返回null
	 * 
	 * @param department
	 *            department
	 *            
	 * @param isContainChildren
	 *            是否包含所有子部门
	 * 
	 * @param maxResults
	 *            最大结果数,null表示无限制
	 * 
	 * @return 子类集合
	 * 
	 */
	public List<Department> getChildrenDepartmentList(Department department, boolean isContainChildren, Integer maxResults);
	
}