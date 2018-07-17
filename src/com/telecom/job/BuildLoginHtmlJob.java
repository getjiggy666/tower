package com.telecom.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.telecom.service.HtmlService;
import com.telecom.util.SpringUtil;

/**
 * Service接口 - 生成登录HTML任务
 */

public class BuildLoginHtmlJob implements Job {
	
	public static final String JOB_NAME = "buildLoginHtmlJob";// 任务名称
	public static final String TRIGGER_NAME = "buildLoginHtmlTrigger";// Trigger名称
	public static final String GROUP_NAME = "buildLoginHtmlGroup";// Group名称

	public void execute(JobExecutionContext context) {
		HtmlService htmlService = (HtmlService) SpringUtil.getBean("quartzHtmlServiceImpl");
		htmlService.buildLoginHtml();
	}

}