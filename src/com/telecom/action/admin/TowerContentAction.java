package com.telecom.action.admin;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.telecom.entity.Admin;
import com.telecom.entity.TowerContent;
import com.telecom.entity.TowerDribKind;
import com.telecom.service.AdminService;
import com.telecom.service.TowerContentService;
import com.telecom.service.TowerDribKindService;
import com.telecom.service.TowerUserService;
import com.telecom.util.ImageUtil;

/**
 * 后台Action类 - 内容列表
 */
@ParentPackage("admin")
public class TowerContentAction extends BaseAdminAction {

    /**
     * 内容列表
     */
    private static final long serialVersionUID = -3854911362905869190L;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "towerContentServiceImpl")
    private TowerContentService towerContentService;

    @Resource(name = "towerUserServiceImpl")
    private TowerUserService towerUserService;

    @Resource(name = "towerDribKindServiceImpl")
    private TowerDribKindService towerDribKindService;

    private TowerContent towerContent;

    private File img_1, img_2, img_3, video, video_img;

    private String mobile;// 发布者

    // 列表
    public String list() {
	pager = towerContentService.findPager(pager);
	return LIST;
    }

    // 添加
    public String add() {
	return INPUT;
    }

    // 保存
    @Validations(requiredStrings = {
	    @RequiredStringValidator(fieldName = "towerContent.title", message = "名称不允许为空!"),
	    @RequiredStringValidator(fieldName = "towerContent.contentType", message = "内容类型不允许为空!") })
    public String save() throws Exception {
	Admin admin = adminService.getLoginAdmin();
	String imgUrl_1 = ImageUtil.copyImageFile(getServletContext(), img_1);
	String imgUrl_2 = ImageUtil.copyImageFile(getServletContext(), img_2);
	String imgUrl_3 = ImageUtil.copyImageFile(getServletContext(), img_3);
	String videoUrl = ImageUtil.copyImageFile(getServletContext(), video);
	String videoImgUrl = ImageUtil.copyImageFile(getServletContext(),
		video_img);
	towerContent.setImgUrl_1(imgUrl_1);
	towerContent.setImgUrl_2(imgUrl_2);
	towerContent.setImgUrl_3(imgUrl_3);
	towerContent.setVideoUrl(videoUrl);
	towerContent.setVideoImgUrl(videoImgUrl);
	towerContent.setStatus("1");
	if (towerUserService.getTowerUserIdByPhone(admin.getMobile()) == null) {
	    redirectUrl = "tower_content!list.action";
	    return ERROR;
	}
	TowerDribKind towerDribKind;
	System.out.println(mobile);
	if (StringUtils.isNotBlank(mobile)
		&& towerUserService.getTowerUserIdByPhone(mobile) != null) {
	    if (towerDribKindService.isKindExist(
		    towerUserService.getTowerUserIdByPhone(mobile), "技能学习") == null) {
		towerDribKind = new TowerDribKind();
		towerDribKind.setName("技能学习");
		towerDribKind.setTowerUser(towerUserService
			.get(towerUserService.getTowerUserIdByPhone(admin
				.getMobile())));
		towerDribKindService.save(towerDribKind);
	    } else {
		towerDribKind = towerDribKindService.isKindExist(
			towerUserService.getTowerUserIdByPhone(admin
				.getMobile()), "技能学习");
	    }
	    towerContent.setTowerUser(towerUserService.get(towerUserService
		    .getTowerUserIdByPhone(mobile)));
	} else {
	    if (towerDribKindService.isKindExist(
		    towerUserService.getTowerUserIdByPhone(admin.getMobile()),
		    "技能学习") == null) {
		towerDribKind = new TowerDribKind();
		towerDribKind.setName("技能学习");
		towerDribKind.setTowerUser(towerUserService
			.get(towerUserService.getTowerUserIdByPhone(admin
				.getMobile())));
		towerDribKindService.save(towerDribKind);
	    } else {
		towerDribKind = towerDribKindService.isKindExist(
			towerUserService.getTowerUserIdByPhone(admin
				.getMobile()), "技能学习");
	    }
	    towerContent.setTowerUser(towerUserService.get(towerUserService
		    .getTowerUserIdByPhone(admin.getMobile())));
	}
	towerContent.setTowerDribKind(towerDribKind);
	System.out.println(towerContent.getTitle());
	towerContentService.save(towerContent);
	redirectUrl = "tower_content!list.action";
	return SUCCESS;
    }

    // 删除
    public String delete() throws Exception {
	towerContentService.delete(ids);
	return ajax(Status.success, "删除成功!");
    }

    public File getImg_1() {
	return img_1;
    }

    public void setImg_1(File img_1) {
	this.img_1 = img_1;
    }

    public File getImg_2() {
	return img_2;
    }

    public void setImg_2(File img_2) {
	this.img_2 = img_2;
    }

    public File getImg_3() {
	return img_3;
    }

    public void setImg_3(File img_3) {
	this.img_3 = img_3;
    }

    public File getVideo() {
	return video;
    }

    public void setVideo(File video) {
	this.video = video;
    }

    public TowerContent getTowerContent() {
	return towerContent;
    }

    public void setTowerContent(TowerContent towerContent) {
	this.towerContent = towerContent;
    }

    public File getVideo_img() {
	return video_img;
    }

    public void setVideo_img(File video_img) {
	this.video_img = video_img;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }
}
