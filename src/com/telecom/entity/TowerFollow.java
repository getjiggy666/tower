package com.telecom.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 关注
 */

@Entity
public class TowerFollow extends BaseEntity {

    /**
     * 关注
     */
    private static final long serialVersionUID = 6962358637428416331L;

    private TowerUser star;// 被关注者
    private TowerUser fans;// 粉丝
    
    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "fk_tower_user")
    public TowerUser getStar() {
        return star;
    }
    public void setStar(TowerUser star) {
        this.star = star;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "fk_tower_user")
    public TowerUser getFans() {
        return fans;
    }
    public void setFans(TowerUser fans) {
        this.fans = fans;
    }
}
