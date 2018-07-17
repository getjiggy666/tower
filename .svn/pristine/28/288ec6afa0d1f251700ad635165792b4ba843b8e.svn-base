package com.telecom.bean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class LogConstant {

	public static Map<String, String> logTypeMap = new HashMap<String, String>();
	
	static{
		LogType logType = new LogType();
		Field[] fields = logType.getClass().getDeclaredFields();
		for(Field field : fields){
			try {
				logTypeMap.put(field.getName(), (String)field.get(logType));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final class LogType {
		public static final String REQUEST="请求调用";
		public static final String ADMIN = "用户管理";
		public static final String ADMINSECURITY = "后台登录";
		public static final String SETTING = "系统设置";
		public static final String AREA = "地区管理";
		public static final String ACCESSSTRATEGY = "访问策略管理";
		public static final String MESSAGE = "通知管理";
		public static final String DEPARTMENT = "部门管理";
		public static final String ROLE = "角色管理";
		public static final String SMSTEMPLATE = "短信模板管理";
	}
	
	public static final class AccesssSource {
		public static final String REQUEST="请求调用";
		public static final String ADMIN = "管理后台";
		public static final String SERVICE = "接口调用";
		public static final String PC = "PC端";
		public static final String WAP = "移动端";
	}
}
