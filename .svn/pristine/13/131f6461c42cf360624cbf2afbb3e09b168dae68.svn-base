package com.telecom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.dao.ArticleCategoryDao;
import com.telecom.entity.ArticleCategory;

/**
 * Dao实现类 - 文章分类
 */

@Repository("articleCategoryDaoImpl")
public class ArticleCategoryDaoImpl extends BaseDaoImpl<ArticleCategory, String> implements ArticleCategoryDao {

	@Override
	public boolean isExistBySign(String sign) {
		String hql = "from ArticleCategory as articleCategory where lower(articleCategory.sign) = lower(:sign)";
		ArticleCategory articleCategory = (ArticleCategory) getSession().createQuery(hql).setParameter("sign", sign).uniqueResult();
		if(articleCategory == null){
			return false;
		}else{
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleCategory> getParentArticleCategoryList(ArticleCategory articleCategory, boolean isContainParent, Integer maxResults) {
		Query query = null;
		if (articleCategory != null) {
			if (isContainParent) {
				if (articleCategory.getParent() == null) {
					return null;
				}
				String parentPath = StringUtils.substringBeforeLast(articleCategory.getPath(), ArticleCategory.PATH_SEPARATOR);
				String[] ids = parentPath.split(ArticleCategory.PATH_SEPARATOR);
				String hql = "from ArticleCategory as articleCategory where articleCategory.id in(:ids) order by articleCategory.grade asc, articleCategory.orderList asc";
				query = getSession().createQuery(hql);
				query.setParameterList("ids", ids);
			} else {
				List<ArticleCategory> parentArticleCategoryList = null;
				ArticleCategory parent = articleCategory.getParent();
				if (maxResults > 0 && parent != null) {
					parentArticleCategoryList = new ArrayList<ArticleCategory>();
					parentArticleCategoryList.add(parent);
				}
				return parentArticleCategoryList;
			}
		} else {
			String hql = "from ArticleCategory as articleCategory order by articleCategory.grade asc, articleCategory.orderList asc";
			query = getSession().createQuery(hql);
		}
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		return query.list();
	}

}
