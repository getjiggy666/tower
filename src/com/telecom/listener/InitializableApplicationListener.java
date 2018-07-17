package com.telecom.listener;
import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;

import com.telecom.bean.sms.SmsController;
import com.telecom.bean.sms.SmsFailFetchThread;
import com.telecom.bean.sms.SmsFetchThread;
import com.telecom.bean.sms.SmsQueryStatusFetchThread;
import com.telecom.entity.Article;
import com.telecom.job.TestJob;
import com.telecom.service.ArticleService;
import com.telecom.service.HtmlService;
import com.telecom.service.JobService;
import com.telecom.util.ImageUtil;

/**
 * 安装初始化
 */

@Component("Initializable")
public class InitializableApplicationListener implements ApplicationListener, ServletContextAware {
		
	private static final String BUILD_HTML_CONFIG_FILE_PATH = "/build_html.conf";
	
	private ServletContext servletContext;
	@Resource(name = "htmlServiceImpl")
	private HtmlService htmlService;
	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	@Resource(name = "jobServiceImpl")
	private JobService jobService;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@Transactional
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if(applicationEvent instanceof ContextRefreshedEvent) {
			// 页面静态化
			File buildHtmlFileConfigFile = new File(servletContext.getRealPath(BUILD_HTML_CONFIG_FILE_PATH));
			if (buildHtmlFileConfigFile.exists()) {
				System.out.print("BUILD HTML ...");
				
				htmlService.buildIndexHtml();
				htmlService.buildLoginHtml();
				htmlService.buildErrorPageHtml();
				htmlService.buildErrorPageAccessDeniedHtml();
				htmlService.buildErrorPage500Html();
				htmlService.buildErrorPage404Html();
				htmlService.buildErrorPage403Html();
				
				System.out.print("..");
				
				List<Article> articleList = articleService.getAllList();
				if (articleList != null) {
					for (int i = 0; i < articleList.size(); i ++) {
						Article article = articleList.get(i);
						articleService.index(article);
						htmlService.buildArticleContentHtml(article);
						if (i % 10 == 0) {
							System.out.print("..");
						}
					}
				}
				
				System.out.println(" OK");
				buildHtmlFileConfigFile.delete();
			}
			
			// 新建图片工具
			new ImageUtil();
			
			// 启动短信发送控制器
			SmsController smsC = new SmsController();
			smsC.start();
			
			// 启动短信队列填充线程 
			SmsFetchThread smsFetch = new SmsFetchThread();
			smsFetch.start();
			
			// 启动发送失败短信队列填充线程 
			SmsFailFetchThread smsFailFetch = new SmsFailFetchThread();
			smsFailFetch.start();
			
			// 启动验证短信真实发送状态队列填充线程
			SmsQueryStatusFetchThread queryStatusFailFetch = new SmsQueryStatusFetchThread();
			queryStatusFailFetch.start();
			
			// 启动示例任务（单线程）
			// TestJob testJob = new TestJob();
			// testJob.start();
			
		}
	}

}