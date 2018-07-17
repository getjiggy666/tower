package com.telecom.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 地区类
 */

@Entity
@JsonIgnoreProperties(value = {"JavassistLazyInitializer", "hibernateLazyInitializer", "handler", "parent", "children"})
public class Area extends BaseEntity {

	private static final long serialVersionUID = 324752293731829753L;

	private String displayName; // 展示名称
	private Integer grade; // 级联等级
	private String name; // 名称
	private Integer orderList; // 排序
	private String path; // 路径
	private Area parent; // 外键关联对象---父对象
	
	private Set<Area> children = new HashSet<Area>(); // 下级对象

	@Column(nullable = false)
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(nullable = false)
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	@Column(nullable = false)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_area_parent")
	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("orderList asc")
	public Set<Area> getChildren() {
		return children;
	}

	public void setChildren(Set<Area> children) {
		this.children = children;
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