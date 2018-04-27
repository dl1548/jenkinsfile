package com.jk.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jk.domain.HostL;
import com.jk.domain.HostMark;
import com.jk.domain.Mark;
import com.jk.repository.HostMarkRepository;
import com.jk.repository.HostRepository;
import com.jk.repository.MarkRepository;
import com.zabbix.api.domain.base.HostGroup;
import com.zabbix.api.domain.base.HostInterface;
import com.zabbix.api.domain.base.Macro;
import com.zabbix.api.domain.base.Template;
import com.zabbix.api.domain.history.HistoryGetRequest;
import com.zabbix.api.domain.host.HostCreateRequest;
import com.zabbix.api.domain.host.HostGetRequest;
import com.zabbix.api.domain.item.ItemGetRequest;
import com.zabbix.api.domain.trigger.TriggerGetRequest;
import com.zabbix.api.domain.trigger.TriggerGetRequest.TriggerGetParams.TriggerFilter;
import com.zabbix.api.service.IHistoryService;
import com.zabbix.api.service.IHostService;
import com.zabbix.api.service.IItemService;
import com.zabbix.api.service.ITriggerService;
import com.zabbix.api.service.impl.HistoryServiceImpl;
import com.zabbix.api.service.impl.HostServiceImpl;
import com.zabbix.api.service.impl.ItemServiceImpl;
import com.zabbix.api.service.impl.TriggerServiceImpl;
import com.zabbix4j.history.History;

@RestController
public class InfrastructureController {
	//private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);
	@Autowired
    HostMarkRepository hostMarkRepository;
	@Autowired
	MarkRepository markrepository;
	@Autowired
	HostRepository hostrepository;
	static IHostService hostService =new HostServiceImpl();
	static ITriggerService triggerService =new TriggerServiceImpl();
	static IHistoryService historyService=new HistoryServiceImpl();
	/**
	 * 创建主机监控
	 * 
	 * @param name可见名
	 * @param mark 标签 数组
	 * @param status 状态0 监控 1：未监控
	 * @param groupId 主机组 数组
	 * @param hostInterface //数组：包含下面6条
	 * @param type //监控方式的类型
	 * @param ip 地址
	 * @param dns DNS地址
	 * @param useIp // 0 -使用dns连接 1 -使用IP地址连接
	 * @param main //配置多个ip的先后顺序 // 0 - not default; 1 - default.
	 * @param port //端口号
	 * @param ipmi_authtype //ipmi算法0 - none; 1 - MD2; 2 - MD5 4 - straight; 5 - OEM; 6 - RMCP+
	 * @param ipmi_privilege//优先权层级 .1 - callback;2 - (default) user;3 - operator;4 - admin;5 - OEM.
	 * @param ipmi_username//用户名
	 * @param ipmi_password //密码
	 * @param templateId 数组模板id
	 * @param macro//宏 对象：包含Macro,Value
	 * @return haha
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * @throws JSONException 
	 */
	@PostMapping(value="/createHost")
	public String createHost(@RequestParam("hostname") String hostname,@RequestParam(value="mark",required=false) String[] mark,@RequestParam("groupId") String[] groupId,
    		@RequestParam("hostInterface") String interfaceList,@RequestParam(value="ipmi_authtype",required=false) Integer ipmi_authtype,
    		@RequestParam(value="ipmi_privilege",required=false) Integer ipmi_privilege,@RequestParam(value="ipmi_username",required=false) String ipmi_username,
    		@RequestParam(value="ipmi_password",required=false) String ipmi_password,@RequestParam(value="templateId",required=false) String[] templateId,@RequestParam(value="macro",required=false) String macro,@RequestParam("auth")String auth
    		,@RequestParam("select")Integer select)throws JSONException, JsonProcessingException, IOException{
		String leixing = null;	
		switch (select) {
			case 0:
				 leixing="Windows";
				break;
			case 1:
				leixing="Linux";
				break;
			case 2:
				 leixing="storage";
				break;
			case 3:
				leixing="network";
					break;
			case 4:
				leixing="vsphere";
					break;
			case 5:
				leixing="other";
					break;
			default:
				break;
			}
			HostCreateRequest create = new HostCreateRequest(auth);
			create.getParams().setHost(hostname);
//			host.setStatus(status);
			
			for (int i = 0; i < groupId.length; i++) {
				HostGroup group = new HostGroup();
				group.setGroupid(groupId[i]);
				create.getParams().getGroups().add(group);
			}
			interfaceList="["+interfaceList+"]";
			JSONArray array=new JSONArray(interfaceList);
			for(int s=0;s<array.length();s++){
				System.out.println(array.get(s));
				HostInterface hostInterface=new HostInterface();
				JSONObject JSONObject=array.getJSONObject(s);
				String port=JSONObject.getString("port");
				String ip=JSONObject.getString("ip");
				String dns=JSONObject.getString("dns");
				String useIp=JSONObject.getString("useIp");
				String main=JSONObject.getString("main");
				String type=JSONObject.getString("type");
				hostInterface.setType(Integer.valueOf(type));
				hostInterface.setIp(ip);
				hostInterface.setDns(dns);
				hostInterface.setUseip(Integer.valueOf(useIp));
				hostInterface.setMain(Integer.valueOf(main));
				hostInterface.setPort(port);
				create.getParams().getInterfaces().add(hostInterface);
				
			}
			create.getParams().setIpmi_authtype(ipmi_authtype);
			create.getParams().setIpmi_privilege(ipmi_privilege);
			create.getParams().setIpmi_username(ipmi_username);
			create.getParams().setIpmi_password(ipmi_password);
//			Macro mac=new Macro();
//			mac.setMacro(macro.getMacro());
//			mac.setValue(macro.getValue());
//			create.getParams().getMacros().add(mac);
			if(templateId!=null){
				for (int i = 0; i < templateId.length; i++) {
					Template temp=new Template();
					temp.setTemplateid(templateId[i].toString());
					create.getParams().getTemplates().add(temp);
				}
			}
			if(macro!=null&& macro!=""){
				List<Macro> macros = new ArrayList<>();
				macro="["+macro+"]";
				JSONArray macarray=new JSONArray(macro);
				for(int e=0;e<macarray.length();e++){
					Macro macr=new Macro();
					JSONObject ob=macarray.getJSONObject(e);
					if(ob.has("hostmacroid")){
						String hostmacroid=ob.getString("hostmacroid");
						macr.setHostmacroid(hostmacroid);
					}
					System.out.println(!ob.getString("Macro").equals(""));
					if(ob.getString("Macro")!=null && !ob.getString("Macro").equals("")){
						String key=ob.getString("Macro");
						String value=ob.getString("Value");
						System.out.println(value);
						System.out.println(key);
						if (key=="") {
							
						}else{
							macr.setMacro(key);
						}
						if (value=="") {
							
						}else{
							macr.setValue(value);
						}
						
						System.out.println("+++++++++++++++"+macr);
						if((macr.getMacro()=="")||(macr.getMacro()==null)){
							
						}else{
							macros.add(macr);
							create.getParams().setMacros(macros);
						}
						
						
					}
					
				}
				
			}
			
			String response = hostService.create(create, auth).toString();
//					hostService
//					.createHost(host, hostInterface, group, template);
			//System.out.println(response);//返回值
			JSONObject result=new JSONObject(response);
		
			if(result.has("result")){
				System.out.println(result.get("result"));
				JSONObject rs= (JSONObject)result.get("result");
				if(rs.has("hostids")){
					JSONArray hostids=rs.getJSONArray("hostids");
					HostL hl=new HostL();
					hl.setSTATUS(0);
					hl.setName(hostname);
					hl.setCreate_Date(String.valueOf(System.currentTimeMillis()/1000));
					hl.setId(Integer.valueOf(hostids.get(0).toString()));
					hl.setType(leixing);
					hostrepository.save(hl);
					if(mark!=null&&mark.length>0){
						for (int i = 0; i < mark.length; i++) {
							System.out.println(mark[i]);
							HostMark ma=new HostMark();
							ma.setMark(mark[i]);
							ma.setCreatedate(String.valueOf(System.currentTimeMillis()/1000));//加入创建时间
							ma.setHostid(Integer.valueOf(hostids.get(0).toString()));
							ma.setHostidmark(hostids.get(0).toString()+mark[i]);
							hostMarkRepository.save(ma);
						}
					}
					
					
					
				}
//				ArrayNode arr = (ArrayNode)node.get("result");
//				for (JsonNode an : arr) {
//					ArrayNode hostids = (ArrayNode)an.get("hostids");
//					for (JsonNode ids : hostids) {
//						HostMark ma=new HostMark();
//						String str="";
//						for (int i = 0; i < mark.length; i++) {
//							if(i>=mark.length){
//								str+=mark[i];
//								break;
//							}
//							str+=mark[i]+",";
//						}
//						ma.setMark(str);
//						ma.setHostid(Integer.valueOf(ids.get(0).toString()));
//						ma.setCreatedate(String.valueOf(System.currentTimeMillis()));//加入创建时间
//						hostMarkRepository.save(ma);
//					}
//				}
			}
			
			return response;
	}
	
	public Integer createMark(@RequestParam(value="name",required=false) String name){
		Mark ma=new Mark();
		ma.setName(name);
		markrepository.save(ma);
		return null;
	}
	/**
	 * 获取已选择标签
	 * @param hostid 主机id
	 * @return
	 */
	@PostMapping(value="/getMark")
	public List<String> getMark(@RequestParam("hostid") Integer hostid){
		JSONArray markarray=new JSONArray();
		JSONObject reobject=new JSONObject();
		List<String> list=new ArrayList<String>();
	List<HostMark> hostmarks=hostMarkRepository.findByHostid(hostid);
	if (hostmarks !=null) {
       List<String> marklist=new ArrayList<>();
		for(HostMark hm:hostmarks){
			String mark=hm.getMark();
			marklist.add(markrepository.findOne(Integer.valueOf(mark)).toString());
		}
			markarray.put(marklist);
			try {
				reobject.put("mark", markarray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			list.add(reobject.toString());
		}
		return list;
	}
	/**
	 * 获取当前主机的事件
	 * @param id
	 * @return
	 */
	@PostMapping(value="/getTriggerById")
	public String getTriggerById(@RequestParam("id") String id,@RequestParam(value="time",required=false) String time,@RequestParam("auth")String auth){
		Integer lastTime=Integer.valueOf(String.valueOf(System.currentTimeMillis()/1000))-Integer.valueOf(time);
		TriggerGetRequest req=new TriggerGetRequest(auth);
		List<String> ids=new ArrayList<String>();
		ids.add(id);
		req.getParams().setHostids(ids);
		req.getParams().setOnly_true(false);
		TriggerFilter filter=new TriggerFilter();
		filter.setValue("1");
		String [] hosts={"host"};
		req.getParams().setLastChangeSince(String.valueOf(lastTime));
		req.getParams().setSelectHosts(hosts);
//		req.getParams().setFilter(filter);
//		String o=triggerService.getObj(req, auth).toString();
//		System.out.println(o);
		return triggerService.getObj(req, auth).toString();
	}
	/**
	 * 获取主机CPU信息
	 * @param id
	 * @return
	 */
	@PostMapping(value="/getCpu")
	public List<History> getCpu(@RequestParam("id") Integer id,@RequestParam("auth")String auth){
		HostGetRequest hostGet=new HostGetRequest(auth);
		hostGet.getParams().setSelectItems("extend");
		return null;
	}
	/**
	 * 获取最新数据
	 * @param hostId
	 * @param auth
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value="/getNewItem")
	public String getNewItem(@RequestParam("hostId") String hostId,@RequestParam("auth")String auth) throws Exception{
		JSONObject json=new JSONObject();
		
		JSONArray itemArr=new JSONArray();
		ItemGetRequest get=new ItemGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setHostids(new String[]{hostId});
		IItemService itemService =new ItemServiceImpl();
		JSONObject jsonObj=(JSONObject)itemService.get(get);
		if(jsonObj.has("result")){
			JSONArray jsonArr=jsonObj.getJSONArray("result");
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject itemObj=new JSONObject();
				JSONObject item=jsonArr.getJSONObject(i);
				itemObj.put("name", item.getString("name"));
				itemObj.put("itemid", item.getString("itemid"));
				itemObj.put("lastclock", item.getLong("lastclock"));
				itemObj.put("value_type", item.getString("value_type"));
				if(item.get("units").equals("bps")){
					itemObj.put("lastvalue", String.valueOf(item.getLong("lastvalue")/1000L));
					itemObj.put("units","kbps");
				}else if(item.get("units").equals("B")){
					itemObj.put("lastvalue", String.valueOf(item.getLong("lastvalue")/1073741824L));
					itemObj.put("units","GB");
				}else{
					itemObj.put("lastvalue", item.getString("lastvalue"));
					itemObj.put("units",item.getString("units"));
				}
				itemArr.put(itemObj);
			}
		}
		json.put("result", itemArr);
		return json.toString();
	}
	/**
	 * 获取历史数据
	 * @param itemId
	 * @param hostId
	 * @param units
	 * @param value_type
	 * @param auth
	 * @return
	 */
	@PostMapping(value="/getHistoryOfItem")
	public String getHistoryOfItem(@RequestParam("itemId")String itemId,
			@RequestParam("hostId")String hostId,
			@RequestParam("units")String units,
			@RequestParam("value_type")String value_type,
			@RequestParam("auth")String auth){
		HistoryGetRequest get=new HistoryGetRequest(auth);
		List<String> itemids=new ArrayList<>();
		itemids.add(itemId);
		List<String> hostids=new ArrayList<>();
		hostids.add(hostId);
		JSONArray lastArr=new JSONArray();
		JSONObject lastJson=new JSONObject();
		get.getParams().setOutput("extend");
		get.getParams().setHostids(hostids);
		get.getParams().setItemids(itemids);
		get.getParams().setLimit(100);
		get.getParams().setHistory(Integer.valueOf(value_type));
		JSONObject json=(JSONObject)historyService.get(get, auth);
		if(json.has("result")){
			try {
				JSONArray jsonArr=json.getJSONArray("result");
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject hObjL=new JSONObject();
					JSONObject hObj=(JSONObject) jsonArr.get(i);
					if(units.equals("bps")){
						hObjL.put("clock", hObj.getLong("clock"));
						hObjL.put("value", String.valueOf(hObj.getLong("value")/1000L));
						hObjL.put("units","Kps");
					}else if(units.equals("B")){
						hObjL.put("clock", hObj.getLong("clock"));
						hObjL.put("value", String.valueOf(hObj.getLong("value")/1073741824L));
						hObjL.put("units","GB");
					}else{
						hObjL.put("clock", hObj.getLong("clock"));
						hObjL.put("value", hObj.getString("value"));
						hObjL.put("units",units);
					}
					lastArr.put(hObjL);
				}
				lastJson.put("result", lastArr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lastJson.toString();
	}
	/**
	 * 根据主机获取触发器信息
	 * @param auth
	 * @param hostId
	 * @return
	 */
	@PostMapping(value="/getTriggerByHost")
	public String getTriggerByHost(@RequestParam("auth")String auth,
			@RequestParam("hostId")String hostId){
		TriggerGetRequest get=new TriggerGetRequest(auth);
		String [] output={"description","status","priority","lastchange","expression","type","comments","value"};
		get.getParams().setOutput(output);
		List<String> hostids=new ArrayList<>();
		hostids.add(hostId);
		get.getParams().setHostids(hostids);
		get.getParams().setExpandExpression(true);
		get.getParams().setExpandComment(true);
		get.getParams().setExpandDescription(true);
		get.getParams().setSelectItems("extend");
		return triggerService.getObj(get, auth).toString();
	}
}
