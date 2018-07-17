package com.telecom.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 收藏
 */

@Entity
public class TowerCollection extends BaseEntity {

    /**
     * 收藏
     */
    private static final long serialVersionUID = 1202188775883959477L;

    private TowerUser towerUser;// 收藏者
    private TowerContent towerContent;// 收藏的文章

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
