package com.cloud.valueobject.entity;

import java.io.Serializable;

public class Article implements Serializable{
	
	private static final long serialVersionUID = -8717501802813327208L;
	
	private int id;
	private AppParameter appParameter;
	private String title;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public AppParameter getAppParameter() {
		return appParameter;
	}
	public void setAppParameter(AppParameter appParameter) {
		this.appParameter = appParameter;
	}
	
	
}
