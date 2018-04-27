package com.jk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hostmark")
public class HostMark {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer hostid;//主机ID
	private String createdate;//创建时间
	private String mark;//标签
	private String hostidmark;
	public String getHostidmark() {
		return hostidmark;
	}
	public void setHostidmark(String hostidmark) {
		this.hostidmark = hostidmark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHostid() {
		return hostid;
	}
	public void setHostid(Integer hostid) {
		this.hostid = hostid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
}
