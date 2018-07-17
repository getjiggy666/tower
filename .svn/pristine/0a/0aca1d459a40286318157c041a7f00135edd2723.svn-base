package com.telecom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - IP过滤子表
 */

@Entity
@JsonIgnoreProperties(value = {"JavassistLazyInitializer", "hibernateLazyInitializer", "handler", "accessStrategy"})
public class AccessStrategyItem extends BaseEntity {

	private static final long serialVersionUID = -7519486123353411526L;
	
	private String accessIp;// IP
	private String accessName;// 备注
	
	private AccessStrategy accessStrategy;// 主表

	@Column(nullable = false)
	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	@Column(nullable = true)
	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@ForeignKey(name = "fk_access_strategy_item_access_strategy")
	public AccessStrategy getAccessStrategy() {
		return accessStrategy;
	}

	public void setAccessStrategy(AccessStrategy accessStrategy) {
		this.accessStrategy = accessStrategy;
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
