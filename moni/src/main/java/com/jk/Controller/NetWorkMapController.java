package com.jk.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zabbix.api.test.Util;
import com.zabbix4j.ZabbixApiException;
import com.zabbix4j.image.Image;
import com.zabbix4j.image.ImageGetRequest;
import com.zabbix4j.map.Map;
import com.zabbix4j.map.MapGetRequest;

@RestController
public class NetWorkMapController {

	@RequestMapping(value = "/networkmap", method = RequestMethod.POST)
	public void getnetworkmap(@RequestParam("auth") String auth) throws ZabbixApiException{
		getImage(auth);
		getMap(auth);
	}
	public static void getImage(String auth) throws ZabbixApiException{
		ImageGetRequest request = new ImageGetRequest();
		request.getParams().setSelect_image(true);
		Image image=new Image(Util.url,auth);
		image.get(request);
	}
	public static void getMap(String auth) throws ZabbixApiException{
		MapGetRequest  mapgetrequest=new MapGetRequest();
		mapgetrequest.getParams().setOutput("extend");
		mapgetrequest.getParams().setSelectSelements("extend");
		mapgetrequest.getParams().setSelectLinks("extend");
		Map map=new Map(Util.url,auth);
		map.get(mapgetrequest);
	} 
}
