package com.zabbix.api.service;
import java.util.List;

import org.json.JSONObject;

import com.zabbix.api.domain.base.HostGroup;
import com.zabbix.api.domain.hostgroup.HostGroupCreateRequest;
import com.zabbix.api.domain.hostgroup.HostGroupDeleteRequest;
import com.zabbix.api.domain.hostgroup.HostGroupExistsRequest;
import com.zabbix.api.domain.hostgroup.HostGroupGetRequest;
import com.zabbix.api.domain.hostgroup.HostGroupGetobjectsRequest;
import com.zabbix.api.domain.hostgroup.HostGroupIsreadableRequest;
import com.zabbix.api.domain.hostgroup.HostGroupIswritableRequest;
import com.zabbix.api.domain.hostgroup.HostGroupMassaddRequest;
import com.zabbix.api.domain.hostgroup.HostGroupMassremoveRequest;
import com.zabbix.api.domain.hostgroup.HostGroupMassupdateRequest;
import com.zabbix.api.domain.hostgroup.HostGroupUpdateRequest;
/**
 * @ClassName: IHostgroupService
 * @Description:
 * @author
 * @date
 */
public interface IHostgroupService {
	/**
	 * 
	 * @Title: hostGroupGet
	 * @Description: 获取设备组信息（json）
	 * @param hostGroupGet
	 * @return Object
	 * @throws
	 */
	public List<HostGroup> hostGroupGet(HostGroupGetRequest hostGroupGet,String auth);
	
	public Object getHostGroup(HostGroupGetRequest hostGroupGet,String auth);
	/**
	 * 
	 * @Title: getHostGroupBean
	 * @Description: 获取设备组信息（bean）
	 * @param hostGroupGet
	 * @return List<HostGroup>
	 * @throws
	 */
	public List<HostGroup> getHostGroupBean(HostGroupGetRequest hostGroupGet,String auth);
	
	
	public Object hostGroupCreate(HostGroupCreateRequest hostGroupCreate,String auth);
	public Object hostGroupDelete(HostGroupDeleteRequest hostGroupDelete,String auth);
	public Object hostGroupExists(HostGroupExistsRequest hostGroupExists,String auth);
	public Object hostGroupGetobjects(HostGroupGetobjectsRequest hostGroupGetobjects,String auth);
	public Object hostGroupIsreadable(HostGroupIsreadableRequest hostGroupIsreadable,String auth);
	public Object hostGroupIswritable(HostGroupIswritableRequest hostGroupIswritable,String auth);
	public Object hostGroupMassadd(HostGroupMassaddRequest hostGroupMassadd,String auth);
	public Object hostGroupMassremove(HostGroupMassremoveRequest hostGroupMassremove,String auth);
	public Object hostGroupMassupdate(HostGroupMassupdateRequest hostGroupMassupdate,String auth);
	public Object hostGroupUpdate(HostGroupUpdateRequest hostGroupUpdate,String auth);
}
