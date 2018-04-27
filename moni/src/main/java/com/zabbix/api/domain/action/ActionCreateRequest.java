package com.zabbix.api.domain.action;
import java.util.*;
import com.zabbix.api.domain.base.Action;
import com.zabbix.api.domain.base.ActionCondition;
import com.zabbix.api.domain.base.ActionFilter;
import com.zabbix.api.domain.base.ActionOperation;
import com.zabbix.api.domain.base.RequestBase;
public class ActionCreateRequest extends RequestBase{
	public ActionCreateRequest(String auth) {
		super("action.create",auth);
	}
	private ActionCreateParams params = new ActionCreateParams();
	public void setParams(ActionCreateParams params) {
		this.params = params;
	}
	public ActionCreateParams getParams() {
		return params;
	}
	public static class ActionCreateParams extends Action{
		private List<ActionOperation> operations;
		private List<ActionCondition> conditions;
		private ActionFilter filter;
		
		public ActionFilter getFilter() {
			return filter;
		}
		public void setFilter(ActionFilter af) {
			this.filter = af;
		}
		public void setOperations(List<ActionOperation> operations) {
			this.operations = operations;
		}
		public List<ActionOperation> getOperations() {
			 if(operations==null){
				operations   = new ArrayList<ActionOperation>();
				return operations;
			}
			 return operations;
		}
		public void setConditions(List<ActionCondition> conditions) {
			this.conditions = conditions;
		}
		public List<ActionCondition> getConditions() {
			 if(conditions==null){
				conditions   = new ArrayList<ActionCondition>();
				return conditions;
			}
			 return conditions;
		}
	}
}
