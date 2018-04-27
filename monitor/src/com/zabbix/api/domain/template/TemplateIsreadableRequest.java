package com.zabbix.api.domain.template;
import java.util.*;

import com.zabbix.api.domain.base.RequestBase;
public class TemplateIsreadableRequest extends RequestBase{
	public TemplateIsreadableRequest() {
		super("template.isreadable");
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
