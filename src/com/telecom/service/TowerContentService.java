package com.telecom.service;

import java.util.List;

import com.telecom.bean.TowerCommentBean;
import com.telecom.bean.TowerContentBean;
import com.telecom.entity.TowerContent;

/**
 * Service接口 - 内容
 */
public interface TowerContentService extends BaseService<TowerContent, String> {

    // 获取首页信息(游客)
    public List<TowerContentBean> getList(String pageNum);

    // 通过账号获取首页信息
    public List<TowerContentBean> getAllListByUser(String towerUserId, String pageNum);

    // 取消收藏
    public boolean no_collection(String towerContentId, String towerUserId);

    // 取消关注
    public boolean no_follow(String towerUserId_fans, String towerUserId);

    // 取消点赞
    public boolean no_praise(String towerContentId, String towerUserId);

    // 搜索
    public List<TowerContentBean> search(String towerUserId, String keyword);

    // 点滴首页,自己的分享内容
    public List<TowerContentBean> getdribhomepage(String towerUserId);

    // 根据点滴分类id,获取该分类内容
    public List<TowerContentBean> getdrib(String dribKindId);

    // 根据内容id获取评论列表
    public List<TowerCommentBean> getCommentListByContentId(
	    String towerContentId);

    // 根据自己塔圈
    public List<TowerContentBean> getMomentsByUser(String towerUserId);

    // 我的收藏
    public List<TowerContentBean> getCollectionByUser(String towerUserId);

    // 精选-需求
    public List<TowerContentBean> getTestA(String towerUserId);

    // 干货
    public List<TowerContentBean> getTestB(String towerUserId);

    // 根据分类id删除分类
	public boolean deleteDribKind(String dribKindId);

    // 调整分类
	public boolean modifyDribKind(String towerContentId, String dribKindId);
}
