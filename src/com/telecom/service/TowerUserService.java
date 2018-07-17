package com.telecom.service;

import java.util.List;

import com.telecom.bean.TowerUserBean;
import com.telecom.entity.TowerUser;

/**
 * Service接口 - 用户
 */
public interface TowerUserService extends BaseService<TowerUser, String> {

    // 通过手机号获得id
    public String getTowerUserIdByPhone(String phone);

    // 登录
    public boolean login(String phone, String password);

    // 获取达人列表(游客)
    public List<TowerUserBean> getgreatmanlist(String greatManType);

    // 获取达人列表(用户)
    public List<TowerUserBean> getgreatmanlist(String towerUserId,
	    String greatManType);

    // 获取关注数量
    public String getFollowNum(String towerUserId);

    // 获取粉丝数量
    public String getFansNum(String towerUserId);

    // 获取关注列表
    public List<TowerUserBean> getFollow(String towerUserId);

    // 获取粉丝列表
    public List<TowerUserBean> getFans(String towerUserId);

    // 是否关注某人
	public String isFollow(String towerUserId_star, String towerUserId);
}
