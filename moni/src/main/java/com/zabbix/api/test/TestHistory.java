package com.zabbix.api.test;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.zabbix.api.domain.base.IntegerHistory;
import com.zabbix.api.domain.history.HistoryGetRequest;
import com.zabbix.api.service.IHistoryService;
import com.zabbix.api.service.impl.HistoryServiceImpl;
import com.zabbix.util.FormatData;
import com.zabbix4j.ZabbixApiException;
import com.zabbix4j.history.History;
import com.zabbix4j.history.HistoryGetResponse;
import com.zabbix4j.history.HistoryObject;
public class TestHistory extends TestCase {
	private static  IHistoryService historyService = new HistoryServiceImpl();
	 static{
 		//登录 
 		new Util().login();
 	}

	public void testGet(String auth){
		//数据准备 
		long stamp=System.currentTimeMillis();
		Long timefrom=null;
		timefrom=stamp/1000-1800;
		HistoryGetRequest get = new HistoryGetRequest(auth);
		get.getParams().setOutput("extend");//refer
		List<String> ss =new  ArrayList<String>();
		ss.add("27884");
		get.getParams().setItemids(ss);
		get.getParams().setHistory(90);
		get.getParams().setTime_from((long)1520659306);
		get.getParams().setTime_till((long)1521525700);
		Object object = historyService.get(get, auth);
		System.out.println(object);
	}

//	public void testGetHistory(){
//		//数据准备 
//		String dd="10154";
//		Date startdate = new Date();
//		HistoryGetRequest get = new HistoryGetRequest();
//		get.getParams().setOutput("extend");//refer
//		get.getParams().setHistory(1);
//		List<String> ss =new  ArrayList<String>();
//		ss.add("25832");//23305,37814
//		get.getParams().setItemids(ss);
////		get.getParams().setTime_from(1399973481l);
////		get.getParams().setTime_till(1399973481l);
////		List<String> sort = new ArrayList<String>();
////		sort.add("DESC");
////		get.getParams().setSortorder(sort);
////		get.getParams().setLimit(10);
//		get.getParams().getHostids().add(dd);
//		List<IntegerHistory> historys = historyService.getHistoryToBean(get,0);
//		Collections.sort(historys);
//		Date enddate = new Date();
////		System.out.println((enddate.getTime()-startdate.getTime())/1000);
//		System.out.println(historys.size());
//		
//	}
//	public static void main(String[] args) throws ZabbixApiException {
//	TestHistory th=new TestHistory();
//	th.testGet();
////		List<String> itemids=new ArrayList<>();
////		itemids.add("27887");
////		HistoryGetRequest get=new HistoryGetRequest();
////		get.getParams().setOutput("extend");
////		get.getParams().setItemids(itemids);
//////		get.getParams().setHistory(7);
////		try {
////			historyService.getHistoryToBean(get);
////		} catch (UnsupportedEncodingException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		
//		
//	}
	
}