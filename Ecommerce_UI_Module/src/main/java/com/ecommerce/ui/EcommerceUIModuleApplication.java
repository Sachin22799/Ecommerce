package com.ecommerce.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EcommerceUIModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceUIModuleApplication.class, args);
		System.out.println("appllication UI module is started  ================================================================");
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
		
	}
}
 