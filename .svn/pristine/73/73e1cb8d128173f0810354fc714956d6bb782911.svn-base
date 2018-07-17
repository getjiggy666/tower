package com.telecom.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.telecom.entity.Article;
import com.telecom.entity.Article.ArticleType;
import com.telecom.entity.ArticleCategory;
import com.telecom.service.ArticleCategoryService;
import com.telecom.service.ArticleService;
import com.telecom.util.DirectiveUtil;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("articleListDirective")
public class ArticleListDirective implements TemplateDirectiveModel {
	
	public static final String TAG_NAME = "article_list";
	private static final String ARTICLE_CATEGORY_ID_PARAMETER_NAME = "article_category_id";
	private static final String ARTICLE_TYPE_PARAMETER_NAME = "article_type";
	private static final String TYPE_PARAMETER_NAME = "type";
	private static final String COUNT_PARAMETER_NAME = "count";
	private static final String IS_CONTAIN_CHILDREN_PARAMETER_NAME = "isContainChildren";
	private static final Boolean DEFAULT_IS_CONTAIN_CHILDREN = true;
	
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String articleCategoryId = DirectiveUtil.getStringParameter(ARTICLE_CATEGORY_ID_PARAMETER_NAME, params);
		Integer articleTypeInteger = DirectiveUtil.getIntegerParameter(ARTICLE_TYPE_PARAMETER_NAME, params);
		String type = DirectiveUtil.getStringParameter(TYPE_PARAMETER_NAME, params);
		Integer count = DirectiveUtil.getIntegerParameter(COUNT_PARAMETER_NAME, params);
		Boolean isContainChildren = DirectiveUtil.getBooleanParameter(IS_CONTAIN_CHILDREN_PARAMETER_NAME, params);
		
		ArticleType articleType = ArticleType.article;
		switch (articleTypeInteger) {			
		case 1:
			articleType = ArticleType.activity;
			break;

		default:
			break;
		}
		
		if (isContainChildren == null) {
			isContainChildren = DEFAULT_IS_CONTAIN_CHILDREN;
		}
		
		ArticleCategory articleCategory = null;
		List<Article> articleList = null;
		if (StringUtils.isNotEmpty(articleCategoryId)) {
			articleCategory = articleCategoryService.load(articleCategoryId);
		}
		articleList = articleService.getArticleList(articleCategory, articleType, type, isContainChildren, count);
		
		if (body != null && articleList != null) {
			if (loopVars.length > 0) {
				loopVars[0] = ObjectWrapper.BEANS_WRAPPER.wrap(articleList);
			}
			body.render(env.getOut());
		}
	}

}