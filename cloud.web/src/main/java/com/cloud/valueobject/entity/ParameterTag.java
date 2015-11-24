package com.cloud.valueobject.entity;

import java.io.Serializable;

public class ParameterTag implements Serializable{
	
	private static final long serialVersionUID = 2024472325108016868L;
	
	private int id;
	private String tagName;
	private int tagType;
	private boolean enable = true;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getTagType() {
		return tagType;
	}
	public void setTagType(int tagType) {
		this.tagType = tagType;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ParameterTag [id=" + id + ", tagName=" + tagName + ", tagType="
				+ tagType + ", enable=" + enable + ", remark=" + remark + "]";
	}

}
