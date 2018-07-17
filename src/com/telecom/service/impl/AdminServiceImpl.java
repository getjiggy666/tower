package com.telecom.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.dao.AdminDao;
import com.telecom.entity.Admin;
import com.telecom.service.AdminService;

/**
 * Service实现类 - 用户
 */

@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin, String> implements AdminService {

	@Resource(name = "adminDaoImpl")
	private AdminDao adminDao;
	
	@Resource(name = "adminDaoImpl")
	public void setBaseDao(AdminDao adminDao) {
		super.setBaseDao(adminDao);
	}
	
	public Admin getLoginAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal == null || !(principal instanceof Admin)) {
			return null;
		} else {
			return (Admin) principal;
		}
	}
	
	@Transactional(readOnly = true)
	public Admin loadLoginAdmin() {
		Admin admin = getLoginAdmin();
		if (admin == null) {
			return null;
		} else {
			return adminDao.load(admin.getId());
		}
	}
	
	@Transactional(readOnly = true)
	public boolean isExistByUsername(String username) {
		return adminDao.isExistByUsername(username);
	}
	
	@Transactional(readOnly = true)
	public Admin getAdminByMd5Username(String md5Username) {
		List<Admin> adminList = adminDao.getAllList();
		for(Admin admin : adminList) {
			String usernameMd5 = DigestUtils.md5Hex(admin.getUsername());
			if(usernameMd5.equals(md5Username)) {
				return admin;
			}
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public Admin getAdminByUsername(String username) {
		return adminDao.getAdminByUsername(username);
	}
	
	@Override
	public Admin getAdminByMobile(String mobile) {
		return adminDao.getAdminByMobile(mobile);
	}

}