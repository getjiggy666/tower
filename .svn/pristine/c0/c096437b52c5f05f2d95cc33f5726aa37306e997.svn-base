package com.telecom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * 实体类 - 通知模板
 */

@Entity
public class MessageTemplate extends BaseEntity {

	private static final long serialVersionUID = -1820835768813597520L;

	private String sign;// 编号
	private String name; // 模板名称
	private String title;// 模板标题
	private String content; // 模板内容
	private Boolean isSystem;// 是否为系统通知
	private Boolean isEnabled;// 账号是否启用
	
	@Column(nullable = false, unique = true)
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(nullable = false)
	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	@Column(nullable = false)
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	// 保存处理
	@Override
	@Transient
	public void onSave() {
		if (isSystem == null) {
			isSystem = false;
		}
		if (isEnabled == null) {
			isEnabled = false;
		}
	}

	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
		if (isSystem == null) {
			isSystem = false;
		}
		if (isEnabled == null) {
			isEnabled = false;
		}
	}

}