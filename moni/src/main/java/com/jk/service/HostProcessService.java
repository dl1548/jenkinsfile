package com.jk.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jk.domain.HostProcess;
import com.zabbix.api.domain.trigger.TriggerGetRequest;
import com.zabbix.api.service.ITriggerService;
import com.zabbix.api.service.impl.TriggerServiceImpl;
import com.zabbix.api.domain.base.Trigger;
import com.zabbix.util.FormatData;

public class HostProcessService {
	public HostProcess objToProcess(JSONObject object,HostProcess host1,Integer position) throws JSONException{
		String processname=object.getString("processname");
		String hostgroupid=object.getString("hostgroupid");
		String hostgroupname=object.getString("hostgroupname");
		String hostid=object.getString("hostid"); 
		String hostname=object.getString("hostname");
		String applicationid=object.getString("applicationid");
		String applicationname=object.getString("applicationname");
		JSONArray itemkey=object.getJSONArray("itemid");
		String itemname=object.getString("itemname");
		String businessname=object.getString("businessname");
		if(null!=host1){
			host1.setProcessname(processname);
			host1.setHostgroupid(hostgroupid);
			host1.setHostgroupname(hostgroupname);
			host1.setHostid(hostid);
			host1.setHostname(hostname);
			host1.setApplicationid(applicationid);
			host1.setApplicationname(applicationname);
			host1.setItemkey(itemkey.toString());
			host1.setItemname(itemname);
			host1.setPosition(position);
			host1.setBusinessname(businessname);
		}else{
			HostProcess host=new HostProcess();
			host.setProcessname(processname);
			host.setHostgroupid(hostgroupid);
			host.setHostgroupname(hostgroupname);
			host.setHostid(hostid);
			host.setHostname(hostname);
			host.setApplicationid(applicationid);
			host.setApplicationname(applicationname);
			host.setItemkey(itemkey.toString());
			host.setItemname(itemname);
			host.setPosition(position);
			host.setBusinessname(businessname);
			return host;
		}
		return host1;
	}
	public Integer[] getFaultNum(List<HostProcess> list,Integer level,String auth){
		int flag=0;
		Set<String> set = new HashSet<>();  
		int maxFault=-1;
		if(list!=null){
		for(HostProcess process:list){
			ITriggerService ITriggerService=new TriggerServiceImpl();
			String hostid=process.getHostid();
			String[] hostids =hostid.split(",");
			if(!hostid.equals(""))
			CollectionUtils.addAll(set, hostids);
			String applicationid=process.getApplicationid();
			String itemid=process.getItemid();
			
			TriggerGetRequest request=new TriggerGetRequest(auth);
			request.getParams().setHostids(Arrays.asList(hostids));
			request.getParams().setApplicationids((Arrays.asList(applicationid.split(","))));
			request.getParams().setItemids((Arrays.asList(itemid.split(","))));
			request.getParams().setOnly_true(true);
			List<Trigger> tlist=ITriggerService.get(request,auth);
			for(Trigger trigger:tlist){
				if(null==level){
					if(maxFault<trigger.getPriority())maxFault=trigger.getPriority();
					flag++;
				}else if(level==trigger.getPriority()){
					if(maxFault<trigger.getPriority())maxFault=trigger.getPriority();
					flag++; 
				}
			}
		}
		}
//		System.out.println(set);
//		System.out.println(set.size());
		return new Integer[]{flag,set.size(),maxFault};
	}
	public JSONObject  getTrigger(List<HostProcess> list,String name,String auth) throws JSONException{
		long crrentTime=System.currentTimeMillis()/1000;
		JSONObject json=new JSONObject();
		JSONArray arr1=new JSONArray();
		if(list!=null){
			Iterator<HostProcess> iterator=list.iterator();
		    while(iterator.hasNext()){
		    	JSONArray arr=new JSONArray();
		    	JSONObject obj=new JSONObject();
		    	int Worst=0;
				int bad=0;
				int other=0;
			ITriggerService ITriggerService=new TriggerServiceImpl();
			HostProcess process=iterator.next();
			String hostid=process.getHostid();
			String[] hostids =hostid.split(",");
			String applicationid=process.getApplicationid();
			String[] applicationids =applicationid.split(",");
			String itemid=process.getItemid();
			String[] itemids =itemid.split(",");
			
			TriggerGetRequest request=new TriggerGetRequest(auth);
			System.out.println(Arrays.asList(hostids));
			request.getParams().setHostids(Arrays.asList(hostids));
			request.getParams().setApplicationids((Arrays.asList(applicationids)));
			request.getParams().setItemids((Arrays.asList(itemids)));
			request.getParams().setOnly_true(true);
			List<Trigger> tlist=ITriggerService.get(request, auth);
			Iterator<Trigger> titerator=tlist.iterator();
			while(titerator.hasNext()){
				Trigger trigger=titerator.next();
				Integer Priority=trigger.getPriority();
				if(Priority==5){
					Worst++;
				}else if(Priority==4){
					bad++;
				}else{
					other++;
				}
				JSONObject object=new JSONObject();
				Integer priority=trigger.getPriority();
				String description=trigger.getDescription();
				String lastchange=trigger.getLastchange();
				String lastchanged=FormatData.stampToDate(lastchange);
				String timezone=FormatData.formatDateTime(crrentTime-Long.valueOf(lastchange));
				List<Integer> groups=trigger.getGroups();
				List<Integer> hosts=trigger.getHosts();
				object.put("priority", priority);
				object.put("description", description);
				object.put("lastchanged", lastchanged);
				object.put("timezone", timezone);
				object.put("groups", groups);
				object.put("groupname", process.getHostgroupname());
				object.put("hosts", hosts);
				object.put("hostname", process.getHostname());
				arr.put(object);
				}
			obj.put("xiangqing", arr);
			obj.put("hostgroupname",process.getHostgroupname());
			obj.put("hostname",process.getHostname());
			obj.put("applicationname",process.getApplicationname());
			obj.put("itemname",process.getItemname());
			obj.put("Worst", Worst);
			obj.put("bad", bad);
			obj.put("other", other);
			obj.put("processname", process.getProcessname());
			arr1.put(obj);
	}
		    }
		json.put(name, arr1);
		return json;
		}
	/* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
