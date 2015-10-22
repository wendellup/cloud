package com.cloud.valueobject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cloud.valueobject.constvar.EnumType.AppParameterParamType;
import com.cloud.valueobject.constvar.EnumType.AppParameterType;

public class AppParameter implements Serializable{

	private static final long serialVersionUID = -433808059881300535L;
	
	private int id = 0;
    private int parentId = 0;
    private AppParameterType type;
    private String name = "";
    private Map<AppParameterParamType, String> param = new HashMap<AppParameterParamType, String>();
    private int sort = 0;
    private boolean enable = true;
    private int depth = 0;
    private String remark = "";
    private long picId = 0;
    private Date beginTime = new Date();
    private Date endTime = new Date();
    private Date updateTime = new Date();
    private int operatorId = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public AppParameterType getType() {
		return type;
	}
	public void setType(AppParameterType type) {
		this.type = type;
	}
	public Map<AppParameterParamType, String> getParam() {
		return param;
	}
	public void setParam(Map<AppParameterParamType, String> param) {
		this.param = param;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getPicId() {
		return picId;
	}
	public void setPicId(long picId) {
		this.picId = picId;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
    
    
}
