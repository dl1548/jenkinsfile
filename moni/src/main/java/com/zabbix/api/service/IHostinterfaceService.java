package com.zabbix.api.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.zabbix.api.domain.base.HostInterface;
import com.zabbix.api.domain.hostinterface.HostInterfaceGetRequest;
/**
 * @ClassName: IHostinterfaceService
 * @Description:
 * @author 
 * @date
 */


public interface IHostinterfaceService {
	/**
	 * @Title: hostInterfaceGet
	 * @Description: 获取接口信息（json）
	 * @param hostInterfaceGet
	 * @return Object
	 * @throws
	 */
	public Object hostInterfaceGet(HostInterfaceGetRequest hostInterfaceGet,String auth);
	/**
	 * 
	 * @Title: getHostInterfaceBean
	 * @Description:  获取接口信息（bean）
	 * @param @param hostInterfaceGet
	 * @param @return  
	 * @return List<HostInterface>
	 * @throws
	 */
	public List<HostInterface> getHostInterfaceBean(HostInterfaceGetRequest hostInterfaceGet,String auth);
}
