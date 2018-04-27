package com.zabbix.api.test;

import junit.framework.TestCase;

import com.zabbix.api.domain.triggerprototype.TriggerPrototypeCreateRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeDeleteRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeGetRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeUpdateRequest;
import com.zabbix.api.service.ITriggerprototypeService;
import com.zabbix.api.service.impl.TriggerprototypeServiceImpl;

public class TestTriggerprototype extends TestCase {
	private static ITriggerprototypeService triggerprototypeService = new TriggerprototypeServiceImpl();
	static {
		// 登录
		new Util().login();
	}
	
	

	public void testTriggerPrototypeCreate(String auth) {
		// 数据准备
		TriggerPrototypeCreateRequest triggerPrototypeCreate = new TriggerPrototypeCreateRequest(auth);
		
		triggerPrototypeCreate.getParams().setExpression("{Template OS Linux:vfs.fs.size[{#FSNAME},pused].last(0)}>50");
		triggerPrototypeCreate.getParams().setDescription("磁盘使用率大于百分之50");
		triggerPrototypeCreate.getParams().setUrl("");
		triggerPrototypeCreate.getParams().setStatus(0);
		triggerPrototypeCreate.getParams().setPriority(5);
		triggerPrototypeCreate.getParams().setComments("");
		
		triggerprototypeService.triggerPrototypeCreate(triggerPrototypeCreate, auth);

	}

	public void testTriggerPrototypeDelete(String auth) {
		// 数据准备
		TriggerPrototypeDeleteRequest triggerPrototypeDelete = new TriggerPrototypeDeleteRequest(auth);
		
		triggerPrototypeDelete.getParams().add("2021");
		
		triggerprototypeService.triggerPrototypeDelete(triggerPrototypeDelete, auth);

	}

	public void testTriggerPrototypeGet(String auth) {
		// 数据准备
		TriggerPrototypeGetRequest triggerPrototypeGet = new TriggerPrototypeGetRequest(auth);
		triggerPrototypeGet.getParams().setOutput("extend");
		triggerprototypeService.triggerPrototypeGet(triggerPrototypeGet, auth);

	}

	public void testTriggerPrototypeGetToBean(String auth) {
		// 数据准备
		TriggerPrototypeGetRequest triggerPrototypeGet = new TriggerPrototypeGetRequest(auth);
		triggerPrototypeGet.getParams().setOutput("extend");
		triggerprototypeService.triggerPrototypeGetToBean(triggerPrototypeGet, auth);

	}
	
	public void testTriggerPrototypeUpdate(String auth) {
		// 数据准备
		TriggerPrototypeUpdateRequest triggerPrototypeUpdate = new TriggerPrototypeUpdateRequest(auth);
		triggerPrototypeUpdate.getParams().setTriggerid("2023");
		triggerPrototypeUpdate.getParams().setExpression("{AddrTable测试192.168.0.253:ipAdEntAddr[{#SNMPVALUE}].last(0)}=0");
		triggerPrototypeUpdate.getParams().setDescription("abc123");
		triggerPrototypeUpdate.getParams().setUrl("");
		triggerPrototypeUpdate.getParams().setStatus(0);
		triggerPrototypeUpdate.getParams().setPriority(2);
		triggerPrototypeUpdate.getParams().setComments("");
		triggerprototypeService.triggerPrototypeUpdate(triggerPrototypeUpdate, auth);

	}
}