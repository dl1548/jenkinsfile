package com.jk.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "host")
public class HostL {
	@Id
	private Integer id;
	private String name;
	private String create_Date;
	private String hostGroupId;
	private Integer STATUS;
	private String type;
	private String parentTemplates;
	private String ip;
	private String cpu;
	private String ram;
	private String available;
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParentTemplates() {
		return parentTemplates;
	}
	public void setParentTemplates(String parentTemplates) {
		this.parentTemplates = parentTemplates;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCreate_Date() {
		return create_Date;
	}
	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}
	public String getHostGroupId() {
		return hostGroupId;
	}
	public void setHostGroupId(String hostGroupId) {
		this.hostGroupId = hostGroupId;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
}
