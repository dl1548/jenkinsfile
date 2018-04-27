package com.jk.Controller;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.repository.UserRepository;
import com.jk.service.HostThread;
import com.jk.service.LoginService;
import com.zabbix.util.FormatData;

@Controller
public class loginController {
	
	@Autowired
    UserRepository userRepository;
	@Autowired
	HostThread hostthread;
	
	
	
	@GetMapping(value="/")
	public String login(Model model){
        return "login.html";
    }
	
	@PostMapping(value="/indexpage")
	@ResponseBody
	public  String login(@RequestParam("loginUser") String loginUser,@RequestParam("loginPwd") String loginPwd,HttpServletRequest request) throws IOException, InterruptedException, ExecutionException, JSONException{
		JSONObject auth=LoginService.login(loginUser,loginPwd);
		if(auth.has("result")){
			JSONObject result=auth.getJSONObject("result");
			FormatData.auth=result.getString("sessionid");
			HttpSession session= request.getSession();
			session.setMaxInactiveInterval(-1);//设置session不超时
			session.setAttribute("auth",result.getString("sessionid"));
			Thread thread1 = new Thread(hostthread);
			thread1.start();//启动同步主机线程
		}else if(auth.has("error")){
		}
		return auth.toString();
	}
}
