package com.zabbix.api.service;
import com.zabbix.api.domain.usergroup.UserGroupCreateRequest;
import com.zabbix.api.domain.usergroup.UserGroupDeleteRequest;
import com.zabbix.api.domain.usergroup.UserGroupExistsRequest;
import com.zabbix.api.domain.usergroup.UserGroupGetRequest;
import com.zabbix.api.domain.usergroup.UserGroupGetobjectsRequest;
import com.zabbix.api.domain.usergroup.UserGroupIsreadableRequest;
import com.zabbix.api.domain.usergroup.UserGroupIswritableRequest;
import com.zabbix.api.domain.usergroup.UserGroupMassaddRequest;
import com.zabbix.api.domain.usergroup.UserGroupMassupdateRequest;
import com.zabbix.api.domain.usergroup.UserGroupUpdateRequest;
public interface IUsergroupService {
	public Object userGroupCreate(UserGroupCreateRequest userGroupCreate,String auth);
	public Object userGroupDelete(UserGroupDeleteRequest userGroupDelete,String auth);
	public Object userGroupExists(UserGroupExistsRequest userGroupExists,String auth);
	public Object userGroupGetobjects(UserGroupGetobjectsRequest userGroupGetobjects,String auth);
	public Object userGroupGet(UserGroupGetRequest userGroupGet,String auth);
	public Object userGroupIsreadable(UserGroupIsreadableRequest userGroupIsreadable,String auth);
	public Object userGroupIswritable(UserGroupIswritableRequest userGroupIswritable,String auth);
	public Object userGroupMassadd(UserGroupMassaddRequest userGroupMassadd,String auth);
	public Object userGroupMassupdate(UserGroupMassupdateRequest userGroupMassupdate,String auth);
	public Object userGroupUpdate(UserGroupUpdateRequest userGroupUpdate,String auth);
}
