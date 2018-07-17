package com.telecom.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import com.telecom.bean.PageTemplateConfig;
import com.telecom.common.FreemarkerManager;
import com.telecom.dao.ArticleDao;
import com.telecom.entity.Article;
import com.telecom.service.ArticleCategoryService;
import com.telecom.service.HtmlService;
import com.telecom.util.SettingUtil;
import com.telecom.util.TemplateConfigUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.ResourceBundleModel;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Service实现类 - 生成静态
 */

@Service("htmlServiceImpl")
public class HtmlServiceImpl implements HtmlService, ServletContextAware {

	private ServletContext servletContext;
	@Resource(name = "freemarkerManager")
	private FreemarkerManager freemarkerManager;
	@Resource(name = "articleDaoImpl")
	private ArticleDao articleDao;
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	// 获取公用数据
	public Map<String, Object> getCommonData() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n");
		ResourceBundleModel resourceBundleModel = new ResourceBundleModel(resourceBundle, new BeansWrapper());
		
		Map<String, Object> commonData = new HashMap<String, Object>();
		commonData.put("bundle", resourceBundleModel);
		commonData.put("base", getContextPath());
		commonData.put("setting", SettingUtil.getSetting());
		commonData.put("currencyFormat", SettingUtil.getCurrencyFormat());
		return commonData;
	}
	
	public void buildHtml(String templatePath, String htmlPath, Map<String, Object> data) {
		try {
			Configuration configuration = freemarkerManager.getConfiguration(servletContext);
			Template template = configuration.getTemplate(templatePath);
			File htmlFile = new File(servletContext.getRealPath(htmlPath));
			File htmlDirectory = htmlFile.getParentFile();
			if (!htmlDirectory.exists()) {
				htmlDirectory.mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buildIndexHtml() {
		Map<String, Object> data = getCommonData();
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.SHOP_INDEX);
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
		
		pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.WAP_INDEX);
		htmlPath = pageTemplateConfig.getHtmlPath();
		templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildLoginHtml() {
		Map<String, Object> data = getCommonData();
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.SHOP_LOGIN);
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
		
		pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.WAP_LOGIN);
		htmlPath = pageTemplateConfig.getHtmlPath();
		templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildRegisterAgreementHtml() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.WAP_REGISTER_AGREEMENT);
		Map<String, Object> data = getCommonData();
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	@Transactional(readOnly = true)
	public void buildArticleContentHtml(String id) {
		Article article = articleDao.get(id);
		buildArticleContentHtml(article);
	}

	@Transactional(readOnly = true)
	public void buildArticleWapContentHtml(String id) {
		Article article = articleDao.get(id);
		buildArticleWapContentHtml(article);
	}
	
	public void buildArticleContentHtml(Article article) {
		if (article == null || StringUtils.isEmpty(article.getContent())) {
			return;
		}
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ARTICLE_CONTENT);
		Map<String, Object> data = getCommonData();
		data.put("article", article);
		data.put("pathList", articleCategoryService.getArticleCategoryPathList(article.getArticleCategory()));
		String htmlPath = article.getHtmlPath();
		String prefix = StringUtils.substringBeforeLast(htmlPath, ".");
		String extension = StringUtils.substringAfterLast(htmlPath, ".");
		List<String> pageContentList = article.getPageContentList();
		for (int i = 0; i < pageContentList.size(); i++) {
			String currentHtmlPath = null;
			if (i == 0) {
				currentHtmlPath = htmlPath;
			} else {
				currentHtmlPath = prefix + "_" + (i + 1) + "." + extension;
			}
			if (article.getIsPublication()) {
				data.put("content", pageContentList.get(i));
				data.put("pageNumber", i + 1);
				data.put("pageCount", pageContentList.size());
				String templatePath = pageTemplateConfig.getTemplatePath();
				buildHtml(templatePath, currentHtmlPath, data);
			} else {
				File htmlFile = new File(servletContext.getRealPath(currentHtmlPath));
				if (htmlFile.exists()) {
					htmlFile.delete();
				}
			}
		}
	}
	
	public void buildArticleWapContentHtml(Article article) {
		if (article == null || StringUtils.isEmpty(article.getWapContent())) {
			return;
		}
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getWapPageTemplateConfig(PageTemplateConfig.ARTICLE_WAP_CONTENT);
		Map<String, Object> data = getCommonData();
		data.put("article", article);
		data.put("pathList", articleCategoryService.getArticleCategoryPathList(article.getArticleCategory()));
		String wapHtmlPath = article.getWapHtmlPath();
		String prefix = StringUtils.substringBeforeLast(wapHtmlPath, ".");
		String extension = StringUtils.substringAfterLast(wapHtmlPath, ".");
		List<String> pageContentList = article.getWapPageContentList();
		for (int i = 0; i < pageContentList.size(); i++) {
			String currentHtmlPath = null;
			if (i == 0) {
				currentHtmlPath = wapHtmlPath;
			} else {
				currentHtmlPath = prefix + "_" + (i + 1) + "." + extension;
			}
			if (article.getIsPublication()) {
				data.put("content", pageContentList.get(i));
				data.put("pageNumber", i + 1);
				data.put("pageCount", pageContentList.size());
				String templatePath = pageTemplateConfig.getWapTemplatePath();
				buildHtml(templatePath, currentHtmlPath, data);
			} else {
				File htmlFile = new File(servletContext.getRealPath(currentHtmlPath));
				if (htmlFile.exists()) {
					htmlFile.delete();
				}
			}
		}
	}
	
	@Transactional(readOnly = true)
	public void buildArticleContentHtml() {
		long articleTotalCount = articleDao.getTotalCount();
		for (int i = 0; i < articleTotalCount; i += 30) {
			List<Article> articleList = articleDao.getArticleList(null, null, null, i, 30);
			for (Article article : articleList) {
				buildArticleContentHtml(article);
			}
		}
	}

	@Transactional(readOnly = true)
	public void buildArticleWapContentHtml() {
		long articleTotalCount = articleDao.getTotalCount();
		for (int i = 0; i < articleTotalCount; i += 30) {
			List<Article> articleList = articleDao.getArticleList(null, null, null, i, 30);
			for (Article article : articleList) {
				buildArticleWapContentHtml(article);
			}
		}
	}
	
	public void buildErrorPageHtml() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "系统出现异常,请与管理员联系!");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildErrorPageAccessDeniedHtml() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE_ACCESS_DENIED);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "您无此访问权限!");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildErrorPage500Html() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE_500);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "系统出现异常,请与管理员联系!");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildErrorPage404Html() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE_404);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "您访问的页面不存在!");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void buildErrorPage403Html() {
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ERROR_PAGE_403);
		Map<String, Object> data = getCommonData();
		data.put("errorContent", "系统出现异常,请与管理员联系!");
		String htmlPath = pageTemplateConfig.getHtmlPath();
		String templatePath = pageTemplateConfig.getTemplatePath();
		buildHtml(templatePath, htmlPath, data);
	}
	
	public void deleteArticleContentHtml(String htmlPath, Integer pageCount) {
		if (htmlPath != null && pageCount != null) {
			String prefix = StringUtils.substringBeforeLast(htmlPath, ".");
			String extension = StringUtils.substringAfterLast(htmlPath, ".");
			for (int i = 0; i < pageCount; i++) {
				String currentHtmlPath = null;
				if (i == 0) {
					currentHtmlPath = htmlPath;
				} else {
					currentHtmlPath = prefix + "_" + (i + 1) + "." + extension;
				}
				File htmlFile = new File(servletContext.getRealPath(currentHtmlPath));
				if (htmlFile.exists()) {
					htmlFile.delete();
				}
			}
		}
	}
	
	public void deleteGoodsContentHtml(String htmlPath) {
		if (htmlPath != null) {
			File htmlFile = new File(servletContext.getRealPath(htmlPath));
			if (htmlFile.exists()) {
				htmlFile.delete();
			}
		}
	}
	
	/**
	 * 获取虚拟路径
	 * 
	 * @return 虚拟路径
	 */
	private String getContextPath() {
		if (servletContext.getMajorVersion() < 2 || (servletContext.getMajorVersion() == 2 && servletContext.getMinorVersion() < 5)) {
			return SettingUtil.getSetting().getContextPath();
		} else {
			return servletContext.getContextPath();
		}
	}

}