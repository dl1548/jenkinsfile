package com.jk.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.converters.ArrayConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jk.domain.HostProcess;
import com.jk.domain.Result;
import com.jk.repository.ProcessRepository;
import com.jk.service.HostProcessService;
import com.zabbix.api.domain.application.ApplicationGetRequest;
import com.zabbix.api.domain.host.HostGetRequest;
import com.zabbix.api.service.IApplicationService;
import com.zabbix.api.service.IHostService;
import com.zabbix.api.service.impl.ApplicationServiceImpl;
import com.zabbix.api.service.impl.HostServiceImpl;
import com.zabbix.api.test.Util;
import com.zabbix4j.ZabbixApiException;
import com.zabbix4j.item.Item;
import com.zabbix4j.item.ItemGetRequest;
import com.zabbix4j.item.ItemGetResponse;

@RestController
public class BusinessViewController {
	
	@Autowired
	ProcessRepository processrepository;
	private static IHostService hostService=new HostServiceImpl();
	private static HostProcessService service=new HostProcessService();
	private static IApplicationService appservice=new ApplicationServiceImpl();
	/**
	 * 业务详情
	 * @param name
	 * @param level
	 * @return
	 * @throws JSONException
	 */
	@PostMapping(value="/showTriggerView")
	public String showTriggerView(@RequestParam("name")String name,@RequestParam("auth")String auth) throws JSONException{
		List<HostProcess> list=processrepository.findByBusinessname(name);
		JSONObject json=service.getTrigger(list,name, auth);
		return json.toString();
	}
	/**
	 * 保存视图
	 * @param process
	 * @return
	 * @throws JSONException
	 */
	@PostMapping(value="/saveBusview")
	public Result<String> saveBusview(@RequestParam("process") String process,@RequestParam("auth")String auth) throws JSONException{
		JSONArray array=new JSONArray(process);
		Result<String> result=new Result<>();
		try {
			for (int s = 0; s < array.length(); s++) {
				JSONObject object = array.getJSONObject(s);
				HostProcess host1 = null;
				String id = object.getString("id");
				if (!"".equals(id))
					host1 = processrepository.findOne(Integer.valueOf(id));
				HostProcess host2 = service.objToProcess(object, host1, s);
				if (host2 != null) {
					String[] hostids = host2.getHostid().split(",");
					List<Integer> list = new ArrayList<>();
					for (String hostid : hostids) {
						if(!hostid.equals(""))
						list.add(Integer.valueOf(hostid));
					}
					ItemGetRequest get = new ItemGetRequest();
					get.getParams().setOutput("extend");
					get.getParams().setHostids(list);
					Item app = new Item(Util.url, auth);
					ItemGetResponse response = app.get(get);
					List<com.zabbix4j.item.ItemGetResponse.Result> results=response.getResult();
					String itemids="";
					JSONArray itemkeys=new JSONArray(host2.getItemkey());
					for(com.zabbix4j.item.ItemGetResponse.Result result2:results){
						for(int i=0;i<itemkeys.length();i++){
							if(itemkeys.get(i).equals(result2.getKey_()))itemids+=result2.getItemid()+",";
						}
					}
					if(itemids.length()>1){
						itemids.substring(0, itemids.length()-1);
					}
					host2.setItemid(itemids);
					host2.setItemkey(itemkeys.toString());
					processrepository.save(host2);
				}
			}
			result.setCode(200);
			result.setData("保存成功");
		} catch (Exception e) {
			result.setCode(500);
			result.setData("保存失败");
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取全部视图
	 * @param name
	 * @param level
	 * @return
	 */
	@PostMapping(value="/getBus")
	public List<Map<String,Object>> getBusByName(@RequestParam(value="name",required=false)String name,@RequestParam(value="level",required=false)Integer level,@RequestParam("auth")String auth){
		List<Map<String,Object>> arry=new ArrayList<>();
		if(null==name){
			Set<String> set = new HashSet<>();
			List<HostProcess> all=processrepository.findAll();
			Iterator<HostProcess> iterator=all.iterator();
			while(iterator.hasNext()){
				HostProcess hp=iterator.next();
				set.add(hp.getBusinessname());
			}
			Iterator<String> iter=set.iterator();
			while(iter.hasNext()){
				Map<String,Object> map=new HashMap<>();
				String busname=iter.next();
				List<HostProcess> list=processrepository.findByBusinessname(busname);
				Integer[] count=service.getFaultNum(list, level, auth);
				map.put("process", list);
				map.put("priority", count[0]);
				map.put("hostcount", count[1]);
				map.put("maxFault", count[2]);
				arry.add(map);
			}
		}else{
			Map<String,Object> map=new HashMap<>();
			List<HostProcess> list=processrepository.findByBusinessname(name);
			Integer[] count=service.getFaultNum(list, level, auth);
			map.put("process", list);
			map.put("priority", count[0]);
			map.put("hostcount", count[1]);
			arry.add(map);
		}
		
		return arry;
	} 
	/**
	 * 删除视图
	 * @param name
	 * @return 
	 */
	@PostMapping(value="/deleteView")
	public Result<String> deleteView(@RequestParam("name")String name,@RequestParam("auth")String auth){
		Result<String> result=new Result<>();
		List<HostProcess> list=processrepository.findByBusinessname(name);
		Iterator<HostProcess> iterator=list.iterator();
		result.setCode(200);
		result.setData("删除成功");
		result.setMsg("删除成功");
		HostProcess hp;
		String hpname="";
		while(iterator.hasNext()){
			hp=iterator.next();
			try {
				processrepository.delete(hp.getId());
			} catch (Exception e) {
				hpname+=hp.getProcessname()+",";
				result.setCode(500);
				result.setData("删除失败");
			}
		}
		if(result.getCode()==500)result.setMsg("删除失败的主机名："+hpname);
		return result;
	}
	/**
	 * 主机获取应用集
	 * @param hostId
	 * @return
	 */
	@PostMapping(value="/getApplicationByHost")
	public String getApplicationByHost(@RequestParam("hostId") List<String> hostIds,@RequestParam("auth")String auth){
		ApplicationGetRequest get=new ApplicationGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setHostids(hostIds);
		return appservice.get(get, auth).toString();
	}
	public static void main(String[] args,String auth) {
		HostGetRequest get=new HostGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setSelectApplications("extend");
		
		hostService.get(get).toString();
	}
	/**
	 * 应用集获取监控项
	 * @param applicationId
	 * @return
	 * @throws ZabbixApiException
	 */
	@PostMapping(value="/getItemByApp")
	public Map<String, String> getItemByApp(@RequestParam("applicationId") Integer applicationId,@RequestParam("auth") String auth,@RequestParam("hostid") List<Integer> hostids) throws ZabbixApiException{
		List<Integer> applicationids=new ArrayList<>();
		applicationids.add(applicationId);
		ItemGetRequest get=new ItemGetRequest();
		get.getParams().setOutput("extend");
		get.getParams().setApplicationids(applicationids);
		get.getParams().setHostids(hostids);
		Item app=new Item(Util.url,auth);
	    ItemGetResponse response=app.get(get);
	    Set<String> set=new HashSet<>();
	    for(com.zabbix4j.item.ItemGetResponse.Result res:response.getResult()){
	    	if(res.getType()==0 || res.getType()==3){
	    		set.add(res.getKey_());
	    	}
	    }
	    Map<String,String> finalstringmap=new HashMap<>();
	    for(String key:set){
	    	String start=key;
			String end="";
			if(key.indexOf("[")!=-1)start=key.substring(0,key.indexOf("["));
			if(key.indexOf("[")!=-1)end=key.substring(key.indexOf("["), key.indexOf("]"))+"]";
			if(TopFiveController.keyvaluemap.containsKey(start)){
				String finalstring=TopFiveController.keyvaluemap.get(start)+end;
				finalstringmap.put(key, finalstring);
			}else{
				finalstringmap.put(key, key);
			}
	    }
		return finalstringmap;
	}
}
