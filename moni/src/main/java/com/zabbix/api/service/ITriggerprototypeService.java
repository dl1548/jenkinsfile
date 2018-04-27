package com.zabbix.api.service;
import java.util.List;

import com.zabbix.api.domain.base.TriggerPrototype;
import com.zabbix.api.domain.itemprototype.ItemPrototypeGetRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeCreateRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeDeleteRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeGetRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeUpdateRequest;
public interface ITriggerprototypeService {
	public Object triggerPrototypeCreate(TriggerPrototypeCreateRequest triggerPrototypeCreate,String auth);
	public Object addTriggerPrototype(TriggerPrototype triggerPrototype,String auth);
	public Object triggerPrototypeDelete(TriggerPrototypeDeleteRequest triggerPrototypeDelete,String auth);
	public Object triggerPrototypeGet(TriggerPrototypeGetRequest triggerPrototypeGet,String auth);
	public Object triggerPrototypeUpdate(TriggerPrototypeUpdateRequest triggerPrototypeUpdate,String auth);
	List<TriggerPrototype> triggerPrototypeGetToBean(TriggerPrototypeGetRequest triggerPrototypeGet,String auth);
}
