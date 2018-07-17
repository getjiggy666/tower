package com.telecom.bean;

public class TowerContentBean {
    private String towerContentId; // id
    private String towerUserId; // 用户id
    private String iconUrl; // 用户头像
    private String name; // 用户姓名
    private String title; // 内容标题
    private String videoUrl, videoImg;// 视频地址,视频第一帧
    private String imgUrl_1, imgUrl_2, imgUrl_3;// 图片地址
    private String content;// 文字描述
    private String contentType;// 内容类型：0纯文字,1图文,2小视频,3长视频
    private String contentKind; // 种类:技能学习
    private String follow;// 关注:0否,1是
    private String collection;// 收藏:0否,1是
    private String praise;// 点赞:0否,1是

    public String getIconUrl() {
	return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
	this.iconUrl = iconUrl;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getFollow() {
	return follow;
    }

    public void setFollow(String follow) {
	this.follow = follow;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getContentKind() {
	return contentKind;
    }

    public void setContentKind(String contentKind) {
	this.contentKind = contentKind;
    }

    public String getCollection() {
	return collection;
    }

    public void setCollection(String collection) {
	this.collection = collection;
    }

    public String getPraise() {
	return praise;
    }

    public void setPraise(String praise) {
	this.praise = praise;
    }

    public String getVideoUrl() {
	return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
	this.videoUrl = videoUrl;
    }

    public String getImgUrl_2() {
	return imgUrl_2;
    }

    public void setImgUrl_2(String imgUrl_2) {
	this.imgUrl_2 = imgUrl_2;
    }

    public String getImgUrl_1() {
	return imgUrl_1;
    }

    public void setImgUrl_1(String imgUrl_1) {
	this.imgUrl_1 = imgUrl_1;
    }

    public String getImgUrl_3() {
	return imgUrl_3;
    }

    public void setImgUrl_3(String imgUrl_3) {
	this.imgUrl_3 = imgUrl_3;
    }

    public String getContentType() {
	return contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getTowerContentId() {
	return towerContentId;
    }

    public void setTowerContentId(String towerContentId) {
	this.towerContentId = towerContentId;
    }

    public String getTowerUserId() {
	return towerUserId;
    }

    public void setTowerUserId(String towerUserId) {
	this.towerUserId = towerUserId;
    }

    public String getVideoImg() {
	return videoImg;
    }

    public void setVideoImg(String videoImg) {
	this.videoImg = videoImg;
    }
}
