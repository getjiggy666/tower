package com.telecom.action.app;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.json.JSONObject;

import com.opensymphony.oscache.util.StringUtil;
import com.telecom.bean.TowerCommentBean;
import com.telecom.bean.TowerContentBean;
import com.telecom.bean.TowerDribKindBean;
import com.telecom.bean.TowerUserBean;
import com.telecom.entity.TowerCollection;
import com.telecom.entity.TowerComment;
import com.telecom.entity.TowerContent;
import com.telecom.entity.TowerDribKind;
import com.telecom.entity.TowerFollow;
import com.telecom.entity.TowerPraise;
import com.telecom.entity.TowerUser;
import com.telecom.service.TowerCollectionService;
import com.telecom.service.TowerCommentService;
import com.telecom.service.TowerContentService;
import com.telecom.service.TowerDribKindService;
import com.telecom.service.TowerFollowService;
import com.telecom.service.TowerPraiseService;
import com.telecom.service.TowerUserService;
import com.telecom.util.CommonUtil;
import com.telecom.util.TowerContentUtil;
import com.telecom.util.HttpUtils;
import com.telecom.util.ImageUtil;
import com.telecom.util.JsonUtil;
import com.telecom.util.SettingUtil;

@ParentPackage("app")
public class AccountAction extends BaseAppAction {

    /**
     * 账号管理
     */
    private static final long serialVersionUID = -3862978363424344845L;

    @Resource(name = "towerUserServiceImpl")
    private TowerUserService towerUserService;

    @Resource(name = "towerContentServiceImpl")
    private TowerContentService towerContentService;

    @Resource(name = "towerCollectionServiceImpl")
    private TowerCollectionService towerCollectionService;

    @Resource(name = "towerFollowServiceImpl")
    private TowerFollowService towerFollowService;

    @Resource(name = "towerCommentServiceImpl")
    private TowerCommentService towerCommentService;

    @Resource(name = "towerPraiseServiceImpl")
    private TowerPraiseService towerPraiseService;

    @Resource(name = "towerDribKindServiceImpl")
    private TowerDribKindService towerDribKindService;

    private String phone;// 用户手机号
    private String verification;// 用户验证码
    private String password;// 登录密码
    private String towerUserId;// 用户唯一id
    private String towerContentId;// 文章id
    private String towerUserId_star;// 被关注者用户id
    private String towerUserId_fans;// 关注者用户id
    private String dribKindId;// 点滴分类id
    private String dribKindName;// 点滴分类命名
    private String dribKindDetail;// 点滴描述
    private String contentType;// 内容类型
    private String pageNum; // 页面请求页码，每页10个

    private File img_1, img_2, img_3, video, icon;// 上传图片和视频

    private String title, content, kind;// 上传标题内容
    private String keyword; // 搜索关键字
    private String video_img; // 视频第一帧

    // 完善个人资料
    private String towerUserName, towerUsersex, towerUserUniversity,
	    towerUserBirthday, towerUserCompany, towerUserPosition,
	    towerUserIntroduce;

    private String greatManType; // 0企业大咖,1学生大牛
    private String comment;// 评论内容

    /**
     * 获取验证码
     * 
     * @author George
     * @throws Exception
     * @date 20180522
     */
    public String getverification() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(phone) || phone.length() != 11) {
	    map.put("returnStatus", "false");
	    map.put("msg", "电话号码位数错误");
	    return output(JsonUtil.toJson(map));
	}
	if (towerUserService.getTowerUserIdByPhone(phone) != null) {
	    map.put("returnStatus", "false");
	    map.put("msg", "该电话号已注册");
	    return output(JsonUtil.toJson(map));
	}
	String host = TowerContentUtil.host;
	String path = TowerContentUtil.path;
	String method = TowerContentUtil.method;
	String appcode = TowerContentUtil.appcode;
	String tpl_id = TowerContentUtil.tpl_id;
	Map<String, String> headers = new HashMap<String, String>();
	// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
	// 83359fd73fe94948385f570e3c139105
	headers.put("Authorization", "APPCODE " + appcode);
	Map<String, String> querys = new HashMap<String, String>();
	String codeString = String
		.valueOf((int) (Math.random() * (9999 - 1000 + 1)) + 1000);
	querys.put("mobile", phone);
	querys.put("param", "code:" + codeString);
	querys.put("tpl_id", tpl_id);
	Map<String, String> bodys = new HashMap<String, String>();
	HttpResponse response = null;
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
	    response = HttpUtils.doPost(host, path, method, headers, querys,
		    bodys);
	    // 获取response的body
	    // System.out.println(EntityUtils.toString(response.getEntity()));
	    JSONObject result = new JSONObject(EntityUtils.toString(response
		    .getEntity())); // Convert
				    // String
				    // to
				    // JSON
				    // Object
	    String token = result.getString("return_code");
	    System.out.println(token);
	    if (token.equals("00000")) {
		TowerUser tu = new TowerUser();
		tu.setPhone(phone);
		tu.setVerification(codeString);
		tu.setStatus("0");
		towerUserService.save(tu);
		map.put("returnStatus", "true");
		map.put("msg", "获取验证码成功");
	    } else {
		map.put("returnStatus", "false");
		map.put("msg", "获取验证码失败");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    map.put("returnStatus", "false");
	    map.put("msg", "获取验证码失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 注册
     * 
     * @author George
     * @throws Exception
     * @date 20180522
     */
    public String register() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(phone) || phone.length() != 11
		|| StringUtil.isEmpty(verification)
		|| StringUtil.isEmpty(password)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	TowerUser tu = towerUserService.get(towerUserService
		.getTowerUserIdByPhone(phone));
	if (tu == null) {
	    map.put("returnStatus", "false");
	    map.put("msg", "验证失败");
	    return output(JsonUtil.toJson(map));
	}
	if (tu.getVerification().equals(verification)) {
	    map.put("returnStatus", "true");
	    map.put("msg", "注册成功");
	    tu.setPassword(password);
	    tu.setIconUrl(TowerContentUtil.icon_default);
	    tu.setName(TowerContentUtil.name_default + phone);
	    if (!StringUtil.isEmpty(greatManType) && greatManType.equals("1")) {
		tu.setCategory("1");
	    } else {
		tu.setCategory("0");
	    }
	    tu.setStatus("1");
	    towerUserService.update(tu);
	} else {
	    map.put("returnStatus", "false");
	    map.put("msg", "验证码错误");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 登录
     * 
     * @author George
     * @throws Exception
     * @date 20180607
     */
    public String login() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(phone) || phone.length() != 11
		|| StringUtil.isEmpty(password)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	TowerUser tu = towerUserService.get(towerUserService
		.getTowerUserIdByPhone(phone));
	if (towerUserService.login(phone, password)) {
	    map.put("towerUserId", tu.getId());
	    map.put("name", tu.getName());
	    map.put("icon",
		    SettingUtil.getSetting().getUpFilePath() + tu.getIconUrl());
	    if (towerDribKindService.isKindExist(tu.getId(), "基本信息") == null) {
		TowerDribKind towerDribKind1 = new TowerDribKind();
		towerDribKind1.setName("基本信息");
		towerDribKind1.setTowerUser(tu);
		towerDribKind1.setKindType("0");
		towerDribKindService.save(towerDribKind1);
		TowerDribKind towerDribKind2 = new TowerDribKind();
		towerDribKind2.setName("教育经历");
		towerDribKind2.setTowerUser(tu);
		towerDribKind2.setKindType("0");
		towerDribKindService.save(towerDribKind2);
		TowerDribKind towerDribKind3 = new TowerDribKind();
		towerDribKind3.setName("学业成绩");
		towerDribKind3.setTowerUser(tu);
		towerDribKind3.setKindType("0");
		towerDribKindService.save(towerDribKind3);
		TowerDribKind towerDribKind4 = new TowerDribKind();
		towerDribKind4.setName("成果证书");
		towerDribKind4.setTowerUser(tu);
		towerDribKind4.setKindType("0");
		towerDribKindService.save(towerDribKind4);
		TowerDribKind towerDribKind5 = new TowerDribKind();
		towerDribKind5.setName("技能拓展");
		towerDribKind5.setTowerUser(tu);
		towerDribKind5.setKindType("0");
		towerDribKindService.save(towerDribKind5);
		TowerDribKind towerDribKind6 = new TowerDribKind();
		towerDribKind6.setName("学习能力");
		towerDribKind6.setTowerUser(tu);
		towerDribKind6.setKindType("0");
		towerDribKindService.save(towerDribKind6);
		TowerDribKind towerDribKind7 = new TowerDribKind();
		towerDribKind7.setName("实践能力");
		towerDribKind7.setTowerUser(tu);
		towerDribKind7.setKindType("0");
		towerDribKindService.save(towerDribKind7);
		TowerDribKind towerDribKind8 = new TowerDribKind();
		towerDribKind8.setName("兴趣爱好");
		towerDribKind8.setTowerUser(tu);
		towerDribKind8.setKindType("0");
		towerDribKindService.save(towerDribKind8);
		TowerDribKind towerDribKind9 = new TowerDribKind();
		towerDribKind9.setName("工作履历");
		towerDribKind9.setTowerUser(tu);
		towerDribKind9.setKindType("0");
		towerDribKindService.save(towerDribKind9);
		TowerDribKind towerDribKind10 = new TowerDribKind();
		towerDribKind10.setName("个人评价");
		towerDribKind10.setTowerUser(tu);
		towerDribKind10.setKindType("0");
		towerDribKindService.save(towerDribKind10);
	    }
	    // map.put("followNum", towerUserService.getFollowNum(tu.getId()));
	    // map.put("fansNum", towerUserService.getFansNum(tu.getId()));
	    map.put("returnStatus", "true");
	    map.put("msg", "登录成功");
	} else {
	    map.put("returnStatus", "false");
	    map.put("msg", "登录失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取"我的"页面，关注和粉丝数目
     * 
     * @author George
     * @throws Exception
     * @date 20180705
     */
    public String getmine() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	map.put("followNum", towerUserService.getFollowNum(towerUserId));
	map.put("fansNum", towerUserService.getFansNum(towerUserId));
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取"我的"关注列表
     * 
     * @author George
     * @throws Exception
     * @date 20180705
     */
    public String getfollow() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	map.put("followList", towerUserService.getFollow(towerUserId));
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取"我的"粉丝列表
     * 
     * @author George
     * @throws Exception
     * @date 20180705
     */
    public String getfans() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	map.put("fansList", towerUserService.getFans(towerUserId));
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取个人信息详情
     * 
     * @author George
     * @throws Exception
     * @date 20180703
     */
    public String getpersonaldetails() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少用户id");
	    return output(JsonUtil.toJson(map));
	}
	TowerUser tu = towerUserService.get(towerUserId);
	map.put("name", tu.getName());
	map.put("icon",
		SettingUtil.getSetting().getUpFilePath() + tu.getIconUrl());
	map.put("sex", tu.getSex());
	map.put("introduce", tu.getIntroduce());
	map.put("company", tu.getCompany());
	map.put("university", tu.getUniversity());
	map.put("category", tu.getCategory());
	map.put("position", tu.getPosition());
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取列表
     * 
     * @author George
     * @throws Exception
     * @date 20180606
     */
    public String getcontentlist() {
	Map<String, Object> map = new HashMap<String, Object>();
	List<TowerContentBean> list;
	if (StringUtil.isEmpty(towerUserId)) {
	    list = towerContentService.getList(pageNum);
	} else {
	    list = towerContentService.getAllListByUser(towerUserId, pageNum);
	}
	map.put("contentList", list);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 收藏
     * 
     * @author George
     * @throws Exception
     * @date 20180608
     */
    public String collection() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)
		|| StringUtil.isEmpty(towerContentId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	// 清空一次数据
	towerContentService.no_collection(towerContentId, towerUserId);
	// 重新添加
	TowerCollection towerCollection = new TowerCollection();
	towerCollection
		.setTowerContent(towerContentService.get(towerContentId));
	towerCollection.setTowerUser(towerUserService.get(towerUserId));
	towerCollectionService.save(towerCollection);
	map.put("returnStatus", "true");
	map.put("msg", "收藏成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 取消收藏
     * 
     * @author George
     * @throws Exception
     * @date 20180608
     */
    public String no_collection() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)
		|| StringUtil.isEmpty(towerContentId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	if (towerContentService.no_collection(towerContentId, towerUserId)) {
	    map.put("returnStatus", "true");
	    map.put("msg", "取消收藏成功");
	} else {
	    map.put("returnStatus", "false");
	    map.put("msg", "取消收藏失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 关注(因前段改起来麻烦,暂定fans为被关注者,仅限此处)
     * 
     * @author George
     * @throws Exception
     * @date 20180707
     */
    public String follow() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId_fans)
		|| StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	// 清空一次数据
	towerContentService.no_follow(towerUserId_fans, towerUserId);
	// 重新添加
	TowerFollow towerFollow = new TowerFollow();
	towerFollow.setStar(towerUserService.get(towerUserId_fans));
	towerFollow.setFans(towerUserService.get(towerUserId));
	towerFollowService.save(towerFollow);
	map.put("returnStatus", "true");
	map.put("msg", "关注成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 取消关注(因前段改起来麻烦,暂定fans为被关注者,仅限此处)
     * 
     * @author George
     * @throws Exception
     * @date 20180707
     */
    public String no_follow() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)
		|| StringUtil.isEmpty(towerUserId_fans)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	towerContentService.no_follow(towerUserId_fans, towerUserId);
	map.put("returnStatus", "true");
	map.put("msg", "取消关注成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 点赞
     * 
     * @author George
     * @throws Exception
     * @date 20180629
     */
    public String praise() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)
		|| StringUtil.isEmpty(towerContentId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	// 清空一次数据
	towerContentService.no_praise(towerContentId, towerUserId);
	// 重新添加
	TowerPraise towerPraise = new TowerPraise();
	towerPraise.setTowerContent(towerContentService.get(towerContentId));
	towerPraise.setTowerUser(towerUserService.get(towerUserId));
	towerPraiseService.save(towerPraise);
	map.put("returnStatus", "true");
	map.put("msg", "点赞成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 取消点赞
     * 
     * @author George
     * @throws Exception
     * @date 20180629
     */
    public String no_praise() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)
		|| StringUtil.isEmpty(towerContentId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	if (towerContentService.no_praise(towerContentId, towerUserId)) {
	    map.put("returnStatus", "true");
	    map.put("msg", "取消点赞成功");
	} else {
	    map.put("returnStatus", "false");
	    map.put("msg", "取消点赞失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 新增分类
     * 
     * @author George
     * @throws Exception
     * @date 20180707
     */
    public String adddribkind() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtils.isBlank(towerUserId)
		|| StringUtils.isBlank(dribKindName)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	// 内容分类
	if (towerDribKindService.isKindExist(towerUserId, dribKindName) == null) {
	    TowerDribKind towerDribKind = new TowerDribKind();
	    towerDribKind.setName(dribKindName);
	    towerDribKind.setTowerUser(towerUserService.get(towerUserId));
	    towerDribKind.setKindType("1");
	    towerDribKindService.save(towerDribKind);
	    map.put("dribKindId", towerDribKind.getId());
	    map.put("returnStatus", "true");
	    map.put("msg", "添加分类成功");
	} else {
	    map.put("dribKindId",
		    towerDribKindService.isKindExist(towerUserId, dribKindName)
			    .getId());
	    map.put("returnStatus", "true");
	    map.put("msg", "该分类已存在");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 调整分类
     * 
     * @author George
     * @throws Exception
     * @date 20180707
     */
    public String modifydribkind() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtils.isBlank(towerContentId)
		|| StringUtils.isBlank(dribKindId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	if (towerContentService.modifyDribKind(towerContentId, dribKindId)) {
	    map.put("returnStatus", "true");
	    map.put("msg", "修改成功");
	} else {
	    map.put("returnStatus", "false");
	    map.put("msg", "修改失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 上传内容
     * 
     * @author George
     * @throws Exception
     * @date 20180612
     */
    public String upload() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (!StringUtil.isEmpty(towerUserId)
		&& towerUserService.get(towerUserId) != null) {
	    if (StringUtil.isEmpty(title) && StringUtil.isEmpty(content)
		    && img_1 == null && img_2 == null && img_3 == null
		    && video == null) {
		map.put("returnStatus", "false");
		map.put("msg", "上传内容为空");
		return output(JsonUtil.toJson(map));
	    }
	    String msg = null;// 接口返回信息
	    TowerContent towerContent = new TowerContent();
	    towerContent.setTowerUser(towerUserService.get(towerUserId));
	    towerContent.setContentType("0");
	    // 标题
	    if (!StringUtil.isEmpty(title)) {
		towerContent.setTitle(title);
	    }
	    // 内容
	    if (!StringUtil.isEmpty(content)) {
		towerContent.setContent(content);
	    }
	    // 内容分类
	    // if (!StringUtil.isEmpty(kind)) {
	    // towerContent.setContent(kind);
	    // }
	    // 内容分类
	    if (!StringUtil.isEmpty(dribKindName)) {
		if (towerDribKindService.isKindExist(towerUserId, dribKindName) == null) {
		    TowerDribKind towerDribKind = new TowerDribKind();
		    towerDribKind.setName(dribKindName);
		    towerDribKind.setTowerUser(towerUserService
			    .get(towerUserId));
		    towerDribKind.setKindType("1");
		    towerDribKindService.save(towerDribKind);
		    towerContent.setTowerDribKind(towerDribKind);
		} else {
		    towerContent.setTowerDribKind(towerDribKindService
			    .isKindExist(towerUserId, dribKindName));
		}
	    }

	    // 图片1
	    try {
		if (img_1 == null) {
		    System.out.println("AccountAction:图片1为空");
		    msg = "图片1为空+";
		} else {
		    String imgUrl_1 = ImageUtil.copyImageFile(
			    getServletContext(), img_1);
		    // String fileName_1 = "img_"
		    // + new SimpleDateFormat("yyyyMMddHHmmss")
		    // .format(new Date())
		    // + CommonUtil.getRandomNumber(4);
		    // System.out.println("AccountAction:" + fileName_1 + "+++"
		    // + towerUserId);
		    // String uploadFileName = FileOperateUtil.copyFile(
		    // getServletContext(), img_1, fileName_1);
		    // String imgUrl_1 = "upload/img/" + uploadFileName;
		    towerContent.setImgUrl_1(imgUrl_1);
		    towerContent.setContentType("1");
		}

	    } catch (Exception e) {
		System.out.println("图片1上传失败");
		e.printStackTrace();
	    }

	    // 图片2
	    try {
		if (img_2 == null) {
		    System.out.println("AccountAction:图片2为空");
		    msg = msg + "图片2为空+";
		} else {
		    String imgUrl_2 = ImageUtil.copyImageFile(
			    getServletContext(), img_2);
		    // String fileName_2 = "img_"
		    // + new SimpleDateFormat("yyyyMMddHHmmss")
		    // .format(new Date())
		    // + CommonUtil.getRandomNumber(4);
		    // System.out.println("AccountAction:" + fileName_2 + "+++"
		    // + towerUserId);
		    // String uploadFileName = FileOperateUtil.copyFile(
		    // getServletContext(), img_2, fileName_2);
		    // String imgUrl_2 = "upload/img/" + uploadFileName;
		    towerContent.setImgUrl_2(imgUrl_2);
		    towerContent.setContentType("1");
		}

	    } catch (Exception e) {
		System.out.println("图片2上传失败");
		e.printStackTrace();
	    }

	    // 图片3
	    try {
		if (img_3 == null) {
		    System.out.println("AccountAction:图片3为空");
		    msg = msg + "图片3为空+";
		} else {
		    String imgUrl_3 = ImageUtil.copyImageFile(
			    getServletContext(), img_3);
		    // String fileName_3 = "img_"
		    // + new SimpleDateFormat("yyyyMMddHHmmss")
		    // .format(new Date())
		    // + CommonUtil.getRandomNumber(4);
		    // System.out.println("AccountAction:" + fileName_3 + "+++"
		    // + towerUserId);
		    // String uploadFileName = FileOperateUtil.copyFile(
		    // getServletContext(), img_3, fileName_3);
		    // String imgUrl_3 = "upload/img/" + uploadFileName;
		    towerContent.setImgUrl_3(imgUrl_3);
		    towerContent.setContentType("1");
		}

	    } catch (Exception e) {
		System.out.println("图片3上传失败");
		e.printStackTrace();
	    }

	    // 视频第一帧
	    try {
		if (!(StringUtils.isNotBlank(video_img))) {
		    System.out.println("AccountAction:视频第一帧为空");
		    msg = msg + "视频第一帧为空";
		} else {
		    String fileName_videoImg = "videoimg_"
			    + new SimpleDateFormat("yyyyMMddHHmmss")
				    .format(new Date())
			    + CommonUtil.getRandomNumber(4);
		    System.out.println("AccountAction:" + fileName_videoImg
			    + "+++" + towerUserId);
		    String videoImgFilePath = SettingUtil.getSetting()
			    .getImageUploadRealPath()
			    + File.separator
			    + fileName_videoImg;
		    if (!ImageUtil.GenerateImage(video_img, SettingUtil
			    .getSetting().getUpFilePath() + videoImgFilePath)) {
			System.out.println("视频第一帧失败"
				+ SettingUtil.getSetting().getUpFilePath()
				+ videoImgFilePath);
			map.put("status", "false");
			map.put("msg", "视频第一帧失败");
			return output(JsonUtil.toJson(map));
		    }
		    towerContent.setVideoImgUrl(videoImgFilePath);
		}

	    } catch (Exception e) {
		System.out.println("视频第一帧失败");
		e.printStackTrace();
	    }

	    // 视频
	    try {
		if (video == null) {
		    System.out.println("AccountAction:视频为空");
		    msg = msg + "视频为空";
		} else {
		    String videoUrl = ImageUtil.copyImageFile(
			    getServletContext(), video);
		    // String fileName_video = "video_"
		    // + new SimpleDateFormat("yyyyMMddHHmmss")
		    // .format(new Date())
		    // + CommonUtil.getRandomNumber(4);
		    // System.out.println("AccountAction:" + fileName_video
		    // + "+++" + towerUserId);
		    // String uploadFileName = FileOperateUtil.copyFile(
		    // getServletContext(), video, fileName_video);
		    // String videoUrl = "upload/img/" + uploadFileName;
		    towerContent.setVideoUrl(videoUrl);
		    towerContent.setContentType("2");
		}

	    } catch (Exception e) {
		System.out.println("视频上传失败");
		e.printStackTrace();
	    }
	    towerContent.setStatus("1");
	    towerContentService.save(towerContent);
	    map.put("returnStatus", "true");
	    map.put("msg", msg + "+++上传成功");
	} else {
	    map.put("returnStatus", "false");
	    map.put("msg", "上传失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 搜索
     * 
     * @author George
     * @throws Exception
     * @date 20180624
     */
    public String search() {
	Map<String, Object> map = new HashMap<String, Object>();
	List<TowerContentBean> list = towerContentService.search(towerUserId,
		keyword);
	map.put("contentList", list);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取点滴
     * 
     * @author George
     * @throws Exception
     * @date 20180628
     */
    public String getdrib() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "未输入用户id");
	    return output(JsonUtil.toJson(map));
	}
	// List<TowerDribKindBean> dribKindBeanList =
	// towerDribKindService.getKind(towerUserId);
	List<TowerContentBean> contentList = towerContentService
		.getdribhomepage(towerUserId);
	// map.put("dribKindList", dribKindBeanList);
	map.put("contentList", contentList);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	map.put("desc", "dribKindList=====>内容列表");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取点滴分类
     * 
     * @author George
     * @throws Exception
     * @date 20180708
     */
    public String getdribkind() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "未输入用户id");
	    return output(JsonUtil.toJson(map));
	}
	List<TowerDribKindBean> dribKindBeanList = towerDribKindService
		.getKind(towerUserId);
	map.put("dribKindList", dribKindBeanList);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	map.put("desc", "dribKindList=====>分类列表");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 根据分类id获取点滴
     * 
     * @author George
     * @throws Exception
     * @date 20180628
     */
    public String getdribbykind() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(dribKindId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "未输入分类id");
	    return output(JsonUtil.toJson(map));
	}
	List<TowerContentBean> contentList = towerContentService
		.getdrib(dribKindId);
	map.put("contentList", contentList);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	map.put("desc", "contentList=====>内容列表");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 根据分类id删除分类
     * 
     * @author George
     * @throws Exception
     * @date 20180628
     */
    public String deletedribkind() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(dribKindId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "未输入分类id");
	    return output(JsonUtil.toJson(map));
	}
	if (towerContentService.deleteDribKind(dribKindId)) {
	    map.put("returnStatus", "true");
	    map.put("msg", "删除成功");
	} else {
	    map.put("returnStatus", "false");
	    map.put("msg", "删除失败");
	}
	return output(JsonUtil.toJson(map));
    }

    /**
     * 编辑点滴分类和描述
     * 
     * @author George
     * @throws Exception
     * @date 20180628
     */
    public String editdribkind() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(dribKindId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "dribKindId为空");
	    return output(JsonUtil.toJson(map));
	}
	TowerDribKind towerDribKind = towerDribKindService.get(dribKindId);
	if (!StringUtil.isEmpty(dribKindName)) {
	    towerDribKind.setName(dribKindName);
	}
	if (!StringUtil.isEmpty(dribKindDetail)) {
	    towerDribKind.setName(dribKindDetail);
	}
	towerDribKindService.update(towerDribKind);
	map.put("returnStatus", "true");
	map.put("msg", "修改成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 完善个人资料
     * 
     * @author George
     * @throws Exception
     * @date 20180628
     */
    public String addpersonadata() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "towerUserId为空");
	    return output(JsonUtil.toJson(map));
	}
	TowerUser towerUser = towerUserService.get(towerUserId);
	if (!StringUtil.isEmpty(towerUserName)) {
	    towerUser.setName(towerUserName);
	}
	if (!StringUtil.isEmpty(towerUsersex)) {
	    towerUser.setSex(towerUsersex);
	}
	if (!StringUtil.isEmpty(towerUserUniversity)) {
	    towerUser.setUniversity(towerUserUniversity);
	}
	if (!StringUtil.isEmpty(towerUserCompany)) {
	    towerUser.setName(towerUserCompany);
	}
	if (!StringUtil.isEmpty(towerUserIntroduce)) {
	    towerUser.setIntroduce(towerUserIntroduce);
	}
	if (!StringUtil.isEmpty(towerUserPosition)) {
	    towerUser.setIntroduce(towerUserPosition);
	}
	if (!StringUtil.isEmpty(towerUserBirthday)) {
	    towerUser.setIntroduce(towerUserBirthday);
	}
	// String fileName_icon = "img_"
	// + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
	// + CommonUtil.getRandomNumber(4);
	// System.out.println("AccountAction:fileName_icon+++" + fileName_icon
	// + "+++" + towerUserId);
	try {
	    if (icon == null) {
		System.out.println("AccountAction:头像图片为空");
	    } else {
		// String uploadFileName = FileOperateUtil.copyFile(
		// getServletContext(), icon, fileName_icon);
		// String imgUrl_icon = "upload/img/" + uploadFileName;
		// towerUser.setIconUrl(imgUrl_icon);
		String imgUrl_icon = ImageUtil.copyImageFile(
			getServletContext(), icon);
		towerUser.setIconUrl(imgUrl_icon);
	    }
	} catch (Exception e) {
	    System.out.println("头像上传失败");
	    e.printStackTrace();
	}
	towerUserService.update(towerUser);
	map.put("returnStatus", "true");
	map.put("msg", "修改成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取达人
     * 
     * @author George
     * @throws Exception
     * @date 20180628
     */
    public String getgreatmanlist() {
	Map<String, Object> map = new HashMap<String, Object>();
	List<TowerUserBean> towerUserBeans = null;
	// 游客登录
	if (StringUtil.isEmpty(towerUserId)) {
	    if (StringUtil.isEmpty(greatManType)) {
		map.put("returnStatus", "false");
		map.put("msg", "greatManType参数为空");
		return output(JsonUtil.toJson(map));
	    }
	    if (greatManType.equals("0")) {
		towerUserBeans = towerUserService.getgreatmanlist("0");
	    } else if (greatManType.equals("1")) {
		towerUserBeans = towerUserService.getgreatmanlist("1");
	    } else {
		map.put("returnStatus", "false");
		map.put("msg", "greatManType参数错误");
		return output(JsonUtil.toJson(map));
	    }
	} else { // 用户登录
	    if (StringUtil.isEmpty(greatManType)) {
		map.put("returnStatus", "false");
		map.put("msg", "greatManType参数为空");
		return output(JsonUtil.toJson(map));
	    }
	    if (greatManType.equals("0")) {
		towerUserBeans = towerUserService.getgreatmanlist(towerUserId,
			"0");
	    } else if (greatManType.equals("1")) {
		towerUserBeans = towerUserService.getgreatmanlist(towerUserId,
			"1");
	    } else {
		map.put("returnStatus", "false");
		map.put("msg", "greatManType参数错误");
		return output(JsonUtil.toJson(map));
	    }
	}
	map.put("towerUserList", towerUserBeans);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	map.put("DESC", "towerUserList:达人列表");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 评论
     * 
     * @author George
     * @throws Exception
     * @date 20180629
     */
    public String comment() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)
		|| StringUtil.isEmpty(towerContentId)
		|| StringUtil.isEmpty(comment)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	// 添加评论
	TowerComment towerComment = new TowerComment();
	towerComment.setTowerContent(towerContentService.get(towerContentId));
	towerComment.setTowerUser(towerUserService.get(towerUserId));
	towerComment.setMessage(comment);
	towerCommentService.save(towerComment);
	map.put("returnStatus", "true");
	map.put("msg", "评论成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取评论列表
     * 
     * @author George
     * @throws Exception
     * @date 20180629
     */
    public String getcomment() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerContentId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	// 添加评论
	List<TowerCommentBean> commentList = towerContentService
		.getCommentListByContentId(towerContentId);
	map.put("commentList", commentList);
	map.put("returnStatus", "true");
	map.put("msg", "评论成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取塔圈
     * 
     * @author George
     * @throws Exception
     * @date 20180629
     */
    public String getmomentslist() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	List<TowerContentBean> list = towerContentService
		.getMomentsByUser(towerUserId);
	map.put("contentList", list);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 我的收藏
     * 
     * @author George
     * @throws Exception
     * @date 20180629
     */
    public String getcollectionlist() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	List<TowerContentBean> list = towerContentService
		.getCollectionByUser(towerUserId);
	map.put("contentList", list);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 精选
     * 
     * @author George
     * @throws Exception
     * @date 20180629
     */
    public String get_a_test() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	List<TowerContentBean> list = towerContentService.getTestA(towerUserId);
	map.put("contentList", list);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 干货
     * 
     * @author George
     * @throws Exception
     * @date 20180629
     */
    public String get_b_test() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId)) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	List<TowerContentBean> list = towerContentService.getTestB(towerUserId);
	map.put("contentList", list);
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    /**
     * 获取用户个人主页
     * 
     * @author George
     * @throws Exception
     * @date 20180708
     */
    public String getPersonalHomePage() {
	Map<String, Object> map = new HashMap<String, Object>();
	if (StringUtil.isEmpty(towerUserId_star)
		&& StringUtil.isEmpty(towerUserId)
		&& towerUserService.get(towerUserId_star) != null
		&& towerUserService.get(towerUserId) != null) {
	    map.put("returnStatus", "false");
	    map.put("msg", "缺少必要的参数");
	    return output(JsonUtil.toJson(map));
	}
	TowerUser tu = towerUserService.get(towerUserId_star);
	map.put("name", tu.getName());
	map.put("icon",
		SettingUtil.getSetting().getUpFilePath() + tu.getIconUrl());
	map.put("introduce", tu.getIntroduce());
	map.put("isFollow",
		towerUserService.isFollow(towerUserId_star, towerUserId));
	map.put("followNum", towerUserService.getFollowNum(towerUserId_star));
	map.put("fansNum", towerUserService.getFansNum(towerUserId_star));
	map.put("dribKindList", towerDribKindService.getKind(towerUserId_star));
	map.put("contentList", towerContentService.getdribhomepage(towerUserId));
	// map.put("company", tu.getCompany());
	// map.put("university", tu.getUniversity());
	// map.put("category", tu.getCategory());
	// map.put("position", tu.getPosition());
	map.put("returnStatus", "true");
	map.put("msg", "获取成功");
	return output(JsonUtil.toJson(map));
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getVerification() {
	return verification;
    }

    public void setVerification(String verification) {
	this.verification = verification;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getTowerUserId() {
	return towerUserId;
    }

    public void setTowerUserId(String towerUserId) {
	this.towerUserId = towerUserId;
    }

    public String getTowerContentId() {
	return towerContentId;
    }

    public void setTowerContentId(String towerContentId) {
	this.towerContentId = towerContentId;
    }

    public String getTowerUserId_fans() {
	return towerUserId_fans;
    }

    public void setTowerUserId_fans(String towerUserId_fans) {
	this.towerUserId_fans = towerUserId_fans;
    }

    public File getImg_2() {
	return img_2;
    }

    public void setImg_2(File img_2) {
	this.img_2 = img_2;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public File getImg_1() {
	return img_1;
    }

    public void setImg_1(File img_1) {
	this.img_1 = img_1;
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

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getKind() {
	return kind;
    }

    public void setKind(String kind) {
	this.kind = kind;
    }

    public String getKeyword() {
	return keyword;
    }

    public void setKeyword(String keyword) {
	this.keyword = keyword;
    }

    public String getDribKindId() {
	return dribKindId;
    }

    public void setDribKindId(String dribKindId) {
	this.dribKindId = dribKindId;
    }

    public String getDribKindName() {
	return dribKindName;
    }

    public void setDribKindName(String dribKindName) {
	this.dribKindName = dribKindName;
    }

    public String getDribKindDetail() {
	return dribKindDetail;
    }

    public void setDribKindDetail(String dribKindDetail) {
	this.dribKindDetail = dribKindDetail;
    }

    public File getIcon() {
	return icon;
    }

    public void setIcon(File icon) {
	this.icon = icon;
    }

    public String getGreatManType() {
	return greatManType;
    }

    public void setGreatManType(String greatManType) {
	this.greatManType = greatManType;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public String getVideo_img() {
	return video_img;
    }

    public void setVideo_img(String video_img) {
	this.video_img = video_img;
    }

    public String getContentType() {
	return contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

    public String getPageNum() {
	return pageNum;
    }

    public void setPageNum(String pageNum) {
	this.pageNum = pageNum;
    }

    public String getTowerUserId_star() {
	return towerUserId_star;
    }

    public void setTowerUserId_star(String towerUserId_star) {
	this.towerUserId_star = towerUserId_star;
    }
}
