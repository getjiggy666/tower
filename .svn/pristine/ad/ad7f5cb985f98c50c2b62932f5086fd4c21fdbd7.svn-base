package com.telecom.service.impl;

import javax.annotation.Resource;

import com.telecom.dao.RoleDao;
import com.telecom.entity.Role;
import com.telecom.service.RoleService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 角色
 */

@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements RoleService {
	
	@Resource(name = "roleDaoImpl")
	RoleDao roleDao;

	@Resource(name = "roleDaoImpl")
	public void setBaseDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
	}

}