package com.zabbix.api.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import junit.framework.TestCase;



















import com.zabbix.api.domain.base.Trigger;
import com.zabbix.api.domain.trigger.TriggerAdddependenciesRequest;
import com.zabbix.api.domain.trigger.TriggerCreateRequest;
import com.zabbix.api.domain.trigger.TriggerDeleteRequest;
import com.zabbix.api.domain.trigger.TriggerDeletedependenciesRequest;
import com.zabbix.api.domain.trigger.TriggerExistsRequest;
import com.zabbix.api.domain.trigger.TriggerGetRequest;
import com.zabbix.api.domain.trigger.TriggerGetRequest.TriggerGetParams.TriggerFilter;
import com.zabbix.api.domain.trigger.TriggerGetobjectsRequest;
import com.zabbix.api.domain.trigger.TriggerIsreadableRequest;
import com.zabbix.api.domain.trigger.TriggerIswritableRequest;
import com.zabbix.api.domain.trigger.TriggerUpdateRequest;
import com.zabbix.api.service.ITriggerService;
import com.zabbix.api.service.impl.TriggerServiceImpl;

public class TestTrigger extends TestCase {
	private static ITriggerService triggerService = new TriggerServiceImpl();
	static {
		// 登录
		new Util().login();
	}

	public void testAdddependencies(String auth) {
		// 数据准备
		TriggerAdddependenciesRequest adddependencies = new TriggerAdddependenciesRequest(auth);

		adddependencies.getParams().setTriggerid("1714");
		adddependencies.getParams().setDependsOnTriggerid("977");

		triggerService.adddependencies(adddependencies, auth);

	}

	public void testCreate(String auth) {
		// 数据准备
		TriggerCreateRequest create = new TriggerCreateRequest(auth);

		create.getParams().setDescription("主机CPU使用率大于阀值20小于阀值80触发告警");
		create.getParams()
				.setExpression(
						"{Template OS Windows:system.cpu.util[,system].last()}>20&&{Template OS Windows:system.cpu.util[,system].last()}<80");
		create.getParams().setPriority(2);
		// Trigger t = new Trigger();
		// t.setTriggerid("977");
		// create.getParams().getDependencies().add(t);

		triggerService.create(create, auth);

	}

	public void testDeletedependencies(String auth) {
		// 数据准备
		TriggerDeletedependenciesRequest deletedependencies = new TriggerDeletedependenciesRequest(auth);

		Trigger t = new Trigger();
		t.setTriggerid("1714");

		deletedependencies.getParams().add(t);

		triggerService.deletedependencies(deletedependencies, auth);

	}


	public void testExists(String auth) {
		// 数据准备
		TriggerExistsRequest exists = new TriggerExistsRequest(auth);
		exists.getParams().setExpression(
				"{ALLMONI204_0.53:system.hostname.diff(0)}>0");

		triggerService.exists(exists, auth);

	}

	public void testGetobjects(String auth) {
		// 数据准备
		TriggerGetobjectsRequest getobjects = new TriggerGetobjectsRequest(auth);
		getobjects.getParams().setTriggerid("1714");
		triggerService.getobjects(getobjects, auth);

	}

	public List<Trigger> testGet(String auth) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		String[] host={"host"};
		String[] group={"name"};
		String v="1";
		
		TriggerFilter filter=new TriggerFilter();
		filter.setValue(v);
		// 数据准备
		TriggerGetRequest get = new TriggerGetRequest(auth);
		get.getParams().setLastchange(str);
//		get.getParams().setHost("192.168.1.1");
		get.getParams().setOnly_true(true);
		get.getParams().setSelectHosts(host);
		get.getParams().setFilter(filter);
		get.getParams().setSortfield("lastchange");//按照时间排序
		get.getParams().setSortorder("DESC");//降叙
		get.getParams().setSelectGroups(group);
		
		return triggerService.get(get, auth);
		
	}
	public static void main(String[] args,String auth) {
		TestTrigger tt=new TestTrigger();
		tt.testGet(auth);
	}

	public void testGetTriggerBean(String auth) {
		// 数据准备
		TriggerGetRequest get = new TriggerGetRequest(auth);
		//get.getParams().setOutput("shorten");
		List<String> hostids = new ArrayList<String>();
		hostids.add("10715");
		get.getParams().setHostids(hostids);
		List<String> itemids = new ArrayList<String>();
		itemids.add("50969");
		get.getParams().setItemids(itemids);
		// List host = new ArrayList();
		// List status = new ArrayList();
		// host.add("Template OS Linux");
		// status.add(0);
		// get.getParams().getFilter().setHost(host);
		// get.getParams().getFilter().setStatus(status);
		// // get.getParams().setSelectDependencies("10010");
		// get.getParams().getSearch().setDescription("Too many processes on");
//		List<Trigger> list = triggerService.getTriggerBean(get);
//		System.out.println(list.size());

	}

	public void testIsreadable(String auth) {
		// 数据准备
		TriggerIsreadableRequest isreadable = new TriggerIsreadableRequest(auth);
		isreadable.getParams().add("1714");
		triggerService.isreadable(isreadable, auth);

	}

	public void testIswritable(String auth) {
		// 数据准备
		TriggerIswritableRequest iswritable = new TriggerIswritableRequest(auth);
		iswritable.getParams().add("1714");
		triggerService.iswritable(iswritable, auth);

	}

	public void testUpdate(String auth) {
		// 数据准备
		TriggerUpdateRequest update = new TriggerUpdateRequest(auth);
		update.getParams().setTriggerid("13914");
		update.getParams().setDescription("linux_test_trigger1");
		update.getParams().setExpression(
				"{ALLMONI204_0.53:system.hostname.diff(0)}>0");
		//
		// Trigger t = new Trigger();
		// t.setTriggerid("977");
		// update.getParams().getDependencies().add(t);
		triggerService.update(update, auth);
	}

	public void testaddTrigger(String auth) {
		Trigger trigger = new Trigger();
		trigger.setDescription("我的触发器");
		trigger.setExpression("{Template OS Windows:system.cpu.util[,system].last()}>20");
		trigger.setPriority(5);
		trigger.setStatus(0);
		triggerService.addTrigger(trigger, auth);
	}
}