package com.zabbix.api.test;

import junit.framework.TestCase;

import com.zabbix.api.domain.itemprototype.ItemPrototypeCreateRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeDeleteRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeExistsRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeGetRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeIsreadableRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeIswritableRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeUpdateRequest;
import com.zabbix.api.service.IItemprototypeService;
import com.zabbix.api.service.impl.ItemprototypeServiceImpl;

public class TestItemprototype extends TestCase {
	private static IItemprototypeService itemprototypeService = new ItemprototypeServiceImpl();
	static {
		// 登录
		new Util().login();
	}

	public void testItemPrototypeCreate(String auth) {
		// 数据准备
		ItemPrototypeCreateRequest itemPrototypeCreate = new ItemPrototypeCreateRequest(auth);
		
		itemPrototypeCreate.getParams().setName("12345");
		itemPrototypeCreate.getParams().setRuleid("1");
		itemPrototypeCreate.getParams().setKey_("ipAdEntAddr3[{#SNMPVALUE}]");
		itemPrototypeCreate.getParams().setHostid("93");
		itemPrototypeCreate.getParams().setType(4);
		itemPrototypeCreate.getParams().setValue_type(3);
		itemPrototypeCreate.getParams().setDelay(30);
		itemPrototypeCreate.getParams().setInterfaceid("54");
//		itemPrototypeCreate.getParams().getApplications().add("13");
		
		itemprototypeService.itemPrototypeCreate(itemPrototypeCreate, auth);

	}

	public void testItemPrototypeDelete(String auth) {
		// 数据准备
		ItemPrototypeDeleteRequest itemPrototypeDelete = new ItemPrototypeDeleteRequest(auth);
		itemPrototypeDelete.getParams().add("42734");
		itemprototypeService.itemPrototypeDelete(itemPrototypeDelete, auth);

	}

	public void testItemPrototypeExists(String auth) {
		// 数据准备
		ItemPrototypeExistsRequest itemPrototypeExists = new ItemPrototypeExistsRequest(auth);
		
		itemPrototypeExists.getParams().setKey_("ipAdEntAddr[{#SNMPVALUE}]");
		itemprototypeService.itemPrototypeExists(itemPrototypeExists, auth);

	}

	public void testItemPrototypeGet(String auth) {
		// 数据准备
		ItemPrototypeGetRequest itemPrototypeGet = new ItemPrototypeGetRequest(auth);
		itemPrototypeGet.getParams().setOutput("extend");
		itemPrototypeGet.getParams().setItemids("10001");
		itemprototypeService.itemPrototypeGet(itemPrototypeGet, auth);

	}

	public void testItemPrototypeGetToBean(String auth) {
		// 数据准备
		ItemPrototypeGetRequest itemPrototypeGet = new ItemPrototypeGetRequest(auth);
		itemPrototypeGet.getParams().setOutput("extend");
		itemPrototypeGet.getParams().setItemids("10001");
		itemprototypeService.itemPrototypeGetToBean(itemPrototypeGet, auth);

	}

	public void testItemPrototypeIsreadable(String auth) {
		// 数据准备
		ItemPrototypeIsreadableRequest itemPrototypeIsreadable = new ItemPrototypeIsreadableRequest(auth);
		itemPrototypeIsreadable.getParams().add("42734");
		itemprototypeService.itemPrototypeIsreadable(itemPrototypeIsreadable, auth);

	}

	public void testItemPrototypeIswritable(String auth) {
		// 数据准备
		ItemPrototypeIswritableRequest itemPrototypeIswritable = new ItemPrototypeIswritableRequest(auth);
		itemPrototypeIswritable.getParams().add("42734");
		itemprototypeService.itemPrototypeIswritable(itemPrototypeIswritable, auth);

	}

	public void testItemPrototypeUpdate(String auth) {
		// 数据准备
		ItemPrototypeUpdateRequest itemPrototypeUpdate = new ItemPrototypeUpdateRequest(auth);
		
		itemPrototypeUpdate.getParams().setItemid("42734");
		itemPrototypeUpdate.getParams().setName("15test");
		itemPrototypeUpdate.getParams().setRuleid("1");
		itemPrototypeUpdate.getParams().setKey_("ipAdEntAddr3[{#SNMPVALUE}]");
		itemPrototypeUpdate.getParams().setHostid("93");
		itemPrototypeUpdate.getParams().setType(4);
		itemPrototypeUpdate.getParams().setValue_type(3);
		itemPrototypeUpdate.getParams().setDelay(30);
		itemPrototypeUpdate.getParams().setInterfaceid("54");
//		itemPrototypeCreate.getParams().getApplications().add("13");
		
		
		itemprototypeService.itemPrototypeUpdate(itemPrototypeUpdate, auth);

	}
}