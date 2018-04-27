package com.zabbix.api.test;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.zabbix.api.domain.base.HostInterface;
import com.zabbix.api.domain.hostinterface.HostInterfaceGetRequest;
import com.zabbix.api.service.IHostinterfaceService;
import com.zabbix.api.service.impl.HostinterfaceServiceImpl;

public class TestHostinterface extends TestCase {
	private static  IHostinterfaceService hostinterfaceService = new HostinterfaceServiceImpl();

	public void testHostInterfaceGet(String auth){
		//数据准备 
		HostInterfaceGetRequest hostInterfaceGet = new HostInterfaceGetRequest(auth);
		hostInterfaceGet.getParams().setOutput("extend");
		List<String> ids = new ArrayList<String>();
		ids.add("10084");
		hostInterfaceGet.getParams().setHostids(ids);
		hostinterfaceService.hostInterfaceGet(hostInterfaceGet, auth);
	
	}
}