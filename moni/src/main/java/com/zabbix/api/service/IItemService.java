package com.zabbix.api.service;
import java.util.List;

import com.zabbix.api.domain.base.Item;
import com.zabbix.api.domain.item.ItemCreateRequest;
import com.zabbix.api.domain.item.ItemDeleteRequest;
import com.zabbix.api.domain.item.ItemExistsRequest;
import com.zabbix.api.domain.item.ItemGetRequest;
import com.zabbix.api.domain.item.ItemGetobjectsRequest;
import com.zabbix.api.domain.item.ItemIsreadableRequest;
import com.zabbix.api.domain.item.ItemIswritableRequest;
import com.zabbix.api.domain.item.ItemUpdateRequest;
/**
 * @ClassName: IItemService
 * @Description:
 * @author
 * @date
 */
public interface IItemService {
	public Object create(ItemCreateRequest create,String auth);
	public Object delete(ItemDeleteRequest delete,String auth);
	public Object exists(ItemExistsRequest exists,String auth);
	public Object getobjects(ItemGetobjectsRequest getobjects,String auth);
	/**
	 * @Title: get
	 * @Description: 获取监控项信息（json）
	 * @param get
	 * @return Object
	 * @throws
	 */
	public Object get(ItemGetRequest get);
	
	/**
	 * @Title: getItemToBean
	 * @Description: 获取监控项信息（bean）
	 * @param get
	 * @return List<Item>
	 * @throws
	 */
	public List<Item> getItemToBean(ItemGetRequest get);
	public Object isreadable(ItemIsreadableRequest isreadable,String auth);
	public Object iswritable(ItemIswritableRequest iswritable,String auth);
	public Object update(ItemUpdateRequest update,String auth);
	List<Item> getItem(ItemGetRequest get,String auth);
}
