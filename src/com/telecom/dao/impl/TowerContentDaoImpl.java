package com.telecom.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.telecom.bean.TowerCommentBean;
import com.telecom.bean.TowerContentBean;
import com.telecom.dao.TowerContentDao;
import com.telecom.entity.TowerCollection;
import com.telecom.entity.TowerComment;
import com.telecom.entity.TowerContent;
import com.telecom.util.SettingUtil;
import com.telecom.util.TowerContentUtil;

/**
 * Dao实现类 - 内容
 */

@Repository("towerContentDaoImpl")
public class TowerContentDaoImpl extends BaseDaoImpl<TowerContent, String> implements TowerContentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerContentBean> getList(String pageNum) {
		// TODO Auto-generated method stub
		List<TowerContent> towerContents;
		if (StringUtils.isNotBlank(pageNum) && TowerContentUtil.isNumeric(pageNum)) {
			int pageNumInt = Integer.parseInt(pageNum);
			Query query = getSession().createQuery("from TowerContent order by id desc");
			query.setFirstResult(pageNumInt * 10 - 10);
			query.setMaxResults(10);
			towerContents = query.list();
		} else {
			towerContents = getAllList();
		}
		List<TowerContentBean> towerContentBeans = new ArrayList<TowerContentBean>();
		for (int i = 0; i < towerContents.size(); i++) {
			TowerContentBean towerContentBean = new TowerContentBean();
			// 内容id
			towerContentBean.setTowerContentId(towerContents.get(i).getId());
			// 作者id
			towerContentBean.setTowerUserId(towerContents.get(i).getTowerUser().getId());
			// 头像
			towerContentBean.setIconUrl(
					SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getTowerUser().getIconUrl());
			// 昵称
			towerContentBean.setName(towerContents.get(i).getTowerUser().getName());
			// 内容类型
			towerContentBean.setContentType(towerContents.get(i).getContentType());
			// 内容标题
			towerContentBean.setTitle(towerContents.get(i).getTitle());
			// 文字内容
			towerContentBean.setContent(towerContents.get(i).getContent());
			if (towerContents.get(i).getContentType().equals("1")) {
				// 图片
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_1())) {
					towerContentBean
							.setImgUrl_1(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_1());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_2())) {
					towerContentBean
							.setImgUrl_2(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_2());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_3())) {
					towerContentBean
							.setImgUrl_3(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_3());
				}
			} else if (towerContents.get(i).getContentType().equals("2")
					|| towerContents.get(i).getContentType().equals("3")) {
				// 视频
				towerContentBean
						.setVideoUrl(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoUrl());
				// 视频第一帧
				if (StringUtils.isNotBlank(towerContents.get(i).getVideoImgUrl())) {
					towerContentBean.setVideoImg(
							SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoImgUrl());
				}
			}
			// 职位
			towerContentBean.setContentKind(towerContents.get(i).getTowerUser().getCategory());
			towerContentBeans.add(towerContentBean);
		}
		Collections.reverse(towerContentBeans);
		return towerContentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerContentBean> getAllListByUser(String towerUserId, String pageNum) {
		// TODO Auto-generated method stub
		List<TowerContent> towerContents;
		if (StringUtils.isNotBlank(pageNum) && TowerContentUtil.isNumeric(pageNum)) {
			int pageNumInt = Integer.parseInt(pageNum);
			Query query = getSession().createQuery("from TowerContent order by id desc");
			query.setFirstResult(pageNumInt * 10 - 10);
			query.setMaxResults(10);
			towerContents = query.list();
		} else {
			towerContents = getAllList();
		}
		List<TowerContentBean> towerContentBeans = new ArrayList<TowerContentBean>();
		for (int i = 0; i < towerContents.size(); i++) {
			TowerContentBean towerContentBean = new TowerContentBean();
			// 内容id
			towerContentBean.setTowerContentId(towerContents.get(i).getId());
			// 作者id
			towerContentBean.setTowerUserId(towerContents.get(i).getTowerUser().getId());
			// 头像
			towerContentBean.setIconUrl(
					SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getTowerUser().getIconUrl());
			// 昵称
			towerContentBean.setName(towerContents.get(i).getTowerUser().getName());

			// 我是否关注
			String sql_follow = "select count(*) from ct_tower_follow where star_id = :star_id and fans_id = :fans_id";
			String isFollow = String.valueOf(getSession().createSQLQuery(sql_follow)
					.setParameter("star_id", towerContents.get(i).getTowerUser().getId())
					.setParameter("fans_id", towerUserId).uniqueResult());
			if (!isFollow.equals("0")) {
				towerContentBean.setFollow("1");
			} else {
				towerContentBean.setFollow("0");
			}

			// 我是否收藏此文章
			String sql_collection = "select count(*) from ct_tower_collection where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
			String isCollection = String.valueOf(getSession().createSQLQuery(sql_collection)
					.setParameter("tower_content_id", towerContents.get(i).getId())
					.setParameter("tower_user_id", towerUserId).uniqueResult());
			if (!isCollection.equals("0")) {
				towerContentBean.setCollection("1");
			} else {
				towerContentBean.setCollection("0");
			}

			// 我是否点赞过
			String sql_praise = "select count(*) from ct_tower_praise where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
			String isPraise = String.valueOf(getSession().createSQLQuery(sql_praise)
					.setParameter("tower_content_id", towerContents.get(i).getId())
					.setParameter("tower_user_id", towerUserId).uniqueResult());
			if (!isPraise.equals("0")) {
				towerContentBean.setPraise("1");
			} else {
				towerContentBean.setPraise("0");
			}

			// 内容类型
			towerContentBean.setContentType(towerContents.get(i).getContentType());
			// 内容标题
			towerContentBean.setTitle(towerContents.get(i).getTitle());
			// 文字内容
			towerContentBean.setContent(towerContents.get(i).getContent());
			// // 图片
			// towerContentBean.setImgUrl_1(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_1());
			// towerContentBean.setImgUrl_2(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_2());
			// towerContentBean.setImgUrl_3(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_3());
			// // 视频
			// towerContentBean.setVideoUrl(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getVideoUrl());
			// // 职位
			// towerContentBean.setContentKind(towerContents.get(i).getTowerUser()
			// .getCategory());
			if (towerContents.get(i).getContentType().equals("1")) {
				// 图片
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_1())) {
					towerContentBean
							.setImgUrl_1(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_1());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_2())) {
					towerContentBean
							.setImgUrl_2(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_2());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_3())) {
					towerContentBean
							.setImgUrl_3(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_3());
				}
			} else if (towerContents.get(i).getContentType().equals("2")
					|| towerContents.get(i).getContentType().equals("3")) {
				// 视频
				towerContentBean
						.setVideoUrl(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoUrl());
				// 视频第一帧
				if (StringUtils.isNotBlank(towerContents.get(i).getVideoImgUrl())) {
					towerContentBean.setVideoImg(
							SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoImgUrl());
				}
			}
			towerContentBeans.add(towerContentBean);
		}
		Collections.reverse(towerContentBeans);
		return towerContentBeans;
	}

	@Override
	public boolean no_collection(String towerContentId, String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "delete from TowerCollection as towerCollection where towerCollection.towerContent.id = :towerContentId"
				+ " and towerCollection.towerUser.id = :towerUserId";
		if (getSession().createQuery(hql).setParameter("towerContentId", towerContentId)
				.setParameter("towerUserId", towerUserId).executeUpdate() >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean no_praise(String towerContentId, String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "delete from TowerPraise as towerPraise where towerPraise.towerContent.id = :towerContentId and towerPraise.towerUser.id = :towerUserId";
		if (getSession().createQuery(hql).setParameter("towerContentId", towerContentId)
				.setParameter("towerUserId", towerUserId).executeUpdate() >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean no_follow(String towerUserId, String towerUserId_fans) {
		// TODO Auto-generated method stub
		String hql = "delete from TowerFollow as towerFollow where towerFollow.star.id = :towerUserId"
				+ " and towerFollow.fans.id = :towerUserId_fans";
		if (getSession().createQuery(hql).setParameter("towerUserId", towerUserId)
				.setParameter("towerUserId_fans", towerUserId_fans).executeUpdate() >= 1) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerContentBean> search(String towerUserId, String keyword) {
		// TODO Auto-generated method stub
		String hql = "from TowerContent as towerContent where towerContent.title like :keyword or towerContent.towerUser.name like :keyword";
		List<TowerContent> towerContents = getSession().createQuery(hql).setParameter("keyword", "%" + keyword + "%")
				.list();
		System.out.println(keyword + "===" + towerContents.size());
		List<TowerContentBean> towerContentBeans = new ArrayList<TowerContentBean>();
		for (int i = 0; i < towerContents.size(); i++) {
			TowerContentBean towerContentBean = new TowerContentBean();
			// id
			towerContentBean.setTowerContentId(towerContents.get(i).getId());
			// 作者id
			towerContentBean.setTowerUserId(towerContents.get(i).getTowerUser().getId());
			// 头像
			towerContentBean.setIconUrl(
					SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getTowerUser().getIconUrl());
			// 昵称
			towerContentBean.setName(towerContents.get(i).getTowerUser().getName());
			// 内容类型
			towerContentBean.setContentType(towerContents.get(i).getContentType());
			// 内容标题
			towerContentBean.setTitle(towerContents.get(i).getTitle());
			// 文字内容
			towerContentBean.setContent(towerContents.get(i).getContent());
			// // 图片
			// towerContentBean.setImgUrl_1(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_1());
			// towerContentBean.setImgUrl_2(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_2());
			// towerContentBean.setImgUrl_3(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_3());
			// // 视频
			// towerContentBean.setVideoUrl(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getVideoUrl());
			if (towerContents.get(i).getContentType().equals("1")) {
				// 图片
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_1())) {
					towerContentBean
							.setImgUrl_1(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_1());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_2())) {
					towerContentBean
							.setImgUrl_2(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_2());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_3())) {
					towerContentBean
							.setImgUrl_3(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_3());
				}
			} else if (towerContents.get(i).getContentType().equals("2")
					|| towerContents.get(i).getContentType().equals("3")) {
				// 视频
				towerContentBean
						.setVideoUrl(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoUrl());
				// 视频第一帧
				if (StringUtils.isNotBlank(towerContents.get(i).getVideoImgUrl())) {
					towerContentBean.setVideoImg(
							SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoImgUrl());
				}
			}
			if (StringUtils.isNotBlank(towerUserId)) {
				// 我是否关注
				String sql_follow = "select count(*) from ct_tower_follow where star_id = :star_id and fans_id = :fans_id";
				String isFollow = String.valueOf(getSession().createSQLQuery(sql_follow)
						.setParameter("star_id", towerContents.get(i).getTowerUser().getId())
						.setParameter("fans_id", towerUserId).uniqueResult());
				if (!isFollow.equals("0")) {
					towerContentBean.setFollow("1");
				} else {
					towerContentBean.setFollow("0");
				}

				// 我是否收藏此文章
				String sql_collection = "select count(*) from ct_tower_collection where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
				String isCollection = String.valueOf(getSession().createSQLQuery(sql_collection)
						.setParameter("tower_content_id", towerContents.get(i).getId())
						.setParameter("tower_user_id", towerUserId).uniqueResult());
				if (!isCollection.equals("0")) {
					towerContentBean.setCollection("1");
				} else {
					towerContentBean.setCollection("0");
				}

				// 我是否点赞过
				String sql_praise = "select count(*) from ct_tower_praise where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
				String isPraise = String.valueOf(getSession().createSQLQuery(sql_praise)
						.setParameter("tower_content_id", towerContents.get(i).getId())
						.setParameter("tower_user_id", towerUserId).uniqueResult());
				if (!isPraise.equals("0")) {
					towerContentBean.setPraise("1");
				} else {
					towerContentBean.setPraise("0");
				}
			} else {
				towerContentBean.setFollow("0");
				towerContentBean.setCollection("0");
				towerContentBean.setPraise("0");
			}
			// 职位
			towerContentBean.setContentKind(towerContents.get(i).getTowerUser().getCategory());
			towerContentBeans.add(towerContentBean);
		}
		Collections.reverse(towerContentBeans);
		return towerContentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerContentBean> getdribhomepage(String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "from TowerContent as towerContent where towerContent.towerUser.id = :towerUserId";
		List<TowerContent> towerContents = getSession().createQuery(hql).setParameter("towerUserId", towerUserId)
				.list();
		List<TowerContentBean> towerContentBeans = new ArrayList<TowerContentBean>();
		for (int i = 0; i < towerContents.size(); i++) {
			TowerContentBean towerContentBean = new TowerContentBean();
			// id
			towerContentBean.setTowerContentId(towerContents.get(i).getId());
			// 作者id
			towerContentBean.setTowerUserId(towerContents.get(i).getTowerUser().getId());
			// 头像
			towerContentBean.setIconUrl(
					SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getTowerUser().getIconUrl());
			// 昵称
			towerContentBean.setName(towerContents.get(i).getTowerUser().getName());
			// 内容类型
			towerContentBean.setContentType(towerContents.get(i).getContentType());
			// 内容标题
			towerContentBean.setTitle(towerContents.get(i).getTitle());
			// 文字内容
			towerContentBean.setContent(towerContents.get(i).getContent());
			// // 图片
			// towerContentBean.setImgUrl_1(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_1());
			// towerContentBean.setImgUrl_2(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_2());
			// towerContentBean.setImgUrl_3(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_3());
			// // 视频
			// towerContentBean.setVideoUrl(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getVideoUrl());
			if (towerContents.get(i).getContentType().equals("1")) {
				// 图片
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_1())) {
					towerContentBean
							.setImgUrl_1(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_1());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_2())) {
					towerContentBean
							.setImgUrl_2(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_2());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_3())) {
					towerContentBean
							.setImgUrl_3(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_3());
				}
			} else if (towerContents.get(i).getContentType().equals("2")
					|| towerContents.get(i).getContentType().equals("3")) {
				// 视频
				towerContentBean
						.setVideoUrl(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoUrl());
				// 视频第一帧
				if (StringUtils.isNotBlank(towerContents.get(i).getVideoImgUrl())) {
					towerContentBean.setVideoImg(
							SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoImgUrl());
				}
			}
			// 职位
			towerContentBean.setContentKind(towerContents.get(i).getTowerUser().getCategory());
			towerContentBeans.add(towerContentBean);
		}
		Collections.reverse(towerContentBeans);
		return towerContentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerContentBean> getdrib(String dribKindId) {
		// TODO Auto-generated method stub
		String hql = "from TowerContent as towerContent where towerContent.towerDribKind.id = :dribKindId";
		List<TowerContent> towerContents = getSession().createQuery(hql).setParameter("dribKindId", dribKindId).list();
		List<TowerContentBean> towerContentBeans = new ArrayList<TowerContentBean>();
		for (int i = 0; i < towerContents.size(); i++) {
			TowerContentBean towerContentBean = new TowerContentBean();
			// id
			towerContentBean.setTowerContentId(towerContents.get(i).getId());
			// 作者id
			towerContentBean.setTowerUserId(towerContents.get(i).getTowerUser().getId());
			// 头像
			towerContentBean.setIconUrl(
					SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getTowerUser().getIconUrl());
			// 昵称
			towerContentBean.setName(towerContents.get(i).getTowerUser().getName());
			// 内容类型
			towerContentBean.setContentType(towerContents.get(i).getContentType());
			// 内容标题
			towerContentBean.setTitle(towerContents.get(i).getTitle());
			// 文字内容
			towerContentBean.setContent(towerContents.get(i).getContent());
			// // 图片
			// towerContentBean.setImgUrl_1(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_1());
			// towerContentBean.setImgUrl_2(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_2());
			// towerContentBean.setImgUrl_3(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_3());
			// // 视频
			// towerContentBean.setVideoUrl(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getVideoUrl());
			if (towerContents.get(i).getContentType().equals("1")) {
				// 图片
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_1())) {
					towerContentBean
							.setImgUrl_1(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_1());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_2())) {
					towerContentBean
							.setImgUrl_2(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_2());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_3())) {
					towerContentBean
							.setImgUrl_3(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_3());
				}
			} else if (towerContents.get(i).getContentType().equals("2")
					|| towerContents.get(i).getContentType().equals("3")) {
				// 视频
				towerContentBean
						.setVideoUrl(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoUrl());
				// 视频第一帧
				if (StringUtils.isNotBlank(towerContents.get(i).getVideoImgUrl())) {
					towerContentBean.setVideoImg(
							SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoImgUrl());
				}
			}
			// 职位
			towerContentBean.setContentKind(towerContents.get(i).getTowerUser().getCategory());
			towerContentBeans.add(towerContentBean);
		}
		Collections.reverse(towerContentBeans);
		return towerContentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerCommentBean> getCommentListByContentId(String towerContentId) {
		// TODO Auto-generated method stub
		String hql = "from TowerComment as towerComment where towerComment.towerContent.id = :towerContentId";
		List<TowerComment> towerComments = getSession().createQuery(hql).setParameter("towerContentId", towerContentId)
				.list();
		List<TowerCommentBean> towerCommentBeans = new ArrayList<TowerCommentBean>();
		for (int i = 0; i < towerComments.size(); i++) {
			TowerCommentBean towerCommentBean = new TowerCommentBean();
			// 用户id
			towerCommentBean.setTowerUserId(towerComments.get(i).getTowerUser().getId());
			// 用户头像
			towerCommentBean.setIconUrl(
					SettingUtil.getSetting().getUpFilePath() + towerComments.get(i).getTowerUser().getIconUrl());
			// 用户昵称
			towerCommentBean.setName(towerComments.get(i).getTowerUser().getName());
			// 评论时间
			towerCommentBean.setDate(towerComments.get(i).getCreateDate());
			// 评论内容
			towerCommentBean.setComment(towerComments.get(i).getMessage());
			towerCommentBeans.add(towerCommentBean);
		}
		Collections.reverse(towerCommentBeans);
		return towerCommentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerContentBean> getMomentsByUser(String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "select towerContent from TowerContent towerContent,TowerFollow towerFollow where towerContent.towerUser.id = towerFollow.star.id and towerFollow.fans.id = :towerUserId";
		List<TowerContent> towerContents = getSession().createQuery(hql).setParameter("towerUserId", towerUserId)
				.list();
		List<TowerContentBean> towerContentBeans = new ArrayList<TowerContentBean>();
		for (int i = 0; i < towerContents.size(); i++) {
			TowerContentBean towerContentBean = new TowerContentBean();
			// 内容id
			towerContentBean.setTowerContentId(towerContents.get(i).getId());
			// 作者id
			towerContentBean.setTowerUserId(towerContents.get(i).getTowerUser().getId());
			// 头像
			towerContentBean.setIconUrl(
					SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getTowerUser().getIconUrl());
			// 昵称
			towerContentBean.setName(towerContents.get(i).getTowerUser().getName());

			// 我是否收藏此文章
			String sql_collection = "select count(*) from ct_tower_collection where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
			String isCollection = String.valueOf(getSession().createSQLQuery(sql_collection)
					.setParameter("tower_content_id", towerContents.get(i).getId())
					.setParameter("tower_user_id", towerUserId).uniqueResult());
			if (!isCollection.equals("0")) {
				towerContentBean.setCollection("1");
			} else {
				towerContentBean.setCollection("0");
			}

			// 我是否点赞过
			String sql_praise = "select count(*) from ct_tower_praise where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
			String isPraise = String.valueOf(getSession().createSQLQuery(sql_praise)
					.setParameter("tower_content_id", towerContents.get(i).getId())
					.setParameter("tower_user_id", towerUserId).uniqueResult());
			if (!isPraise.equals("0")) {
				towerContentBean.setPraise("1");
			} else {
				towerContentBean.setPraise("0");
			}
			// // 我是否收藏此文章
			// String sql_collection =
			// "select count(*) from ct_tower_collection where tower_content_id
			// = :tower_content_id and tower_user_id = :tower_user_id";
			// towerContentBean
			// .setFollow(String.valueOf(getSession()
			// .createSQLQuery(sql_collection)
			// .setParameter("tower_content_id",
			// towerContents.get(i).getId())
			// .setParameter("tower_user_id", towerUserId)
			// .uniqueResult()));
			// // 我是否点赞过
			// String sql_praise =
			// "select count(*) from ct_tower_praise where tower_content_id =
			// :tower_content_id and tower_user_id = :tower_user_id";
			// towerContentBean
			// .setPraise(String.valueOf(getSession()
			// .createSQLQuery(sql_praise)
			// .setParameter("tower_content_id",
			// towerContents.get(i).getId())
			// .setParameter("tower_user_id", towerUserId)
			// .uniqueResult()));
			// 内容类型
			towerContentBean.setContentType(towerContents.get(i).getContentType());
			// 内容标题
			towerContentBean.setTitle(towerContents.get(i).getTitle());
			// 文字内容
			towerContentBean.setContent(towerContents.get(i).getContent());
			// // 图片
			// towerContentBean.setImgUrl_1(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_1());
			// towerContentBean.setImgUrl_2(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_2());
			// towerContentBean.setImgUrl_3(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_3());
			// // 视频
			// towerContentBean.setVideoUrl(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getVideoUrl());
			if (towerContents.get(i).getContentType().equals("1")) {
				// 图片
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_1())) {
					towerContentBean
							.setImgUrl_1(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_1());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_2())) {
					towerContentBean
							.setImgUrl_2(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_2());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_3())) {
					towerContentBean
							.setImgUrl_3(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_3());
				}
			} else if (towerContents.get(i).getContentType().equals("2")
					|| towerContents.get(i).getContentType().equals("3")) {
				// 视频
				towerContentBean
						.setVideoUrl(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoUrl());
				// 视频第一帧
				if (StringUtils.isNotBlank(towerContents.get(i).getVideoImgUrl())) {
					towerContentBean.setVideoImg(
							SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoImgUrl());
				}
			}
			// 职位
			towerContentBean.setContentKind(towerContents.get(i).getTowerUser().getCategory());
			towerContentBeans.add(towerContentBean);
		}
		Collections.reverse(towerContentBeans);
		return towerContentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerContentBean> getCollectionByUser(String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "from TowerCollection as towerCollection where towerCollection.towerUser.id = :towerUserId";
		List<TowerCollection> towerCollections = getSession().createQuery(hql).setParameter("towerUserId", towerUserId)
				.list();
		List<TowerContentBean> towerContentBeans = new ArrayList<TowerContentBean>();
		for (int i = 0; i < towerCollections.size(); i++) {
			TowerContentBean towerContentBean = new TowerContentBean();
			// 内容id
			towerContentBean.setTowerContentId(towerCollections.get(i).getTowerContent().getId());
			// 作者id
			towerContentBean.setTowerUserId(towerCollections.get(i).getTowerUser().getId());
			// 头像
			towerContentBean.setIconUrl(SettingUtil.getSetting().getUpFilePath()
					+ towerCollections.get(i).getTowerContent().getTowerUser().getIconUrl());
			// 昵称
			towerContentBean.setName(towerCollections.get(i).getTowerContent().getTowerUser().getName());

			// 我是否关注
			String sql_follow = "select count(*) from ct_tower_follow where star_id = :star_id and fans_id = :fans_id";
			String isFollow = String.valueOf(getSession().createSQLQuery(sql_follow)
					.setParameter("star_id", towerCollections.get(i).getTowerUser().getId())
					.setParameter("fans_id", towerUserId).uniqueResult());
			if (!isFollow.equals("0")) {
				towerContentBean.setFollow("1");
			} else {
				towerContentBean.setFollow("0");
			}

			// 我是否点赞过
			String sql_praise = "select count(*) from ct_tower_praise where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
			String isPraise = String.valueOf(getSession().createSQLQuery(sql_praise)
					.setParameter("tower_content_id", towerCollections.get(i).getTowerContent().getId())
					.setParameter("tower_user_id", towerUserId).uniqueResult());
			if (!isPraise.equals("0")) {
				towerContentBean.setPraise("1");
			} else {
				towerContentBean.setPraise("0");
			}
			// 我是否点赞过
			// String sql_praise =
			// "select count(*) from ct_tower_praise where tower_content_id =
			// :tower_content_id and tower_user_id = :tower_user_id";
			// towerContentBean
			// .setPraise(String.valueOf(getSession()
			// .createSQLQuery(sql_praise)
			// .setParameter(
			// "tower_content_id",
			// towerCollections.get(i).getTowerContent()
			// .getId())
			// .setParameter("tower_user_id", towerUserId)
			// .uniqueResult()));
			// 内容类型
			towerContentBean.setContentType(towerCollections.get(i).getTowerContent().getContentType());
			// 内容标题
			towerContentBean.setTitle(towerCollections.get(i).getTowerContent().getTitle());
			// 文字内容
			towerContentBean.setContent(towerCollections.get(i).getTowerContent().getContent());
			// // 图片
			// towerContentBean.setImgUrl_1(SettingUtil.getSetting()
			// .getUpFilePath()
			// + towerCollections.get(i).getTowerContent().getImgUrl_1());
			// towerContentBean.setImgUrl_2(SettingUtil.getSetting()
			// .getUpFilePath()
			// + towerCollections.get(i).getTowerContent().getImgUrl_2());
			// towerContentBean.setImgUrl_3(SettingUtil.getSetting()
			// .getUpFilePath()
			// + towerCollections.get(i).getTowerContent().getImgUrl_3());
			// // 视频
			// towerContentBean.setVideoUrl(SettingUtil.getSetting()
			// .getUpFilePath()
			// + towerCollections.get(i).getTowerContent().getVideoUrl());
			if (towerCollections.get(i).getTowerContent().getContentType().equals("1")) {
				// 图片
				if (StringUtils.isNotBlank(towerCollections.get(i).getTowerContent().getImgUrl_1())) {
					towerContentBean.setImgUrl_1(SettingUtil.getSetting().getUpFilePath()
							+ towerCollections.get(i).getTowerContent().getImgUrl_1());
				}
				if (StringUtils.isNotBlank(towerCollections.get(i).getTowerContent().getImgUrl_2())) {
					towerContentBean.setImgUrl_2(SettingUtil.getSetting().getUpFilePath()
							+ towerCollections.get(i).getTowerContent().getImgUrl_2());
				}
				if (StringUtils.isNotBlank(towerCollections.get(i).getTowerContent().getImgUrl_3())) {
					towerContentBean.setImgUrl_3(SettingUtil.getSetting().getUpFilePath()
							+ towerCollections.get(i).getTowerContent().getImgUrl_3());
				}
			} else if (towerCollections.get(i).getTowerContent().getContentType().equals("2")
					|| towerCollections.get(i).getTowerContent().getContentType().equals("3")) {
				// 视频
				towerContentBean.setVideoUrl(SettingUtil.getSetting().getUpFilePath()
						+ towerCollections.get(i).getTowerContent().getVideoUrl());
				// 视频第一帧
				if (StringUtils.isNotBlank(towerCollections.get(i).getTowerContent().getVideoImgUrl())) {
					towerContentBean.setVideoImg(SettingUtil.getSetting().getUpFilePath()
							+ towerCollections.get(i).getTowerContent().getVideoImgUrl());
				}
			}
			// 职位
			towerContentBean.setContentKind(towerCollections.get(i).getTowerContent().getTowerUser().getCategory());
			towerContentBeans.add(towerContentBean);
		}
		Collections.reverse(towerContentBeans);
		return towerContentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerContentBean> getTestA(String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "from TowerContent as towerContent where towerContent.contentType = '4'";
		List<TowerContent> towerContents = getSession().createQuery(hql).list();
		List<TowerContentBean> towerContentBeans = new ArrayList<TowerContentBean>();
		for (int i = 0; i < towerContents.size(); i++) {
			TowerContentBean towerContentBean = new TowerContentBean();
			// 内容id
			towerContentBean.setTowerContentId(towerContents.get(i).getId());
			// 作者id
			towerContentBean.setTowerUserId(towerContents.get(i).getTowerUser().getId());
			// 头像
			towerContentBean.setIconUrl(
					SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getTowerUser().getIconUrl());
			// 昵称
			towerContentBean.setName(towerContents.get(i).getTowerUser().getName());
			// 我是否关注
			String sql_follow = "select count(*) from ct_tower_follow where star_id = :star_id and fans_id = :fans_id";
			String isFollow = String.valueOf(getSession().createSQLQuery(sql_follow)
					.setParameter("star_id", towerContents.get(i).getTowerUser().getId())
					.setParameter("fans_id", towerUserId).uniqueResult());
			if (!isFollow.equals("0")) {
				towerContentBean.setFollow("1");
			} else {
				towerContentBean.setFollow("0");
			}

			// 我是否收藏此文章
			String sql_collection = "select count(*) from ct_tower_collection where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
			String isCollection = String.valueOf(getSession().createSQLQuery(sql_collection)
					.setParameter("tower_content_id", towerContents.get(i).getId())
					.setParameter("tower_user_id", towerUserId).uniqueResult());
			if (!isCollection.equals("0")) {
				towerContentBean.setCollection("1");
			} else {
				towerContentBean.setCollection("0");
			}

			// 我是否点赞过
			String sql_praise = "select count(*) from ct_tower_praise where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
			String isPraise = String.valueOf(getSession().createSQLQuery(sql_praise)
					.setParameter("tower_content_id", towerContents.get(i).getId())
					.setParameter("tower_user_id", towerUserId).uniqueResult());
			if (!isPraise.equals("0")) {
				towerContentBean.setPraise("1");
			} else {
				towerContentBean.setPraise("0");
			}
			// 内容类型
			towerContentBean.setContentType(towerContents.get(i).getContentType());
			// 内容标题
			towerContentBean.setTitle(towerContents.get(i).getTitle());
			// 文字内容
			towerContentBean.setContent(towerContents.get(i).getContent());
			// // 图片
			// towerContentBean.setImgUrl_1(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_1());
			// towerContentBean.setImgUrl_2(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_2());
			// towerContentBean.setImgUrl_3(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_3());
			// // 视频
			// towerContentBean.setVideoUrl(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getVideoUrl());
			if (towerContents.get(i).getContentType().equals("1")) {
				// 图片
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_1())) {
					towerContentBean
							.setImgUrl_1(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_1());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_2())) {
					towerContentBean
							.setImgUrl_2(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_2());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_3())) {
					towerContentBean
							.setImgUrl_3(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_3());
				}
			} else if (towerContents.get(i).getContentType().equals("2")
					|| towerContents.get(i).getContentType().equals("3")) {
				// 视频
				towerContentBean
						.setVideoUrl(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoUrl());
				// 视频第一帧
				if (StringUtils.isNotBlank(towerContents.get(i).getVideoImgUrl())) {
					towerContentBean.setVideoImg(
							SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoImgUrl());
				}
			}

			// 职位
			towerContentBean.setContentKind(towerContents.get(i).getTowerUser().getCategory());
			towerContentBeans.add(towerContentBean);
		}
		Collections.reverse(towerContentBeans);
		return towerContentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TowerContentBean> getTestB(String towerUserId) {
		// TODO Auto-generated method stub
		String hql = "from TowerContent as towerContent where towerContent.towerUser.id = '8a48e28f63ca054d0163ca0639ce0001'";
		List<TowerContent> towerContents = getSession().createQuery(hql).list();
		List<TowerContentBean> towerContentBeans = new ArrayList<TowerContentBean>();
		for (int i = 0; i < towerContents.size(); i++) {
			TowerContentBean towerContentBean = new TowerContentBean();
			// 内容id
			towerContentBean.setTowerContentId(towerContents.get(i).getId());
			// 作者id
			towerContentBean.setTowerUserId(towerContents.get(i).getTowerUser().getId());
			// 头像
			towerContentBean.setIconUrl(
					SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getTowerUser().getIconUrl());
			// 昵称
			towerContentBean.setName(towerContents.get(i).getTowerUser().getName());
			// 我是否关注
			String sql_follow = "select count(*) from ct_tower_follow where star_id = :star_id and fans_id = :fans_id";
			String isFollow = String.valueOf(getSession().createSQLQuery(sql_follow)
					.setParameter("star_id", towerContents.get(i).getTowerUser().getId())
					.setParameter("fans_id", towerUserId).uniqueResult());
			if (!isFollow.equals("0")) {
				towerContentBean.setFollow("1");
			} else {
				towerContentBean.setFollow("0");
			}

			// 我是否收藏此文章
			String sql_collection = "select count(*) from ct_tower_collection where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
			String isCollection = String.valueOf(getSession().createSQLQuery(sql_collection)
					.setParameter("tower_content_id", towerContents.get(i).getId())
					.setParameter("tower_user_id", towerUserId).uniqueResult());
			if (!isCollection.equals("0")) {
				towerContentBean.setCollection("1");
			} else {
				towerContentBean.setCollection("0");
			}

			// 我是否点赞过
			String sql_praise = "select count(*) from ct_tower_praise where tower_content_id = :tower_content_id and tower_user_id = :tower_user_id";
			String isPraise = String.valueOf(getSession().createSQLQuery(sql_praise)
					.setParameter("tower_content_id", towerContents.get(i).getId())
					.setParameter("tower_user_id", towerUserId).uniqueResult());
			if (!isPraise.equals("0")) {
				towerContentBean.setPraise("1");
			} else {
				towerContentBean.setPraise("0");
			}
			// 内容类型
			towerContentBean.setContentType(towerContents.get(i).getContentType());
			// 内容标题
			towerContentBean.setTitle(towerContents.get(i).getTitle());
			// 文字内容
			towerContentBean.setContent(towerContents.get(i).getContent());
			// // 图片
			// towerContentBean.setImgUrl_1(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_1());
			// towerContentBean.setImgUrl_2(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_2());
			// towerContentBean.setImgUrl_3(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getImgUrl_3());
			// // 视频
			// towerContentBean.setVideoUrl(SettingUtil.getSetting()
			// .getUpFilePath() + towerContents.get(i).getVideoUrl());
			if (towerContents.get(i).getContentType().equals("1")) {
				// 图片
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_1())) {
					towerContentBean
							.setImgUrl_1(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_1());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_2())) {
					towerContentBean
							.setImgUrl_2(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_2());
				}
				if (StringUtils.isNotBlank(towerContents.get(i).getImgUrl_3())) {
					towerContentBean
							.setImgUrl_3(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getImgUrl_3());
				}
			} else if (towerContents.get(i).getContentType().equals("2")
					|| towerContents.get(i).getContentType().equals("3")) {
				// 视频
				towerContentBean
						.setVideoUrl(SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoUrl());
				// 视频第一帧
				if (StringUtils.isNotBlank(towerContents.get(i).getVideoImgUrl())) {
					towerContentBean.setVideoImg(
							SettingUtil.getSetting().getUpFilePath() + towerContents.get(i).getVideoImgUrl());
				}
			}
			// 职位
			towerContentBean.setContentKind(towerContents.get(i).getTowerUser().getCategory());
			towerContentBeans.add(towerContentBean);
		}
		Collections.reverse(towerContentBeans);
		return towerContentBeans;
	}

	@Override
	public boolean deleteDribKind(String dribKindId) {
		// TODO Auto-generated method stub
		String sql_1 = "update ct_tower_content set tower_drib_kind_id = null where tower_drib_kind_id = :dribKindId";
		getSession().createSQLQuery(sql_1).setParameter("dribKindId", dribKindId).executeUpdate();
		String sql_2 = "delete from ct_tower_drib_kind where id = :dribKindId";
		getSession().createSQLQuery(sql_2).setParameter("dribKindId", dribKindId).executeUpdate();
		return true;
	}

	@Override
	public boolean modifyDribKind(String towerContentId, String dribKindId) {
		// TODO Auto-generated method stub
		String sql_1 = "update ct_tower_content set tower_drib_kind_id = :dribKindId where id = :towerContentId";
		if (getSession().createSQLQuery(sql_1).setParameter("dribKindId", dribKindId)
				.setParameter("towerContentId", towerContentId).executeUpdate() >= 1) {
			return true;
		}
		return false;
	}
}
