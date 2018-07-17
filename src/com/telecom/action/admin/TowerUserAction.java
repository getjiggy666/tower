package com.telecom.action.admin;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.telecom.entity.TowerUser;
import com.telecom.service.TowerUserService;
import com.telecom.util.ImageUtil;
import com.telecom.util.TowerContentUtil;

/**
 * 后台Action类 - 用户列表
 */
@ParentPackage("admin")
public class TowerUserAction extends BaseAdminAction {

    /**
     * 用户列表
     */
    private static final long serialVersionUID = -4187072172671771346L;

    @Resource(name = "towerUserServiceImpl")
    private TowerUserService towerUserService;

    private TowerUser towerUser;

    private File icon;

    // 列表
    public String list() {
	pager = towerUserService.findPager(pager);
	return LIST;
    }

    // 添加
    public String add() {
	return INPUT;
    }

    // 保存
    @Validations(requiredStrings = { @RequiredStringValidator(fieldName = "towerUser.phone", message = "电话号不允许为空!") })
    public String save() throws Exception {
	if (icon == null) {
	    towerUser.setIconUrl(TowerContentUtil.icon_default);
	} else {
	    String iconUrl = ImageUtil.copyImageFile(getServletContext(), icon);
	    towerUser.setIconUrl(iconUrl);
	}
	if (!StringUtils.isNotBlank(towerUser.getName())) {
	    towerUser.setName(TowerContentUtil.name_default
		    + towerUser.getPhone());
	}
	towerUser.setStatus("1");
	towerUserService.save(towerUser);
	redirectUrl = "tower_user!list.action";
	return SUCCESS;
    }

    // 更新
    public String update() throws Exception {
	TowerUser persistent = towerUserService.load(id);
	persistent.setName(towerUser.getName());
	persistent.setUniversity(towerUser.getUniversity());
	System.out.println(id + ":" + towerUser.getUniversity());
	if (icon == null) {
	    persistent.setIconUrl(towerUser.getIconUrl());
	} else {
	    String iconUrl = ImageUtil.copyImageFile(getServletContext(), icon);
	    persistent.setIconUrl(iconUrl);
	}
	towerUserService.update(persistent);
	redirectUrl = "tower_user!list.action";
	return SUCCESS;
    }

    // 编辑
    public String edit() {
	towerUser = towerUserService.load(id);
	return INPUT;
    }

    public TowerUser getTowerUser() {
	return towerUser;
    }

    public void setTowerUser(TowerUser towerUser) {
	this.towerUser = towerUser;
    }

    public File getIcon() {
	return icon;
    }

    public void setIcon(File icon) {
	this.icon = icon;
    }
}
