package com.telecom.util;

public class TowerContentUtil {
	public static final String host = "http://dingxin.market.alicloudapi.com";// 验证码短信域名
	public static final String path = "/dx/sendSms";// 方法名
	public static final String method = "POST";// 请求方式
	public static final String appcode = "deed2b6e7aa244819f8fae7c5087e76d";// appcode
	public static final String tpl_id = "TP18060410";// 短信模板

	public static final String workers = "职业大咖";// 企业大咖
	public static final String student = "学生大牛";// 学生大牛

	public static final String icon_default = "/upload/image/tower_icon.png"; // 默认头像
	public static final String name_default = "用户"; // 默认头像

	// 方法一：用JAVA自带的函数
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
