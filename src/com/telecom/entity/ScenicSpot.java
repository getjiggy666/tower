package com.telecom.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

/**
 * 实体类 - 景点
 */

@Entity
public class ScenicSpot extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5068712300109455334L;
	
	private String scenicName;//景点名称
	private String scenicContent;//景点描述
	private String scenicImg;//景点图片
	private BigDecimal longitude;//经度
	private BigDecimal latitude;// 纬度

	public String getScenicName() {
		return scenicName;
	}

	public void setScenicName(String scenicName) {
		this.scenicName = scenicName;
	}

	public String getScenicContent() {
		return scenicContent;
	}

	public void setScenicContent(String scenicContent) {
		this.scenicContent = scenicContent;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getScenicImg() {
		return scenicImg;
	}

	public void setScenicImg(String scenicImg) {
		this.scenicImg = scenicImg;
	}
}
