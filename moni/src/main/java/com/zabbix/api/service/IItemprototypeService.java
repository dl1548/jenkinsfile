package com.zabbix.api.service;
import java.util.List;

import com.zabbix.api.domain.base.ItemPrototype;
import com.zabbix.api.domain.itemprototype.ItemPrototypeCreateRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeDeleteRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeExistsRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeGetRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeIsreadableRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeIswritableRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeUpdateRequest;
public interface IItemprototypeService {
	public Object itemPrototypeCreate(ItemPrototypeCreateRequest itemPrototypeCreate,String auth);
	public Object itemPrototypeDelete(ItemPrototypeDeleteRequest itemPrototypeDelete,String auth);
	public Object itemPrototypeExists(ItemPrototypeExistsRequest itemPrototypeExists,String auth);
	public Object itemPrototypeGet(ItemPrototypeGetRequest itemPrototypeGet,String auth);
	public Object itemPrototypeIsreadable(ItemPrototypeIsreadableRequest itemPrototypeIsreadable,String auth);
	public Object itemPrototypeIswritable(ItemPrototypeIswritableRequest itemPrototypeIswritable,String auth);
	public Object itemPrototypeUpdate(ItemPrototypeUpdateRequest itemPrototypeUpdate,String auth);
	public List<ItemPrototype> itemPrototypeGetToBean(ItemPrototypeGetRequest itemPrototypeGet,String auth);
}
