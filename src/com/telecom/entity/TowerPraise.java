package com.telecom.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 点赞
 */

@Entity
public class TowerPraise extends BaseEntity {

    /**
     * 点赞
     */
    private static final long serialVersionUID = 984039048141123542L;
    private TowerUser towerUser;// 点赞者
    private TowerContent towerContent;// 点赞的内容

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "fk_tower_user")
    public TowerUser getTowerUser() {
	return towerUser;
    }

    public void setTowerUser(TowerUser towerUser) {
	this.towerUser = towerUser;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "fk_tower_content")
    public TowerContent getTowerContent() {
	return towerContent;
    }

    public void setTowerContent(TowerContent towerContent) {
	this.towerContent = towerContent;
    }
}
