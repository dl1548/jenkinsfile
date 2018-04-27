package com.zabbix.api.domain.triggerprototype;
import java.util.*;

import com.zabbix.api.domain.base.RequestBase;
public class TriggerPrototypeDeleteRequest extends RequestBase{
	public TriggerPrototypeDeleteRequest(String auth) {
		super("triggerprototype.delete",auth);
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
