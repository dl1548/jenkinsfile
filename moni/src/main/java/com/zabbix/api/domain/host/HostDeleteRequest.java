package com.zabbix.api.domain.host;
import java.util.*;

import com.zabbix.api.domain.base.Host;
import com.zabbix.api.domain.base.RequestBase;
public class HostDeleteRequest extends RequestBase{
	public HostDeleteRequest(String auth) {
		super("host.delete",auth);
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
