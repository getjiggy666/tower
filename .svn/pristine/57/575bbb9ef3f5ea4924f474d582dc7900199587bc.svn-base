package com.telecom.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springmodules.cache.annotations.Cacheable;

import com.telecom.dao.ArticleCategoryDao;
import com.telecom.entity.ArticleCategory;
import com.telecom.service.ArticleCategoryService;

/**
 * Service实现类 - 文章分类
 */

@Service("articleCategoryServiceImpl")
public class ArticleCategoryServiceImpl extends
		BaseServiceImpl<ArticleCategory, String> implements
		ArticleCategoryService {

	@Resource(name = "articleCategoryDaoImpl")
	private ArticleCategoryDao articleCategoryDao;

	@Resource(name = "articleCategoryDaoImpl")
	public void setBaseDao(ArticleCategoryDao articleCategoryDao) {
		super.setBaseDao(articleCategoryDao);
	}

	@Override
	public boolean isExistBySign(String sign) {
		return articleCategoryDao.isExistBySign(sign);
	}

	@Override
	public List<ArticleCategory> getArticleCategoryTreeList() {
		List<ArticleCategory> allList = articleCategoryDao.getAllList();
		return recursivPrizeCategoryTreeList(allList, null, null);
	}

	// 递归父类排序分类树
	private List<ArticleCategory> recursivPrizeCategoryTreeList(List<ArticleCategory> allArticleCategoryList, ArticleCategory article,List<ArticleCategory> temp) {
		if (temp == null) {
			temp = new ArrayList<ArticleCategory>();
		}
		for (ArticleCategory articleCategory : allArticleCategoryList) {
			ArticleCategory parent = articleCategory.getParent();
			if ((article == null && parent == null)
					|| (articleCategory != null && parent == article)) {
				temp.add(articleCategory);
				if (articleCategory.getChildren() != null
						&& articleCategory.getChildren().size() > 0) {
					recursivPrizeCategoryTreeList(allArticleCategoryList,
							articleCategory, temp);
				}
			}
		}
		return temp;
	}

	@Override
	public boolean isUniqueBySign(String oldSign, String newSign) {
		if(oldSign != null && oldSign.equals(newSign)){
			return true;
		}
		if(articleCategoryDao.isExistBySign(newSign)){
			return false;
		}else{
			return true;
		}
	}
	
	@Cacheable(modelId = "articleCategoryCaching")
	@Transactional(readOnly = true)
	public List<ArticleCategory> getParentArticleCategoryList(ArticleCategory articleCategory, boolean isContainParent, Integer maxResults) {
		List<ArticleCategory> parentArticleCategoryList = articleCategoryDao.getParentArticleCategoryList(articleCategory, isContainParent, maxResults);
		if (parentArticleCategoryList != null) {
			for (ArticleCategory parentArticleCategory : parentArticleCategoryList) {
				if (!Hibernate.isInitialized(parentArticleCategory)) {
					Hibernate.initialize(parentArticleCategory);
				}
			}
		}
		return parentArticleCategoryList;
	}
	
	@Cacheable(modelId = "articleCategoryCaching")
	public List<ArticleCategory> getArticleCategoryPathList(ArticleCategory articleCategory) {
		List<ArticleCategory> articleCategoryPathList = new ArrayList<ArticleCategory>();
		List<ArticleCategory> parentArticleCategoryList = this.getParentArticleCategoryList(articleCategory, true, null);
		if (parentArticleCategoryList != null) {
			articleCategoryPathList.addAll(parentArticleCategoryList);
		}
		articleCategoryPathList.add(articleCategory);
		return articleCategoryPathList;
	}
}
