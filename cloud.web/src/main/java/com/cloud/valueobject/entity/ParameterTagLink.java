package com.cloud.valueobject.entity;

import java.io.Serializable;

public class ParameterTagLink implements Serializable{
	
	private static final long serialVersionUID = -5424235850331085542L;
	
	private int businessId;
	private int tagId;
	private int businessSortNo;
	private int tagSortNo;
	private boolean enable = true;
	private long startTime;
	private long endTime;
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public int getBusinessSortNo() {
		return businessSortNo;
	}
	public void setBusinessSortNo(int businessSortNo) {
		this.businessSortNo = businessSortNo;
	}
	public int getTagSortNo() {
		return tagSortNo;
	}
	public void setTagSortNo(int tagSortNo) {
		this.tagSortNo = tagSortNo;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
}
