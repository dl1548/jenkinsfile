package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters; 
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;  
import com.alibaba.fastjson.support.config.FastJsonConfig;  
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter; 
import com.jk.repository.HostRepository;
import com.jk.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.jk"},basePackageClasses = {UserRepository.class,HostRepository.class})  
@ComponentScan
@ServletComponentScan
public class moniApplication  {
		
	public static void main(String[] args) {
		SpringApplication.run(moniApplication.class,args);
	}
	
	//fastjson自动解析
	@Bean  
	public HttpMessageConverters fastJsonHttpMessageConverters() {  
	FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();  
	FastJsonConfig fastJsonConfig = new FastJsonConfig();  
	fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);  
	fastConverter.setFastJsonConfig(fastJsonConfig);  
	HttpMessageConverter<?> converter = fastConverter;  
	return new HttpMessageConverters(converter);  
	} 
	
	
}