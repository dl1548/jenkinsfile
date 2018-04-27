package com.zabbix.api.service.impl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.zabbix.api.domain.action.ActionCreateRequest;
import com.zabbix.api.domain.action.ActionCreateRequest.ActionCreateParams;
import com.zabbix.api.domain.action.ActionDeleteRequest;
import com.zabbix.api.domain.action.ActionExistsRequest;
import com.zabbix.api.domain.action.ActionGetRequest;
import com.zabbix.api.domain.action.ActionUpdateRequest;
import com.zabbix.api.domain.action.ActionUpdateRequest.ActionUpdateParams;
import com.zabbix.api.domain.base.Action;
import com.zabbix.api.domain.base.ActionCondition;
import com.zabbix.api.domain.base.ActionOperation;
import com.zabbix.api.domain.base.ActionOperationCommand;
import com.zabbix.api.domain.base.ActionOperationCondition;
import com.zabbix.api.domain.base.ActionOperationMessage;
import com.zabbix.api.domain.base.ActionOperationSyn;
import com.zabbix.api.service.IActionService;
import com.zabbix.util.FormatData;
/**
 * @ClassName: ActionServiceImpl
 * @Description: 
 * @author 
 * @date 
 * @version 
 */
public  class ActionServiceImpl implements IActionService {
	
	public Object create(ActionCreateRequest create,String auth){
		
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println("请求："+js.toJson(create));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(create)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			
			 rs = new JSONObject(response);
			
//			if (rs.has("result")) {
//				result = rs.get("result");
//			}
//			 else if (rs.has("error")) {
//				result = rs.get("error");
//			}
		}
		 catch (HttpException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		 catch (JSONException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
//	@Override
//	public boolean addAction(Action action,String auth){
//		boolean result = false;
//		ActionCreateRequest create = new ActionCreateRequest();
//		ActionCreateParams params = create.getParams();
//		if(action!=null){
//			List<ActionOperation> actionOperations = action.getOperations();
//			if(actionOperations!=null&&actionOperations.size()>0){
//				String actionName = action.getName();
//				Integer evaltype = action.getEvaltype();
//				Integer eventSourse = action.getEventsource();
//				Integer escperiod = action.getEsc_period();
//				String dshortdata = action.getDef_shortdata();
//				String dlongdata = action.getDef_longdata() ;
//				Integer status = action.getStatus();
//				String rshortdata = action.getR_shortdata();
//				String rlongdata = action.getR_longdata();
//				Integer remasg = action.getRecovery_msg();
//				if(actionName!=null&&!"".equals(actionName)&&evaltype!=null&&eventSourse!=null&&escperiod!=null){
//					params.setName(actionName);
//					params.setEsc_period(escperiod);
//					params.setEvaltype(evaltype);
//					params.setEventsource(eventSourse);
//					params.setDef_shortdata(dshortdata);
//					params.setDef_longdata(dlongdata);
//					params.setStatus(status);
//					params.setR_shortdata(rshortdata);
//					params.setR_longdata(rlongdata);
//					params.setRecovery_msg(remasg);
//					params.setOperations(actionOperations);
//					params.setConditions(action.getConditions());
//					for(int i=0;i<actionOperations.size();i++){
//						ActionOperation actionOperation =actionOperations.get(i);
//						actionOperation.getEsc_period();
//						actionOperation.getEsc_step_from();
//						actionOperation.getEsc_step_to();
//						actionOperation.getEvaltype();
//						params.seto
//						ActionOperationCommand actionOperationCommand= actionOperation.getOpcommand();
//						List<ActionOperationSyn> operationSyns = actionOperation.getOpcommand_grp();
//					}
//					ActionOperation ao = new ActionOperation();
//					ao.setOperationtype(0);
//					ao.setEsc_period(0);
//					ao.setEsc_step_from(1);
//					ao.setEsc_step_to(2);
//					ao.setEvaltype(0);
//					ActionOperationSyn aos = new ActionOperationSyn();
//					aos.setUsrgrpid("17");
//					ao.getOpmessage_grp().add(aos);
//					ActionOperationMessage aom = new ActionOperationMessage();
//					aom.setDefault_msg(1);
//					aom.setMediatypeid("1");
//					ao.setOpmessage(aom);
//					params.getOperations().add(ao);
//					ActionCondition ac = new ActionCondition();
//					ac.setConditiontype(3);
//					ac.setOperator(0);
//					ac.setValue("200");
//					params.getConditions().add(ac);
//					JSONObject rs = (JSONObject) create(create);
//					if (rs.has("result")) {
//						result = true;
//					}
//					 else if (rs.has("error")) {
//						result = false;
//					}
//				}
//			}
//		}
//		return result;
//	}
	
	public Object delete(ActionDeleteRequest delete,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			//System.out.println(js.toJson(delete));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(delete)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			//System.out.println(response);
			 rs = new JSONObject(response);
			//System.out.println(rs);
//			if (rs.has("result")) {
//				result = rs.get("result");
//			}
//			 else if (rs.has("error")) {
//				result = rs.get("error");
//			}
		}catch (HttpException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		 catch (JSONException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public Object exists(ActionExistsRequest exists,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			//System.out.println(js.toJson(exists));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(exists)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			//System.out.println(response);
			 rs = new JSONObject(response);
			//System.out.println(rs);
			if (rs.has("result")) {
				result = rs.get("result");
			}
			 else if (rs.has("error")) {
				result = rs.get("error");
			}
		}
		 catch (HttpException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		 catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * Title: get
	 * Description: 动作获取（json）
	 * @param get
	 * @return
	 * @see com.zabbix.api.service.IActionService#get(com.zabbix.api.domain.action.ActionGetRequest)
	 */
	public Object get(ActionGetRequest get,String auth){
		Gson js = new Gson();
		Object result = null;
		String response=null;
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			//System.out.println(js.toJson(get));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(get)));
			client.executeMethod(putMethod);
			 response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
				System.out.println(rs);
				if (rs.has("result")) {
					result = rs.get("result");
				}
				 else if (rs.has("error")) {
					result = rs.get("error");
				}		
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("+++++++++++++++++++++"+result.toString());
		return result;
	}
	
	/*
	 * Title: getActionBean
	 * Description: 获取动作（bean）
	 * @param get
	 * @return
	 * @see com.zabbix.api.service.IActionService#getActionBean(com.zabbix.api.domain.action.ActionGetRequest)
	 */
	@Override
public List<Action> getActionBean(ActionGetRequest get,String auth) throws HttpException, IOException, JSONException {
		List<Action> actions = null;
		Gson js = new Gson();
		Object result = null;
		String response=null;
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		//System.out.println(js.toJson(get));
		putMethod.setRequestBody( FormatData.fromString(js.toJson(get)));
		client.executeMethod(putMethod);
		 response = putMethod.getResponseBodyAsString();
		 rs = new JSONObject(response);
			//System.out.println(rs);
			if (rs.has("result")) {
				result = rs.get("result");
			}
			 else if (rs.has("error")) {
				result = rs.get("error");
			}
			//System.out.println("++++++++++++++++++++++++++++"+result);
		if (rs.has("result")){
			try {
				JSONArray array = rs.getJSONArray("result");
				if(array!=null&&array.length()>0){
					actions = new ArrayList<Action>();
					for(int i = 0;i<array.length();i++){
						Action action = new Action();
						JSONObject object = array.getJSONObject(i);
						action.setActionid(object.getString("actionid"));
						action.setDef_longdata(object.getString("def_longdata"));
						action.setDef_shortdata(object.getString("def_longdata"));
						action.setEsc_period(object.getInt("esc_period"));
						if(rs.has("evaltype")){
//							action.setEvaltype(object.getInt("evaltype"));
						}
						
						action.setEventsource(object.getInt("eventsource"));
						action.setName(object.getString("name"));
						action.setR_longdata(object.getString("r_longdata"));
						action.setR_shortdata(object.getString("r_shortdata"));
						action.setRecovery_msg(object.getInt("recovery_msg"));
						action.setStatus(object.getInt("status"));
				/*******************************operation封装********************************************/
						JSONArray operations = object.getJSONArray("operations");
					//	System.out.println(operations);
						if(operations!=null){
						//	System.out.println("11111111111111111111111111");
							List<ActionOperation> operationlist = new ArrayList<ActionOperation>();
							//Iterator opkeys = operations.keys(); 
							//while(opkeys.hasNext()){
							for (int j = 0; j < operations.length(); j++) {
								ActionOperation actionOperation = new ActionOperation();
								//String opkey = (String) opkeys.next();
								JSONObject opobj = operations.getJSONObject(j);
							
								
								if(opobj!=null){
									actionOperation.setOperationid(opobj.getString("operationid"));
									actionOperation.setOperationtype(opobj.getInt("operationtype"));
									actionOperation.setActionid(opobj.getString("actionid"));
									actionOperation.setEsc_period(opobj.getInt("esc_period"));
									actionOperation.setEsc_step_from(opobj.getInt("esc_step_from"));
									actionOperation.setEsc_step_to(opobj.getInt("esc_step_to"));
//									actionOperation.setEvaltype(opobj.getInt("evaltype"));
								//	System.out.println("Operationid==="+actionOperation.getOperationid());
//									actionOperation.setOpcommand(opcommand);
									if(opobj.has("opcommand")){
										JSONObject opcommand = opobj.getJSONObject("opcommand");//opcommand
										if(opcommand!=null){
											ActionOperationCommand command = new ActionOperationCommand();
											command.setAuthtype(opcommand.getInt("authtype"));
											command.setCommand(opcommand.getString("command"));
											command.setExecute_on(opcommand.getInt("execute_on"));
											command.setOperationid(opcommand.getString("operationid"));
											command.setPassword(opcommand.getString("password"));
											command.setPort(opcommand.getString("port"));
											command.setPrivatekey(opcommand.getString("privatekey"));
											command.setPublickey(opcommand.getString("publickey"));
											command.setScriptid(opcommand.getString("scriptid"));
											command.setType(opcommand.getInt("type"));
											command.setUsername(opcommand.getString("username"));
											actionOperation.setOpcommand(command);
										}
									}
									if(opobj.has("opcommand_grp")){
										JSONArray opcommandgrp = opobj.getJSONArray("opcommand_grp");
										if(opcommandgrp!=null&&opcommandgrp.length()>0){
											List<ActionOperationSyn> opcommgrps = new ArrayList<ActionOperationSyn>();
											for(int k=0;k<opcommandgrp.length();k++){
												ActionOperationSyn mycommgrp = new ActionOperationSyn();
												JSONObject commgrpjson = opcommandgrp.getJSONObject(j);
												mycommgrp.setOpcommand_grpid(commgrpjson.getString("opcommand_grpid"));
												mycommgrp.setOperationid(commgrpjson.getString("operationid"));
												mycommgrp.setGroupid(commgrpjson.getString("groupid"));
												opcommgrps.add(mycommgrp);
											}
											actionOperation.setOpcommand_grp(opcommgrps);
										}
									}
									if(opobj.has("opcommand_hst")){
										JSONArray opmesagehst = opobj.getJSONArray("opcommand_hst");
										if(opmesagehst!=null&&opmesagehst.length()>0){
											List<ActionOperationSyn> opcommhst = new ArrayList<ActionOperationSyn>();
											for(int k=0;k<opmesagehst.length();k++){
												ActionOperationSyn mycommghst = new ActionOperationSyn();
												JSONObject commhstjson = opmesagehst.getJSONObject(k);
												mycommghst.setOpcommand_hstid(commhstjson.getString("opcommand_hstid"));
												mycommghst.setOperationid(commhstjson.getString("operationid"));
												mycommghst.setHostid(commhstjson.getString("hostid"));
												opcommhst.add(mycommghst);
											}
											actionOperation.setOpcommand_hst(opcommhst);
										}
									}
									if(opobj.has("opgroup")){
										JSONArray opgroup = opobj.getJSONArray("opgroup");
										if(opgroup!=null&&opgroup.length()>0){
											List<ActionOperationSyn> opgrouplist = new ArrayList<ActionOperationSyn>();
											for(int m=0;m<opgroup.length();m++){
												JSONObject opgroupjson = opgroup.getJSONObject(m);
												ActionOperationSyn myopgroup = new ActionOperationSyn();
												myopgroup.setOperationid(opgroupjson.getString("operationid"));
												myopgroup.setGroupid(opgroupjson.getString("groupid"));
												opgrouplist.add(myopgroup);
											}
											actionOperation.setOpgroup(opgrouplist);
										}
									}
									if(opobj.has("opmessage")){
										JSONObject opmessage = opobj.getJSONObject("opmessage");
										if(opmessage!=null){
											ActionOperationMessage message = new ActionOperationMessage();
											message.setDefault_msg(opmessage.getInt("default_msg"));
											message.setMediatypeid(opmessage.getString("mediatypeid"));
											message.setMessage(opmessage.getString("message"));
											message.setOperationid(opmessage.getString("operationid"));
											message.setSubject(opmessage.getString("subject"));
											actionOperation.setOpmessage(message);
										}
									}
									if(opobj.has("opmessage_grp")){
										JSONArray opmessagegrp = opobj.getJSONArray("opmessage_grp");
										if(opmessagegrp!=null&&opmessagegrp.length()>0){
											List<ActionOperationSyn> opmsggrp = new ArrayList<ActionOperationSyn>();
											for(int n=0;n<opmessagegrp.length();n++){
												JSONObject messagegrp = opmessagegrp.getJSONObject(n);
												ActionOperationSyn msggrp = new ActionOperationSyn();
												msggrp.setOperationid(messagegrp.getString("operationid"));
												msggrp.setUsrgrpid(messagegrp.getString("usrgrpid"));
												opmsggrp.add(msggrp);
											}
											actionOperation.setOpmessage_grp(opmsggrp);
										}
									}
									if(opobj.has("opmessage_usr")){
										JSONArray opmsgusr = opobj.getJSONArray("opmessage_usr");
										if(opmsgusr!=null&&opmsgusr.length()>0){
											List<ActionOperationSyn> opmsgusrs = new ArrayList<ActionOperationSyn>();
											for(int y=0;y<opmsgusr.length();y++){
												JSONObject opmsgusrjson = opmsgusr.getJSONObject(y);
												ActionOperationSyn opmessageusr = new ActionOperationSyn();
												opmessageusr.setOperationid(opmsgusrjson.getString("operationid"));
												if(opmsgusrjson.has("userid")){
													opmessageusr.setUserid(opmsgusrjson.getString("userid"));
												}
												
												opmsgusrs.add(opmessageusr);
											}
											actionOperation.setOpmessage_usr(opmsgusrs);
										}
									}
									if(opobj.has("optemplate")){
										JSONArray optemplate = opobj.getJSONArray("optemplate");
										if(optemplate!=null&&optemplate.length()>0){
											List<ActionOperationSyn> optemplatelist = new ArrayList<ActionOperationSyn>();
											for(int l=0;l<optemplate.length();l++){
												JSONObject optemplatejson = optemplate.getJSONObject(l);
												ActionOperationSyn optemp = new ActionOperationSyn();
												optemp.setOperationid(optemplatejson.getString("operationid"));
												optemp.setTemplateid(optemplatejson.getString("templateid"));
												optemplatelist.add(optemp);
											}
											actionOperation.setOptemplate(optemplatelist);
										}
									}
									if(opobj.has("opconditions")){
										JSONArray opconditions = opobj.getJSONArray("opconditions");//jetrbrains
										if(opconditions!=null&&opconditions.length()>0){
											List<ActionOperationCondition> opconditionslist = new ArrayList<ActionOperationCondition>(); 
											for(int l=0;l<opconditions.length();l++){
												JSONObject conditionjson = opconditions.getJSONObject(l);
												ActionOperationCondition operationCondition = new ActionOperationCondition();
												operationCondition.setConditiontype(conditionjson.getInt("conditiontype"));
												operationCondition.setOpconditionid(conditionjson.getString("opconditionid"));
												operationCondition.setOperationid(conditionjson.getString("operationid"));
												operationCondition.setOperator(conditionjson.getInt("operator"));
												operationCondition.setValue(conditionjson.getString("value"));
												opconditionslist.add(operationCondition);
											}
											actionOperation.setOpconditions(opconditionslist);
										}
									}
								}
							operationlist.add(actionOperation);
							}
//							action.setOperations(operationlist);
						}
						/*******************************************conditions 封装***************************************************/
						if(rs.has("conditions")){
						//	System.out.println("2222222222222222222222");
						JSONObject conditions = object.getJSONObject("conditions");
						if(conditions!=null){
							List<ActionCondition> conditionlist =new ArrayList<ActionCondition>();
							Iterator opkeys = conditions.keys(); 
							while(opkeys.hasNext()){
								ActionCondition actionCondition = new ActionCondition();
								String opkey = (String) opkeys.next();
								JSONObject conditionjson = conditions.getJSONObject(opkey);
								actionCondition.setActionid(conditionjson.getString("actionid"));
								actionCondition.setConditionid(conditionjson.getString("conditionid"));
								actionCondition.setConditiontype(conditionjson.getInt("conditiontype"));
								actionCondition.setOperator(conditionjson.getInt("operator"));
								actionCondition.setValue(conditionjson.getString("value"));
								conditionlist.add(actionCondition);
							}
//							action.setConditions(conditionlist);
						}
						
					}
						actions.add(action);
				}
			}} catch (JSONException e) {
				e.printStackTrace();
				return actions; 
			}
			return actions;
		}
		 else if (rs.has("error")) {
			return actions;
		}
		return actions;
	}
	public Object update(ActionUpdateRequest update,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		String response=null;
		try {
			//System.out.println(js.toJson(update));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(update)));
			client.executeMethod(putMethod);
			response = putMethod.getResponseBodyAsString();
			//System.out.println(response);
		}
		 catch (HttpException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}



//	@Override
//	public boolean updateAction(Action action) {
//		boolean result = false;
//		ActionUpdateRequest update = new ActionUpdateRequest();
//		ActionUpdateParams params = update.getParams();
//		if(action!=null){
////			List<ActionOperation> actionOperations = action.getOperations();
//			String actionid = action.getActionid();
//			if(actionid!=null&&!"".equals(actionid)){
//				if(actionOperations!=null&&actionOperations.size()>0){
//					String actionName = action.getName();
//					Integer evaltype = action.getEvaltype();
//					Integer eventSourse = action.getEventsource();
//					Integer escperiod = action.getEsc_period();
//					String dshortdata = action.getDef_shortdata();
//					String dlongdata = action.getDef_longdata() ;
//					Integer status = action.getStatus();
//					String rshortdata = action.getR_shortdata();
//					String rlongdata = action.getR_longdata();
//					Integer remasg = action.getRecovery_msg();
//					if(actionName!=null&&!"".equals(actionName)&&evaltype!=null&&eventSourse!=null&&escperiod!=null){
//						params.setActionid(actionid);
//						params.setName(actionName);
//						params.setEsc_period(escperiod);
//						params.setEvaltype(evaltype);
//						params.setEventsource(eventSourse);
//						params.setDef_shortdata(dshortdata);
//						params.setDef_longdata(dlongdata);
//						params.setStatus(status);
//						params.setR_shortdata(rshortdata);
//						params.setR_longdata(rlongdata);
//						params.setRecovery_msg(remasg);
//						params.setOperations(actionOperations);
//						params.setConditions(action.getConditions());
//						JSONObject rs = (JSONObject) update(update);
//						if (rs.has("result")) {
//							result = true;
//						}
//						 else if (rs.has("error")) {
//							result = false;
//						}
//					}
//				}
//			}
//		}
//		return result;
//	}

}
