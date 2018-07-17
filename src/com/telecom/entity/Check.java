package com.telecom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 审核类
 */

@Entity
public class Check extends BaseEntity {

	private static final long serialVersionUID = 6058051122866723841L;
	
	// 认证状态（未处理、已通过、已驳回、已作废）
	public enum CheckStatus {
		unprocessed, passed, back, invalid
	};
	
	// 审核动作（通过、驳回、作废、提交）
	public enum CheckAction {
		pass, back, invalid, submit
	};
	
	private CheckAction checkAction ; // 审核动作
	private String comment ; // 审核意见
	private Date checkTime ; // 审核时间
	private Admin admin ; // 外键关联对象---审核人
	private SmsTemplate smsTemplate; // 外键关联对象---审核短信模板
	
	@Column(nullable = false)
	public CheckAction getCheckAction() {
		return checkAction;
	}

	public void setCheckAction(CheckAction checkAction) {
		this.checkAction = checkAction;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(nullable = false)
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_check_admin")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_check_sms_template")
	public SmsTemplate getSmsTemplate() {
		return smsTemplate;
	}

	public void setSmsTemplate(SmsTemplate smsTemplate) {
		this.smsTemplate = smsTemplate;
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