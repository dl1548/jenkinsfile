package com.jk.service.impl;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.domain.HostL;
import com.jk.repository.HostRepository;
import com.jk.service.HostThread;
import com.zabbix.api.domain.base.Item;
import com.zabbix.api.domain.host.HostGetRequest;
import com.zabbix.api.domain.item.ItemGetRequest;
import com.zabbix.api.service.IHostService;
import com.zabbix.api.service.IItemService;
import com.zabbix.api.service.impl.HostServiceImpl;
import com.zabbix.api.service.impl.ItemServiceImpl;
import com.zabbix.util.FormatData;

@Service
public class HostThreadImpl implements HostThread {

	private static IHostService hostService = new HostServiceImpl();
	private static IItemService itemService = new ItemServiceImpl();
	
	@Autowired
	HostRepository hostrepository;
	
	@Override
	public void run(){
			HostGetRequest get=new HostGetRequest(FormatData.auth);
			get.getParams().setOutput("extend");
			get.getParams().setSelectInterfaces("extend");
			get.getParams().setSelectParentTemplates("extend");
			JSONObject response=(JSONObject)hostService.get(get);
			if(response.has("result")){
			try {
			JSONArray array=response.getJSONArray("result");
			for(int i=0;i<array.length();i++){
				HostL hostl=new HostL();
				JSONObject object=array.getJSONObject(i);
				String hostid=object.getString("hostid");
				String host=object.getString("host");
				String available=object.getString("available");
				String status=object.getString("status");
				String name=object.getString("name");
				hostl.setId(Integer.valueOf(hostid));
				hostl.setName(name);
				hostl.setAvailable(available);
				hostl.setSTATUS(Integer.valueOf(status));
				
				JSONArray arr=object.getJSONArray("parentTemplates");
				
				JSONArray rearray=new JSONArray();
				JSONObject treobject=new JSONObject();
				for(int j=0;j<arr.length();j++){
					JSONObject obj = arr.getJSONObject(j);
					
					JSONObject reobj=new JSONObject();
					String templateid=obj.getString("templateid");
					String templatename=obj.getString("name");
					reobj.put("templateid", templateid);
					reobj.put("name", templatename);
					rearray.put(reobj);
				}
				treobject.put("parentTemplates", rearray);
				System.out.println(treobject.toString());
				hostl.setParentTemplates(treobject.toString());
				
				JSONArray interfaces=object.getJSONArray("interfaces");
				JSONArray reinterfaces=new JSONArray();
				JSONObject ireobject=new JSONObject();
				for(int t=0;t<interfaces.length();t++){
					JSONObject inter=interfaces.getJSONObject(t);
					JSONObject reinter=new JSONObject();
					String ip=inter.getString("ip");
					reinter.put("ip", ip);
					reinterfaces.put(reinter);
				}
				ireobject.put("interfaces", reinterfaces);
				hostl.setIp(ireobject.toString());
				
				ItemGetRequest request=new ItemGetRequest(FormatData.auth);
				request.getParams().setOutput("extend");
				request.getParams().setHost(host);
				List<Item> items = itemService.getItemToBean(request);
				if(null!=items){
					Iterator<Item> iter = items.iterator();
					Map<String,String> map=new HashMap<>();
				    while(iter.hasNext()){
						Item item=iter.next();
						map.put(item.getKey_(),item.getLastvalue());
				    }
				    boolean flag=map.containsKey("system.uname");
				    NumberFormat num = NumberFormat.getPercentInstance();
				    String type="";
				    if(flag){
				    	String value=map.get("system.uname");
				    	if(value.startsWith("Linux")){
				    		type="Linux";
				    		if(map.containsKey("systemcpu")){
				    			String cpuUsed=map.get("systemcpu");
				    			hostl.setCpu(cpuUsed+"%");//linux cpu使用率
				    		}
				    		
				    		if(map.containsKey("vm.memory.size[total]") && map.containsKey("vm.memory.size[used]")){
				    			String total=map.get("vm.memory.size[total]");
				    			Double douTotal=Double.valueOf(total);
				    			String usedSize=map.get("vm.memory.size[used]");
				    			Double douUsedSize=Double.valueOf(usedSize);
				    			Double used=douUsedSize/douTotal;
				    			hostl.setRam(num.format(used));//linux memory使用率
				    		}
				    	}else if(value.startsWith("Windows")){
				    		type="Windows";
				    		if(map.containsKey("perf_counter[\\Processor(_Total)\\% Processor Time]")){
				    			String cpuUsed=map.get("perf_counter[\\Processor(_Total)\\% Processor Time]");
				    			hostl.setCpu(cpuUsed+"%");//windows cpu使用率
				    		}
				    		if(map.containsKey("vm.memory.size[total]") && map.containsKey("vm.memory.size[free]")){
				    			String total=map.get("vm.memory.size[total]");
				    			Double douTotal=Double.valueOf(total);
				    			String freeSize=map.get("vm.memory.size[free]");
				    			Double douFreeSize=Double.valueOf(freeSize);
				    			Double used=1-(douFreeSize/douTotal);
				    			hostl.setRam(num.format(used));//windows memory使用率
				    		}
				    	}
				    	hostl.setType(type);
				    }else if(FormatData.getLikeByMap(map, "ifInOctets")){
				    	Set<String> set=map.keySet();
				    	Iterator<String> iterator=set.iterator();
				    	Double used=0.0;
			    		Double free=0.0;
			    		Double memotyUsed =0.0;
				    	while(iterator.hasNext()){
				    		String key=iterator.next();
				    		hostl.setType("network");
				    		if(key.equals("cpuUtilization5m")){
				    			hostl.setCpu(map.get(key)+"%");
				    		}
				    		if(key.equals("MemoryPoolUsed")){
				    			used=Double.valueOf(map.get(key));
				    		}
				    		if(key.equals("MemoryPoolFree")){
				    			free=Double.valueOf(map.get(key));
				    		}
				    	}
				    	if((used+free)!=0)memotyUsed=used/(used+free);
				    	hostl.setRam(num.format(memotyUsed));
				    }
					}
				HostL ho=hostrepository.findOne(hostl.getId());
				if(ho!=null){
					ho.setCpu(hostl.getCpu());
					ho.setRam(hostl.getRam());
					ho.setIp(hostl.getIp());
					ho.setParentTemplates(hostl.getParentTemplates());
					System.out.println("++++++++++"+ho.getType());
					if(ho.getType()==null){
						ho.setType(hostl.getType());
					}
					ho.setSTATUS(hostl.getSTATUS());
					ho.setAvailable(hostl.getAvailable());
					hostrepository.save(ho);
				}else{
					hostrepository.save(hostl);
				}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			
			
			}
		}


}

