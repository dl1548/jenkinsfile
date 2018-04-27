package com.zabbix.api.domain.trigger;
import java.util.*;

import com.zabbix.api.domain.base.RequestBase;
public class TriggerIsreadableRequest extends RequestBase{
	public TriggerIsreadableRequest(String auth) {
		super("trigger.isreadable",auth);
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
