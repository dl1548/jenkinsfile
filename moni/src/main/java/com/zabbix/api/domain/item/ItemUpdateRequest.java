package com.zabbix.api.domain.item;
import java.util.*;

import com.zabbix.api.domain.base.Item;
import com.zabbix.api.domain.base.RequestBase;
public class ItemUpdateRequest extends RequestBase{
	public ItemUpdateRequest(String auth) {
		super("item.update",auth);
	}
	private ItemUpdateParams params = new ItemUpdateParams();
	public void setParams(ItemUpdateParams params) {
		this.params = params;
	}
	public ItemUpdateParams getParams() {
		return params;
	}
	public static class ItemUpdateParams extends Item{
		private List<String> applications;
		public void setApplications(List<String> applications) {
			this.applications = applications;
		}
		public List<String> getApplications() {
			 if(applications==null){
				applications   = new ArrayList<String>();
				return applications;
			}
			 return applications;
		}
	}
}
