package com.telecom.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 评论
 */

@Entity
public class TowerComment extends BaseEntity {

    /**
     * 评论
     */
    private static final long serialVersionUID = -196464852332328565L;

    private TowerContent towerContent;// 文章
    private TowerUser towerUser;// 评论者
    private String message;// 留言

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "fk_tower_content")
    public TowerContent getTowerContent() {
	return towerContent;
    }

    public void setTowerContent(TowerContent towerContent) {
	this.towerContent = towerContent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "fk_tower_user")
    public TowerUser getTowerUser() {
	return towerUser;
    }

    public void setTowerUser(TowerUser towerUser) {
	this.towerUser = towerUser;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
