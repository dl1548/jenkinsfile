package com.jk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zabbixuser")
public class ZabbixUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String userid;//用户id
	String alias;//用户名
	String passwordid;//密码
	String nickname;//姓名
	Integer type;//用户角色
	String deptid;//部门id
	Integer jobid;//职位id
	String period;//允许告警时间
	String phone;//电话
	String groupid;//用户组id
	String sendto ;//邮件
	String active;//是否启用告警
	String severity;//告警等级
	String mediatypeid;
	//告警方式
	String email;
	String message;
	String wechat;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getMediatypeid() {
		return mediatypeid;
	}
	public void setMediatypeid(String mediatypeid) {
		this.mediatypeid = mediatypeid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDeptid() {
		return deptid;
	}
	public String getPasswordid() {
		return passwordid;
	}
	public void setPasswordid(String passwordid) {
		this.passwordid = passwordid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
	public Integer getJobid() {
		return jobid;
	}
	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSendto() {
		return sendto;
	}
	public void setSendto(String sendto) {
		this.sendto = sendto;
	}
	
}
