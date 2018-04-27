package com.zabbix.api.domain.application;

import java.util.ArrayList;
import java.util.List;

import com.zabbix.api.domain.base.RequestBase;

public class ApplicationDeleteRequest extends RequestBase{
	public ApplicationDeleteRequest(String auth) {
		super("application.delete",auth);
	}
	private List<String> params;
	public void setParams(List<String> params) {
		this.params = params;
	}
	public List<String> getParams() {
		 if(params==null){
			params   = new ArrayList<String>();
			return params;
		}
		 return params;
	}
}
