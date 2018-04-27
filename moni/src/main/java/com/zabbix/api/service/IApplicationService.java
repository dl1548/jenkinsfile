package com.zabbix.api.service;

import java.util.List;

import com.zabbix.api.domain.application.ApplicationDeleteRequest;
import com.zabbix.api.domain.application.ApplicationGetRequest;
import com.zabbix.api.domain.base.Application;

public interface IApplicationService {

    public Object get(ApplicationGetRequest get,String auth);

    List<Application> getApplicationToBean(ApplicationGetRequest get,String auth);
    
    public Object delete(ApplicationDeleteRequest delete,String auth);

	List<Application> getApplication(ApplicationGetRequest get,String auth);
}
