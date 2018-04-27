package com.zabbix.api.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.zabbix.api.domain.base.Host;
import com.zabbix.api.domain.base.HostGroup;
import com.zabbix.api.domain.base.Template;
import com.zabbix.api.domain.template.TemplateCreateRequest;
import com.zabbix.api.domain.template.TemplateDeleteRequest;
import com.zabbix.api.domain.template.TemplateExistsRequest;
import com.zabbix.api.domain.template.TemplateGetRequest;
import com.zabbix.api.domain.template.TemplateGetobjectsRequest;
import com.zabbix.api.domain.template.TemplateIsreadableRequest;
import com.zabbix.api.domain.template.TemplateIswritableRequest;
import com.zabbix.api.domain.template.TemplateMassaddRequest;
import com.zabbix.api.domain.template.TemplateMassremoveRequest;
import com.zabbix.api.domain.template.TemplateMassupdateRequest;
import com.zabbix.api.domain.template.TemplateUpdateRequest;
import com.zabbix.api.service.ITemplateService;
import com.zabbix.api.service.impl.TemplateServiceImpl;


public class TestTemplate extends TestCase {
	private static ITemplateService templateService = new TemplateServiceImpl();
	static {
		// 登录
		new Util().login();
	}

	public void testCreate(String auth) {
		// 数据准备
		TemplateCreateRequest create = new TemplateCreateRequest(auth);
		
		create.getParams().setHost("testTemplate_wei123");
		create.getParams().setName("testTemplate123");
		HostGroup hg = new HostGroup();
		hg.setGroupid("1");
		create.getParams().getGroups().add(hg);
		
		Host h = new Host();
		h.setHostid("158");
		create.getParams().getHosts().add(h);
		
		templateService.create(create, auth);

	}

	public void testDelete(String auth) {
		// 数据准备
		TemplateDeleteRequest delete = new TemplateDeleteRequest(auth);
		delete.getParams().add("10203");
		templateService.delete(delete, auth);

	}
public static void main(String[] args) {
	TestTemplate t=new TestTemplate();
	System.out.println("+++++++++++++++++");
	
}
	public void testExists(String auth) {
		// 数据准备
		TemplateExistsRequest exists = new TemplateExistsRequest(auth);
		exists.getParams().getTemplateid().add("1");
		templateService.exists(exists, auth);

	}

	public void testGetobjects(String auth) {
		// 数据准备
		TemplateGetobjectsRequest getobjects = new TemplateGetobjectsRequest(auth);
		
		getobjects.getParams().setHost("59");
		
		templateService.getobjects(getobjects, auth);

	}

	public Object testGet(String auth) {
		// 数据准备
		TemplateGetRequest get = new TemplateGetRequest(auth);
		get.getParams().setOutput("extend");
		get.getParams().setSelectHosts("count");
		get.getParams().setSelectApplications("count");
		get.getParams().setSelectItems("count");
		get.getParams().setSelectTriggers("count");
		return templateService.get(get, auth);

	}
	
	public List<Template> testGetBean(String auth) {
		// 数据准备
		TemplateGetRequest get = new TemplateGetRequest(auth);
		get.getParams().setOutput("extend");
//		List<String> hosts = new ArrayList<String>();
//		hosts.add("Template OS Windows");
//		get.getParams().getFilter().setHost(hosts);
		List<Template> templates = templateService.getTemplateToBean(get, auth);
		return templates;

	}

	public void testIsreadable(String auth) {
		// 数据准备
		TemplateIsreadableRequest isreadable = new TemplateIsreadableRequest(auth);
		isreadable.getParams().add("163");
		templateService.isreadable(isreadable, auth);

	}

	public void testIswritable(String auth) {
		// 数据准备
		TemplateIswritableRequest iswritable = new TemplateIswritableRequest(auth);
		iswritable.getParams().add("163");
		templateService.iswritable(iswritable, auth);

	}

	public void testMassadd(String auth) {
		// 数据准备
		TemplateMassaddRequest massadd = new TemplateMassaddRequest(auth);

		Template t = new Template();
		t.setTemplateid("163");
		massadd.getParams().getTemplates().add(t);
		
		t = new Template();
		t.setTemplateid("68");
		massadd.getParams().getTemplates().add(t);
		
		HostGroup hg = new HostGroup();
		hg.setGroupid("6");
		massadd.getParams().getGroups().add(hg);
//		massadd.getParams().getGroups().add(hg);
		
		templateService.massadd(massadd, auth);

	}

	public void testMassremove(String auth) {
		// 数据准备
		TemplateMassremoveRequest massremove = new TemplateMassremoveRequest(auth);

		massremove.getParams().getTemplateids().add("68");
		massremove.getParams().getTemplateids().add("163");
		
		massremove.getParams().setGroupids("1");
		templateService.massremove(massremove, auth);

	}

	public void testMassupdate(String auth) {
		// 数据准备
		TemplateMassupdateRequest massupdate = new TemplateMassupdateRequest(auth);
		
		Template tem = new Template();
		tem.setTemplateid("163");
		massupdate.getParams().getTemplates().add(tem);

		HostGroup hg = new HostGroup();
		hg.setGroupid("6");
		massupdate.getParams().getGroups().add(hg);
		
		Host h = new Host();
		h.setHostid("28");
		massupdate.getParams().getHosts().add(h);
		
		Template t = new Template();
		t.setTemplateid("44");
		massupdate.getParams().getTemplates_clear().add("44");
		
		templateService.massupdate(massupdate, auth);

	}

	public void testUpdate(String auth) {
		// 数据准备
		TemplateUpdateRequest update = new TemplateUpdateRequest(auth);
		
		update.getParams().setTemplateid("163");
		update.getParams().setHost("test_weiwei");
		update.getParams().setName("test_weiwei");
		HostGroup hg = new HostGroup();
		hg.setGroupid("1");
		update.getParams().getGroups().add(hg);
		
		Host h = new Host();
		h.setHostid("27");
		update.getParams().getHosts().add(h);
		
		Template t = new Template();
		t.setTemplateid("44");
		update.getParams().getTemplates_clear().add(t);
		
		templateService.update(update, auth);

	}
}