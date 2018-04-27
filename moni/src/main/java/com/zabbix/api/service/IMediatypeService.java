package com.zabbix.api.service;
import com.zabbix.api.domain.mediatype.MediaTypeCreateRequest;
import com.zabbix.api.domain.mediatype.MediaTypeDeleteRequest;
import com.zabbix.api.domain.mediatype.MediaTypeGetRequest;
import com.zabbix.api.domain.mediatype.MediaTypeUpdateRequest;
public interface IMediatypeService {
	public Object mediaTypeCreate(MediaTypeCreateRequest mediaTypeCreate,String auth);
	public Object mediaTypeDelete(MediaTypeDeleteRequest mediaTypeDelete,String auth);
	public Object mediaTypeGet(MediaTypeGetRequest mediaTypeGet,String auth);
	public Object mediaTypeUpdate(MediaTypeUpdateRequest mediaTypeUpdate,String auth);
}
