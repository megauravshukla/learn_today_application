package com.dxc.luxoft.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public ApiInfo apiDetails() {
		return new ApiInfo("Learn Today", 
				"Learn Today Application, An Online Learning Platform",
				"1.0",
				"Free To Use",
				new springfox.documentation.service
				.Contact("Gaurav Kumar Shukla",
						"abc.com", 
						"kumargaurav03dec@gmail.com"),
						"API Licence ", 
						"abc.com", 
						Collections.emptyList());
	}

	@Bean
	public Docket appApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiDetails());
	}

}
