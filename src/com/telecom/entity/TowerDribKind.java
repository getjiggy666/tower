package com.telecom.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 点滴分类
 */

@Entity
public class TowerDribKind extends BaseEntity {

    /**
     * 分类
     */
    private static final long serialVersionUID = -1372753745255759900L;

    private String name;// 点滴类别名称
    private String detail;// 点滴类别描述
    private TowerUser towerUser;// 用户
	private String kindType;//0默认类别，不可删除 1新增类别，可以删除

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDetail() {
	return detail;
    }

    public void setDetail(String detail) {
	this.detail = detail;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "fk_tower_user")
    public TowerUser getTowerUser() {
	return towerUser;
    }

    public void setTowerUser(TowerUser towerUser) {
	this.towerUser = towerUser;
    }

	public String getKindType() {
		return kindType;
	}

	public void setKindType(String kindType) {
		this.kindType = kindType;
	}
}
