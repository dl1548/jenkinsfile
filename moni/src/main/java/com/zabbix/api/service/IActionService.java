package com.zabbix.api.service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.json.JSONException;

import com.zabbix.api.domain.action.ActionCreateRequest;
import com.zabbix.api.domain.action.ActionDeleteRequest;
import com.zabbix.api.domain.action.ActionExistsRequest;
import com.zabbix.api.domain.action.ActionGetRequest;
import com.zabbix.api.domain.action.ActionUpdateRequest;
import com.zabbix.api.domain.base.Action;
/**
 * @ClassName:
 * @Description:
 * @author 
 * @date
 * @version
 */
public interface IActionService {
	/**
	 * @Title: get
	 * @Description: 动作获取（json）
	 * @param get
	 * @return Object
	 * @throws
	 */
	public Object get(ActionGetRequest get,String auth);
	
	/**
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws HttpException 
	 * @throws UnsupportedEncodingException 
	 * @Title: getActionBean
	 * @Description: 动作的获取（bean）
	 * @param get
	 * @return List<Action>
	 * @throws
	 */
	public List<Action> getActionBean(ActionGetRequest get,String auth) throws UnsupportedEncodingException, HttpException, IOException, JSONException;
	
	public Object create(ActionCreateRequest create,String auth);
	public Object delete(ActionDeleteRequest delete,String auth);
	public Object exists(ActionExistsRequest exists,String auth);
	public Object update(ActionUpdateRequest update,String auth);

//	boolean addAction(Action action,String auth);
//	boolean updateAction(Action action,String auth);
}
