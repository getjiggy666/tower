package com.telecom.dao;

import java.util.List;

import com.telecom.entity.ArticleCategory;

/**
 * Dao接口 - 文章分类
 */

public interface ArticleCategoryDao extends BaseDao<ArticleCategory, String> {

	/**
	 * 判断标识是否存在
	 * @param sign 标识
	 * @return 布尔值
	 */
	public boolean isExistBySign(String sign);
	
	/**
	 * 根据ArticleCategory对象获取所有父类集合,若无父类则返回null
	 * 
	 * @param articleCategory
	 *            文章分类,null表示无限制
	 *            
	 * @param isContainParent
	 *            是否包含所有父分类
	 * 
	 * @param maxResults
	 *            最大结果数,null表示无限制
	 * 
	 * @return 父类集合
	 * 
	 */
	public List<ArticleCategory> getParentArticleCategoryList(ArticleCategory articleCategory, boolean isContainParent, Integer maxResults);
}
