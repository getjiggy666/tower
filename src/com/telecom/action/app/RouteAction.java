package com.telecom.action.app;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.oscache.util.StringUtil;
import com.telecom.entity.Route;
import com.telecom.entity.RouteCreatorRecord;
import com.telecom.entity.RouteEntrantsRecord;
import com.telecom.entity.TowerUser;
import com.telecom.service.RouteCreatorRecordService;
import com.telecom.service.RouteEntrantsRecordService;
import com.telecom.service.RouteService;
import com.telecom.service.TowerUserService;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.telecom.util.CommonUtil;
import com.telecom.util.HttpUtils;
import com.telecom.util.ImageUtil;
import com.telecom.util.JsonUtil;
import com.telecom.util.SettingUtil;

@ParentPackage("app")
public class RouteAction extends BaseAppAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6588944346702629607L;

    @Resource(name = "routeServiceImpl")
    private RouteService routeService;

    @Resource(name = "towerUserServiceImpl")
    private TowerUserService towerUserService;

    @Resource(name = "routeCreatorRecordServiceImpl")
    private RouteCreatorRecordService routeCreatorRecordService;

    @Resource(name = "routeEntrantsRecordServiceImpl")
    private RouteEntrantsRecordService routeEntrantsRecordService;

    // private File FileData;// 照片文件
    private String FileDataFileName;// 照片名称

    private String routeId;// 路线id
    private String routeCreator;// 路线发起者手机号
    private String routeEntrants;// 路线参与者
    private String routeCreatorRecordId;// 路线创建记录
    private String scenicSpotId;// 景点id
    private String latitude;// 景点id
    private String longitude;// 景点id
    private String imageData;
    private String ext;

    public String index() {
	return "index";
    }

    /**
     * 获取路线
     * 
     * @author George
     * @throws Exception
     */
    public String getroute() {
	Map<String, Object> map = new HashMap<String, Object>();
	List<Route> routeList = routeService.getAllList();
	if (routeList != null && routeList.size() != 0) {
	    map.put("status", "true");
	    map.put("routeList", routeList);
	    map.put("msg", "获取成功");
	} else {
	    map.put("status", "false");
	    map.put("msg", "获取失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 查看路线详情(非必须)
     * 
     * @param routeId
     *            :路线id
     * @author George
     * @throws Exception
     */

    public String getroute_detail() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (routeId != null) {
	    map.put("status", "true");
	    map.put("scenicSpotList", routeService.getScenicSpotList(routeId));
	    map.put("msg", "获取成功");
	} else {
	    map.put("status", "false");
	    map.put("msg", "获取失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 发起路线
     * 
     * @param routeId
     *            :路线id
     * @param routeCreator
     *            :创建者/电话
     * @author George
     * @throws Exception
     */
    public String create_route() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (routeId != null && routeCreator != null) {
	    String towerUserId = towerUserService
		    .getTowerUserIdByPhone(routeCreator);
	    if (StringUtil.isEmpty(towerUserId)) {
		TowerUser tU = new TowerUser();
		tU.setPhone(routeCreator);
		towerUserService.save(tU);
	    }
	    RouteCreatorRecord rcr = new RouteCreatorRecord();
	    rcr.setRoute(routeId);
	    rcr.setRouteCreator(towerUserService
		    .getTowerUserIdByPhone(routeCreator));
	    rcr.setStatus("0");
	    routeCreatorRecordService.save(rcr);
	    map.put("status", "true");
	    map.put("routeCreatorRecordId", rcr.getId());
	    // map.put("scenicSpotList",
	    // routeService.getScenicSpotList(routeId));
	    map.put("msg", "发起成功");
	} else {
	    map.put("status", "false");
	    map.put("msg", "发起失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 参与路线
     * 
     * @param routeCreatorRecordId
     *            :路线创建记录id
     * @param routeEntrants
     *            :路线参与者
     * @author George
     * @throws Exception
     */
    public String entrants_route() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (!StringUtil.isEmpty(routeCreatorRecordId)) {
	    map.put("scenicSpotList",
		    routeService.getEntrantsRouteList(routeCreatorRecordId));
	    map.put("status", "true");
	    map.put("msg", "获取成功");
	} else {
	    map.put("status", "false");
	    map.put("msg", "获取失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 拍照图片
     * 
     * @param routeCreatorRecordId
     *            :路线创建记录id
     * @param routeEntrants
     *            :路线参与者
     * @param scenicSpotId
     *            :景点id
     * @param latitude
     *            :纬度
     * @param longitude
     *            :经度
     * @param FileData
     *            文件
     * @param FileDataFileName
     *            文件名称
     * @author George
     * @throws Exception
     */
    public String img_upload() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (!StringUtil.isEmpty(routeCreatorRecordId)
		&& !StringUtil.isEmpty(routeEntrants)
		&& !StringUtil.isEmpty(scenicSpotId)
		&& !StringUtil.isEmpty(latitude)
		&& !StringUtil.isEmpty(longitude)) {
	    if (routeService.regionBoolean(scenicSpotId, latitude, longitude)) {
		System.out.println("RouteAction---" + FileDataFileName + "---"
			+ routeCreatorRecordId);
		try {
		    if (!(StringUtils.isNotBlank(imageData) && StringUtils
			    .isNotBlank(ext))) {
			System.out.println("照片上传失败");
			map.put("status", "false");
			map.put("msg", "照片上传失败");
			return output(JsonUtil.toJson(map));
		    }
		    // String uploadFileName = FileOperateUtil.copyFile(
		    // getServletContext(), FileData, FileDataFileName);
		    // String img_url = "upload/img/" + uploadFileName;
		    String fileName = "img_"
			    + new SimpleDateFormat("yyyyMMddHHmmss")
				    .format(new Date())
			    + CommonUtil.getRandomNumber(4) + ext;
		    String basePath = CommonUtil.getWebRootPath();
		    String filePath = SettingUtil.getSetting()
			    .getImageUploadPath() + File.separator + fileName;
		    if (!ImageUtil
			    .GenerateImage(imageData, basePath + filePath)) {
			System.out.println("照片上传失败");
			map.put("status", "false");
			map.put("msg", "照片上传失败");
			return output(JsonUtil.toJson(map));
		    }
		    if (routeService.isUploadRepeat(routeCreatorRecordId,
			    routeEntrants)) {
			RouteEntrantsRecord rer = new RouteEntrantsRecord();
			rer.setImgUrl(filePath);
			rer.setRouteCreatorRecord(routeCreatorRecordId);
			rer.setRouteEntrants(routeEntrants);
			rer.setScenicSpot(scenicSpotId);
			routeEntrantsRecordService.save(rer);
			map.put("status", "true");
			map.put("msg", "照片上传成功");
			map.put("filePath", filePath);
			return output(JsonUtil.toJson(map));
		    } else {
			map.put("status", "false");
			map.put("msg", "照片重复");
		    }
		} catch (Exception e) {
		    map.put("status", "false");
		    map.put("msg", "照片上传失败");
		    e.printStackTrace();
		}
	    } else {
		map.put("status", "false");
		map.put("msg", "不在景点范围内");
	    }

	} else {
	    map.put("status", "false");
	    map.put("msg", "获取失败");
	}
	map.put("DESC", "距离景点不得超过1000米");
	return output(JsonUtil.toJson(map));
    }

    public String getRouteId() {
	return routeId;
    }

    public void setRouteId(String routeId) {
	this.routeId = routeId;
    }

    public String getRouteCreator() {
	return routeCreator;
    }

    public void setRouteCreator(String routeCreator) {
	this.routeCreator = routeCreator;
    }

    public String getRouteEntrants() {
	return routeEntrants;
    }

    public void setRouteEntrants(String routeEntrants) {
	this.routeEntrants = routeEntrants;
    }

    public String getRouteCreatorRecordId() {
	return routeCreatorRecordId;
    }

    public void setRouteCreatorRecordId(String routeCreatorRecordId) {
	this.routeCreatorRecordId = routeCreatorRecordId;
    }

    public String getScenicSpotId() {
	return scenicSpotId;
    }

    public void setScenicSpotId(String scenicSpotId) {
	this.scenicSpotId = scenicSpotId;
    }

    public String getLatitude() {
	return latitude;
    }

    public void setLatitude(String latitude) {
	this.latitude = latitude;
    }

    public String getLongitude() {
	return longitude;
    }

    public void setLongitude(String longitude) {
	this.longitude = longitude;
    }

    public String getImageData() {
	return imageData;
    }

    public void setImageData(String imageData) {
	this.imageData = imageData;
    }

    public String getExt() {
	return ext;
    }

    public void setExt(String ext) {
	this.ext = ext;
    }

    public static void main(String args[]) {
	// double x = 25984;
	// double y = 1144.19;
	// for(int i = 0;i<100;i++){
	// if((x+352*i)-y*i<0){
	// System.out.println(i);
	// break;
	// }
	// }
	String host = "http://dingxin.market.alicloudapi.com";
	String path = "/dx/sendSms";
	String method = "POST";
	String appcode = "deed2b6e7aa244819f8fae7c5087e76d";
	Map<String, String> headers = new HashMap<String, String>();
	// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
	// 83359fd73fe94948385f570e3c139105
	headers.put("Authorization", "APPCODE " + appcode);
	Map<String, String> querys = new HashMap<String, String>();
	querys.put("mobile", "17702726786");
	querys.put("param", "code:1234");
	querys.put("tpl_id", "TP1711063");
	Map<String, String> bodys = new HashMap<String, String>();

	try {
	    /**
	     * 重要提示如下: HttpUtils请从
	     * https://github.com/aliyun/api-gateway-demo-sign
	     * -java/blob/master/src
	     * /main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java 下载
	     * 
	     * 相应的依赖请参照
	     * https://github.com/aliyun/api-gateway-demo-sign-java/blob
	     * /master/pom.xml
	     */
	    HttpResponse response = HttpUtils.doPost(host, path, method,
		    headers, querys, bodys);
	    System.out.println(response.toString());
	    // 获取response的body
	    System.out.println(EntityUtils.toString(response.getEntity()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
