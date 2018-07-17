package com.telecom.entity;

import javax.persistence.Entity;

/**
 * 实体类 - 塔兮用户
 */

@Entity
public class TowerUser extends BaseEntity {

    /**
     * 用户
     */
    private static final long serialVersionUID = 6270400718365676322L;

    private String phone;// 用户手机号
    private String password;// 用户登录密码
    private String verification;// 验证码
    private String iconUrl;// 头像地址
    private String name;// 姓名
    private String sex;// 性别
    private String birthday;// 生日
    private String university;// 大学
    private String company;// 公司
    private String introduce;// 介绍
    private String category;// 0企业大咖,1学生大牛
    private String position;// 职位;
    private String status;// 0未注册成功,1注册成功用户

    public String getVerification() {
	return verification;
    }

    public void setVerification(String verification) {
	this.verification = verification;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getBirthday() {
	return birthday;
    }

    public void setBirthday(String birthday) {
	this.birthday = birthday;
    }

    public String getUniversity() {
	return university;
    }

    public void setUniversity(String university) {
	this.university = university;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public String getPosition() {
	return position;
    }

    public void setPosition(String position) {
	this.position = position;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getIconUrl() {
	return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
	this.iconUrl = iconUrl;
    }

    public String getCompany() {
	return company;
    }

    public void setCompany(String company) {
	this.company = company;
    }

    public String getIntroduce() {
	return introduce;
    }

    public void setIntroduce(String introduce) {
	this.introduce = introduce;
    }
}
