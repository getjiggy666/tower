package com.telecom.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.telecom.bean.LogConstant;
import com.telecom.entity.Log;
import com.telecom.service.LogService;
import com.telecom.util.CommonUtil;
import com.telecom.util.SpringUtil;

/**
 * 过滤器 - 日志过滤器
 */

public class LogFilter implements Filter {

	private final Logger log = Logger.getLogger(getClass());
	private boolean mode = true;
		
	public void init(FilterConfig filterConfig) throws ServletException {
        String configMode = filterConfig.getInitParameter("mode");
        if ( configMode != null ) {
            mode = Boolean.valueOf(configMode);
        }
	}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(mode){
			LogService logService = (LogService) SpringUtil.getBean("logServiceImpl");
			String actionClass = this.getClass().getName();
			String actionMethod = "doFilter";
			
			String logInfo = "url: " + getBasePath((HttpServletRequest)request) + "<br>" + "参数:" + getRequestParameterJson(request);
			String operator = "未知";
			String accessSource = LogConstant.AccesssSource.REQUEST;
			String ip = CommonUtil.getRemoteHost((HttpServletRequest)request);
			String operation = LogConstant.LogType.REQUEST;
			String logType = "REQUEST";
			String logDesc = LogConstant.LogType.REQUEST;
			
			Log sysLog = new Log();
			sysLog.setAccesssSource(accessSource);
			sysLog.setOperation(operation);
			sysLog.setOperator(operator);
			sysLog.setActionClass(actionClass);
			sysLog.setActionMethod(actionMethod);
			sysLog.setLogType(logType);
			sysLog.setLogDesc(logDesc);
			sysLog.setIp(ip);
			sysLog.setInfo(logInfo);
			logService.save(sysLog);
			
			log.info("ip: " + CommonUtil.getRemoteHost((HttpServletRequest)request) + "<br>" + "url: " + getBasePath((HttpServletRequest)request) + "<br>" + "参数:" + getRequestParameterJson(request));
		}
		chain.doFilter(request, response);
	}
	
	public String getRequestParameterJson(ServletRequest request) {
		StringBuffer jsonBuffer = new StringBuffer("{");
		Enumeration<String> parameters = request.getParameterNames();
		if(parameters != null) {
			while(parameters.hasMoreElements()) {
				String key = parameters.nextElement();
				String value = request.getParameter(key);
				jsonBuffer.append("\"" + key + "\"" + " : " + "\"" + value +  "\"");
			}
		}
		jsonBuffer.append("}");
		return jsonBuffer.toString();
	}
	
	// 获取BasePath
	protected String getBasePath(HttpServletRequest request){
		if("80".equals(request.getServerPort())) {
			return request.getScheme() + "://" + request.getServerName() + request.getRequestURI();
		} else {
			return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
		}
		
	}

}