package com.telecom.bean;

public class TowerUserBean {
    private String towerUserId; // id
    private String iconUrl; // 用户头像
    private String name; // 用户姓名
    private String follow;// 关注:0否,1是
    private String followNum;// 关注人数
    private String company;// 公司
    private String university;// 学校

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

    public String getFollow() {
	return follow;
    }

    public void setFollow(String follow) {
	this.follow = follow;
    }

    public String getCompany() {
	return company;
    }

    public void setCompany(String company) {
	this.company = company;
    }

    public String getUniversity() {
	return university;
    }

    public void setUniversity(String university) {
	this.university = university;
    }

    public String getFollowNum() {
	return followNum;
    }

    public void setFollowNum(String followNum) {
	this.followNum = followNum;
    }
}
