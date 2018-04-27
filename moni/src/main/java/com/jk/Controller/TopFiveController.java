package com.jk.Controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jk.domain.KeyValue;
import com.jk.repository.KeyvalueRepository;
import com.zabbix.api.domain.history.HistoryGetRequest;
import com.zabbix.api.domain.host.HostGetRequest;
import com.zabbix.api.domain.item.ItemGetRequest;
import com.zabbix.api.domain.item.ItemGetRequest.ItemGetParams.ItemSearch;
import com.zabbix.api.service.IHistoryService;
import com.zabbix.api.service.IHostService;
import com.zabbix.api.service.IItemService;
import com.zabbix.api.service.impl.HistoryServiceImpl;
import com.zabbix.api.service.impl.HostServiceImpl;
import com.zabbix.api.service.impl.ItemServiceImpl;

@Component
@RestController
public class TopFiveController {
	//private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);
	@Autowired
    private KeyvalueRepository pository;
	
	private static KeyvalueRepository keyvaluerepository;
    @PostConstruct
	public void init(){
		TopFiveController.keyvaluerepository=pository;
		List<KeyValue> keyvalues=keyvaluerepository.findAll();
		for(KeyValue kv:keyvalues){
			keyvaluemap.put(kv.getKey(),kv.getValue());
		}
	}
	static Map<String,String> keyvaluemap=new HashMap<>();
	private static IHostService hostService = new HostServiceImpl();
	private static IItemService itemService = new ItemServiceImpl();
	private static IHistoryService historyService =new HistoryServiceImpl();
	static String[] str2=null;
	static String item=null;
	static String itemid=null;
	/**
	 * 根据主机组获取监控
	 * @param hostid
	 * @return
	 */
	@PostMapping(value="/topFive")
	public Map<String, String> getApp(@RequestParam("hostGroup") String hostGroup,@RequestParam("auth")String auth){
		Map<String,String> finalstringmap=new HashMap<>();
		List<String> keyAllUse=new ArrayList<>();
		List<String> list2=new ArrayList<>();
		HostGetRequest get=new HostGetRequest(auth);
		get.getParams().setOutput("hosts");
		get.getParams().setGroupids(new String[]{hostGroup});
		get.getParams().setSelectItems("extend");
		JSONObject jsonObj=(JSONObject) hostService.get(get);
		try {
			if(jsonObj.getJSONArray("result")!=null){
			JSONArray jsonArr=(JSONArray) jsonObj.get("result");
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject hostItem=jsonArr.getJSONObject(i);
				JSONArray itemsArr=hostItem.getJSONArray("items");
				if (itemsArr.length()!=0) {
					for (int j = 0; j < itemsArr.length(); j++) {
						JSONObject itemObj=itemsArr.getJSONObject(j);
						if(itemObj.has("key_")){
							if(Integer.valueOf((String)itemObj.get("value_type"))==0||Integer.valueOf((String)itemObj.get("value_type"))==3){
							list2.add((String) itemObj.get("key_"));
							}
						}
						
					}
				}
				
				}
			}
			Set<String> uniqueSet =new HashSet<String>(list2);
			for (Object temp : uniqueSet) {
				
				if(Collections.frequency(list2, temp)>=2){
					keyAllUse.add((String) temp);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		for(String key:keyAllUse){
			String start=key;
			String end="";
			if(key.indexOf("[")!=-1)start=key.substring(0,key.indexOf("["));
			if(key.indexOf("[")!=-1)end=key.substring(key.indexOf("["), key.indexOf("]"))+"]";
			if(keyvaluemap.containsKey(start)){
				String finalstring=keyvaluemap.get(start)+end;
				finalstringmap.put(key, finalstring);
			}else{
				finalstringmap.put(key, key);
			}
		}
	    System.out.println("翻译后"+finalstringmap.size()+"个监控项");
		return finalstringmap;
	}
	/**
	 * 获取监控项历史
	 */
	@PostMapping(value="/getValue")
	public String getHistoryByKey(@RequestParam("key") String key,@RequestParam("hostGroup") String hostGroup,
								  @RequestParam("time") String time,@RequestParam(value="sort",required=false) Integer sort,@RequestParam("auth")String auth){
		long stamp=System.currentTimeMillis();
		JSONObject jsonlast=new JSONObject();
		
		JSONArray itemArrlast=new JSONArray();
		Long timefrom=null;
		switch (time) {
		case "0.5":
			timefrom=stamp/1000-1800;
			break;
		case "1":
			timefrom=stamp/1000-3600;
			break;
		case "12":
			timefrom=stamp/1000-43200;
			break;
		case "24":
			timefrom=stamp/1000-86400;
			break;
		case "now":
			break;
		default:
			break;
		}
		ItemGetRequest get=new ItemGetRequest(auth);
		ItemSearch search=new ItemSearch();
		
		JSONArray item=new JSONArray();
		JSONObject json=new JSONObject();
		search.setKey_(key);
		get.getParams().setGroupids(new String[]{hostGroup});
		get.getParams().setSearch(search);
		JSONObject jsonObj=(JSONObject) itemService.get(get);
		if(jsonObj.has("result")){
			try {
				JSONArray jsonArr=jsonObj.getJSONArray("result");
				if(jsonArr.length()!=0){
					for (int i = 0; i < jsonArr.length(); i++) {
						
						JSONObject itemObj=new JSONObject();
						JSONObject hostItem=(JSONObject) jsonArr.get(i);
						if(Integer.valueOf(hostItem.getString("state"))==0){
//							map.put(hostItem.getString("hostid"), hostItem.getString("itemid"));
							itemObj.put("hostid",hostItem.getString("hostid"));
							itemObj.put("itemid",hostItem.getString("itemid"));
							itemObj.put("history",hostItem.getString("value_type"));
							itemObj.put("units",hostItem.getString("units"));
							item.put(itemObj);
						}

					}
				}
				json.put("item", item);
				JSONArray itemArr=json.getJSONArray("item");
				for (int i = 0; i < itemArr.length(); i++) {
					JSONObject itemObjLast=new JSONObject();
					JSONObject itemInfo=itemArr.getJSONObject(i);
					 List<String> hostids=new ArrayList<>();
					 List<String> itemids=new ArrayList<>();
					 List<String> sortfield=new ArrayList<>();
					 List<String> sortorder=new ArrayList<>();
					 sortorder.add("DESC");
					 sortfield.add("clock");
					   	hostids.add(itemInfo.getString("hostid"));
					   	itemids.add(itemInfo.getString("itemid"));
					   	HistoryGetRequest hget=new HistoryGetRequest(auth);
						hget.getParams().setOutput("extend");
						hget.getParams().setHostids(hostids);
						if(time.equals("now")){
							hget.getParams().setSortfield(sortfield);
							hget.getParams().setSortorder(sortorder);
							hget.getParams().setLimit(1);
						}else{
							hget.getParams().setTime_from(timefrom);
						}
						hget.getParams().setHistory(itemInfo.getInt("history"));
						hget.getParams().setItemids(itemids);
						JSONObject result=(JSONObject) historyService.get(hget, auth);
						
						if(result.has("result")){
							JSONArray resultArr=result.getJSONArray("result");
							if(resultArr.length()!=0){
								JSONArray rs=result.getJSONArray("result");
								List<String> list=new ArrayList<>();
								for (int j = 0; j < rs.length(); j++) {
									list.add(rs.getJSONObject(j).getString("value"));
								}
								switch (sort) {
								case 0:

									String max=Collections.max(list);//max
									System.out.println(max);
									if(itemInfo.get("units").equals("bps")){
										BigDecimal   b   =   new   BigDecimal(max);  
										double   maxd   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
										itemObjLast.put("lastvalue", maxd/1000);
										itemObjLast.put("units","kbps");
										itemObjLast.put("hostid", itemInfo.getString("hostid"));
									}else if(itemInfo.get("units").equals("B")){
										itemObjLast.put("lastvalue", Long.valueOf(max)/1073741824L);
										itemObjLast.put("units","GB");
										itemObjLast.put("hostid", itemInfo.getString("hostid"));
									}else{
										
											itemObjLast.put("lastvalue", max);
											itemObjLast.put("units",itemInfo.getString("units"));
											itemObjLast.put("hostid", itemInfo.getString("hostid"));
										
										
									}
									break;
								case 1:
									String min=Collections.min(list);//min
									if(itemInfo.get("units").equals("bps")){
										BigDecimal   b   =   new   BigDecimal(min);  
										double   mind   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
										itemObjLast.put("lastvalue", mind/1000);
										itemObjLast.put("units","kbps");
										itemObjLast.put("hostid", itemInfo.getString("hostid"));
									}else if(itemInfo.get("units").equals("B")){
										itemObjLast.put("lastvalue", Long.valueOf(min)/1073741824L);
										itemObjLast.put("units","GB");
										itemObjLast.put("hostid", itemInfo.getString("hostid"));
									}else{
										itemObjLast.put("lastvalue", min);
										itemObjLast.put("units",itemInfo.getString("units"));
										itemObjLast.put("hostid", itemInfo.getString("hostid"));
									}
									break;
								case 2:
									Double  num = 0.0;
									for (int j = 0; j < list.size(); j++) {
										Double a=Double.valueOf(list.get(j));
										num=a+num;
										
									}
									double avg1=num/list.size();//min
									
									if(itemInfo.get("units").equals("bps")){
										BigDecimal   b   =   BigDecimal.valueOf(avg1/1000L);  
										double   avg   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
										itemObjLast.put("lastvalue", avg);
										itemObjLast.put("units","kbps");
										itemObjLast.put("hostid", itemInfo.getString("hostid"));
									}else if(itemInfo.get("units").equals("B")){
										BigDecimal   b   =   BigDecimal.valueOf(avg1/1073741824L);  
										double   avg   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
										itemObjLast.put("lastvalue", avg);
										itemObjLast.put("units","GB");
										itemObjLast.put("hostid", itemInfo.getString("hostid"));
									}else{
										BigDecimal   b=BigDecimal.valueOf(avg1);
										double   avg   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
										itemObjLast.put("lastvalue", avg);
										itemObjLast.put("units",itemInfo.getString("units"));
										itemObjLast.put("hostid", itemInfo.getString("hostid"));
									}
									break;
								case 3:
									for (int j = 0; j < list.size(); j++) {
										if(itemInfo.get("units").equals("bps")){
											BigDecimal   b   =   BigDecimal.valueOf(Long.parseLong(list.get(i))/1000L);  
											double   avg   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
											itemObjLast.put("lastvalue", avg);
											itemObjLast.put("units","kbps");
											itemObjLast.put("hostid", itemInfo.getString("hostid"));
										}else if(itemInfo.get("units").equals("B")){
											BigDecimal   b   =   BigDecimal.valueOf(Long.parseLong(list.get(i))/1073741824L);  
											double   avg   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
											itemObjLast.put("lastvalue", avg);
											itemObjLast.put("units","GB");
											itemObjLast.put("hostid", itemInfo.getString("hostid"));
										}else{
											BigDecimal   b=BigDecimal.valueOf(Long.parseLong(list.get(i)));
											double   avg   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
											itemObjLast.put("lastvalue", avg);
											itemObjLast.put("units",itemInfo.getString("units"));
											itemObjLast.put("hostid", itemInfo.getString("hostid"));
										}
									}
									
									break;
								default:
									break;
								}
							}
							
						}
						itemArrlast.put(itemObjLast);
				}
				jsonlast.put("result", itemArrlast);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return jsonlast.toString();
	}
}
