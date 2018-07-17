package com.telecom.entity;

import javax.persistence.Entity;

/**
 * 实体类 - 路线
 */

@Entity
public class Route extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2062333904043675752L;
	
	private String routeName;

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
}
