package com.telecom.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 内容实体
 */

@Entity
public class TowerContent extends BaseEntity {

    /**
     * 内容
     */
    private static final long serialVersionUID = -4620572083702500852L;

    private String title;// 标题
    private String contentType;// 内容类型：0纯文字,1图文,2小视频,3长视频,4需求
    private String kind;// 种类：生活日常,技能才干
    private String videoUrl;// 视频地址
    private String videoImgUrl;// 视频第一帧
    private String imgUrl_1, imgUrl_2, imgUrl_3;// 图片地址
    private String content;// 文字描述
    private TowerUser towerUser;// 发布者
    private TowerDribKind towerDribKind;// 所属分类
    private String status;// 0未发布,1已发布,2官方发布

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getContentType() {
	return contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

    public String getImgUrl_1() {
	return imgUrl_1;
    }

    public void setImgUrl_1(String imgUrl_1) {
	this.imgUrl_1 = imgUrl_1;
    }

    public String getImgUrl_2() {
	return imgUrl_2;
    }

    public void setImgUrl_2(String imgUrl_2) {
	this.imgUrl_2 = imgUrl_2;
    }

    public String getImgUrl_3() {
	return imgUrl_3;
    }

    public void setImgUrl_3(String imgUrl_3) {
	this.imgUrl_3 = imgUrl_3;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "fk_tower_user")
    public TowerUser getTowerUser() {
	return towerUser;
    }

    public void setTowerUser(TowerUser towerUser) {
	this.towerUser = towerUser;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getVideoUrl() {
	return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
	this.videoUrl = videoUrl;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getKind() {
	return kind;
    }

    public void setKind(String kind) {
	this.kind = kind;
    }

    public String getVideoImgUrl() {
	return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
	this.videoImgUrl = videoImgUrl;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "fk_tower_drib_kind")
    public TowerDribKind getTowerDribKind() {
	return towerDribKind;
    }

    public void setTowerDribKind(TowerDribKind towerDribKind) {
	this.towerDribKind = towerDribKind;
    }

}
