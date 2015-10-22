package com.cloud.valueobject.entity;

import java.io.Serializable;

public class Test implements Serializable{

	private static final long serialVersionUID = 3908753822862325927L;
	
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
