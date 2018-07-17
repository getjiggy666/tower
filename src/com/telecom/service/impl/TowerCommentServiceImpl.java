package com.telecom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telecom.dao.TowerCommentDao;
import com.telecom.entity.TowerComment;
import com.telecom.service.TowerCommentService;

/**
 * Service实现类 - 评论
 */

@Service("towerCommentServiceImpl")
public class TowerCommentServiceImpl extends
	BaseServiceImpl<TowerComment, String> implements TowerCommentService {

    @Resource(name = "towerCommentDaoImpl")
    TowerCommentDao towerCommentDao;

    @Resource(name = "towerCommentDaoImpl")
    public void setBaseDao(TowerCommentDao towerCommentDao) {
	super.setBaseDao(towerCommentDao);
    }
}
