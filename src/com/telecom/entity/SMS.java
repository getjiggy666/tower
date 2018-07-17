package com.telecom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

/**
 * 实体类 - 短信
 */

@Entity
public class SMS extends BaseEntity {
	
	private static final long serialVersionUID = -5134654201234575684L;
	
	// 类别（短信、彩信）
	public enum SMSType {
		sms, mms
	};
	
	// 级别（高、低）
	public enum SMSLevel {
		high, low
	};
	
	// 状态（未发送、发送中、无效、失败、成功）
	public enum SMSStatus {
		unsend, sending, invalid, failure, success
	};
	
	private String account;	//账户
	private String text;	//内容
	private SMSType type;	//类别
	private SMSLevel level;	//级别
	private SMSStatus status; //状态
	private Date sendTime;//发送时间
	private String middleStatus;//二层状态
	private String middleDesc;//二层描述
	private String gatewayStatus;//三层状态
	private String gatewayDesc;//三层描述
	private Integer errorCount;//失败次数
	private Integer orderList;
	private String xml;
	private String msgId;
	
	@Column(nullable=false)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(nullable=false)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Column(unique=true,insertable = false,updatable=false)
	public Integer getOrderList() {
		return orderList;
	}
	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}
	
	@Enumerated
	@Column(nullable = false)
	public SMSType getType() {
		return type;
	}
	public void setType(SMSType type) {
		this.type = type;
	}
	
	@Enumerated
	@Column(nullable = false)
	public SMSLevel getLevel() {
		return level;
	}
	public void setLevel(SMSLevel level) {
		this.level = level;
	}
	
	@Enumerated
	@Column(nullable = false)
	public SMSStatus getStatus() {
		return status;
	}
	public void setStatus(SMSStatus status) {
		this.status = status;
	}
	
	@Column(nullable = true)
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@Column(nullable = true)
	public String getMiddleStatus() {
		return middleStatus;
	}
	public void setMiddleStatus(String middleStatus) {
		this.middleStatus = middleStatus;
	}
	
	@Column(nullable = true)
	public String getMiddleDesc() {
		return middleDesc;
	}
	public void setMiddleDesc(String middleDesc) {
		this.middleDesc = middleDesc;
	}
	
	@Column(nullable = true)
	public String getGatewayStatus() {
		return gatewayStatus;
	}
	public void setGatewayStatus(String gatewayStatus) {
		this.gatewayStatus = gatewayStatus;
	}
	
	@Column(nullable = true)
	public String getGatewayDesc() {
		return gatewayDesc;
	}
	public void setGatewayDesc(String gatewayDesc) {
		this.gatewayDesc = gatewayDesc;
	}
	
	@Column(nullable = false)
	public Integer getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}
	
	@Column(nullable = true)
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	
	@Column(nullable = true)
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	// 保存处理
	@Override
	@Transient
	public void onSave() {
		if(type == null){
			type = SMSType.sms;
		}
		if(level == null){
			level = SMSLevel.low;
		}
		if(status == null){
			status = SMSStatus.unsend;
		}
		if(errorCount == null){
			errorCount = new Integer(0);
		}
	}
	
	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
		if(type == null){
			type = SMSType.sms;
		}
		if(level == null){
			level = SMSLevel.low;
		}
		if(status == null){
			status = SMSStatus.unsend;
		}
		if(errorCount == null){
			errorCount = new Integer(0);
		}
		if(sendTime == null){
			sendTime = new Date();
		}
	}
}
