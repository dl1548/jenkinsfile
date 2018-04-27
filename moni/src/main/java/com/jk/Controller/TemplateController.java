package com.jk.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zabbix.api.domain.application.ApplicationDeleteRequest;
import com.zabbix.api.domain.application.ApplicationGetRequest;
import com.zabbix.api.domain.base.Host;
import com.zabbix.api.domain.base.HostGroup;
import com.zabbix.api.domain.item.ItemCreateRequest;
import com.zabbix.api.domain.item.ItemDeleteRequest;
import com.zabbix.api.domain.item.ItemGetRequest;
import com.zabbix.api.domain.item.ItemUpdateRequest;
import com.zabbix.api.domain.template.TemplateCreateRequest;
import com.zabbix.api.domain.template.TemplateDeleteRequest;
import com.zabbix.api.domain.template.TemplateGetRequest;
import com.zabbix.api.domain.template.TemplateUpdateRequest;
import com.zabbix.api.domain.trigger.TriggerCreateRequest;
import com.zabbix.api.domain.trigger.TriggerDeleteRequest;
import com.zabbix.api.domain.trigger.TriggerGetRequest;
import com.zabbix.api.domain.trigger.TriggerUpdateRequest;
import com.zabbix.api.service.IApplicationService;
import com.zabbix.api.service.IItemService;
import com.zabbix.api.service.ITemplateService;
import com.zabbix.api.service.ITriggerService;
import com.zabbix.api.service.impl.ApplicationServiceImpl;
import com.zabbix.api.service.impl.ItemServiceImpl;
import com.zabbix.api.service.impl.TemplateServiceImpl;
import com.zabbix.api.service.impl.TriggerServiceImpl;
import com.zabbix.api.test.Util;
import com.zabbix4j.ZabbixApiException;
import com.zabbix4j.application.ApplicationCreateRequest;
import com.zabbix4j.application.ApplicationCreateResponse;
import com.zabbix4j.application.ApplicationUpdateRequest;
import com.zabbix4j.application.ApplicationUpdateResponse.Result;

@RestController
public class TemplateController {
	private static ITemplateService templateService=new TemplateServiceImpl();
	private static IApplicationService applicationService=new ApplicationServiceImpl();
	private static IItemService itemService=new ItemServiceImpl();
	private static ITriggerService triggerService=new TriggerServiceImpl();
	//private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);
	/**
	 * 获取模板详细信息
	 * @return
	 */
	@PostMapping(value="/getTemplates")
	public String getTemplates(@RequestParam("auth")String auth){
		TemplateGetRequest get=new TemplateGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setSelectApplications("count");
		get.getParams().setSelectItems("count");
		get.getParams().setSelectTriggers("count");
		get.getParams().setSelectHosts("extend");
		
		return templateService.get(get, auth).toString();
	}

	/**
	 * 创建模板
	 * @param host名字
	 * @param groupId主机组
	 * @param hostId主机
	 * @return
	 */
	@PostMapping(value="createTemplate")
	public String createTemplate(@RequestParam(value="host",required=false) String host,
			@RequestParam("hostgroupid") String[] groupId,
			@RequestParam(value="hostId",required=false) String[] hostId,@RequestParam("auth")String auth){
		TemplateCreateRequest create=new TemplateCreateRequest(auth);
		create.getParams().setHost(host);
//		create.getParams().setName(host);
		if(groupId!=null){
			for (int i = 0; i < groupId.length; i++) {
				HostGroup hg = new HostGroup();
				hg.setGroupid(groupId[i]);
				create.getParams().getGroups().add(hg);
			}
		}
		
		if (hostId!=null&&hostId.length>0) {
			for (int i = 0; i < hostId.length; i++) {
				Host h = new Host();
				h.setHostid(hostId[i]);
				create.getParams().getHosts().add(h);
			}
		}
		
		
		
		
		return templateService.create(create, auth).toString();
	}
	/**
	 * 删除模板
	 * @param templateId 模板id
	 * @return
	 */
	@PostMapping(value="/deleteTemplate")
	public String deleteTemplate(@RequestParam(value="templateId") String[] templateId,@RequestParam("auth")String auth){
		TemplateDeleteRequest delete = new TemplateDeleteRequest(auth);
		for (int i = 0; i < templateId.length; i++) {
			delete.getParams().add(templateId[i]);
			
		}
		return templateService.delete(delete, auth).toString();
	}
	/**
	 * 修改
	 * @param templateId
	 * @param host
	 * @param groupId
	 * @param hostId
	 * @return
	 */
	@PostMapping(value="/updateTemplate")
	public String updateTemplate(@RequestParam(value="templateId") String templateId,
			@RequestParam(value="host",required=false) String host,
			@RequestParam(value="groupId",required=false) String[] groupId,
			@RequestParam(value="hostId",required=false) String[] hostId,@RequestParam("auth")String auth){
TemplateUpdateRequest update = new TemplateUpdateRequest(auth);
		
		update.getParams().setTemplateid(templateId);
		update.getParams().setHost(host);
		for (int i = 0; i < hostId.length; i++) {
			Host h = new Host();
			h.setHostid(hostId[i]);
			update.getParams().getHosts().add(h);
		}
		for (int i = 0; i < groupId.length; i++) {
			HostGroup hg = new HostGroup();
			hg.setGroupid(groupId[i]);
			update.getParams().getGroups().add(hg);
		}
		
		
		return templateService.update(update, auth).toString();
	}
	/**
	 * 根据模板获取应用集
	 * @param templateId模板Id
	 * @return
	 */
	@PostMapping(value="/getApplicationByTemplateId")
	public String getApplicationByTemplateId(@RequestParam("templateId") String templateId,@RequestParam("auth")String auth){
		List<String> templateIds=new ArrayList<String>();
		templateIds.add(templateId);
		ApplicationGetRequest get=new ApplicationGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setTemplateids(templateIds);
		get.getParams().setSelectItems("items");
		
		
		return applicationService.get(get, auth).toString();
	}
	/**
	 * 根据模板获取监控项
	 * @param templateId模板Id
	 * @return
	 */
	@PostMapping(value="/getItemByTemplateId")
	public String getItemByTemplateId(@RequestParam("templateId") String[] templateId,@RequestParam("auth")String auth){
		ItemGetRequest get=new ItemGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setSelectApplications("extend");
		get.getParams().setTemplateids(templateId);
		return itemService.get(get).toString();
	}
	/**
	 * 根据模板id获取触发器
	 * @param templateId
	 * @return
	 */
	@PostMapping(value="/getTriggerByTemplateId")
	public String getTriggerByTemplateId(@RequestParam("templateId") String templateId,@RequestParam("auth")String auth){
		TriggerGetRequest get=new TriggerGetRequest(auth);
		String[] ids={"description","status","priority","lastchange","expression","type","comments"};
		List<String> list=new ArrayList<>();
		list.add(templateId);
		get.getParams().setOutput(ids);
		get.getParams().setTemplateids(list);
		get.getParams().setExpandExpression(true);
		get.getParams().setExpandComment(true);
		
		 return triggerService.getObj(get, auth).toString();
	}
//	public static void main(String[] args) {
//		TriggerGetRequest get=new TriggerGetRequest();
//		String[] ids={"extend"};
//		List<String> list=new ArrayList<>();
//		list.add("10177");
////		get.getParams().setOutput(ids);
//		get.getParams().setTemplateids(list);
//		
//		 triggerService.getObj(get).toString();
//	}
	/**
	 * 创建应用集
	 * @param name应用集名称
	 * @param hostId 主机ID
	 * @return
	 */
	@PostMapping(value="/createApplication")
	public com.zabbix4j.application.ApplicationCreateResponse.Result createApplication(@RequestParam("name") String name,@RequestParam("auth") String auth,@RequestParam("hostId") Integer hostId){
		ApplicationCreateRequest create=new ApplicationCreateRequest();
		com.zabbix4j.application.Application ap=new com.zabbix4j.application.Application(Util.url,auth);
		create.getParams().setName(name);
		create.getParams().setHostid(hostId);
		ApplicationCreateResponse response=null;
		try {
			response=ap.create(create);
			
		} catch (ZabbixApiException e) {
			e.printStackTrace();
		}
		if(response!=null){
			return response.getResult();
		}
		return null;
	}
	
	/**
	 * 删除应用集
	 * @param id 应用集id
	 * @return
	 */
	@PostMapping(value="/deleteApplication")
	public String deleteApplication(@RequestParam("id") Integer[] id,@RequestParam("auth")String auth){
	ApplicationDeleteRequest delete=new ApplicationDeleteRequest(auth);
		for (int i = 0; i < id.length; i++) {
			
			delete.getParams().add(id[i].toString());
		}
		
		return applicationService.delete(delete, auth).toString();
	}
	/**
	 * 修改应用集
	 * @param applicationId应用集id
	 * @param hostId主机id
	 * @param name应用集名字
	 * @return
	 * @throws ZabbixApiException 
	 */
	@PostMapping(value="/updateApplication")
	public Result updateApplication(@RequestParam("applicationId") Integer applicationId,
			@RequestParam("hostId") Integer hostId,@RequestParam("auth") String auth,
			@RequestParam("name") String name) throws ZabbixApiException{
		ApplicationUpdateRequest update=new ApplicationUpdateRequest();
		com.zabbix4j.application.Application ap=new com.zabbix4j.application.Application(Util.url,auth);
		update.getParams().setApplicationid(applicationId);
		update.getParams().setHostid(hostId);
		update.getParams().setName(name);
		
		return ap.update(update).getResult();

	}
	/**
	 * 根据ID获取监控项
	 * @param hostIds主机id
	 * @param appId应用集id
	 * @return
	 */
	@PostMapping(value="/getItemByApplicationId")
	public String getItemByApplicationId(@RequestParam("hostIds") String[] hostIds,@RequestParam("auth")String auth){
		ItemGetRequest get = new ItemGetRequest(auth);
		get.getParams().setOutput("extend");// shorten, refer, extend
//		String[] hostids = { "10177" };
//		String[] appid={"935"};
		get.getParams().setHostids(hostIds);
		get.getParams().setSelectTriggers("count");
		get.getParams().setSelectApplications("extend");
		
		return itemService.get(get).toString();
	}
	@PostMapping(value="/getItemByApplication")
	public String getItemByApplication(@RequestParam("applicationId") String applicationId,@RequestParam("auth")String auth){
		ItemGetRequest get = new ItemGetRequest(auth);
		get.getParams().setOutput("extend");// shorten, refer, extend
//		String[] hostids = { "10177" };
//		String[] appid={"935"};
		get.getParams().setSelectTriggers("count");
		get.getParams().setApplicationids(new String[]{applicationId});
		get.getParams().setSelectApplications("extend");
	
		return itemService.get(get).toString();
	}
	/**
	 * 删除监控项
	 * @param itemIds监控项ID
	 * @return
	 */
	@PostMapping(value="/deleteItem")
	public String deleteItem(@RequestParam("itemIds") String[] itemIds,@RequestParam("auth")String auth){
		ItemDeleteRequest delete = new ItemDeleteRequest(auth);
		for (int i = 0; i < itemIds.length; i++) {
			
			delete.getParams().add(itemIds[i]);
		}
		return itemService.delete(delete, auth).toString();
	}
	/**
	 * 创建监控项
	 * @param name 名称
	 * @param type 类型
	 * @param key  键值
	 * @param hostid 模板id
	 * @param value_type  信息类型
	 * @param data_type   数据类型
	 * @param units       单位
	 * @param formula     自定义倍数
	 * @param delay       数据更新间隔
	 * @param history     历史数据保留时长
	 * @param trends      趋势数据存储周期
	 * @param delta       存储值
	 * @param port 		    端口
	 * @param snmpOid     SNMPOID
	 * @param snmpCommunity  	  SNMP community:
	 * @param ipmiSensor  ipmi传感器
	 * @param params  公式
	 * @return
	 */
	@PostMapping(value="/createItem")
	public String createItem(@RequestParam("name")String name,@RequestParam("type")Integer type,@RequestParam(value="status",required=false)String status,
			                 @RequestParam("hostid")String hostid,@RequestParam(value="interfaceid",required=false)String interfaceid,
			                 @RequestParam(value="port",required=false)String port,@RequestParam(value="ipmiSensor",required=false)String ipmiSensor,@RequestParam(value="params",required=false)String params,
			                 @RequestParam(value="snmpOid",required=false)String snmpOid,@RequestParam(value="snmpCommunity",required=false)String snmpCommunity,
			                 @RequestParam("key")String key,@RequestParam("value_type")Integer value_type,
			                 @RequestParam(value="data_type",required=false)Integer data_type,@RequestParam(value="units",required=false)String units,
			                 @RequestParam(value="formula",required=false)Integer formula,@RequestParam("delay")Integer delay,
			                 @RequestParam(value="history",required=false)Integer history,@RequestParam(value="trends",required=false)Integer trends,
			                 @RequestParam(value="delta",required=false)Integer delta,@RequestParam(value="applications",required=false)List<String> applications,@RequestParam("auth")String auth){
		
		ItemCreateRequest request=new ItemCreateRequest(auth);
		request.getParams().setName(name);
		switch (type) {
		case 0:
			type=0;
			break;
		case 1:
			type=1;
			break;
		case 2:
			type=12;
			break;
		case 3:
			type=15;
			break;
		default:
			break;
		}
		request.getParams().setType(type);
		request.getParams().setKey_(key);
		request.getParams().setValue_type(value_type);
		request.getParams().setDelay(delay);
		request.getParams().setHostid(hostid);
		if(null!=interfaceid)request.getParams().setInterfaceid(interfaceid);
		if(null!=snmpOid)request.getParams().setSnmp_oid(snmpOid);
		if(null!=ipmiSensor)request.getParams().setIpmi_sensor(ipmiSensor);
		if(null!=params)request.getParams().setParams(params);
		if(null!=snmpCommunity)request.getParams().setSnmp_community(snmpCommunity);
		if(null!=port)request.getParams().setPort(port);
		if(null!=data_type)request.getParams().setData_type(data_type);
		if(null!=units)request.getParams().setUnits(units);
		if(null!=formula)request.getParams().setFormula(formula);
		if(null!=history)request.getParams().setHistory(history);
		if(null!=trends)request.getParams().setTrends(trends);
		if(null!=delta)request.getParams().setDelta(delta);
		if(status.equals("true")){
			request.getParams().setStatus(0);
		}else if(status.equals("false")){request.getParams().setStatus(1);}
		
		if(null!=applications)request.getParams().setApplications(applications);
		String result=itemService.create(request, auth).toString();
		return result;
	}
	@PostMapping(value="/updateItem")
	public String updateItem(@RequestParam("itemid")String itemid,@RequestParam("status") String status,
			@RequestParam("name")String name,@RequestParam("type")Integer type,
            @RequestParam("hostid")String hostid,@RequestParam(value="interfaceid",required=false)String interfaceid,
            @RequestParam(value="port",required=false)String port,@RequestParam(value="ipmiSensor",required=false)String ipmiSensor,@RequestParam(value="params",required=false)String params,
            @RequestParam(value="snmpOid",required=false)String snmpOid,@RequestParam(value="snmpCommunity",required=false)String snmpCommunity,
            @RequestParam("key")String key,@RequestParam("value_type")Integer value_type,
            @RequestParam(value="data_type",required=false)Integer data_type,@RequestParam(value="units",required=false)String units,
            @RequestParam(value="formula",required=false)Integer formula,@RequestParam("delay")Integer delay,
            @RequestParam(value="history",required=false)Integer history,@RequestParam(value="trends",required=false)Integer trends,
            @RequestParam(value="delta",required=false)Integer delta,@RequestParam(value="applications",required=false)List<String> applications,@RequestParam("auth")String auth){
		switch (type) {
		case 0:
			type=0;
			break;
		case 1:
			type=1;
			break;
		case 2:
			type=12;
			break;
		case 3:
			type=15;
			break;
		default:
			break;
		}
		ItemUpdateRequest request=new ItemUpdateRequest(auth);
		request.getParams().setItemid(itemid);
		request.getParams().setName(name);
		request.getParams().setType(type);
		request.getParams().setKey_(key);
		request.getParams().setValue_type(value_type);
		request.getParams().setDelay(delay);
		if(null!=data_type)request.getParams().setData_type(data_type);
		if(null!=units)request.getParams().setUnits(units);
		if(null!=formula)request.getParams().setFormula(formula);
		if(null!=history)request.getParams().setHistory(history);
		if(null!=trends)request.getParams().setTrends(trends);
		if(null!=delta)request.getParams().setDelta(delta);
		if(null!=applications)request.getParams().setApplications(applications);
		if(status.equals("true")){
			request.getParams().setStatus(0);
		}else if(status.equals("false")){request.getParams().setStatus(1);}
		String result=itemService.update(request, auth).toString();
		return result;
	}
	/**
	 * 监控项获取触发器
	 * @param itemId
	 * @return
	 */
	@PostMapping(value="/getTriggerByItem")
	public String getTriggerByItem(@RequestParam("itemId") String itemId,@RequestParam("auth")String auth){
		TriggerGetRequest get=new TriggerGetRequest(auth);
		List<String> itemids=new ArrayList<>();
		itemids.add(itemId);
		get.getParams().setOutput(new String[]{"description","status","priority","lastchange","expression","type"});
		get.getParams().setItemids(itemids);
		get.getParams().setExpandExpression(true);
		get.getParams().setExpandComment(true);
		
		return triggerService.getObj(get, auth).toString();
	}
	/**
	 * 创建触发器
	 * @param description 描述
	 * @param type 		    类型 0不生成 1生成多个事件
	 * @param priority    等级
	 * @param expression  公式
	 * @param comments    注释
	 * @param status	  启用0 未启用1
	 * @param url         url
	 */
	@PostMapping(value="/newTrigger")
	public String createTrigger(@RequestParam("description")String description,@RequestParam("type")String type,
			                  @RequestParam("priority")Integer priority,@RequestParam("expression")String expression,
			                  @RequestParam(value="url",required=false)String url,
			                  @RequestParam(value="comments",required=false)String comments,
			                  @RequestParam(value="status",required=false)String status,
			                  @RequestParam("auth")String auth){
		ITriggerService triggerService=new TriggerServiceImpl();
		TriggerCreateRequest create = new TriggerCreateRequest(auth);
		create.getParams().setDescription(description);
		if(type.equals("true")){
			create.getParams().setType(0);
		}else if(type.equals("false")){create.getParams().setType(1);}
		create.getParams().setPriority(priority);
		create.getParams().setExpression(expression);
		create.getParams().setComments(comments);
		if(null!=url){
			create.getParams().setUrl(url);
		}
		
		if(status.equals("true")){
			create.getParams().setStatus(0);
		}else if(status.equals("false")){create.getParams().setStatus(1);}
		String str=triggerService.create(create, auth).toString();
		
		return str;
	}
	/**
	 * 修改触发器信息
	 * @param description
	 * @param type
	 * @param priority
	 * @param expression
	 * @param comments
	 * @param triggerId
	 * @return
	 */
	@PostMapping(value="/updateTrigger")
	public String updateTrigger(@RequestParam("description")String description,@RequestParam("type")String type,@RequestParam("status")String status,
			                  @RequestParam("priority")Integer priority,@RequestParam("expression")String expression,
			                  @RequestParam(value="comments",required=false)String comments,@RequestParam("triggerId") String triggerid,@RequestParam("auth")String auth){
		ITriggerService triggerService=new TriggerServiceImpl();
		TriggerUpdateRequest update = new TriggerUpdateRequest(auth);
		update.getParams().setDescription(description);
		if(type.equals("true")){
			update.getParams().setType(0);
		}else if(type.equals("false")){update.getParams().setType(1);}
		if(status.equals("true")){
			update.getParams().setStatus(0);
		}else if(status.equals("false")){update.getParams().setStatus(1);}
		update.getParams().setPriority(priority);
		update.getParams().setExpression(expression);
		update.getParams().setComments(comments);
		update.getParams().setTriggerid(triggerid);
		String str=triggerService.update(update, auth).toString();
		
		return str;
}
	/**
	 * 删除触发器
	 * @param triggerIds
	 * @return
	 */
	@PostMapping("/deleteTrigger")
	public String deleteTrigger(@RequestParam("triggerIds") String[] triggerIds,@RequestParam("auth")String auth){
		TriggerDeleteRequest delete=new TriggerDeleteRequest(auth);
		for (int j = 0; j < triggerIds.length; j++) {
			delete.getParams().add(triggerIds[j]);
		}
		
		return triggerService.delete(delete, auth).toString();
	}
	}

