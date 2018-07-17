package com.telecom.action.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.telecom.entity.Article;
import com.telecom.entity.ArticleCategory;
import com.telecom.service.ArticleCategoryService;
import com.telecom.service.ArticleService;
import com.telecom.service.CacheService;
import com.telecom.service.HtmlService;

/**
 * 后台Action类 - 生成静态
 */

@ParentPackage("admin")
public class BuildHtmlAction extends BaseAdminAction {

	private static final long serialVersionUID = -2197609380142883572L;

	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	@Resource(name = "htmlServiceImpl")
	private HtmlService htmlService;
	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;
	
	private String buildType;// 更新类型
	private String buildContent;// 更新内容
	private Integer maxResults;// 每次更新数
	private Integer firstResult;// 起始结果数
	
	public String allInput() {
		return "all_input";
	}
	
	// 一键更新
	@SuppressWarnings("deprecation")
	@InputConfig(resultName = "ajaxError")
	public String all() throws Exception {
		long beginTimeMillis = System.currentTimeMillis();
		if (StringUtils.isEmpty(buildType)) {
			buildType = "all";
		}
		if (StringUtils.isEmpty(buildContent)) {
			buildContent = "errorPage";
		}
		if (maxResults == null || maxResults <= 0) {
			maxResults = 50;
		}
		if (firstResult == null || firstResult < 0) {
			firstResult = 0;
		}
		
		if (buildContent.equalsIgnoreCase("errorPage")) {
			htmlService.buildErrorPageHtml();
			htmlService.buildErrorPageAccessDeniedHtml();
			htmlService.buildErrorPage500Html();
			htmlService.buildErrorPage404Html();
			htmlService.buildErrorPage403Html();
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put(STATUS_PARAMETER_NAME, "errorPageFinish");
			jsonMap.put("buildCount", 5);
			jsonMap.put("buildTime", System.currentTimeMillis() - beginTimeMillis);
			return ajax(jsonMap);
		} else if (buildContent.equalsIgnoreCase("article")) {
			List<Article> articleList = null;
			if (buildType.equalsIgnoreCase("all")) {
				articleList = articleService.getArticleList(null, null, null, firstResult, maxResults);
			}
			if (articleList != null && articleList.size() > 0) {
				for (Article article : articleList) {
					articleService.index(article);
					htmlService.buildArticleContentHtml(article);
				}
			}
			if (articleList != null && articleList.size() == maxResults) {
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put(STATUS_PARAMETER_NAME, "articleBuilding");
				jsonMap.put("firstResult", firstResult + articleList.size());
				jsonMap.put("buildCount", maxResults);
				jsonMap.put("buildTime", System.currentTimeMillis() - beginTimeMillis);
				return ajax(jsonMap);
			} else {
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put(STATUS_PARAMETER_NAME, "articleFinish");
				jsonMap.put("buildCount", articleList.size());
				jsonMap.put("buildTime", System.currentTimeMillis() - beginTimeMillis);
				return ajax(jsonMap);
			}
		}
		return NONE;
	}
	
	public String articleInput() {
		return "article_input";
	}
	
	// 更新文章
	@InputConfig(resultName = "ajaxError")
	public String article() {
		long beginTimeMillis = System.currentTimeMillis();
		if (maxResults == null || maxResults <= 0) {
			maxResults = 50;
		}
		if (firstResult == null || firstResult < 0) {
			firstResult = 0;
		}

		ArticleCategory articleCategory = null;
		if (StringUtils.isNotEmpty(id)) {
			articleCategory = articleCategoryService.load(id);
		}
		List<Article> articleList = articleService.getArticleList(articleCategory, null, null, firstResult, maxResults);
		
		if (articleList != null && articleList.size() > 0) {
			for (Article article : articleList) {
				articleService.index(article);
				htmlService.buildArticleContentHtml(article);
			}
		}
		if (articleList != null && articleList.size() == maxResults) {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put(STATUS_PARAMETER_NAME, "ARTICLE_BUILDING");
			jsonMap.put("firstResult", firstResult + articleList.size());
			jsonMap.put("buildCount", maxResults);
			jsonMap.put("buildTime", System.currentTimeMillis() - beginTimeMillis);
			return ajax(jsonMap);
		} else {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put(STATUS_PARAMETER_NAME, "ARTICLE_FINISH");
			jsonMap.put("buildCount", articleList.size());
			jsonMap.put("buildTime", System.currentTimeMillis() - beginTimeMillis);
			cacheService.flushArticleListPageCache(getRequest());
			return ajax(jsonMap);
		}
	}
	
	public String goodsInput() {
		return "goods_input";
	}
	
	// 获取文章分类树
	public List<ArticleCategory> getArticleCategoryTreeList() {
		return articleCategoryService.getArticleCategoryTreeList();
	}
	
	// 获取默认开始日期
	public Date getDefaultBeginDate() {
		return DateUtils.addDays(new Date(), -7);
	}

	// 获取默认结束日期
	public Date getDefaultEndDate() {
		return new Date();
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getBuildContent() {
		return buildContent;
	}

	public void setBuildContent(String buildContent) {
		this.buildContent = buildContent;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	
	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

}