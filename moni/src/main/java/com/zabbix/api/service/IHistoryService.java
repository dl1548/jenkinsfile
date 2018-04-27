package com.zabbix.api.service;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.zabbix.api.domain.base.IntegerHistory;
import com.zabbix.api.domain.history.HistoryGetRequest;
/**
 * @ClassName: IHistoryService
 * @Description:
 * @author 
 * @date
 */
public interface IHistoryService {
	/**
	 * @Title: get
	 * @Description: 获取历史信息（json）
	 * @param get
	 * @return Object
	 * @throws
	 */
	public Object get(HistoryGetRequest get,String auth);

	/**
	 * @throws UnsupportedEncodingException 
	 * @param type 数据类型 
	 * @Title: getHistoryToBean
	 * @Description: 获取历史信息（bean）
	 * @param get
	 * @return List<IntegerHistory>
	 * @throws
	 */
	public List<IntegerHistory> getHistoryToBean(HistoryGetRequest get,String auth) throws UnsupportedEncodingException;
}
