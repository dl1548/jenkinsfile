package com.zabbix.api.service;
import java.util.List;

import com.zabbix.api.domain.base.Trigger;
import com.zabbix.api.domain.trigger.TriggerAdddependenciesRequest;
import com.zabbix.api.domain.trigger.TriggerCreateRequest;
import com.zabbix.api.domain.trigger.TriggerDeleteRequest;
import com.zabbix.api.domain.trigger.TriggerDeletedependenciesRequest;
import com.zabbix.api.domain.trigger.TriggerExistsRequest;
import com.zabbix.api.domain.trigger.TriggerGetRequest;
import com.zabbix.api.domain.trigger.TriggerGetobjectsRequest;
import com.zabbix.api.domain.trigger.TriggerIsreadableRequest;
import com.zabbix.api.domain.trigger.TriggerIswritableRequest;
import com.zabbix.api.domain.trigger.TriggerUpdateRequest;
public interface ITriggerService {
	public Object adddependencies(TriggerAdddependenciesRequest adddependencies,String auth);
	public Object create(TriggerCreateRequest create,String auth);
	public Object addTrigger(Trigger trigger,String auth);
	public Object deletedependencies(TriggerDeletedependenciesRequest deletedependencies,String auth);
	public Object delete(TriggerDeleteRequest delete,String auth);
	public Object exists(TriggerExistsRequest exists,String auth);
	public Object getobjects(TriggerGetobjectsRequest getobjects,String auth);
	public List<Trigger> get(TriggerGetRequest get,String auth);
	public Object isreadable(TriggerIsreadableRequest isreadable,String auth);
	public Object iswritable(TriggerIswritableRequest iswritable,String auth);
	public Object update(TriggerUpdateRequest update,String auth);
	public Object getObj(TriggerGetRequest get,String auth);
	public List<Trigger> getTriggerBean(TriggerGetRequest get,String auth);
	
}
