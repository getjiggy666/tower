package com.telecom.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.telecom.service.HtmlService;
import com.telecom.util.SpringUtil;

/**
 * Service接口 - 生成错误页HTML任务
 */

public class BuildErrorHtmlJob implements Job {
	
	public static final String JOB_NAME = "buildErrorHtmlJob";// 任务名称
	public static final String TRIGGER_NAME = "buildErrorHtmlTrigger";// Trigger名称
	public static final String GROUP_NAME = "buildErrorHtmlGroup";// Group名称

	public void execute(JobExecutionContext context) {
		HtmlService htmlService = (HtmlService) SpringUtil.getBean("quartzHtmlServiceImpl");
		htmlService.buildErrorPageHtml();
		htmlService.buildErrorPageAccessDeniedHtml();
		htmlService.buildErrorPage500Html();
		htmlService.buildErrorPage404Html();
		htmlService.buildErrorPage403Html();
	}

}