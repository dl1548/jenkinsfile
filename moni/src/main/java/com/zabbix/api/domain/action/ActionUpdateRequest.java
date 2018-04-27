package com.zabbix.api.domain.action;
import java.util.*;

import com.zabbix.api.domain.base.Action;
import com.zabbix.api.domain.base.ActionCondition;
import com.zabbix.api.domain.base.ActionFilter;
import com.zabbix.api.domain.base.ActionOperation;
import com.zabbix.api.domain.base.RequestBase;
public class ActionUpdateRequest extends RequestBase{
	public ActionUpdateRequest(String auth) {
		super("action.update",auth);
	}
	private ActionUpdateParams params = new ActionUpdateParams();
	public void setParams(ActionUpdateParams params) {
		this.params = params;
	}
	public ActionUpdateParams getParams() {
		return params;
	}
	public static class ActionUpdateParams extends Action{
		private List<ActionCondition> conditions;
		private List<ActionOperation> operations;
		private ActionFilter filter;
		
		
		public ActionFilter getFilter() {
			return filter;
		}
		public void setFilter(ActionFilter filter) {
			this.filter = filter;
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
	}
}
