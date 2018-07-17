package com.telecom.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.dao.ArticleDao;
import com.telecom.entity.Article;
import com.telecom.entity.Article.ArticleType;
import com.telecom.entity.ArticleCategory;
import com.telecom.service.ArticleService;

/**
 * Service实现类 - 文章
 */

@Service("articleServiceImpl")
public class ArticleServiceImpl extends BaseServiceImpl<Article, String> implements ArticleService {
	
	@Resource(name = "articleDaoImpl")
	private ArticleDao articleDao;
	
	@Resource(name = "articleDaoImpl")
	public void setBaseDao(ArticleDao articleDao){
		super.setBaseDao(articleDao);
	}
	
	public void index(Article article) {
		FullTextSession fullTextSession = Search.getFullTextSession(articleDao.getSession());
		fullTextSession.index(article);
	}
	
	public void index() {
		List<Article> articles = articleDao.getAllList();
		FullTextSession fullTextSession = Search.getFullTextSession(articleDao.getSession());
		for (Article article : articles) {
			fullTextSession.index(article);
		}
	}
	
	@Transactional(readOnly = true)
	public List<Article> getArticleList(ArticleCategory articleCategory, ArticleType articleType, String type, boolean isContainChildren, Integer maxResults) {
		return articleDao.getArticleList(articleCategory, articleType, type, isContainChildren, maxResults);
	}
	
	@Transactional(readOnly = true)
	public List<Article> getArticleList(ArticleCategory articleCategory, Date beginDate, Date endDate, Integer firstResult, Integer maxResults) {
		return articleDao.getArticleList(articleCategory, beginDate, endDate, firstResult, maxResults);
	}
}
