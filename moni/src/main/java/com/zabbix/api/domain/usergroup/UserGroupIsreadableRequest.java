package com.zabbix.api.domain.usergroup;
import java.util.*;

import com.zabbix.api.domain.base.RequestBase;
public class UserGroupIsreadableRequest extends RequestBase{
	public UserGroupIsreadableRequest(String auth) {
		super("usergroup.isreadable",auth);
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
