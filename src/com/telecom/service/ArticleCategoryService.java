package com.telecom.service;

import java.util.List;

import com.telecom.entity.ArticleCategory;

/**
 * Service接口 - 文章分类
 */

public interface ArticleCategoryService extends BaseService<ArticleCategory, String> {

	/**
	 * 判断标识是否存在
	 * @param sign 标识
	 * @return 布尔值
	 */
	public boolean isExistBySign(String sign);
	
	/**
	 * 获取文章分类树节点
	 * @return 分类树节点
	 */
	public List<ArticleCategory> getArticleCategoryTreeList();
	
	/**
	 * 判断标识是否唯一（不区分大小写）
	 * 
	 * @param sign
	 *            旧标识
	 *            
	 * @param newSign
	 *            新标识
	 * 
	 */
	public boolean isUniqueBySign(String oldSign, String newSign);
	
	/**
	 * 根据ArticleCategory对象获取路径集合
	 * 
	 * @param articleCategory
	 *            articleCategory
	 * 
	 * @return ArticleCategory路径集合
	 * 
	 */
	public List<ArticleCategory> getArticleCategoryPathList(ArticleCategory articleCategory);
}
