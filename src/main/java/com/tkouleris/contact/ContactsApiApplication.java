package com.tkouleris.contact;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ContactsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsApiApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/contacts/*"))
				.build()
				.apiInfo(apiDetails());
		
	}
	
	private ApiInfo apiDetails()
	{
		return new ApiInfo("Contacts API"
			, "The sole purpose of this api is to start learning Spring Boot by developing an API that holds and "
			+ "retrives contacts."
			, "1.0"
			, "Free to use"
			, new springfox.documentation.service.Contact("Thodoris Kouleris", "http://tkouleris.eu/", "tkouleris@gmail.com")
			, "http://tkouleris.eu/"
			,null, Collections.emptyList());
	}

}
