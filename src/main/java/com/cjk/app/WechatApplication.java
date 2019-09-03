package com.cjk.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
public class WechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}
	
	@Value("${server.port}")
	private String port;

	@RequestMapping("/hello")
	@ResponseBody
	public String satHello(){
		return "turn to spring boot is success, it's from " + port;
		
	}
}
