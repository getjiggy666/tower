package com.telecom.action.admin;

import java.io.File;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.telecom.entity.ScenicSpot;
import com.telecom.service.RouteService;
import com.telecom.service.ScenicSpotService;
import com.telecom.util.ImageUtil;

/**
 * 后台Action类 - 景点
 */

@ParentPackage("admin")
public class ScenicSpotAction extends BaseAdminAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7203820273583851616L;

	private ScenicSpot scenicSpot;

	@Resource(name = "scenicSpotServiceImpl")
	private ScenicSpotService scenicSpotService;

	@Resource(name = "routeServiceImpl")
	private RouteService routeService;

	// 景点图片
	private File scenicImgFile;

	// 列表
	public String list() {
		pager = scenicSpotService.findPager(pager);
		return LIST;
	}

	// 编辑
	public String edit() {
		scenicSpot = scenicSpotService.load(id);
		return INPUT;
	}

	// 更新
	public String update() throws Exception {
		ScenicSpot persistent = scenicSpotService.load(id);
		String scenicImg = ImageUtil.copyImageFile(getServletContext(),
				scenicImgFile);
		System.out.println(persistent.getScenicName() + "------" + scenicImg);
		if (scenicImgFile != null) {
			persistent.setScenicImg(scenicImg);
		}
		scenicSpotService.update(persistent);
		redirectUrl = "scenic_spot!list.action";
		return SUCCESS;
	}

	public File getScenicImgFile() {
		return scenicImgFile;
	}

	public void setScenicImgFile(File scenicImgFile) {
		this.scenicImgFile = scenicImgFile;
	}

	public ScenicSpot getScenicSpot() {
		return scenicSpot;
	}

	public void setScenicSpot(ScenicSpot scenicSpot) {
		this.scenicSpot = scenicSpot;
	}

}
