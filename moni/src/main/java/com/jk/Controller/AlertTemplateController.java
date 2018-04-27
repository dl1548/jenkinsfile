package com.jk.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zabbix.api.domain.action.ActionCreateRequest;
import com.zabbix.api.domain.action.ActionGetRequest;
import com.zabbix.api.domain.action.ActionUpdateRequest;
import com.zabbix.api.domain.base.Action;
import com.zabbix.api.domain.base.ActionCondition;
import com.zabbix.api.domain.base.ActionFilter;
import com.zabbix.api.domain.base.ActionOperation;
import com.zabbix.api.domain.base.ActionOperationMessage;
import com.zabbix.api.domain.base.ActionOperationSyn;
import com.zabbix.api.domain.base.Host;
import com.zabbix.api.domain.base.Item;
import com.zabbix.api.domain.base.Template;
import com.zabbix.api.domain.host.HostGetRequest;
import com.zabbix.api.domain.item.ItemGetRequest;
import com.zabbix.api.domain.template.TemplateDeleteRequest;
import com.zabbix.api.domain.trigger.TriggerCreateRequest;
import com.zabbix.api.domain.trigger.TriggerGetRequest;
import com.zabbix.api.domain.trigger.TriggerUpdateRequest;
import com.zabbix.api.service.IActionService;
import com.zabbix.api.service.IHostService;
import com.zabbix.api.service.IItemService;
import com.zabbix.api.service.ITemplateService;
import com.zabbix.api.service.ITriggerService;
import com.zabbix.api.service.impl.ActionServiceImpl;
import com.zabbix.api.service.impl.HostServiceImpl;
import com.zabbix.api.service.impl.ItemServiceImpl;
import com.zabbix.api.service.impl.TemplateServiceImpl;
import com.zabbix.api.service.impl.TriggerServiceImpl;
import com.zabbix.api.test.TestAction;
import com.zabbix.api.test.TestTemplate;
import com.zabbix.api.test.Util;
import com.zabbix4j.ZabbixApiException;
import com.zabbix4j.action.ActionDeleteRequest;
import com.zabbix4j.host.HostGetResponse.Result;
@RestController
public class AlertTemplateController {
	private static ITemplateService templateService = new TemplateServiceImpl();
	private static IItemService itemService = new ItemServiceImpl();
	private static IHostService hostService = new HostServiceImpl();
	private static ITriggerService triggerService = new TriggerServiceImpl();
	private static IActionService actionService = new ActionServiceImpl();
	private static Integer groupOrHost=null;//判断主机还是主机组
	private static List<String> listh=new ArrayList<String>();
	private static List<String> listg=new ArrayList<String>();
	//private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);
	/**
	 * 
	 * @return返回所有监控模板信息
	 */
	@PostMapping(value="/getTemplate")
	public List<Template> getTemplate(@RequestParam("auth")String auth){
		TestTemplate tem=new TestTemplate();
		
		return tem.testGetBean(auth);
	}

	/**
	 * 删除模板
	 * @param templateId 前端传来模板id
	 * @return 返回结果
	 * @throws JSONException 
	 */
	@PostMapping(value="/deleteTemplates")
	public String deleteTemplates(@RequestParam("templateId") Integer[] templateId,@RequestParam("auth")String auth) throws JSONException{
		String  response=null;
		TemplateDeleteRequest deleteRequest=new TemplateDeleteRequest(auth);
		if(templateId!=null){
			String tem=Arrays.toString(templateId);
			deleteRequest.getParams().add(tem);
			 response=(String) templateService.delete(deleteRequest, auth);
//			JSONObject rs = new JSONObject(response);
//				if (rs.has("result")) {
//					result ="删除成功!"; 
//				}
//				 else if (rs.has("error")) {
//					result = (String) rs.get("error");
//				}
		}
		
		return response;
	}
	/**
	 * 根据主机ID查询监控项
	 * @param templateId
	 * @return
	 */
	@PostMapping(value="getItemByHost")
	public List<Item> getItemByHost(@RequestParam("hostArr") String[] hostArr,@RequestParam("auth")String auth){
		ItemGetRequest request=new ItemGetRequest(auth);
		
	//	System.out.println("hostid+++++++:"+hostArr[0]);
		request.getParams().setOutput("extend");
		request.getParams().setHostids(hostArr);
		List<Item> items = itemService.getItemToBean(request);
		if (items != null && items.size() > 0) {
			groupOrHost=0;//如果传来的是主机则为0，主机id查询出主机名字输出
			if (groupOrHost==0) {
				HostGetRequest hostRequest=new HostGetRequest(auth);
				hostRequest.getParams().setOutput("extend");
				hostRequest.getParams().setHostids(hostArr);
				List<Host> listH=hostService.getHostToBean(hostRequest, auth);
				for (Host host : listH) {
					listh.add(host.getHost());
				}
			}
		} 
		return items;
	}
	
	/**
	 * 根据主机组ID查询监控项
	 * @param hostGroup
	 * @return
	 * @throws ZabbixApiException 
	 */
	@PostMapping(value="/getItemByHostGroup")
	public List<Item> getItemByHostGroup(@RequestParam("hostGroup") String hostGroup,@RequestParam("auth")String auth) throws ZabbixApiException{
		String[] groupid={hostGroup};
		ItemGetRequest request=new ItemGetRequest(auth);
	//	System.out.println("hostgroup+++++++:"+groupid[0]);
		request.getParams().setOutput("extend");
		request.getParams().setGroupids(groupid);
		List<Item> items = itemService.getItemToBean(request);
		if (items != null && items.size() > 0) {
		} 
		groupOrHost=1;//如果传来的是主机组则为1，主机id查询出主机名字输出
		List<Integer> listid=new ArrayList<Integer>();
		listid.add(Integer.valueOf(hostGroup));
		com.zabbix4j.host.HostGetRequest hostreq =new com.zabbix4j.host.HostGetRequest();
		hostreq.getParams().setOutput("extend");
		hostreq.getParams().setGroupids(listid);
		com.zabbix4j.host.Host zabbixhost=new com.zabbix4j.host.Host(Util.url,auth);
		List<Result> rs=zabbixhost.get(hostreq).getResult();
		for (Result result : rs) {
			listg.add(result.getHost());
		}

		
		return items;
	}
	
	/**
	 * 生成触发器
	 * @param key 键值
	 * @param select 筛选范围 0时间，1次数
	 * @param input 时间或者次数
	 * @param type 汇聚方法 avg,max,min
	 * @param fuhao >=，<=,=,>,<,!=对应选择结构0,1,2,3,4,5
	 * @param number 判断条件
	 * @param alertLv 告警等级
	 * @param descript 标题描述
	 * @return
	 * @throws JSONException 
	 */
	@PostMapping(value="/createTrigger")
	public java.util.Map<String, String> createTrigger(@RequestParam("key") String key,
			@RequestParam("select") Integer select,@RequestParam("input") String input,@RequestParam("type") String type,@RequestParam("fuhao") Integer fuhao,
			@RequestParam("number") String number,@RequestParam("alertLv") String alertLv,@RequestParam("descript") String descript,@RequestParam("auth")String auth) throws JSONException{
//		System.out.println("key+"+key);
//		System.out.println("type+"+type);
//		System.out.println("fuhao+"+fuhao);
//		System.out.println("number+"+number);
//		System.out.println("alertLv+"+alertLv);
//		System.out.println("descript+"+descript);
		String string="";
//		System.out.println(groupOrHost);
		Object result=null;
		java.util.Map<String, String> map1=new HashMap<>();
		if(groupOrHost==1){//主机组级别的触发器：检索主机组对主机进行创建
			for (int i = 0; i < listg.size(); i++) {
				switch (fuhao) {
				case 0:
					if(select==0){
						string="{"+listg.get(i)+":"+key+"."+type+"("+input+")}>="+number+"";	
					}else if(select==1){
						string="{"+listg.get(i)+":"+key+".last("+input+")}>="+number+"";
					}
					
					break;
				case 1:
					if(select==0){
						string="{"+listg.get(i)+":"+key+"."+type+"("+input+")}<="+number+"";	
					}else if(select==1){
						string="{"+listg.get(i)+":"+key+".last("+input+")}<="+number+"";
					}

					break;
				case 2:
					if(select==0){
						string="{"+listg.get(i)+":"+key+"."+type+"("+input+")}="+number+"";	
					}else if(select==1){
						string="{"+listg.get(i)+":"+key+".last("+input+")}="+number+"";
					}

					break;
				case 3:
					if(select==0){
						string="{"+listg.get(i)+":"+key+"."+type+"("+input+")}>"+number+"";	
					}else if(select==1){
						string="{"+listg.get(i)+":"+key+".last("+input+")}>"+number+"";
					}

					break;
				case 4:
					if(select==0){
						string="{"+listg.get(i)+":"+key+"."+type+"("+input+")}<"+number+"";	
					}else if(select==1){
						string="{"+listg.get(i)+":"+key+".last("+input+")}<"+number+"";
					}

					break;
				case 5:
					if(select==0){
						string="{"+listg.get(i)+":"+key+"."+type+"("+input+")}!="+number+"";	
					}else if(select==1){
						string="{"+listg.get(i)+":"+key+".last("+input+")}!="+number+"";
					}

					break;
				default:
					break;
				}
				
		//		System.out.println(string);
				TriggerCreateRequest create = new TriggerCreateRequest(auth);
				create.getParams().setDescription(descript);
				create.getParams()
						.setExpression(string);
				create.getParams().setPriority(Integer.valueOf(alertLv));
				// Trigger t = new Trigger();
				// t.setTriggerid("977");
				// create.getParams().getDependencies().add(t);

				result=triggerService.create(create, auth);
				map1.put(listg.get(i), result.toString());
			}
			listg.clear();
		}else if(groupOrHost==0){//主机级别触发器，遍历主机进行创建
			for (int i = 0; i < listh.size(); i++) {
				switch (fuhao) {
				case 0:
					if(select==0){
						string="{"+listh.get(i)+":"+key+"."+type+"("+input+")}>="+number+"";
					}else if(select==1){
						string="{"+listh.get(i)+":"+key+".last("+input+")}>="+number+"";
					}
					break;
				case 1:
					if(select==0){
						string="{"+listh.get(i)+":"+key+"."+type+"("+input+")}<="+number+"";
					}else if(select==1){
						string="{"+listh.get(i)+":"+key+".last("+input+")}<="+number+"";
					}
					break;
				case 2:
					if(select==0){
						string="{"+listh.get(i)+":"+key+"."+type+"("+input+")}="+number+"";
					}else if(select==1){
						string="{"+listh.get(i)+":"+key+".last("+input+")}="+number+"";
					}
					break;
				case 3:
					if(select==0){
						string="{"+listh.get(i)+":"+key+"."+type+"("+input+")}>"+number+"";
					}else if(select==1){
						string="{"+listh.get(i)+":"+key+".last("+input+")}>"+number+"";
					}
					break;
				case 4:
					if(select==0){
						string="{"+listh.get(i)+":"+key+"."+type+"("+input+")}<"+number+"";
					}else if(select==1){
						string="{"+listh.get(i)+":"+key+".last("+input+")}<"+number+"";
					}
					break;
				case 5:
					if(select==0){
						string="{"+listh.get(i)+":"+key+"."+type+"("+input+")}!="+number+"";
					}else if(select==1){
						string="{"+listh.get(i)+":"+key+".last("+input+")}!="+number+"";
					}
					break;
				default:
					break;
				}
				
				//System.out.println(string);
				TriggerCreateRequest create = new TriggerCreateRequest(auth);
				create.getParams().setDescription(descript);
				create.getParams()
						.setExpression(string);
				create.getParams().setPriority(Integer.valueOf(alertLv));
				// Trigger t = new Trigger();
				// t.setTriggerid("977");
				// create.getParams().getDependencies().add(t);

				result=triggerService.create(create,auth);
				
				map1.put(listh.get(i), result.toString());
		}
		listh.clear();
	 }
		
			return map1;
	}
	
	/**
	 * 获取所有action模板
	 * @return
	 * @throws JSONException 
	 * @throws ZabbixApiException 
	 */
	@PostMapping(value="/getAction")
	public String getAction(@RequestParam("auth")String auth) throws JSONException, ZabbixApiException{
		ActionGetRequest request=new ActionGetRequest(auth);
		request.getParams().setOutput("extend");
		request.getParams().setSelectConditions("extend");
		request.getParams().setSelectOperations("extend");
		request.getParams().setSelectFilter("extend");
		
		
//		TestAction ta=new TestAction();
//		TestAction ta=new TestAction();
//		List<Action> action=ta.testGetActionBean();
		
		return actionService.get(request, auth).toString();
	}
	/**
	 * id修改触发器
	 * @param hostIds      主机id
	 * @param hostGroupIds 主机组id
	 * @param description  描述
	 * @param type         生成多个
	 * @param status	     状态
	 * @param priority     级别
	 * @param expression   表达式
	 * @param comments     注释
	 * @param triggerid    触发器id
	 * @return
	 */
	@PostMapping(value="/updateTriggerByIds")
	public List<String> updateTrigger(
			@RequestParam(value="hostIds",required=false)List<Integer> hostIds,
			@RequestParam(value="hostGroupIds",required=false)List<Integer> hostGroupIds,
							  @RequestParam("description")String description,@RequestParam(value="type",required=false)String type,@RequestParam(value="status",required=false)String status,
			                  @RequestParam("priority")Integer priority,@RequestParam("expression")String expression,
			                  @RequestParam(value="comments",required=false)String comments,@RequestParam("triggerId") String[] triggerid,@RequestParam("auth")String auth){
		ITriggerService triggerService=new TriggerServiceImpl();
		TriggerUpdateRequest update = new TriggerUpdateRequest(auth);
		List<String> rs=new ArrayList<>();
		for (int i = 0; i < triggerid.length; i++) {
			update.getParams().setDescription(description);
			if(type!=null&&type.equals("true")){
				update.getParams().setType(0);
			}else if(type.equals("false")){update.getParams().setType(1);}
			if(status.equals("true")){
				update.getParams().setStatus(0);
			}else if(status.equals("false")){update.getParams().setStatus(1);}
			update.getParams().setHosts(hostIds);
			update.getParams().setGroups(hostGroupIds);
			update.getParams().setPriority(priority);
			update.getParams().setExpression(expression);
			if(comments!=null)
			update.getParams().setComments(comments);
			update.getParams().setTriggerid(triggerid[i]);
			String str=triggerService.update(update,auth).toString();
			rs.add(str);
		//	System.out.println(str);
		}
		
		return rs;
}

	/**
	 * 获取触发器
	 * @param triggerIds
	 * @return
	 */
	@PostMapping(value="/getTriggerByTriggerId")
	public String getTriggerTriggerById(@RequestParam(value="triggerIds",required=false) List<String> triggerIds,@RequestParam("auth")String auth ){
		TriggerGetRequest get=new TriggerGetRequest(auth);
		String [] output={"description","status","priority","lastchange","expression","type","comments","value"};
		get.getParams().setTriggerids(triggerIds);
		get.getParams().setOutput(output);
		get.getParams().setExpandExpression(true);
		get.getParams().setExpandComment(true);
		get.getParams().setSelectItems("extend");
		get.getParams().setSelectHosts(new String[]{"name"});
		get.getParams().setSelectGroups(new String[]{"name"});
		get.getParams().setSelectItems("extend");
		
		return triggerService.getObj(get, auth).toString();
	}
	/**
	 * 创建action
	 * @param name输入名字
	 * @param esc_period默认操作步骤停留时间(必须大于60秒)
	 * @param def_shortdata//触发的标题
	 * @param def_longdata//触发的文本
	 * @param status//动作的状态（0：启用;1：禁用）
	 * @param usrgrpid//用户组的ID
	 * @param userid//用户的ID
	 * @param subject//恢复的标题
	 * @param message//恢复的正文
	 * @param groupIds//主机组id
	 * @param hostIds//主机id
	 * @param triggerId//触发器Id
	 * @return
	 * @throws JSONException 
	 */
	@PostMapping(value="/createAction")
	public String createAction(@RequestParam("name") String name,
			@RequestParam(value="groupIds",required=false) String[] groupIds,@RequestParam(value="hostIds",required=false) String[] hostIds,
			@RequestParam(value="triggerId",required=false) String[] triggerId,
			@RequestParam("def_shortdata") String def_shortdata,@RequestParam("def_longdata") String def_longdata,@RequestParam("status") Integer status,
			@RequestParam("subject") String subject,@RequestParam("message") String message,@RequestParam("auth")String auth,
			@RequestParam("action")String action) throws JSONException{
//		System.out.println("+++++++++++++++"+name);
//		System.out.println("+++++++++++++++"+esc_period);
//		System.out.println("+++++++++++++++"+def_shortdata);
//		System.out.println("+++++++++++++++"+subject);
//		System.out.println("+++++++++++++++"+message);
//		System.out.println(status);
//		System.out.println(usrgrpid[0]);
//		System.out.println(userid[0]);
		ActionCreateRequest create = new ActionCreateRequest(auth);
		create.getParams().setName(name);//输入名字
//		create.getParams().setEsc_period(esc_period*60);//默认操作步骤停留时间(必须大于60秒)
		//create.getParams().setEvaltype(0);//required 计算方式
		create.getParams().setEventsource(0);//required 发生事件的类型（event source）0 - event created by a trigger;默认选择此项
		create.getParams().setDef_shortdata(def_shortdata);//触发的标题
		create.getParams().setDef_longdata(def_longdata);//触发的文本
		create.getParams().setStatus(status);//动作的状态（0：启用;1：禁用）
		JSONArray array=new JSONArray(action);
		for (int i = 0; i < array.length(); i++) {
			JSONObject jsonObj=(JSONObject) array.get(i);
			Integer time=jsonObj.getInt("time");
			JSONArray userid=jsonObj.getJSONArray("user");
			JSONArray usrgrpid=jsonObj.getJSONArray("usGroup");
			JSONArray userArr=jsonObj.getJSONArray("user");
			ActionOperation ao = new ActionOperation();
			ao.setOperationtype(0);//操作类型（0 - send message;1 - remote command;2 - add host;3 - remove host;4 - add to host group;5 - remove from host group;6 - link to template;7 - unlink from template;8 - enable host;9 - disable host.）
			ao.setEsc_period(time*60);//步骤持续时间 	Step duration
			ao.setEsc_step_from(i+1);//开始步骤
			ao.setEsc_step_to(i+1);//结束步骤
			
		if(usrgrpid!=null){
				for (int l = 0; l < usrgrpid.length(); l++) {
					ActionOperationSyn aos = new ActionOperationSyn();
					aos.setUsrgrpid(String.valueOf(usrgrpid.get(l)));//用户组的ID
					
					ao.getOpmessage_grp().add(aos);//以设备组运行远程命令操作
				}
			
		}
		if (userid!=null) {
			for (int j = 0; j < userid.length(); j++) {
			ActionOperationSyn aos1 = new ActionOperationSyn();
			aos1.setUserid(String.valueOf(userid.get(j)));//用户ID
		
			ao.getOpmessage_usr().add(aos1);//用户
			}
		}
		
		
		create.getParams().getOperations().add(ao);
		}
		

		List<ActionCondition> ac= new ArrayList<>();
		ActionCondition act1=new ActionCondition();
		act1.setConditiontype(5);
		act1.setOperator(0);
		act1.setValue("1");
		ac.add(act1);
		if(groupIds!=null){
			for (int i = 0; i < groupIds.length; i++) {
				ActionCondition act=new ActionCondition();
				act.setConditiontype(0);
				act.setOperator(0);
				act.setValue(groupIds[i]);
				ac.add(act);
			}
		}
		if(hostIds!=null){
			for (int i = 0; i < hostIds.length; i++) {
				ActionCondition act=new ActionCondition();
				act.setConditiontype(1);
				act.setOperator(0);
				act.setValue(hostIds[i]);
				ac.add(act);
			}
		}
		
		ActionFilter af=new ActionFilter();
		af.setConditions(ac);
		af.setEvaltype(0);
		create.getParams().setFilter(af);
		
		
		ActionOperationMessage aom = new ActionOperationMessage();
		aom.setDefault_msg(1);//是否使用默认动作消息文本和主题（0为使用，1为不使用）
		aom.setMediatypeid("4");//发送信息的媒体类型
		aom.setSubject(subject);//恢复标题
		aom.setMessage(message);//恢复正文
//		ao.setEvaltype(0);//操作算法类型
//		ao.setOpmessage(aom);//发送消息操作

//		ActionCondition ac = new ActionCondition();
//		ac.setConditiontype(3);//触发条件类型
//		ac.setOperator(0);//条件操作符
//		ac.setValue("200");//值
//		create.getParams().getConditions().add(ac);
		
		
		
			return actionService.create(create, auth).toString();
	}
	/**
	 * 根据ID返回Action
	 * @param id前端返回的ID
	 * @return
	 * @throws ZabbixApiException 
	 * @throws JSONException 
	 */
	@PostMapping(value="/getActionById")
	public List<Action> getActionById(@RequestParam("id") String id,@RequestParam("auth")String auth) throws ZabbixApiException, JSONException, UnsupportedEncodingException{
		ActionGetRequest get = new ActionGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setSelectConditions("extend");
		get.getParams().setSelectOperations("extend");
		get.getParams().getActionids().add(id);
		List<Action> actionlist =null;
		
		try {
			actionlist = actionService.getActionBean(get, auth);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return actionlist;
	}
	
	//更新
	/**
	 * 修改action
	 * @param name输入名字
	 * @param esc_period默认操作步骤停留时间(必须大于60秒)
	 * @param def_shortdata//触发的标题
	 * @param def_longdata//触发的文本
	 * @param status//动作的状态（0：启用;1：禁用）
	 * @param usrgrpid//用户组的ID
	 * @param userid//用户的ID
	 * @param subject//恢复的标题
	 * @param message//恢复的正文
	 * @return
	 * @throws JSONException 
	 */
	@PostMapping(value="/updateAction")
	public String updateAction (@RequestParam("id") String id,@RequestParam("name") String name,
			@RequestParam(value="groupIds",required=false) String[] groupIds,@RequestParam(value="hostIds",required=false) String[] hostIds,
			@RequestParam(value="triggerId",required=false) String[] triggerId,
			@RequestParam("def_shortdata") String def_shortdata,@RequestParam("def_longdata") String def_longdata,@RequestParam("status") Integer status,
			@RequestParam("subject") String subject,@RequestParam("message") String message,@RequestParam("auth")String auth,
			@RequestParam("action")String action) throws JSONException{
		ActionUpdateRequest update = new ActionUpdateRequest(auth);
		update.getParams().setActionid(id);

		update.getParams().setName(name);
//		update.getParams().setEsc_period(esc_period*60);
//		update.getParams().setEvaltype(0);
		//update.getParams().setEventsource(0);
		update.getParams().setDef_shortdata(def_shortdata);
		update.getParams().setDef_longdata(def_longdata);
		update.getParams().setStatus(0);
		JSONArray array=new JSONArray(action);
		for (int i = 0; i < array.length(); i++) {
			JSONObject jsonObj=(JSONObject) array.get(i);
			Integer time=jsonObj.getInt("time");
			JSONArray userid=jsonObj.getJSONArray("user");
			JSONArray usrgrpid=jsonObj.getJSONArray("usGroup");
			System.out.println(time);
			JSONArray userArr=jsonObj.getJSONArray("user");
			ActionOperation ao = new ActionOperation();
			ao.setOperationtype(0);//操作类型（0 - send message;1 - remote command;2 - add host;3 - remove host;4 - add to host group;5 - remove from host group;6 - link to template;7 - unlink from template;8 - enable host;9 - disable host.）
			ao.setEsc_period(time*60);//步骤持续时间 	Step duration
			ao.setEsc_step_from(i+1);//开始步骤
			ao.setEsc_step_to(i+1);//结束步骤
			
		if(usrgrpid!=null){
				for (int l = 0; l < usrgrpid.length(); l++) {
					ActionOperationSyn aos = new ActionOperationSyn();
					aos.setUsrgrpid(String.valueOf(usrgrpid.get(l)));//用户组的ID
					System.out.println(String.valueOf(usrgrpid.get(l)));
					ao.getOpmessage_grp().add(aos);//以设备组运行远程命令操作
				}
			
		}
		if (userid!=null) {
			for (int j = 0; j < userid.length(); j++) {
			ActionOperationSyn aos1 = new ActionOperationSyn();
			aos1.setUserid(String.valueOf(userid.get(j)));//用户ID
			System.out.println(String.valueOf(userid.get(j)));
			ao.getOpmessage_usr().add(aos1);//用户
			}
		}
		
		
		update.getParams().getOperations().add(ao);
		}
		
		
		
		List<ActionCondition> ac= new ArrayList<>();
		ActionCondition act1=new ActionCondition();
		act1.setConditiontype(5);
		act1.setOperator(0);
		act1.setValue("1");
		ac.add(act1);
		if(groupIds!=null){
			for (int i = 0; i < groupIds.length; i++) {
				ActionCondition act=new ActionCondition();
				act.setConditiontype(0);
				act.setOperator(0);
				act.setValue(groupIds[i]);
				ac.add(act);
			}
		}
		if(hostIds!=null){
			for (int i = 0; i < hostIds.length; i++) {
				ActionCondition act=new ActionCondition();
				act.setConditiontype(1);
				act.setOperator(0);
				act.setValue(hostIds[i]);
				ac.add(act);
			}
		}
		/*if (triggerId!=null) {
			for (int i = 0; i < triggerId.length; i++) {
				ActionCondition act=new ActionCondition();
				act.setConditiontype(2);
				act.setOperator(0);
				act.setValue(triggerId[i]);
				ac.add(act);
			}
			
		}*/
	//	System.out.println(ac.size());
		ActionFilter af=new ActionFilter();
		af.setConditions(ac);
		af.setEvaltype(0);
		update.getParams().setFilter(af);
	
		
		ActionOperationMessage aom = new ActionOperationMessage();
		aom.setDefault_msg(1);//是否使用默认动作消息文本和主题（0为使用，1为不使用）
		aom.setMediatypeid("4");//发送信息的媒体类型
		aom.setSubject(subject);//恢复标题
		aom.setMessage(message);//恢复正文
//		ao.setOpmessage(aom);//发送消息操作
		
//		update.getParams().getOperations().add(ao);

//		ActionCondition ac = new ActionCondition();
//		ac.setConditiontype(3);//触发条件类型
//		ac.setOperator(0);//条件操作符
//		ac.setValue("200");//值
//		create.getParams().getConditions().add(ac);
		
		
		
			return actionService.update(update, auth).toString();
	}
	
	/**
	 * 删除动作
	 * @param ids
	 * @return
	 * @throws ZabbixApiException
	 */
	@PostMapping(value="/deleteActionById")
	public List<Integer> deleteActionById(@RequestParam("ids") List<Integer> ids,@RequestParam("auth") String auth) throws ZabbixApiException{
		ActionDeleteRequest delete=new ActionDeleteRequest();
			delete.getParams().addAll(ids);
			com.zabbix4j.action.Action ac=new com.zabbix4j.action.Action(Util.url,auth);
			
		
		
		return ac.delete(delete).getResult().getActionids();
	}
	@PostMapping(value="/getObj")
	public Object getObj(String auth) throws JSONException{
		TestAction ta=new TestAction();
		return ta.testGet(auth);
	}
	
}
