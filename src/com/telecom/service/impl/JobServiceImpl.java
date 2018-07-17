package com.telecom.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.springframework.stereotype.Service;

import com.telecom.job.BuildArticleContentHtmlJob;
import com.telecom.job.BuildErrorHtmlJob;
import com.telecom.job.BuildLoginHtmlJob;
import com.telecom.job.DeleteArticleContentHtmlJob;
import com.telecom.service.JobService;
import com.telecom.util.SettingUtil;

/**
 * Service实现类 - 任务
 */

@Service("jobServiceImpl")
public class JobServiceImpl implements JobService {
	
	@Resource(name = "scheduler")
	private Scheduler scheduler;
	
	public void buildLoginHtml() {
		try {
			SimpleTrigger simpleTrigger = new SimpleTrigger();
			simpleTrigger.setName(BuildLoginHtmlJob.TRIGGER_NAME);
			simpleTrigger.setGroup(BuildLoginHtmlJob.GROUP_NAME);
			simpleTrigger.setJobName(BuildLoginHtmlJob.JOB_NAME);
			simpleTrigger.setJobGroup(BuildLoginHtmlJob.GROUP_NAME);
			simpleTrigger.setStartTime(DateUtils.addSeconds(new Date(), SettingUtil.getSetting().getBuildHtmlDelayTime()));
			simpleTrigger.setRepeatCount(0);
			simpleTrigger.setRepeatInterval(60);
			
			JobDetail jobDetail = scheduler.getJobDetail(BuildLoginHtmlJob.JOB_NAME, BuildLoginHtmlJob.GROUP_NAME);
			if (jobDetail != null) {
				scheduler.rescheduleJob(BuildLoginHtmlJob.TRIGGER_NAME, BuildLoginHtmlJob.GROUP_NAME, simpleTrigger);
			} else {
				jobDetail = new JobDetail(BuildLoginHtmlJob.JOB_NAME, BuildLoginHtmlJob.GROUP_NAME, BuildLoginHtmlJob.class);
				scheduler.scheduleJob(jobDetail, simpleTrigger);
			}
			
			if (scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void buildArticleContentHtml(String id) {
		try {
			String jobName = BuildArticleContentHtmlJob.JOB_NAME + id;
			String triggerName = BuildArticleContentHtmlJob.TRIGGER_NAME + id;
			String groupName = BuildArticleContentHtmlJob.GROUP_NAME;
			
			SimpleTrigger simpleTrigger = new SimpleTrigger();
			simpleTrigger.setName(triggerName);
			simpleTrigger.setGroup(groupName);
			simpleTrigger.setJobName(jobName);
			simpleTrigger.setJobGroup(groupName);
			simpleTrigger.setStartTime(DateUtils.addSeconds(new Date(), SettingUtil.getSetting().getBuildHtmlDelayTime()));
			simpleTrigger.setRepeatCount(0);
			simpleTrigger.setRepeatInterval(60);
			
			JobDetail jobDetail = scheduler.getJobDetail(jobName, groupName);
			if (jobDetail != null) {
				scheduler.rescheduleJob(triggerName, groupName, simpleTrigger);
				jobDetail.getJobDataMap().put("id", id);
			} else {
				jobDetail = new JobDetail(jobName, groupName, BuildArticleContentHtmlJob.class);
				jobDetail.getJobDataMap().put("id", id);
				scheduler.scheduleJob(jobDetail, simpleTrigger);
			}
			
			if (scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void buildArticleContentHtml() {
		try {
			String jobName = BuildArticleContentHtmlJob.JOB_NAME;
			String triggerName = BuildArticleContentHtmlJob.TRIGGER_NAME;
			String groupName = BuildArticleContentHtmlJob.GROUP_NAME;
			
			SimpleTrigger simpleTrigger = new SimpleTrigger();
			simpleTrigger.setName(triggerName);
			simpleTrigger.setGroup(groupName);
			simpleTrigger.setJobName(jobName);
			simpleTrigger.setJobGroup(groupName);
			simpleTrigger.setStartTime(DateUtils.addSeconds(new Date(), SettingUtil.getSetting().getBuildHtmlDelayTime() * 2));
			simpleTrigger.setRepeatCount(0);
			simpleTrigger.setRepeatInterval(60);
			
			String[] triggerNames = scheduler.getTriggerNames(groupName);
			String[] jobNames = scheduler.getJobNames(groupName);
			if (triggerNames != null) {
				for (String tn : triggerNames) {
					scheduler.pauseTrigger(tn, groupName);
					scheduler.unscheduleJob(tn, groupName);
				}
			}
			if (jobNames != null) {
				for (String jn : jobNames) {
					scheduler.deleteJob(jn, groupName);
				}
			}
			
			JobDetail jobDetail = scheduler.getJobDetail(jobName, groupName);
			if (jobDetail != null) {
				scheduler.rescheduleJob(triggerName, groupName, simpleTrigger);
			} else {
				jobDetail = new JobDetail(jobName, groupName, BuildArticleContentHtmlJob.class);
				scheduler.scheduleJob(jobDetail, simpleTrigger);
			}
			
			if (scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void buildErrorHtml() {
		try {
			SimpleTrigger simpleTrigger = new SimpleTrigger();
			simpleTrigger.setName(BuildErrorHtmlJob.TRIGGER_NAME);
			simpleTrigger.setGroup(BuildErrorHtmlJob.GROUP_NAME);
			simpleTrigger.setJobName(BuildErrorHtmlJob.JOB_NAME);
			simpleTrigger.setJobGroup(BuildErrorHtmlJob.GROUP_NAME);
			simpleTrigger.setStartTime(DateUtils.addSeconds(new Date(), SettingUtil.getSetting().getBuildHtmlDelayTime()));
			simpleTrigger.setRepeatCount(0);
			simpleTrigger.setRepeatInterval(60);
			
			JobDetail jobDetail = scheduler.getJobDetail(BuildErrorHtmlJob.JOB_NAME, BuildErrorHtmlJob.GROUP_NAME);
			if (jobDetail != null) {
				scheduler.rescheduleJob(BuildErrorHtmlJob.TRIGGER_NAME, BuildErrorHtmlJob.GROUP_NAME, simpleTrigger);
			} else {
				jobDetail = new JobDetail(BuildErrorHtmlJob.JOB_NAME, BuildErrorHtmlJob.GROUP_NAME, BuildErrorHtmlJob.class);
				scheduler.scheduleJob(jobDetail, simpleTrigger);
			}
			
			if (scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteArticleContentHtml(String htmlPath, Integer pageCount) {
		try {
			String jobName = DeleteArticleContentHtmlJob.JOB_NAME + htmlPath;
			String triggerName = DeleteArticleContentHtmlJob.TRIGGER_NAME + htmlPath;
			String groupName = DeleteArticleContentHtmlJob.GROUP_NAME;
			
			SimpleTrigger simpleTrigger = new SimpleTrigger();
			simpleTrigger.setName(triggerName);
			simpleTrigger.setGroup(groupName);
			simpleTrigger.setJobName(jobName);
			simpleTrigger.setJobGroup(groupName);
			simpleTrigger.setStartTime(DateUtils.addSeconds(new Date(), SettingUtil.getSetting().getBuildHtmlDelayTime()));
			simpleTrigger.setRepeatCount(0);
			simpleTrigger.setRepeatInterval(60);
			
			JobDetail jobDetail = scheduler.getJobDetail(jobName, groupName);
			if (jobDetail != null) {
				scheduler.rescheduleJob(triggerName, groupName, simpleTrigger);
				jobDetail.getJobDataMap().put("htmlPath", htmlPath);
				jobDetail.getJobDataMap().put("pageCount", pageCount);
			} else {
				jobDetail = new JobDetail(jobName, groupName, DeleteArticleContentHtmlJob.class);
				jobDetail.getJobDataMap().put("htmlPath", htmlPath);
				jobDetail.getJobDataMap().put("pageCount", pageCount);
				scheduler.scheduleJob(jobDetail, simpleTrigger);
			}
			
			if (scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}