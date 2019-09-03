package com.cjk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;


@SpringBootApplication
@SpringBootConfiguration
@EnableWebMvc
public class WeChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeChatApplication.class, args);
	}
	
//	@Value("${server.port}")
//	private String port;
//
//	@RequestMapping("/hello")
//	@ResponseBody
//	public String satHello(){
//		return "turn to spring boot is success, it's from " + port;
//
//	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return stringHttpMessageConverter;
	}
}
