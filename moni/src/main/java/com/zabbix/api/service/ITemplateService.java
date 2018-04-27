package com.zabbix.api.service;
import java.util.List;

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
public interface ITemplateService {
	public Object create(TemplateCreateRequest create,String auth);
	public Object delete(TemplateDeleteRequest delete,String auth);
	public Object exists(TemplateExistsRequest exists,String auth);
	public Object getobjects(TemplateGetobjectsRequest getobjects,String auth);
	public Object get(TemplateGetRequest get,String auth);
	public Object isreadable(TemplateIsreadableRequest isreadable,String auth);
	public Object iswritable(TemplateIswritableRequest iswritable,String auth);
	public Object massadd(TemplateMassaddRequest massadd,String auth);
	public Object massremove(TemplateMassremoveRequest massremove,String auth);
	public Object massupdate(TemplateMassupdateRequest massupdate,String auth);
	public Object update(TemplateUpdateRequest update,String auth);
	List<Template> getTemplateToBean(TemplateGetRequest get,String auth);
}
