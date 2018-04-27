package com.zabbix.api.domain.base;
public class Permission{
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String groupid;
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Integer permission;
	public void setPermission(Integer permission) {
		this.permission = permission;
	}
	public Integer getPermission() {
		return permission;
	}
}
