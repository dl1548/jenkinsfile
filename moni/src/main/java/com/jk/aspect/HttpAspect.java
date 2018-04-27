package com.jk.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component//引用到容器
public class HttpAspect {
	private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);
	//匹配controller包及其子包下的所有类的所有方法  
	@Pointcut("within(*com.jk.Controller..*)")  
	public void log(){  
	} 
	@Before("log()")//执行方法之前输出如下日志
	public void doBefore(JoinPoint joinpoint){
		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		 HttpServletRequest req=attributes.getRequest();
		//url
		logger.info("url={}",req.getRequestURL());
		//method
		logger.info("method={}",req.getMethod());
		//ip
		logger.info("ip={}",req.getRemoteAddr());
		//类方法
		logger.info("class_method={}",joinpoint.getSignature().getDeclaringTypeName()+"."+joinpoint.getSignature().getName());
		//参数
		logger.info("args={}",joinpoint.getArgs());
	}
	@After("log()")
	public void doAfter(){
		
	}
//	@AfterReturning(returning="object" ,pointcut="log()")
//	public void doAfterReturning(Object object){
//		logger.info("response={}",object);
//	}
}
