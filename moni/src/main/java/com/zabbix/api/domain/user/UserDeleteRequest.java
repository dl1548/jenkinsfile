package com.zabbix.api.domain.user;
import java.util.*;

import com.zabbix.api.domain.base.RequestBase;
import com.zabbix.api.domain.base.User;
public class UserDeleteRequest extends RequestBase{
	public UserDeleteRequest(String auth) {
		super("user.delete",auth);
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
