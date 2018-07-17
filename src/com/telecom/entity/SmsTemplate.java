package com.telecom.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.ForeignKey;

import com.telecom.entity.Check.CheckStatus;

/**
 * 实体类 - 短信模板
 */

@Entity
@JsonIgnoreProperties(value = {"JavassistLazyInitializer", "hibernateLazyInitializer", "handler", "checkSet"})
public class SmsTemplate extends BaseEntity {

	private static final long serialVersionUID = -1820835768813593110L;
	
	// 模板类别（系统内容、活动内容）
	public enum TemplateType {
		system, activity
	};
	
	private String sign;// 编号
	private String name; // 模板名称
	private String content; // 模板内容
	private CheckStatus checkStatus; // 审核状态
	private String memo; // 备注
	private Admin admin; // 外键关联对象---后台管理用户
	private Boolean isUse; // 是否启用
	private TemplateType templateType; //模板类别
	
	private Set<Check> checkSet = new HashSet<Check>(); // 审核记录集合

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
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Enumerated
	@Column(nullable = false)
	public CheckStatus getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(CheckStatus checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Column(nullable = false)
	public Boolean getIsUse() {
		return isUse;
	}

	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_sms_template_admin")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@OneToMany(mappedBy = "smsTemplate", fetch = FetchType.LAZY)
	@OrderBy("createDate desc")
	public Set<Check> getCheckSet() {
		return checkSet;
	}

	public void setCheckSet(Set<Check> checkSet) {
		this.checkSet = checkSet;
	}
	
	@Enumerated
	@Column(nullable = false)
	public TemplateType getTemplateType() {
		return templateType;
	}

	public void setTemplateType(TemplateType templateType) {
		this.templateType = templateType;
	}

	// 保存处理
	@Override
	@Transient
	public void onSave() {
	}

	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
	}

}