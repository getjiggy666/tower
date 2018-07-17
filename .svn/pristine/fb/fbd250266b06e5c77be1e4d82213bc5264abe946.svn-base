package com.telecom.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.telecom.bean.CommonConstant;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.UserAgentType;
import net.sf.uadetector.service.UADetectorServiceFactory;

/**
 * 访问来源(终端)处理工具类
 * 
 * @author Administrator
 *
 */
public class AccessSourceUtil {

	/**
	 * 获取userAgent对象
	 * @param request
	 * @return
	 */
	public static ReadableUserAgent getUserAgent(HttpServletRequest request){
		// 解析userAgent信息
		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
		String strUA = request.getHeader("User-Agent");
		ReadableUserAgent agent = null;
		if(StringUtils.isNotEmpty(strUA)){
			agent = parser.parse(request.getHeader("User-Agent"));
		}
		return agent;
	}
	
	/**
	 * 获取访问来源类型
	 * 
	 * @param agent
	 * @return
	 */
	public static String getAccessType(ReadableUserAgent agent) {
		// 若agent对象的type信息返回wap类型或mobile类型，则视为移动终端
		if(UserAgentType.WAP_BROWSER.getName().equals(agent.getTypeName()) ||
		   UserAgentType.MOBILE_BROWSER.getName().equals(agent.getTypeName())){
			return CommonConstant.AccessSource.MOBILE;
		}	
		// 若agent对象的type信息返回browser类型，则视为PC终端
		else if(UserAgentType.BROWSER.getName().equals(agent.getTypeName())){
			return CommonConstant.AccessSource.PC;
		}
		// 否则返回null
		return null;
	}
	
	/**
	 * 获取设备类型
	 * 
	 * @param agent
	 * @return
	 */
	public static String getDeviceType(ReadableUserAgent agent) {
		return agent.getDeviceCategory().getName();
	}
	
	/**
	 * 获取操作系统
	 * 
	 * @param agent
	 * @return
	 */
	public static String getOperatingSystem(ReadableUserAgent agent) {
		return agent.getOperatingSystem().getName();
	}
	
	/**
	 * 获取内核
	 * 
	 * @param agent
	 * @return
	 */
	public static String getFamily(ReadableUserAgent agent) {
		return agent.getFamily().getName();
	}
	
}
