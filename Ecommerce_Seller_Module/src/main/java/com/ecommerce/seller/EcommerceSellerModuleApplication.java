package com.ecommerce.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EcommerceSellerModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceSellerModuleApplication.class, args);
		System.out.println("appllication seller module started  ================================================================");

	}
	
	@Bean
	public Docket productaip() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ecommerce.seller")).build() ;
		
	}

}
