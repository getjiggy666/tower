package com.telecom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 实体类 - 验证码
 */

@Entity
public class AuthCode extends BaseEntity {

	private static final long serialVersionUID = -7519486124545671526L;

	private String mobile;// 号码
	private String authCode;// 验证码
	private Date sendTime;// 发送时间
	private Boolean isEnabled;// 是否启用
	
	@Column(nullable = false)
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(nullable = false)
	public String getAuthCode() {
		return authCode;
	}
	
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	@Column(nullable = false)
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(nullable = false)
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
}
