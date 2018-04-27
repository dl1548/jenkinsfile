package com.jk.Controller;

import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;
import com.zabbix.api.domain.trigger.TriggerGetRequest;

@RestController
public class EventController {
	
	public String getEvent(String auth){
		TriggerGetRequest tget=new TriggerGetRequest(auth);
		String [] output={"description","status","priority","lastchange","expression"};
		String [] hosts={"host"};
		String [] groups={"name"};
		tget.getParams().setOutput(output);
		tget.getParams().setExpandExpression(true);
		tget.getParams().getFilter().setValue("1");
		tget.getParams().setSelectHosts(hosts);
		tget.getParams().setSelectGroups(groups);
		return null;
	}
	@Test
	public void get(String auth) {
		TriggerGetRequest tget=new TriggerGetRequest(auth);
		String [] output={"description","status","priority","lastchange","expression"};
		String [] hosts={"name"};
		String [] groups={"name"};
		tget.getParams().setOutput(output);
		tget.getParams().setExpandExpression(true);
		tget.getParams().getFilter().setValue("1");
		tget.getParams().setSelectHosts(hosts);
		tget.getParams().setSelectGroups(groups);
	}
}
