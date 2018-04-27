package com.jk.Controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zabbix.api.domain.event.EventGetRequest;
import com.zabbix.api.domain.trigger.TriggerDeleteRequest;
import com.zabbix.api.domain.trigger.TriggerGetRequest;
import com.zabbix.api.service.IEventService;
import com.zabbix.api.service.ITriggerService;
import com.zabbix.api.service.impl.EventServiceImpl;
import com.zabbix.api.service.impl.TriggerServiceImpl;
import com.zabbix4j.alert.AlertGetRequest;

@RestController
public class TriggerControllor {
	private static ITriggerService triggerService=new TriggerServiceImpl();
	private static IEventService eventService=new EventServiceImpl();
	//private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);
	
	/**
	 * 根据模板获取触发器
	 * @param templateId 模板名字
	 * @return
	 */
	@PostMapping(value="/getTriggerByTem")
	public String getTriggerByTem(@RequestParam("template") String template,@RequestParam("auth")String auth){
		TriggerGetRequest get=new TriggerGetRequest(auth);
		String [] output={"description","status","priority","lastchange","expression","type","comments","value"};
		get.getParams().setOutput(output);
		get.getParams().setHost(template);
		get.getParams().setExpandExpression(true);
		get.getParams().setSelectItems("extend");
		return triggerService.getObj(get, auth).toString();
	}
	/**
	 * 删除触发器
	 * @param triggerIds触发器id
	 * @return
	 */
	public String deleteTrigger(@RequestParam("triggerIds") Integer[] triggerIds,@RequestParam("auth")String auth){
		TriggerDeleteRequest delete=new TriggerDeleteRequest(auth);
		for (int i = 0; i < triggerIds.length; i++) {
			delete.getParams().add(triggerIds[i].toString());
		}
		
		return triggerService.delete(delete, auth).toString();
	}
	/**
	 * 获取告警
	 * @return
	 */
	@PostMapping("/getTrigger")
	public String getTrigger(@RequestParam("auth")String auth){
			JSONObject json=new JSONObject();
			TriggerGetRequest tget=new TriggerGetRequest(auth);
			String [] output={"description","status","priority","lastchange","expression","value"};
			String [] hosts={"host"};
			String [] groups={"name"};
			tget.getParams().setOutput(output);
			tget.getParams().setExpandExpression(true);
			tget.getParams().setExpandComment(true);
			tget.getParams().setSelectLastEvent("extend");
			tget.getParams().getFilter().setValue("1");
			tget.getParams().setSelectHosts(hosts);
			tget.getParams().setSelectGroups(groups);
			Object tObj=triggerService.getObj(tget, auth);
			try {
				json.put("trigger",tObj);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			EventGetRequest eget=new EventGetRequest(auth);
			eget.getParams().setOutput("extend");
			eget.getParams().setSelect_acknowledges("extend");
			eget.getParams().setSelectHosts("extend");
			eget.getParams().setSelect_alerts("extend");
			List<Integer> values = new ArrayList<Integer>();
			values.add(1);
			eget.getParams().setValue(values);
			eget.getParams().setSource(0);
			eget.getParams().setObject(0);
			eget.getParams().setLimit(50);
			eget.getParams().getSortfield().add("clock");
			eget.getParams().getSortorder().add("DESC");
			AlertGetRequest aget=new AlertGetRequest();
			aget.getParams().setOutput("extend");
			aget.getParams().setLimit(50);
			try {
				json.put("event",eventService.get(eget, auth));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		
		return json.toString();
	}
	/**
	 * 根据主机和触发器id获取事件
	 * @param triggerId
	 * @param hostId
	 * @return
	 */
	@PostMapping(value="/getEventByTrigger")
	public String getEventByTrigger(@RequestParam(value="triggerId") String triggerId,
			@RequestParam(value="hostId") List<String> hostId,@RequestParam(value="eventId") String eventId,@RequestParam("auth")String auth){
		EventGetRequest eget=new EventGetRequest(auth);
		eget.getParams().setOutput("extend");
		List<Integer> values = new ArrayList<>();
		values.add(1);
		List<String> eventids=new ArrayList<>();
		eventids.add(eventId);
		eget.getParams().setValue(values);
		eget.getParams().setSource(0);
		eget.getParams().setObject(0);
		eget.getParams().setEventids(eventids);
		eget.getParams().setObjectids(triggerId);
		eget.getParams().setSelect_acknowledges("extend");
		eget.getParams().setSelect_alerts("extend");
		eget.getParams().setSelectHosts("extend");
		eget.getParams().setHostids(hostId);
		
		return eventService.get(eget, auth).toString();
	}
	/**
	 * 查询告警
	 * @param time
	 * @return
	 */
	@PostMapping(value="/selectTriggerBySelect")
	public String selectTriggerByTime(@RequestParam(value="hostgroup",required=false) String hostgroup,@RequestParam(value="time",required=false) Integer time
			,@RequestParam(value="priority",required=false) Integer priority,@RequestParam(value="host",required=false) String host
			,@RequestParam(value="everyOne",required=false) Integer everyOne,@RequestParam("auth")String auth){
		Object tObj=new Object();
		TriggerGetRequest tget=new TriggerGetRequest(auth);
			String [] output={"description","status","priority","lastchange","expression","value"};
			String [] hosts={"host"};
			String [] groups={"name"};
			tget.getParams().setOutput(output);
			tget.getParams().setExpandExpression(true);
			tget.getParams().setExpandComment(true);
			tget.getParams().setExpandDescription(true);
			tget.getParams().setSelectLastEvent("extend");
			tget.getParams().setSortfield("lastchange");//按照时间排序
			tget.getParams().setSortorder("DESC");//降叙
			if (!hostgroup.equals("")) {
				List<String> groupids=new ArrayList<>();
				groupids.add(hostgroup);
				tget.getParams().setGroupids(groupids);
			}
			if(time!=null){
				String lastChangeSince=null;
				Long now=System.currentTimeMillis()/1000;
				lastChangeSince=String.valueOf(now-time*86400);	
				tget.getParams().setLastChangeSince(lastChangeSince);
			}
			if (!host.equals("")) {
				List<String> hostids=new ArrayList<>();
				hostids.add(host);
				tget.getParams().setHostids(hostids);
			}
			if (priority!=null) {
				tget.getParams().setMin_severity(priority);
			}
			if(everyOne==1){
				tget.getParams().getFilter().setValue("1");
			}
			tget.getParams().setSelectHosts(hosts);
			
			tget.getParams().setSelectGroups(groups);
			 tObj=triggerService.getObj(tget, auth);
		return tObj.toString();
	}
			
		 
		

//public static void main(String[] args) {
//	EventGetRequest eget=new EventGetRequest();
//	eget.getParams().setOutput("extend");
////	get.getParams().setSelectItems("extend");
//	eget.getParams().setSelect_acknowledges("extend");
//	eget.getParams().setSelectHosts("extend");
//	eget.getParams().setSelect_alerts("extend");
////	get.getParams().setSelectTriggers("extend");
//	List values = new ArrayList();
//	values.add("1");
//	List<String> hostids=new ArrayList<>();
//	hostids.add("10189");
//	eget.getParams().setHostids(hostids);
//	eget.getParams().setValue(values);
//	eget.getParams().setSource(0);
//	eget.getParams().setObject(0);
//	eget.getParams().setObjectids(triggerId);
////	get.getParams().getTriggerids().add("967");
////	get.getParams().setTime_from(1399132800l);
//	eget.getParams().getSortfield().add("clock");
//	eget.getParams().getSortorder().add("ASC");
//	eventService.get(eget).toString();
//}
}
