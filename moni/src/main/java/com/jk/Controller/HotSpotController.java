package com.jk.Controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zabbix.api.domain.base.IntegerHistory;
import com.zabbix.api.domain.history.HistoryGetRequest;
import com.zabbix.api.service.IHistoryService;
import com.zabbix.api.service.impl.HistoryServiceImpl;
import com.zabbix.api.test.Util;
import com.zabbix4j.ZabbixApiException;
import com.zabbix4j.item.Item;
import com.zabbix4j.item.ItemGetRequest;
import com.zabbix4j.item.ItemGetResponse;
import com.zabbix4j.item.ItemGetResponse.Result;

@RestController
public class HotSpotController {
	private static IHistoryService historyService = new HistoryServiceImpl();
	
	 public static String stampToDate(String s){
	        String res;
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
	        Long lt=Long.valueOf(s);
	        Date date = new Date(lt*1000L);
	        res = simpleDateFormat.format(date);
	        return res;
	    }
	//private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);
	/**
	 * cpu性能峰值
	 * @param host主机名
	 * @param select 周期(day week month)
	 * @param timeStart 起始
	 * @param timeEnd 结束
	 * @param cpu 键值数组
	 * @return
	 * @throws ZabbixApiException
	 */
	@PostMapping(value="/getCPUItemByHost")
	public String getCPUItemByHost(@RequestParam(value="host") List<String> list,@RequestParam("select") String select,
			@RequestParam("timeStart") String timeStart,
			@RequestParam("timeEnd") String timeEnd,@RequestParam("auth")String auth,
			@RequestParam("cpu") String[] cpu) throws ZabbixApiException{
		Integer itemId=null;
		Integer history=null;
		Integer hostId=null;
		Long timeStartLong=null;
		Long timeEndLong=null;
		SimpleDateFormat sdfh = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
//			timeStartLong=sdf.parse(timeStart).getTime();//日期转时间戳
//			timeEndLong=sdf.parse(timeEnd).getTime();
			
			timeStartLong=sdfh.parse(stampToDate(String.valueOf(timeStart))).getTime()/1000;//日期转时间戳
			timeEndLong=sdfh.parse(stampToDate(String.valueOf(timeEnd))).getTime()/1000;//日期转时间戳

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSONObject obj=new JSONObject();
		for (int i = 0; i < list.size(); i++) {
			ItemGetRequest itemGet=new ItemGetRequest();
			itemGet.getParams().setOutput("extend");
			itemGet.getParams().setHost(list.get(i));
			Item item=new Item(Util.url,auth);
			ItemGetResponse resp=item.get(itemGet);
			List<Result> result=resp.getResult();
			for (int j = 0; j < result.size(); j++) {
				hostId=result.get(j).getHostid();
			for (int l = 0; l < cpu.length; l++) {
				if(result.get(j).getKey_().equals(cpu[l])){
					itemId=result.get(j).getItemid();
					history=result.get(j).getHistory();
					getByTime(itemId,history,hostId, select, timeStartLong, timeEndLong, auth);
				}
				
			}
			}
			try {
				obj.put(String.valueOf(hostId),getByTime(itemId, history, hostId, select, timeStartLong, timeEndLong, auth));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			}
		
		return obj.toString();
	}
	
	/**
	 * men性能峰值
	 * @param host主机名
	 * @param select 周期(day week month)
	 * @param timeStart 起始
	 * @param timeEnd 结束
	 * @param men 键值数组
	 * @return
	 * @throws ZabbixApiException
	 */
	@PostMapping(value="/getMenItemByHost")
	public String getMenItemByHost(@RequestParam(value="host") List<String> list,@RequestParam("select") String select,
			@RequestParam("timeStart") String timeStart,
			@RequestParam("timeEnd") String timeEnd,@RequestParam("auth")String auth,
			@RequestParam("men") String[] men) throws ZabbixApiException{
		Integer itemId=null;
		Integer history=null;
		Integer hostId=null;
		Long timeStartLong=null;
		Long timeEndLong=null;
		SimpleDateFormat sdfh = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			timeStartLong=sdfh.parse(stampToDate(String.valueOf(timeStart))).getTime()/1000;//日期转时间戳
			timeEndLong=sdfh.parse(stampToDate(String.valueOf(timeEnd))).getTime()/1000;//日期转时间戳

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSONObject obj=new JSONObject();
		for (int i = 0; i < list.size(); i++) {
			ItemGetRequest itemGet=new ItemGetRequest();
			itemGet.getParams().setOutput("extend");
			itemGet.getParams().setHost(list.get(i));
			Item item=new Item(Util.url,auth);
			ItemGetResponse resp=item.get(itemGet);
			List<Result> result=resp.getResult();
			for (int j = 0; j < result.size(); j++) {
				hostId=result.get(j).getHostid();
				for (int l = 0; l < men.length; l++) {
					if(result.get(j).getKey_().equals(men[l])){
						itemId=result.get(j).getItemid();
						history=result.get(j).getHistory();
						getByTime(itemId,history,hostId, select, timeStartLong, timeEndLong, auth);
					}
				}
				

			}
			try {
				obj.put(String.valueOf(hostId),getByTime(itemId, history, hostId, select, timeStartLong, timeEndLong, auth));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			}
		
		return obj.toString();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map.Entry[] getSortedHashtableByValue(Map map) {
		Set set = map.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);
		Arrays.sort(entries, new Comparator() {
		public int compare(Object arg0, Object arg1) {
		Double key1 = Double.valueOf((String) ((Map.Entry) arg0).getValue());
		Double key2 = Double.valueOf((String) ((Map.Entry) arg1).getValue());
		return key1.compareTo(key2);
		}
		});
		return entries;
		}
	public static List<IntegerHistory> getHistory(Integer itemId, Integer history, Integer hostId,Long timeStart,Long time_till,@RequestParam("auth")String auth) throws JSONException{
		 List<IntegerHistory> result = null;
		HistoryGetRequest get = new HistoryGetRequest(auth);
		get.getParams().setOutput("extend");//refer
		List<String> ss =new  ArrayList<String>();
		if(itemId!=null){
			ss.add(itemId.toString());
			get.getParams().setHistory(history);
			get.getParams().setItemids(ss);
			get.getParams().setTime_from(timeStart);
			get.getParams().setTime_till(time_till);
			try {
				result = historyService.getHistoryToBean(get, auth);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 根据时间和周期数据处理获取jsonobj
	 * @param select 类型
	 * @param timeStart 开始
	 * @param timeEnd 结束
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public static JSONArray getByTime(Integer itemId, Integer history, Integer hostId,String select,Long timeStart,Long timeEnd,@RequestParam("auth")String auth){
		JSONObject avg=new JSONObject();
		JSONObject max=new JSONObject();
		JSONObject min=new JSONObject();
		JSONArray host=new JSONArray();
		JSONObject cpujson=new JSONObject();
		Long time_till=(long)0;
		List<IntegerHistory> result=new ArrayList<>();
		Map.Entry[] entries=null;
		Integer j=0;
		try {
		switch (select) {
		case "month":
			while(timeEnd>=time_till){
			
			//执行...
			time_till=timeStart+86400;
			result=getHistory(itemId,history,hostId, timeStart, time_till, auth);
			Map<String,String> map=new HashMap<>(); 
			Double count=0.0;
			if(result!=null){
				for (IntegerHistory rs : result) {
				
					map.put(rs.getClock().toString(), rs.getValue());
				}
				entries= getSortedHashtableByValue(map);
				for (int i = 0; i < entries.length; i++) {
					count=Double.valueOf((entries[i].getValue().toString()))+count;
				}
			Double ofavg=count/entries.length;
			BigDecimal   b   =   new   BigDecimal(ofavg);
			Double   oavg   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
			avg.put(String.valueOf((timeStart)), oavg);
			max.put(String.valueOf((timeStart)), entries[(entries.length)-1].getValue());
			//min.put(String.valueOf(FormatData.stampToDate((String) entries[(entries.length)-1].getKey())), entries[0].getValue());

			}else{
				avg.put(String.valueOf(String.valueOf((timeStart))), 0);
				max.put(String.valueOf((timeStart)), 0);
				//min.put(String.valueOf(FormatData.stampToDate(String.valueOf((timeStart+(time_till-timeStart))))), 0);
				
			}
				
				host.put(0,avg);
				host.put(1,max);
				//host.put(2,max);
				cpujson.put(String.valueOf(hostId), host);
			timeStart=time_till;
			}
			
			break;
			
		case "week":
			while(timeEnd>=time_till){
			
			//执行...
			time_till=timeStart+3600;
			result=getHistory(itemId,history,hostId, timeStart, time_till, auth);
			Map<String,String> map=new HashMap<>(); 
			Double count=0.0;
			if(result!=null){
				for (IntegerHistory rs : result) {
					
					map.put(rs.getClock().toString(), rs.getValue());
				}
				entries= getSortedHashtableByValue(map);
				for (int i = 0; i < entries.length; i++) {
					count=Double.valueOf((entries[i].getValue().toString()))+count;
				}
				Double ofavg=count/entries.length;
				BigDecimal   b   =   new   BigDecimal(ofavg);
				Double   oavg   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
				avg.put(String.valueOf((timeStart)), oavg);
				max.put(String.valueOf((timeStart)), entries[(entries.length)-1].getValue());
				//min.put(String.valueOf(FormatData.stampToDate((String) entries[(entries.length)-1].getKey())), entries[0].getValue());

				}else{
					avg.put(String.valueOf(String.valueOf((timeStart))), 0);
					max.put(String.valueOf((timeStart)), 0);
					//min.put(String.valueOf(FormatData.stampToDate(String.valueOf((timeStart+(time_till-timeStart))))), 0);
				
			}
				
				host.put(0,avg);
				host.put(1,max);
				//host.put(2,min);
				cpujson.put(String.valueOf(hostId), host);
			timeStart=time_till;
			}
			
			break;
		case "day":
			while(timeEnd>=time_till){
			
			//执行...
			time_till=timeStart+3600;
			result=getHistory(itemId,history,hostId, timeStart, time_till, auth);
			Map<String,String> map=new HashMap<>(); 
			Double count=0.0;
			if(result!=null){
				for (IntegerHistory rs : result) {
					
					map.put(rs.getClock().toString(), rs.getValue());
				}
				entries= getSortedHashtableByValue(map);
				for (int i = 0; i < entries.length; i++) {
					count=Double.valueOf((entries[i].getValue().toString()))+count;
				}
				Double ofavg=count/entries.length;
				BigDecimal   b   =   new   BigDecimal(ofavg);
				Double   oavg   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
				avg.put(String.valueOf((timeStart)), oavg);
				max.put(String.valueOf((timeStart)), entries[(entries.length)-1].getValue());
				//min.put(String.valueOf(FormatData.stampToDate((String) entries[(entries.length)-1].getKey())), entries[0].getValue());

				}else{
					avg.put(String.valueOf(String.valueOf((timeStart))), 0);
					max.put(String.valueOf((timeStart)), 0);
					//min.put(String.valueOf(FormatData.stampToDate(String.valueOf((timeStart+(time_till-timeStart))))), 0);
				
			}
//				System.out.println("2="+max);
				host.put(0,avg);
				host.put(1,max);
//				host.put(2,max);
//				cpujson.put(String.valueOf(hostId), host);
			timeStart=time_till;

			}
			break;
		default:
			break;
		}
		
//		cpujson.put("cpu", jsonArr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return host;
	}
	
}
