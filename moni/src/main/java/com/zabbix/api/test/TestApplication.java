package com.zabbix.api.test;

import java.util.List;

import com.zabbix.api.domain.application.ApplicationGetRequest;
import com.zabbix.api.domain.base.Application;
import com.zabbix.api.domain.base.Item;
import com.zabbix.api.service.IApplicationService;
import com.zabbix.api.service.impl.ApplicationServiceImpl;

public class TestApplication {
    private static IApplicationService applicationService = new ApplicationServiceImpl();
    static {
        // 登录
        new Util().login();
    }
public static void main(String[] args) {
	TestApplication ta=new TestApplication();
}
    public static Object testGet(String auth) {
        ApplicationGetRequest get = new ApplicationGetRequest(auth);
        get.getParams().setOutput("extend");
//        get.getParams().getHostids().add("10154");
//        get.getParams().getFilter().getName().add("CPU");
        get.getParams().setSelectItems("extend");
        return applicationService.get(get, auth);
    }
    public static List<Application> testGettoBean(String auth) {
        ApplicationGetRequest get = new ApplicationGetRequest(auth);
        get.getParams().setOutput("extend");
        //get.getParams().getHostids().add("10174");
        //get.getParams().getFilter().getName().add("CPU");
        get.getParams().setSelectItems("extend");
        return applicationService.getApplicationToBean(get, auth);
    }
}
