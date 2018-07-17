package com.telecom.bean;

public class TowerDribKindBean {
    private String dribKindId; // id
    private String dribKindName; // 分类名称
    private String dribKindDetail; // 分类描述
    private String dribKindNum;// 分类数量
    private String dribKindType;//0默认类别，不可删除 1新增类别，可以删除

    public String getDribKindId() {
	return dribKindId;
    }

    public void setDribKindId(String dribKindId) {
	this.dribKindId = dribKindId;
    }

    public String getDribKindName() {
	return dribKindName;
    }

    public void setDribKindName(String dribKindName) {
	this.dribKindName = dribKindName;
    }

    public String getDribKindDetail() {
	return dribKindDetail;
    }

    public void setDribKindDetail(String dribKindDetail) {
	this.dribKindDetail = dribKindDetail;
    }

    public String getDribKindNum() {
	return dribKindNum;
    }

    public void setDribKindNum(String dribKindNum) {
	this.dribKindNum = dribKindNum;
    }

	public String getDribKindType() {
		return dribKindType;
	}

	public void setDribKindType(String dribKindType) {
		this.dribKindType = dribKindType;
	}
}
