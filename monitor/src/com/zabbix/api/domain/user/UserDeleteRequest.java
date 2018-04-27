package com.zabbix.api.domain.user;
import java.util.*;

import com.zabbix.api.domain.base.RequestBase;
import com.zabbix.api.domain.base.User;
public class UserDeleteRequest extends RequestBase{
	public UserDeleteRequest() {
		super("user.delete");
	}
	private List<User> params;
	public void setParams(List<User> params) {
		this.params = params;
	}
	public List<User> getParams() {
		 if(params==null){
			params   = new ArrayList<User>();
			return params;
		}
		 return params;
	}
}
