package com.telecom.bean;

import java.util.Date;

public class TowerCommentBean {
    private String towerUserId; // 用户id
    private String iconUrl; // 用户头像
    private String name; // 用户姓名
    private Date date; // 评论时间
    private String comment;// 视频地址

    public String getTowerUserId() {
	return towerUserId;
    }

    public void setTowerUserId(String towerUserId) {
	this.towerUserId = towerUserId;
    }

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

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }
}
