package com.jk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hostgroup")
public class Hostgroup {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String createDate;
	private String describes;
	private Integer count;
	private Integer hostgroupid;
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public Integer getHostgroupid() {
		return hostgroupid;
	}
	public void setHostgroupid(Integer hostgroupid) {
		this.hostgroupid = hostgroupid;
	}
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDates) {
		this.createDate = createDates;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
