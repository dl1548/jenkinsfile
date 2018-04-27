package com.zabbix.api.domain.trigger;
import java.util.*;

import com.zabbix.api.domain.base.RequestBase;
import com.zabbix.api.domain.base.Trigger;
public class TriggerCreateRequest extends RequestBase{
	public TriggerCreateRequest(String auth) {
		super("trigger.create",auth);
	}
	private TriggerCreateParams params = new TriggerCreateParams();
	public void setParams(TriggerCreateParams params) {
		this.params = params;
	}
	public TriggerCreateParams getParams() {
		return params;
	}
	public static class TriggerCreateParams extends Trigger{
		private List<Trigger> dependencies;
		public void setDependencies(List<Trigger> dependencies) {
			this.dependencies = dependencies;
		}
		public List<Trigger> getDependencies() {
			 if(dependencies==null){
				dependencies   = new ArrayList<Trigger>();
				return dependencies;
			}
			 return dependencies;
		}
	}
}
