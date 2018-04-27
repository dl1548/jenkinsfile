package com.jk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usermedia")
public class Media {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String mediaid;
	private String active;
	private String mediatypeid;
	private String period;
	private String sendto;
	private String severity;
	private String userid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}
	public String getMediaid() {
		return mediaid;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getActive() {
		return active;
	}
	public void setMediatypeid(String mediatypeid) {
		this.mediatypeid = mediatypeid;
	}
	public String getMediatypeid() {
		return mediatypeid;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getPeriod() {
		return period;
	}
	public void setSendto(String sendto) {
		this.sendto = sendto;
	}
	public String getSendto() {
		return sendto;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getSeverity() {
		return severity;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserid() {
		return userid;
	}
}

