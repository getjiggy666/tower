package com.telecom.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.dao.ArticleDao;
import com.telecom.entity.Article;
import com.telecom.entity.Article.ArticleType;
import com.telecom.entity.ArticleCategory;

/**
 * Dao实现类 - 文章
 */

@Repository("articleDaoImpl")
public class ArticleDaoImpl extends BaseDaoImpl<Article, String> implements ArticleDao {

	@SuppressWarnings("unchecked")
	public List<Article> getArticleList(ArticleCategory articleCategory, ArticleType articleType, String type, boolean isContainChildren, Integer maxResults) {
		Query query = null;
		if (articleCategory != null) {
			if (StringUtils.equalsIgnoreCase(type, "top")) {
				if (isContainChildren) {
					String hql = "from Article as article where article.isPublication = :isPublication and article.isTop = :isTop and article.articleCategory.path like :path and article.articleType = :articleType order by article.isTop desc, article.createDate desc";
					query = getSession().createQuery(hql);
					query.setParameter("isPublication", true).setParameter("isTop", true).setParameter("path", articleCategory.getPath() + "%").setParameter("articleType", articleType);
				} else {
					String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory = :articleCategory and article.isTop = :isTop and article.articleType = :articleType order by article.isTop desc, article.createDate desc";
					query = getSession().createQuery(hql);
					query.setParameter("isPublication", true).setParameter("articleCategory", articleCategory).setParameter("isTop", true).setParameter("articleType", articleType);
				}
			} else if (StringUtils.equalsIgnoreCase(type, "recommend")) {
				if (isContainChildren) {
					String hql = "from Article as article where article.isPublication = :isPublication and article.isRecommend = :isRecommend and article.articleCategory.path like :path and article.articleType = :articleType order by article.isTop desc, article.createDate desc";
					query = getSession().createQuery(hql);
					query.setParameter("isPublication", true).setParameter("isRecommend", true).setParameter("path", articleCategory.getPath() + "%").setParameter("articleType", articleType);
				} else {
					String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory = :articleCategory and article.isRecommend = :isRecommend and article.articleType = :articleType order by article.isTop desc, article.createDate desc";
					query = getSession().createQuery(hql);
					query.setParameter("isPublication", true).setParameter("articleCategory", articleCategory).setParameter("isRecommend", true).setParameter("articleType", articleType);
				}
			} else if (StringUtils.equalsIgnoreCase(type, "hot")) {
				if (isContainChildren) {
					String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory.path like :path and article.articleType = :articleType order by article.hits desc, article.createDate desc";
					query = getSession().createQuery(hql);
					query.setParameter("isPublication", true).setParameter("path", articleCategory.getPath() + "%").setParameter("articleType", articleType);
				} else {
					String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory = :articleCategory and article.articleType = :articleType order by article.hits desc, article.createDate desc";
					query = getSession().createQuery(hql);
					query.setParameter("isPublication", true).setParameter("articleCategory", articleCategory).setParameter("articleType", articleType);
				}
			} else {
				if (isContainChildren) {
					String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory.path like :path and article.articleType = :articleType order by article.isTop desc, article.createDate desc";
					query = getSession().createQuery(hql);
					query.setParameter("isPublication", true).setParameter("path", articleCategory.getPath() + "%").setParameter("articleType", articleType);
				} else {
					String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory = :articleCategory and article.articleType = :articleType order by article.isTop desc, article.createDate desc";
					query = getSession().createQuery(hql);
					query.setParameter("isPublication", true).setParameter("articleCategory", articleCategory).setParameter("articleType", articleType);
				}
			}
		} else {
			if (StringUtils.equalsIgnoreCase(type, "top")) {
				String hql = "from Article as article where article.isPublication = :isPublication and article.isTop = :isTop and article.articleType = :articleType order by article.isTop desc, article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("isTop", true).setParameter("articleType", articleType);
			} else if (StringUtils.equalsIgnoreCase(type, "recommend")) {
				String hql = "from Article as article where article.isPublication = :isPublication and article.isRecommend = :isRecommend and article.articleType = :articleType order by article.isTop desc, article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("isRecommend", true).setParameter("articleType", articleType);
			} else if (StringUtils.equalsIgnoreCase(type, "hot")) {
				String hql = "from Article as article where article.isPublication = :isPublication and article.articleType = :articleType order by article.hits desc, article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("articleType", articleType);
			} else {
				String hql = "from Article as article where article.isPublication = :isPublication and article.articleType = :articleType order by article.isTop desc, article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("articleType", articleType);
			}
		}
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getArticleList(ArticleCategory articleCategory, Date beginDate, Date endDate, Integer firstResult, Integer maxResults) {
		Query query = null;
		if (articleCategory != null) {
			if (beginDate != null && endDate == null) {
				String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory.path like :path and article.createDate > :beginDate order by article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("path", articleCategory.getPath() + "%").setParameter("beginDate", beginDate);
			} else if (endDate != null && beginDate == null) {
				String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory.path like :path and article.createDate < :endDate order by article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("path", articleCategory.getPath() + "%").setParameter("endDate", endDate);
			} else if (endDate != null && beginDate != null) {
				String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory.path like :path and article.createDate > :beginDate and article.createDate < :endDate order by article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("path", articleCategory.getPath() + "%").setParameter("beginDate", beginDate).setParameter("endDate", endDate);
			} else {
				String hql = "from Article as article where article.isPublication = :isPublication and article.articleCategory.path like :path order by article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("path", articleCategory.getPath() + "%");
			}
		} else {
			if (beginDate != null && endDate == null) {
				String hql = "from Article as article where article.isPublication = :isPublication and article.createDate > :beginDate order by article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("beginDate", beginDate);
			} else if (endDate != null && beginDate == null) {
				String hql = "from Article as article where article.isPublication = :isPublication and article.createDate < :endDate order by article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("endDate", endDate);
			} else if (endDate != null && beginDate != null) {
				String hql = "from Article as article where article.isPublication = :isPublication and article.createDate > :beginDate and article.createDate < :endDate order by article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true).setParameter("beginDate", beginDate).setParameter("endDate", endDate);
			} else {
				String hql = "from Article as article where article.isPublication = :isPublication order by article.createDate desc";
				query = getSession().createQuery(hql);
				query.setParameter("isPublication", true);
			}
		}
		if (firstResult != null) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		return query.list();
	}
	
}
