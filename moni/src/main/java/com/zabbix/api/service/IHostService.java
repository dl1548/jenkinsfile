package com.zabbix.api.service;
import java.util.List;

import com.zabbix.api.domain.base.Host;
import com.zabbix.api.domain.base.HostGroup;
import com.zabbix.api.domain.base.HostInterface;
import com.zabbix.api.domain.base.Template;
import com.zabbix.api.domain.host.HostCreateRequest;
import com.zabbix.api.domain.host.HostDeleteRequest;
import com.zabbix.api.domain.host.HostExistsRequest;
import com.zabbix.api.domain.host.HostGetRequest;
import com.zabbix.api.domain.host.HostGetobjectsRequest;
import com.zabbix.api.domain.host.HostIsreadableRequest;
import com.zabbix.api.domain.host.HostIswritableRequest;
import com.zabbix.api.domain.host.HostMassaddRequest;
import com.zabbix.api.domain.host.HostMassremoveRequest;
import com.zabbix.api.domain.host.HostMassupdateRequest;
import com.zabbix.api.domain.host.HostUpdateRequest;
/**
 * @ClassName: IHostService
 * @Description:
 * @author
 * @date
 */
public interface IHostService {
	public Object create(HostCreateRequest create,String auth);
	/**
	 * @Title: get
	 * @Description: 获取设备信息（json）
	 * @param get
	 * @return Object
	 * @throws
	 */
	public Object get(HostGetRequest get);
	
	/**
	 * @Title: getHostToBean
	 * @Description: 获取设备信息（bean）
	 * @param get
	 * @return List<Host>
	 * @throws
	 */
	public List<Host> getHostToBean(HostGetRequest get,String auth);
	String createHost(Host host, HostInterface hostInterface,
			HostGroup hostgroup, Template template,String auth);
	
	public Object delete(HostDeleteRequest delete,String auth);
	public Object exists(HostExistsRequest exists,String auth);
	public Object getobjects(HostGetobjectsRequest getobjects,String auth);
	public Object isreadable(HostIsreadableRequest isreadable,String auth);
	public Object iswritable(HostIswritableRequest iswritable,String auth);
	public Object massadd(HostMassaddRequest massadd,String auth);
	public Object massremove(HostMassremoveRequest massremove,String auth);
	public Object massupdate(HostMassupdateRequest massupdate,String auth);
	public Object update(HostUpdateRequest update,String auth);
	
}
