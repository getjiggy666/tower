package com.telecom.service;

import java.util.Date;
import java.util.List;

import com.telecom.entity.Article;
import com.telecom.entity.ArticleCategory;
import com.telecom.entity.Article.ArticleType;

/**
 * Service接口 - 文章
 */

public interface ArticleService extends BaseService<Article, String> {

	/**
	 * 创建索引
	 * @param article 文章
	 */
	public void index(Article article);
	
	/**
	 * 创建索引
	 */
	public void index();
	
	/**
	 * 根据文章分类、文章类型、是否包含子分类文章、最大结果数获取文章集合（只包含isPublication=true的对象）
	 * 
	 * @param articleCategory
	 *            文章分类,null表示无限制
	 *            
	 * @param articleType
	 *            文章类别       
	 *              
	 * @param type
	 *            文章类型,null表示无限制
	 *            
	 * @param isContainChildren
	 *            是否包含子分类文章
	 * 
	 * @param maxResults
	 *            最大结果数,null表示无限制
	 * 
	 * @return 此分类下的文章集合
	 */
	public List<Article> getArticleList(ArticleCategory articleCategory, ArticleType articleType, String type, boolean isContainChildren, Integer maxResults);
	
	/**
	 * 根据文章分类、起始日期、结束日期、起始结果数、最大结果数获取文章集合（只包含isPublication=true的对象,包含子分类文章）
	 * 
	 * @param articleCategory
	 *            文章分类,null表示无限制
	 *            
	 * @param beginDate
	 *            起始日期,null表示无限制
	 *            
	 * @param endDate
	 *            结束日期,null表示无限制
	 *            
	 * @param firstResult
	 *            起始结果数,null表示无限制
	 *            
	 * @param maxResults
	 *            最大结果数,null表示无限制
	 * 
	 * @return 此分类下的所有文章集合
	 */
	public List<Article> getArticleList(ArticleCategory articleCategory, Date beginDate, Date endDate, Integer firstResult, Integer maxResults);
}
