package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.TowerCommentDao;
import com.telecom.entity.TowerComment;

/**
 * Dao实现类 - 评论
 */
@Repository("towerCommentDaoImpl")
public class TowerCommentDaoImpl extends BaseDaoImpl<TowerComment, String>
	implements TowerCommentDao {

}
