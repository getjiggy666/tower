package com.telecom.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.telecom.util.SerialNumberUtil;

/**
 * 实体类 - IP过滤
 */

@Entity
@JsonIgnoreProperties(value = {"JavassistLazyInitializer", "hibernateLazyInitializer", "handler", "accessStrategyItemSet"})
public class AccessStrategy extends BaseEntity {

	private static final long serialVersionUID = -7519486123253411526L;

	private String sign;// 编号
	private String name;// 名称

	private Set<AccessStrategyItem> accessStrategyItemSet = new HashSet<AccessStrategyItem>();// 子表

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

	@OneToMany(mappedBy = "accessStrategy", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	public Set<AccessStrategyItem> getAccessStrategyItemSet() {
		return accessStrategyItemSet;
	}

	public void setAccessStrategyItemSet(
			Set<AccessStrategyItem> accessStrategyItemSet) {
		this.accessStrategyItemSet = accessStrategyItemSet;
	}
	
	// 获取IP列表
	@Transient
	public List<String> getIpList(){
		List<String> ipList = new ArrayList<String>();
		for (AccessStrategyItem accessStrategyItem : accessStrategyItemSet) {
			ipList.add(accessStrategyItem.getAccessIp());
		}
		return ipList;
	}


	// 保存处理
	@Override
	@Transient
	public void onSave() {
		if(sign == null){
			sign = SerialNumberUtil.buildAccessStrategySn();
		}
	}

	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
	}
}
