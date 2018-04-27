package com.zabbix.api.test;

import junit.framework.TestCase;

import com.zabbix.api.domain.base.MediaType;
import com.zabbix.api.domain.mediatype.MediaTypeCreateRequest;
import com.zabbix.api.domain.mediatype.MediaTypeDeleteRequest;
import com.zabbix.api.domain.mediatype.MediaTypeGetRequest;
import com.zabbix.api.domain.mediatype.MediaTypeUpdateRequest;
import com.zabbix.api.service.IMediatypeService;
import com.zabbix.api.service.impl.MediatypeServiceImpl;

public class TestMediatype extends TestCase {
	private static IMediatypeService mediatypeService = new MediatypeServiceImpl();
	public void testMediaTypeDelete(String auth) {
		// 数据准备
		MediaTypeDeleteRequest mediaTypeDelete = new MediaTypeDeleteRequest(auth);
		mediaTypeDelete.getParams().add("5");
		mediatypeService.mediaTypeDelete(mediaTypeDelete, auth);

	}

	public void testMediaTypeGet(String auth) {
		// 数据准备
		MediaTypeGetRequest mediaTypeGet = new MediaTypeGetRequest(auth);
		
		mediaTypeGet.getParams().setOutput("shoren");
		mediaTypeGet.getParams().getFilter().setDescription("邮件提醒");
//		mediaTypeGet.getParams().setDescription("邮件提醒");;
		
		Object object = mediatypeService.mediaTypeGet(mediaTypeGet, auth);
		System.out.println(object);
		
	}
}