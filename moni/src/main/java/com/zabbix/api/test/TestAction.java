package com.zabbix.api.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.TestCase;

import com.zabbix.api.domain.action.ActionCreateRequest;
import com.zabbix.api.domain.action.ActionDeleteRequest;
import com.zabbix.api.domain.action.ActionExistsRequest;
import com.zabbix.api.domain.action.ActionGetRequest;
import com.zabbix.api.domain.action.ActionGetRequest.ActionGetParams.ActionSearch;
import com.zabbix4j.ZabbixApiException;
import com.zabbix4j.action.*;
import com.zabbix.api.domain.action.ActionUpdateRequest;
import com.zabbix.api.domain.base.Action;
import com.zabbix.api.domain.base.ActionCondition;
import com.zabbix.api.domain.base.ActionFilter;
import com.zabbix.api.domain.base.ActionOperation;
import com.zabbix.api.domain.base.ActionOperationMessage;
import com.zabbix.api.domain.base.ActionOperationSyn;
import com.zabbix.api.service.IActionService;
import com.zabbix.api.service.impl.ActionServiceImpl;
import com.zabbix.util.FormatData;


public class TestAction extends TestCase {
	private static IActionService actionService = new ActionServiceImpl();
	static {
		// 登录
		new Util().login();
	}
//	public static void main(String[] args) throws JSONException {
//	TestAction ta=new TestAction();
//////		List<Action> ac=ta.testGetActionBean();
//////		for (Action a : ac) {
//////			System.out.println(a.getName());
//////		}
//		ta.testUpdate();
//	//ta.testCreate();
////		com.zabbix4j.action.ActionCreateRequest req=new com.zabbix4j.action.ActionCreateRequest();
////		req.getParams();
//		
//	}
	public Object testCreate(String auth) throws JSONException {
		// 数据准备
		ActionCreateRequest create = new ActionCreateRequest(auth);
		create.getParams().setName("testAc555");//输入名字
		create.getParams().setEsc_period(120);//默认操作步骤停留时间(必须大于60秒)
		//create.getParams().setEvaltype(0);//required 计算方式
		create.getParams().setEventsource(0);//required 发生事件的类型（event source）0 - event created by a trigger;默认选择此项
		create.getParams().setDef_shortdata("{TRIGGER.NAME}: {TRIGGER.STATUS}   Last value: {ITEM.LASTVALUE}   {TRIGGER.URL}");//触发的标题
		create.getParams().setDef_longdata("{TRIGGER.NAME}: {TRIGGER.STATUS}");//触发的文本
		create.getParams().setStatus(0);//动作的状态（0：启用;1：禁用）
//		create.getParams().setEvaltype(0);
		ActionOperation ao = new ActionOperation();
		ao.setOperationtype(0);//操作类型（0 - send message;1 - remote command;2 - add host;3 - remove host;4 - add to host group;5 - remove from host group;6 - link to template;7 - unlink from template;8 - enable host;9 - disable host.）
		ao.setEsc_period(0);//步骤持续时间 	Step duration
		ao.setEsc_step_from(1);//开始步骤
		ao.setEsc_step_to(1);//结束步骤
//		ao.setEvaltype(0);//操作算法类型
		ActionCondition ac = new ActionCondition();
		ac.setConditiontype(1);//触发条件类型
		ac.setOperator(0);//条件操作符
		ac.setValue("10165");
		ActionFilter af=new ActionFilter();
		af.setEvaltype(0);
//		af.getConditions().add(ac);
//		create.getParams().getFilter().add(af);
		ActionOperationSyn aos = new ActionOperationSyn();
		aos.setUsrgrpid("7");//用户组的ID
		
		ao.getOpmessage_grp().add(aos);//以设备组运行远程命令操作
		ActionOperationMessage aom = new ActionOperationMessage();
		aom.setDefault_msg(0);//是否使用默认动作消息文本和主题（0为使用，1为不使用）
		aom.setMediatypeid("4");//发送信息的媒体类型
		ao.setOpmessage(aom);//发送消息操作
		
		create.getParams().getOperations().add(ao);

		
//		ac.setValue("10084");//值
//		create.getParams().getConditions().add(ac);
		
		ac.setConditiontype(3);//触发条件类型
		ac.setOperator(2);//条件操作符
		ac.setValue("59");//值
		create.getParams().getConditions().add(ac);
		JSONObject jo=(JSONObject)actionService.create(create, auth);
		
		
			jo.get("id");
			//System.out.println("++++++++++++++++"+jo.get("id"));
		
		return jo;
	}

	public void testAddAction(String auth){
		System.out.println("sssssssssssssss");
		Action action = new Action();
		List<ActionOperation> actionOperations = new ArrayList<ActionOperation>();
		List<ActionCondition> actionConditions = new ArrayList<ActionCondition>();
		// 数据准备
		ActionCreateRequest create = new ActionCreateRequest(auth);
		action.setName("testAction13333");
		action.setEsc_period(120);
//		action.setEvaltype(0);
		action.setEventsource(0);
		action.setDef_shortdata("TRIGGER.NAME}: {TRIGGER.STATUS}   Last value: {ITEM.LASTVALUE}   {TRIGGER.URL}");
		action.setDef_longdata("{TRIGGER.NAME}: {TRIGGER.STATUS}");
		action.setStatus(0);
		ActionOperation ao = new ActionOperation();
		ao.setOperationtype(0);
		ao.setEsc_period(0);
		ao.setEsc_step_from(1);
		ao.setEsc_step_to(2);
//		ao.setEvaltype(0);
		
		ActionOperationSyn aos = new ActionOperationSyn();
		aos.setUsrgrpid("7");
		
		ao.getOpmessage_grp().add(aos);
		ActionOperationMessage aom = new ActionOperationMessage();
		aom.setDefault_msg(1);
		aom.setMediatypeid("1");
		ao.setOpmessage(aom);
		
		actionOperations.add(ao);

		ActionCondition ac = new ActionCondition();
		ac.setConditiontype(2);
		ac.setOperator(0);
		ac.setValue("20629");
		actionConditions.add(ac);
		
		ActionCondition ac1 = new ActionCondition();
		ac1.setConditiontype(5);
		ac1.setOperator(0);
		ac1.setValue("1");
		actionConditions.add(ac1);
		
		ActionCondition ac2 = new ActionCondition();
		ac2.setConditiontype(16);
		ac2.setOperator(7);
		ac2.setValue("");
		actionConditions.add(ac2);
		action.setOperationsl(actionOperations);
		action.setConditionsl(actionConditions);
//		actionService.addAction(action,auth);
//		actionService.create(create);
	}
	
	
	public void testDelete(String auth) {
		// 数据准备
		ActionDeleteRequest delete = new ActionDeleteRequest(auth);
		
		delete.getParams().add("3");
		
		JSONObject rs = (JSONObject) actionService.delete(delete, auth);
		if (rs.has("result")) {
			System.out.println(true);
		}
		 else if (rs.has("error")) {
			 System.out.println(false);
		}

	}

	public void testExists(String auth) {
		// 数据准备
		ActionExistsRequest exists = new ActionExistsRequest(auth);
		exists.getParams().getActionid().add("31");
		actionService.exists(exists, auth);

	}
	public static void main(String[] args) {
		TestAction ta=new TestAction();
		try {
			ta.testGet(FormatData.auth);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Object testGet(String auth) throws JSONException {
		// 数据准备
		ActionGetRequest get = new ActionGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setSelectConditions("extend");
		get.getParams().setSelectOperations("extend");
		get.getParams().setSelectFilter("extend");
//		JSONObject jo=(JSONObject)actionService.get(get, auth);
		return actionService.get(get, auth);

	}
//public static void main(String[] args) throws JSONException {
//	TestAction ta=new TestAction();
//	List<Action> ac=ta.testGetActionBean();
//	for (int i = 0; i < ac.size(); i++) {
//		System.out.println("+++++++"+ac.get(i).getConditions());
//	}
////	ta.testUpdate();
//	
//}

	public List<Action> testGetActionBean(String auth) throws JSONException {
		// 数据准备
		List<Integer> list=new ArrayList<Integer>();
		list.add(0);
		ActionGetRequest get = new ActionGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setSelectConditions("extend");
		get.getParams().setSelectOperations("extend");
		get.getParams().setSelectFilter("extend");
//		get.getParams().getFilter().setEventsource(list);
//		get.getParams().getActionids().add("3");
		List<Action> actionlist =null;
		try {
			actionlist = actionService.getActionBean(get, auth);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actionlist;
	}

	public void testUpdate(String auth) {
		// 数据准备
		ActionUpdateRequest update = new ActionUpdateRequest(auth);
		update.getParams().setActionid("20");

		update.getParams().setName("testAction123457778888");
		update.getParams().setEsc_period(120);
//		update.getParams().setEvaltype(0);
		//update.getParams().setEventsource(0);
		update.getParams().setDef_shortdata("TRIGGER.NAME}: {TRIGGER.STATUS}   Last value: {ITEM.LASTVALUE}   {TRIGGER.URL}");
		update.getParams().setDef_longdata("{TRIGGER.NAME}: {TRIGGER.STATUS}");
		update.getParams().setStatus(0);
		ActionFilter af=new ActionFilter();
		ActionOperation ao = new ActionOperation();
		ao.setOperationtype(0);
		ao.setEsc_period(0);
		ao.setEsc_step_from(1);
		ao.setEsc_step_to(1);
//		ao.setEvaltype(0);
		ActionCondition ac = new ActionCondition();
		ac.setConditiontype(16);//触发条件类型
		ac.setOperator(4);//条件操作符
		ac.setValue("");
		
		ActionCondition ac1 = new ActionCondition();
		ac1.setConditiontype(5);//触发条件类型
		ac1.setOperator(0);//条件操作符
		ac1.setValue("1");
		ActionFilter afupdate=new ActionFilter();
		afupdate.setEvaltype(0);
		afupdate.getConditions().add(ac);
		afupdate.getConditions().add(ac1);
		update.getParams().setFilter(afupdate);
		ActionOperationSyn aos = new ActionOperationSyn();
		aos.setUsrgrpid("7");
		
		ao.getOpmessage_grp().add(aos);
		ActionOperationMessage aom = new ActionOperationMessage();
		aom.setDefault_msg(1);
		aom.setMediatypeid("1");
		ao.setOpmessage(aom);
		
		update.getParams().getOperations().add(ao);
	

//		update.getParams().getConditions().add(ac);
		
		actionService.update(update, auth);

	}

	public void testUpdateByBean(String auth) {
		Action action = new Action();
		List<ActionOperation> actionOperations = new ArrayList<ActionOperation>();
		List<ActionCondition> actionConditions = new ArrayList<ActionCondition>();
		// 数据准备
//		ActionCreateRequest create = new ActionCreateRequest();
		action.setActionid("21");
		action.setName("testAction2");
		action.setEsc_period(120);
//		action.setEvaltype(0);
//		action.setEventsource(0);
		action.setDef_shortdata("TRIGGER.NAME}: {TRIGGER.STATUS}   Last value: {ITEM.LASTVALUE}   {TRIGGER.URL}");
		action.setDef_longdata("{TRIGGER.NAME}: {TRIGGER.STATUS}");
		action.setStatus(0);

		ActionOperation ao = new ActionOperation();
		ao.setOperationtype(0);
		ao.setEsc_period(0);
		ao.setEsc_step_from(1);
		ao.setEsc_step_to(2);
//		ao.setEvaltype(0);
		
		ActionOperationSyn aos = new ActionOperationSyn();
		aos.setUsrgrpid("7");
		
		ao.getOpmessage_grp().add(aos);
		ActionOperationMessage aom = new ActionOperationMessage();
		aom.setDefault_msg(1);
		aom.setMediatypeid("1");
		ao.setOpmessage(aom);
		
		actionOperations.add(ao);

		ActionCondition ac = new ActionCondition();
		ac.setConditiontype(2);
		ac.setOperator(0);
		ac.setValue("20629");
		actionConditions.add(ac);
		
		ActionCondition ac1 = new ActionCondition();
		ac1.setConditiontype(5);
		ac1.setOperator(0);
		ac1.setValue("1");
		actionConditions.add(ac1);
		
//		ActionCondition ac2 = new ActionCondition();
//		ac2.setConditiontype(16);
//		ac2.setOperator(7);
//		ac2.setValue("");
//		actionConditions.add(ac2);
		action.setOperationsl(actionOperations);
		action.setConditionsl(actionConditions);
		
		//actionService.updateAction(action, auth);

	}
	
	public void testdsssText(String auth){
		DecimalFormat formater = new DecimalFormat("0.000000");
		formater.setRoundingMode(RoundingMode.FLOOR);
		System.out.println((formater.format(11111112.227)));
	}
	
}