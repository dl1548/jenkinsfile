package com.zabbix.api.domain.usergroup;
import java.util.*;

import com.zabbix.api.domain.base.Permission;
import com.zabbix.api.domain.base.RequestBase;
import com.zabbix.api.domain.base.UserGroup;
public class UserGroupCreateRequest extends RequestBase{
	public UserGroupCreateRequest(String auth) {
		super("usergroup.create",auth);
	}
	private UserGroupCreateParams params = new UserGroupCreateParams();
	public void setParams(UserGroupCreateParams params) {
		this.params = params;
	}
	public UserGroupCreateParams getParams() {
		return params;
	}
	public static class UserGroupCreateParams extends UserGroup{
		private List<String> userids;
		public void setUserids(List<String> userids) {
			this.userids = userids;
		}
		public List<String> getUserids() {
			 if(userids==null){
				userids   = new ArrayList<String>();
				return userids;
			}
			 return userids;
		}
	}
}
