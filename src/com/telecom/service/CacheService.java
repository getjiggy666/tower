package com.telecom.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Service接口 - 缓存
 */

public interface CacheService {

	/**
	 * 刷新文章列表页面缓存
	 * 
	 * @param request
	 *            HttpServletRequest
	 * 
	 */
	public void flushArticleListPageCache(HttpServletRequest request);

	/**
	 * 刷新奖品列表页面缓存
	 * 
	 * @param request
	 *            HttpServletRequest
	 * 
	 */
	public void flushGoodsListPageCache(HttpServletRequest request);

	/**
	 * 刷新奖品评论列表页面缓存
	 * 
	 * @param request
	 *            HttpServletRequest
	 * 
	 */
	public void flushCommentListPageCache(HttpServletRequest request);

	/**
	 * 刷新在线留言页面缓存
	 * 
	 * @param request
	 *            HttpServletRequest
	 * 
	 */
	public void flushLeaveMessagePageCache(HttpServletRequest request);

	/**
	 * 刷新所有页面缓存
	 * 
	 * @param request
	 *            HttpServletRequest
	 * 
	 */
	public void flushAllPageCache(HttpServletRequest request);

	/**
	 * 刷新奖品页面缓存
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	public void flushPrizeListPageCache(HttpServletRequest request);

}