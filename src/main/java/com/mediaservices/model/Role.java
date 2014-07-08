package com.mediaservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROLES")
public class Role {
	@Id @GeneratedValue
	private int roleId;
	private String name;
	private int hierarchy;
	
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
	}
	
}
