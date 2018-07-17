package com.telecom.dao;

import java.util.List;

import com.telecom.bean.TowerDribKindBean;
import com.telecom.entity.TowerDribKind;

/**
 * Dao实现类 - 点滴种类
 */
public interface TowerDribKindDao extends BaseDao<TowerDribKind, String> {

    // 获取种类
    public List<TowerDribKindBean> getKind(String towerUserId);

    // 验证该用户此分类是否存在
    public TowerDribKind isKindExist(String towerUserId,
	    String towerDribKindName);
}
